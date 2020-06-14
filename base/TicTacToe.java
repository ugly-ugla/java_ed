package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public void tableOutput(char[] arr){
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| " + arr[i * 3] + " " + arr[(i * 3) + 1] + " " + arr[(i * 3) + 2] + " |");
        }
        System.out.println("---------");
    }


    public static boolean ifWins(char[] arr, char ch) {
        if (
                ((arr[0] == ch && arr[0] == arr[1]) && (arr[0] == arr[2])) //first line, horizontal
                        || ((arr[3] == ch && arr[3] == arr[4]) && (arr[3] == arr[5])) //second line, horizontal
                        || ((arr[6] == ch && arr[6] == arr[7]) && (arr[6] == arr[8])) //third line, horizontal
                        || ((arr[0] == ch && arr[0] == arr[4]) && (arr[0] == arr[8])) //diagonal 0,0 / 8,8
                        || ((arr[2] == ch && arr[2] == arr[4]) && (arr[2] == arr[6])) //diagonal 2,2 / 6,6
                        || ((arr[0] == ch && arr[0] == arr[3]) && (arr[0] == arr[6])) //first column, vertical
                        || ((arr[1] == ch && arr[1] == arr[4]) && (arr[1] == arr[7])) //second column, vertical
                        || ((arr[2] == ch && arr[2] == arr[5]) && (arr[2] == arr[8]))) //third column, vertical
        {
            return true;
        }
        return false;
    }


    public static String gameState(char[] arr){
        int xNum = 0;
        int oNum = 0;
        int emptyCells = 0;
        boolean xWins = ifWins(arr, 'X');
        boolean oWins = ifWins(arr, 'O');
        String gameState = "";

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'X') {
                xNum++;
            } else if (arr[i] == 'O') {
                oNum++;
            } else if (arr[i] == ' ') {
                emptyCells++;
            }
        }

        if ((xNum - oNum > 1 || oNum - xNum > 1) || (xWins && oWins)) {
            gameState = "Impossible";
        }else if (emptyCells > 0 && (!xWins && !oWins)) {
            gameState = "Game not finished";
        } else if (!xWins && !oWins) {
            gameState = "Draw";
        } else if (xWins || oWins) {
            if (xWins) {
                gameState = "X wins";
            } else {
                gameState = "O wins";
            }
        }

        return gameState;
    }


    public static boolean isNumeric (String number) {

        boolean isANumber = true;

        if (number == null) isANumber = false;

        try {
            int n = Integer.parseInt(number);
        }
        catch (NumberFormatException e) {
            isANumber = false;
            System.out.println("You should enter numbers!");
        }
        return isANumber;
    }


    public static int[] userInput(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("Enter the coordinates: ");
            String firstNum = scanner.next();

            if (isNumeric(firstNum)){
                int x = Integer.parseInt(firstNum);
                String secondNum = scanner.next();
                if (isNumeric(secondNum)) {
                    int y = Integer.parseInt(secondNum);
                    if ((x > 0 && x < 4) && (y > 0 && y < 4)) {
                        return new int[]{x, y};
                    } else {
                        System.out.println("Coordinates should be from 1 to 3!");
                    }
                }
            }
        }
    }


    public void tableChange(char[] chars, int[] userCoordinates, char ch) {
        while (true) {
            if (userCoordinates[0] == 1) {
                if (userCoordinates[1] == 1) {
                    if (chars[6] == ' ') {
                        chars[6] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                } else if (userCoordinates[1] == 2) {
                    if (chars[3] == ' ') {
                        chars[3] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                } else if (userCoordinates[1] == 3) {
                    if (chars[0] == ' ') {
                        chars[0] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                }
            } else if (userCoordinates[0] == 2) {
                if (userCoordinates[1] == 1) {
                    if (chars[7] == ' ') {
                        chars[7] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                } else if (userCoordinates[1] == 2) {
                    if (chars[4] == ' ') {
                        chars[4] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                } else if (userCoordinates[1] == 3) {
                    if (chars[1] == ' ') {
                        chars[1] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                }
            } else if (userCoordinates[0] == 3) {
                if (userCoordinates[1] == 1) {
                    if (chars[8] == ' ') {
                        chars[8] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                } else if (userCoordinates[1] == 2) {
                    if (chars[5] == ' ') {
                        chars[5] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                } else if (userCoordinates[1] == 3) {
                    if (chars[2] == ' ') {
                        chars[2] = ch;
                        break;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                        userCoordinates = userInput();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
        char[] arr = new char[9];
        Arrays.fill(arr, ' ');
        String gameResult = "";
        main.tableOutput(arr);
        while (true) {
            main.tableChange(arr, userInput(), 'X');
            main.tableOutput(arr);
            gameResult = gameState(arr);

            if (gameResult.equals("Draw") || gameResult.equals("X wins") || gameResult.equals("O wins")) {
                break;
            }

            main.tableChange(arr, userInput(), 'O');
            main.tableOutput(arr);
            gameResult = gameState(arr);

            if (gameResult.equals("Draw") || gameResult.equals("X wins") || gameResult.equals("O wins")) {
                break;
            }
        }

        System.out.println(gameResult);
    }
}
