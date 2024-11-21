/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Question {
    private static int correctAnswer;
    private static ArrayList<Integer> numbers;
    private static ArrayList<String> operators;
    private static int difficulty;
    
    private static String buildExpression(ArrayList<Integer> numbers, ArrayList<String> operators) {
        StringBuilder expression = new StringBuilder();
        expression.append(numbers.get(0));
        for (int i = 0; i < difficulty; i++) {
            expression.append(" ").append(operators.get(i)).append(" ").append(numbers.get(i + 1));
        }
//        3 4 5
//        * /
//        3 * 4 / 5 
        return expression.toString();
    }

    private static String randomOperation(Random rand, int a, int b) {
        String[] operations = { "+", "-", "*", "/" };
        String op = operations[rand.nextInt(operations.length)];
    
        if (op.equals("/") && (a % b != 0)) {
            return randomOperation(rand, a, b);
        }
        return op;
    }
    
    private static int evaluateExpression(String expression) {
            String[] tokens = expression.split(" ");
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
            return result;
    }
    public static String renQuestion(int difficulty) {
        setDifficulty(difficulty);
        
        String msg = "";
      
        Random rand = new Random();
        numbers = new ArrayList<>();
        operators = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int x = (rand.nextInt(19) + 1);
            numbers.add(x);
        }
        
        do {
            operators.clear();
            for (int i = 0; i < difficulty; i++) {
                int a = numbers.get(i);
                int b = numbers.get(i + 1);
                operators.add(randomOperation(rand, a, b));
            }
            correctAnswer = evaluateExpression(buildExpression(numbers, operators));
        } while (correctAnswer <= 0 || correctAnswer > 100);

        Collections.shuffle(numbers);
        
        for (int i : numbers) {
            System.out.println(i);
            msg += i + ";"; 
        }
        
//        for(String i : operators){
//            System.out.println(i);
//        }
        
        msg += correctAnswer + ";" + difficulty;
        
        System.out.println(msg);
        
        return msg;
    }
    
    
//    public static void main(String[] args) {
//        renQuestion();
//    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        Question.difficulty = difficulty;
    }
}
