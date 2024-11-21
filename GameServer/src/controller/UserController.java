/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DatabaseConnection;
import java.util.ArrayList;
import model.UserModel;
/**
 *
 * @author admin
 */
public class UserController {
    //  SQL
    private final String INSERT_USER = "INSERT INTO users (username, password, score, win, draw, lose) VALUES (?, ?, 1000, 0, 0, 0)";
    
    private final String CHECK_USER = "SELECT userId from users WHERE username = ? limit 1";
    
    private final String LOGIN_USER = "SELECT username, password, score FROM users WHERE username=? AND password=?";
    
    private final String GET_INFO_USER = "SELECT username, password, score, win, draw, lose FROM users WHERE username=?";
    
    private final String GET_SCORE_USER = "SELECT score FROM users WHERE username=?";
    
    private final String UPDATE_USER = "UPDATE users SET score = ?, win = ?, draw = ?, lose = ? WHERE username=?";
    //  Instance
    private final String GET_RANKING_USER = "SELECT username, win, draw, lose, score FROM users";
    
    private final Connection con;
    
    public UserController() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }
    public String register(String username, String password) {
    PreparedStatement p = null;
    ResultSet r = null;
    try {
        p = con.prepareStatement(CHECK_USER, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        p.setString(1, username);
        r = p.executeQuery();
        if (r.first()) {
            return "failed;" + "User Already Exists";
        } else {
            r.close();
            p.close();
            p = con.prepareStatement(INSERT_USER);
            p.setString(1, username);
            p.setString(2, password);
            p.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (r != null) try { r.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (p != null) try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
    return "success;";
}
public String login(String username, String password) {
    PreparedStatement p = null;
    ResultSet r = null;
    try {
        p = con.prepareStatement(LOGIN_USER, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        p.setString(1, username);
        p.setString(2, password);
        r = p.executeQuery();
        if (r.first()) {
            int score = r.getInt("score");
            return "success;" + username + ";" + score;
        } else {
            return "failed;" + "Please enter the correct account password!";
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (r != null) try { r.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (p != null) try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
    return null;
}

    
    public String getInfoUser(String username) {
        UserModel user = new UserModel();
        try {
            PreparedStatement p = con.prepareStatement(GET_INFO_USER);
            p.setString(1, username);
            
            ResultSet r = p.executeQuery();
            while(r.next()) {
                user.setUserName(r.getString("username"));
                user.setScore(r.getInt("score"));
                user.setWin(r.getInt("win"));
                user.setDraw(r.getInt("draw"));
                user.setLose(r.getInt("lose"));
            }
            return "success;" + user.getUserName() + ";" + user.getScore() + ";" + user.getWin() + ";" + user.getDraw() + ";" + user.getLose() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return null;
    }
    public String getScoreUser(String username){
        UserModel user = new UserModel();
        try {
            PreparedStatement p = con.prepareStatement(GET_SCORE_USER);
            p.setString(1, username);
            
            ResultSet r = p.executeQuery();
            while(r.next()) {
                user.setScore(r.getInt("score"));
            }
            return "success;" + user.getScore();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return null;
    }
    
    public boolean updateUser(UserModel user) throws SQLException {
        boolean rowUpdated;
        PreparedStatement p = con.prepareStatement(UPDATE_USER);
        //  Login User 
        p.setInt(1, user.getScore());
        p.setInt(2, user.getWin());
        p.setInt(3, user.getDraw());
        p.setInt(4, user.getLose());
        p.setString(5, user.getUserName());
        
//            ResultSet r = p.executeQuery();
        rowUpdated = p.executeUpdate() > 0;
        return rowUpdated;
    }

    public UserModel getUser(String username) {
        UserModel user = new UserModel();
        try {
            PreparedStatement p = con.prepareStatement(GET_INFO_USER);
            p.setString(1, username);
            
            ResultSet r = p.executeQuery();
            while(r.next()) {
                user.setUserName(r.getString("username"));
                user.setScore(r.getInt("score"));
                user.setWin(r.getInt("win"));
                user.setDraw(r.getInt("draw"));
                user.setLose(r.getInt("lose"));

            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }   
        return null;
    }
    public ArrayList<UserModel> getListRanking() {
        ArrayList<UserModel> rankingList = new ArrayList<>();

        try {
            PreparedStatement p = con.prepareStatement(GET_RANKING_USER);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                UserModel user = new UserModel();
                user.setUserName(r.getString("username"));
                user.setWin(r.getInt("win"));
                user.setDraw(r.getInt("draw"));
                user.setLose(r.getInt("lose"));
                user.setScore(r.getInt("score"));

                rankingList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rankingList;
}

}
