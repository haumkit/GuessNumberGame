package service;

import controller.UserController;
import helper.CountDownTimer;
import helper.CustumDateTimeFormatter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import model.UserModel;
import run.ServerRun;

public class Room {
    String id;
    String time = "00:00";
    Client client1 = null, client2 = null;
    ArrayList<Client> clients = new ArrayList<>();
    
    
    boolean gameStarted = false, isResultProcessed = false;
    CountDownTimer matchTimer;
    CountDownTimer waitingTimer;
    
    String resultClient1;
    String resultClient2;
    
    String playAgainC1;
    String playAgainC2;
    String waitingTime= "00:00";

    public LocalDateTime startedTime;

    public Room(String id) {
        // room id
        this.id = id;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void startGame() {
        gameStarted = true;
        isResultProcessed = false;
        matchTimer = new CountDownTimer(31);
        matchTimer.setTimerCallBack(
            null,
            (Callable) () -> {
                time = "" + CustumDateTimeFormatter.secondsToMinutes(matchTimer.getCurrentTick());
                System.out.println(time);
                if (time.equals("00:00")) {
                    waitingClientTimer();
                    if (resultClient1 == null && resultClient2 == null) {
                        draw();
                        broadcast("RESULT_GAME;success;DRAW;" + client1.getLoginUser() + ";" + client2.getLoginUser() + ";" + id);
                    } 
                }
                return null;
            },
            1
        );
    }
    
    public void waitingClientTimer() {
        waitingTimer = new CountDownTimer(12);
        waitingTimer.setTimerCallBack(
            null,
            (Callable) () -> {
                waitingTime = "" + CustumDateTimeFormatter.secondsToMinutes(waitingTimer.getCurrentTick());
                System.out.println("waiting: " + waitingTime);
                if (waitingTime.equals("00:00")) {
                    if (playAgainC1 == null && playAgainC2 == null) {
                        broadcast("ASK_PLAY_AGAIN;NO");
                        deleteRoom();
                    } 
                }
                return null;
            },
            1
        );
    }
    
    public void deleteRoom () {
        client1.setJoinedRoom(null);
        client1.setcCompetitor(null);
        client2.setJoinedRoom(null);
        client2.setcCompetitor(null);
        ServerRun.roomManager.remove(this);
    }
    
    public void resetRoom() {
        gameStarted = false;
        resultClient1 = null;
        resultClient2 = null;
        playAgainC1 = null;
        playAgainC2 = null;
        time = "00:00";
        waitingTime = "00:00";
    }
    
    public synchronized String handleResultClient() throws SQLException {
        
//        if (isResultProcessed) {
//            System.out.println("Result already processed.");
//            return null;  // Tránh xử lý lại nếu kết quả đã được xử lý
//        }
//        isResultProcessed = true;
        int timeClient1 = 0;
        int timeClient2 = 0;
        
        if (resultClient1 != null) {
            String[] splitted1 = resultClient1.split(";");
            timeClient1 = Integer.parseInt(splitted1[7]);
        }
        if (resultClient2 != null) {
            String[] splitted2 = resultClient2.split(";");
            timeClient2 = Integer.parseInt(splitted2[7]);
        }
        
        if (resultClient1 == null & resultClient2 == null) {
            draw();
            return "DRAW";
        } else if (resultClient1 != null && resultClient2 == null) {
            if (calculateResult(resultClient1) > 0) {
                client1Win(timeClient1);
                return client1.getLoginUser();
            } else {
                draw();
                return "DRAW";
            }
        } else if (resultClient1 == null && resultClient2 != null) {
            if (calculateResult(resultClient2) > 0) {
                client2Win(timeClient2);
                return client2.getLoginUser();
            } else {
                draw();
                return "DRAW";
            }
        } else if (resultClient1 != null && resultClient2 != null) {
            int pointClient1 = calculateResult(resultClient1);
            int pointClient2 = calculateResult(resultClient2);
            
            if (pointClient1 > pointClient2) {
                client1Win(timeClient1);
                return client1.getLoginUser();
            } else if (pointClient1 < pointClient2) {
                    client2Win(timeClient2);
                    return client2.getLoginUser();
                } else {
                    if((pointClient1 == 1) && (pointClient2 == 1))
                        if(timeClient1 < timeClient2){
                            client1Win(timeClient1);
                            return client1.getLoginUser();
                        }
                        else if(timeClient1 > timeClient2){
                            client2Win(timeClient2);
                            return client2.getLoginUser();
                            }
                            else{
                                draw();
                                return "DRAW";
                            }
                    else{
                        draw();
                        return "DRAW";      
                    }
                }
        }
        return null;
    }
    
    public int calculateResult (String received) {
        String[] splitted = received.split(";");
        
        String user1 = splitted[1];
        
        String difficulty = splitted[4];
        String correctAnswer = splitted[5];
        String clientResult = splitted[6];
        
        int res = 0;
        String[] tokens = clientResult.split(" ");
        int result = Integer.parseInt(tokens[0]);

        try {
            for (int i = 1; i < tokens.length; i += 2) {
                String operator = tokens[i];
                int operand = Integer.parseInt(tokens[i + 1]);
                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        result /= operand;
                        break;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid expression format");
        }
        System.out.println("Ket qua " + user1 + ": " + result);
        if(result == Integer.parseInt(correctAnswer))
             return 1;
                
        return 0;
    }

    public void draw () throws SQLException {
        UserModel user1 = new UserController().getUser(client1.getLoginUser());
        UserModel user2 = new UserController().getUser(client2.getLoginUser());
        
        user1.setDraw(user1.getDraw() + 1);
        user2.setDraw(user2.getDraw() + 1);
        
        
        int totalMatchUser1 = user1.getWin() + user1.getDraw() + user1.getLose();
        int totalMatchUser2 = user2.getWin() + user2.getDraw() + user2.getLose();
        
        //float newAvgCompetitor1 = (totalMatchUser1 * user1.getAvgCompetitor() + user2.getScore()) / (totalMatchUser1 + 1);
        //float newAvgCompetitor2 = (totalMatchUser2 * user1.getAvgCompetitor() + user1.getScore()) / (totalMatchUser2 + 1);
        
//        newAvgCompetitor1 = Math.round(newAvgCompetitor1 * 100) / 100;
//        newAvgCompetitor2 = Math.round(newAvgCompetitor2 * 100) / 100;
        
        //user1.setAvgCompetitor(newAvgCompetitor1);
        //user2.setAvgCompetitor(newAvgCompetitor2);
        
        new UserController().updateUser(user1);
        new UserController().updateUser(user2);
    }
    
    public void client1Win(int time) throws SQLException {
        UserModel user1 = new UserController().getUser(client1.getLoginUser());
        UserModel user2 = new UserController().getUser(client2.getLoginUser());
        
        System.out.println("Client1Win");
        System.out.println(user1.getUserName() + "diem: " + user1.getScore());
        System.out.println(user2.getUserName() + "diem: " + user2.getScore());
        
        user1.setWin(user1.getWin() + 1);
        user2.setLose(user2.getLose() + 1);
        
        user1.setScore(user1.getScore()+ 1);
        user2.setScore(user2.getScore()- 1);
        System.out.println(user1.getUserName() + " Chien thang");
        System.out.println(user1.getUserName() + "diem: " + user1.getScore());
        System.out.println(user2.getUserName() + "diem: " + user2.getScore());
        
        //int totalMatchUser1 = user1.getWin() + user1.getDraw() + user1.getLose();
        //int totalMatchUser2 = user2.getWin() + user2.getDraw() + user2.getLose();
        
//        float newAvgCompetitor1 = (totalMatchUser1 * user1.getAvgCompetitor() + user2.getScore()) / (totalMatchUser1 + 1);
//        float newAvgCompetitor2 = (totalMatchUser2 * user1.getAvgCompetitor() + user1.getScore()) / (totalMatchUser2 + 1);
//        
//        user1.setAvgCompetitor(newAvgCompetitor1);
//        user2.setAvgCompetitor(newAvgCompetitor2);
//        
//        float newAvgTime1 = (totalMatchUser1 * user1.getAvgTime() + time) / (totalMatchUser1 + 1);
//        System.out.println("newAvgTime1: " + newAvgTime1);
//        user1.setAvgTime(newAvgTime1);
        
        new UserController().updateUser(user1);
        new UserController().updateUser(user2);
    }
    
    public void client2Win(int time) throws SQLException {
        UserModel user1 = new UserController().getUser(client1.getLoginUser());
        UserModel user2 = new UserController().getUser(client2.getLoginUser());
        
        System.out.println("Client2Win");
        
        System.out.println(user1.getUserName() + "diem: " + user1.getScore());
        System.out.println(user2.getUserName() + "diem: " + user2.getScore());
        
        user2.setWin(user2.getWin() + 1);
        user1.setLose(user1.getLose() + 1);
        
        user2.setScore(user2.getScore()+ 1);
        user1.setScore(user1.getScore()- 1);
        
        System.out.println(user2.getUserName() + " Chien thang");
        System.out.println(user1.getUserName() + "diem: " + user2.getScore());
        System.out.println(user2.getUserName() + "diem: " + user1.getScore());
        
//        int totalMatchUser1 = user1.getWin() + user1.getDraw() + user1.getLose();
//        int totalMatchUser2 = user2.getWin() + user2.getDraw() + user2.getLose();
//        
//        float newAvgCompetitor1 = (totalMatchUser1 * user1.getAvgCompetitor() + user2.getScore()) / (totalMatchUser1 + 1);
//        float newAvgCompetitor2 = (totalMatchUser2 * user1.getAvgCompetitor() + user1.getScore()) / (totalMatchUser2 + 1);
//        
//        user1.setAvgCompetitor(newAvgCompetitor1);
//        user2.setAvgCompetitor(newAvgCompetitor2);
//        
//        float newAvgTime2 = (totalMatchUser2 * user2.getAvgTime() + time) / (totalMatchUser2 + 1);
//        System.out.println("newAvgTime2: " + newAvgTime2);
//        user2.setAvgTime(newAvgTime2);
        
        new UserController().updateUser(user1);
        new UserController().updateUser(user2);
    }
    
    public void userLeaveGame (String username) throws SQLException {
        if (client1.getLoginUser().equals(username)) {
            client2Win(0);
        } else if (client2.getLoginUser().equals(username)) {
            client1Win(0);
        }
    }
    
    public String handlePlayAgain () {
        if (playAgainC1 == null || playAgainC2 == null) {
            return "NO";
        } else if (playAgainC1.equals("YES") && playAgainC2.equals("YES")) {
            return "YES";
        } else if (playAgainC1.equals("NO") && playAgainC2.equals("YES")) {
//            ServerRun.clientManager.sendToAClient(client2.getLoginUser(), "ASK_PLAY_AGAIN;NO");
//            deleteRoom();
            return "NO";
        } else if (playAgainC2.equals("NO") && playAgainC2.equals("YES")) {
//            ServerRun.clientManager.sendToAClient(client1.getLoginUser(), "ASK_PLAY_AGAIN;NO");
//            deleteRoom();
            return "NO";
        } else {
            return "NO";
        }
    }
    
    // add/remove client
    public boolean addClient(Client c) {
        if (!clients.contains(c)) {
            clients.add(c);
            if (client1 == null) {
                client1 = c;
            } else if (client2 == null) {
                client2 = c;
            }
            return true;
        }
        return false;
    }

    public boolean removeClient(Client c) {
        if (clients.contains(c)) {
            clients.remove(c);
            return true;
        }
        return false;
    }

    // broadcast messages
    public void broadcast(String msg) {
        clients.forEach((c) -> {
            c.sendData(msg);
        });
    }
    
    public Client find(String username) {
        for (Client c : clients) {
            if (c.getLoginUser()!= null && c.getLoginUser().equals(username)) {
                return c;
            }
        }
        return null;
    }

    // gets sets
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient1() {
        return client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public Client getClient2() {
        return client2;
    }

    public void setClient2(Client client2) {
        this.client2 = client2;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
    
    public int getSizeClient() {
        return clients.size();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getResultClient1() {
        return resultClient1;
    }

    public void setResultClient1(String resultClient1) {
        this.resultClient1 = resultClient1;
    }

    public String getResultClient2() {
        return resultClient2;
    }

    public void setResultClient2(String resultClient2) {
        this.resultClient2 = resultClient2;
    }

    public String getPlayAgainC1() {
        return playAgainC1;
    }

    public void setPlayAgainC1(String playAgainC1) {
        this.playAgainC1 = playAgainC1;
    }

    public String getPlayAgainC2() {
        return playAgainC2;
    }

    public void setPlayAgainC2(String playAgainC2) {
        this.playAgainC2 = playAgainC2;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }

    
    
}
