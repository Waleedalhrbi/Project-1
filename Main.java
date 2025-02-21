import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //  Tic tac toe game
        // Waleed Alharbi

        // -----------------------------------------------

        Scanner scan = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1- Play one round");
        System.out.println("2- Play 3 rounds");
        int option = scan.nextInt();

        if (option == 1) {
            char[][] board = { {'1','2','3'},
                    {'4','5','6'},
                    {'7','8','9'}};

            printTheBoard(board);
            while (true) {
                playerTurn(scan, board);
                if (checkTheGameFinished(board)){ // To stop the while loop if the game finished
                    break;
                }
                printTheBoard(board);
                computerTurn(board);
                if (checkTheGameFinished(board)){ // To stop the while loop if the game finished
                    break;
                }
                printTheBoard(board);
            }
        } else if (option == 2) {
            int playerWins = 0;
            int computerWins = 0;

            for (int i = 0; i < 3; i++) {
                char[][] board = { {'1','2','3'},
                        {'4','5','6'},
                        {'7','8','9'}};

                printTheBoard(board);
                while (true) {
                    playerTurn(scan, board);
                    if (checkTheGameFinished(board)){
                        if (checkWinner(board) == 'X') {
                            playerWins++;
                        } else if (checkWinner(board) == 'O') {
                            computerWins++;
                        }
                        break;
                    }
                    printTheBoard(board);
                    computerTurn(board);
                    if (checkTheGameFinished(board)){
                        if (checkWinner(board) == 'X') {
                            playerWins++;
                        } else if (checkWinner(board) == 'O') {
                            computerWins++;
                        }
                        break;
                    }
                    printTheBoard(board);
                }

                System.out.println("Score: Player " + playerWins + " - Computer " + computerWins);

                if (playerWins == 2 || computerWins == 2) {
                    break; // No need to play the last round if someone has already won
                }
            }

            if (playerWins > computerWins) {
                System.out.println("Player wins the series!");
            } else if (computerWins > playerWins) {
                System.out.println("Computer wins the series!");
            } else {
                System.out.println("It's a tie! Need a tiebreaker round");
            }
        } else {
            System.out.println("Invalid choice. Please select 1 or 2");
        }


    }

    public static boolean checkTheGameFinished(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                printTheBoard(board);
                if (board[i][0] == 'X') {
                    System.out.println("Game Over! Winner is: Player");
                } else {
                    System.out.println("Game Over! Winner is: Computer");
                }
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                printTheBoard(board);
                if (board[0][i] == 'X') {
                    System.out.println("Game Over! Winner is: Player");
                } else {
                    System.out.println("Game Over! Winner is: Computer");
                }
                return true;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            printTheBoard(board);
            if (board[0][0] == 'X') {
                System.out.println("Game Over! Winner is: Player");
            } else {
                System.out.println("Game Over! Winner is: Computer");
            }
            return true;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            printTheBoard(board);
            if (board[0][2] == 'X') {
                System.out.println("Game Over! Winner is: Player");
            } else {
                System.out.println("Game Over! Winner is: Computer");
            }
            return true;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }

        printTheBoard(board);
        System.out.println("Game Over! It's a Tie!");
        return true;
    }

    private static char checkWinner(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }

        return '-';
    }

    private static void computerTurn(char[][] board) {
        Random rend = new Random();
        int computerChoice;
        while (true) {
            computerChoice = rend.nextInt(9) + 1; // To generate a random number between 1 and 9
            if (checkPosition(board, Integer.toString(computerChoice))) { // Ensure the slot is available
                break;
            }
        }

        System.out.println("Computer choice: " + computerChoice);
        placePosition(board, Integer.toString(computerChoice), 'O');
    }

    public static boolean checkPosition(char[][] board, String position) {
        switch (position) {
            case "1":
                return board[0][0] == '1';
            case "2":
                return board[0][1] == '2';
            case "3":
                return board[0][2] == '3';
            case "4":
                return board[1][0] == '4';
            case "5":
                return board[1][1] == '5';
            case "6":
                return board[1][2] == '6';
            case "7":
                return board[2][0] == '7';
            case "8":
                return board[2][1] == '8';
            case "9":
                return board[2][2] == '9';
            default:
                return false;
        }
    }

    public static void playerTurn(Scanner scan, char[][] board) {
        String userChoice;
        while (true) {
            System.out.println("What position do you want to play? (1-9)");
            userChoice = scan.next();
            if (checkPosition(board, userChoice)) { // Check if the position is available
                break;
            } else {
                System.out.println(userChoice + " is not a valid move!");
            }
        }
        placePosition(board, userChoice, 'X');
    }

    private static void placePosition(char[][] board, String position, char character) {
        switch (position) {
            case "1":
                board[0][0] = character;
                break;
            case "2":
                board[0][1] = character;
                break;
            case "3":
                board[0][2] = character;
                break;
            case "4":
                board[1][0] = character;
                break;
            case "5":
                board[1][1] = character;
                break;
            case "6":
                board[1][2] = character;
                break;
            case "7":
                board[2][0] = character;
                break;
            case "8":
                board[2][1] = character;
                break;
            case "9":
                board[2][2] = character;
                break;
            default:
                System.out.println("Enter a number between 1 and 9");
        }
    }

    public static void printTheBoard(char[][] board) {
        System.out.println("------------------------------------------------------------\n");
        System.out.println(board[0][0] + "|" + board[0][1] + '|' + board[0][2]);
        System.out.println("-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + '|' + board[1][2]);
        System.out.println("-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + '|' + board[2][2]);
        System.out.println("------------------------------------------------------------\n");
    }
}









// Calculator :

/*  import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Calculator
        // Waleed Alharbi

        // -----------------------------------------------
        Scanner input = new Scanner(System.in);
        String userOption;
        int operationOption, num1, num2;
        ArrayList<Double> results = new ArrayList<>(); // to store the result and use it later to display the last result

        System.out.print("Please enter the first number: ");
        num1 = input.nextInt();
        System.out.print("Please enter the second number to display the menu: ");
        num2 = input.nextInt();

        do {
            System.out.println("Menu of operations: ");
            System.out.println("Enter 1 to addition the numbers");
            System.out.println("Enter 2 to subtraction the numbers");
            System.out.println("Enter 3 to multiplication the numbers");
            System.out.println("Enter 4 to division the numbers");
            System.out.println("Enter 5 to modulus the numbers");
            System.out.println("Enter 6 to find minimum number");
            System.out.println("Enter 7 to find maximum number");
            System.out.println("Enter 8 to find the average of numbers");
            System.out.println("Enter 9 to print the last result in calculator");
            System.out.println("Enter 10 to print the list of all results in calculator");
            System.out.println("Enter 0 to exit the calculator");

            System.out.print("Please enter your option: ");
            operationOption = input.nextInt();

            switch (operationOption) {
                case 1:
                    double sum = sumOfTwoNum(num1, num2);
                    results.add(sum);
                    break;
                case 2:
                    double difference = subtractTwoNum(num1, num2);
                    results.add(difference);
                    break;
                case 3:
                    double product = multiplyTwoNum(num1, num2);
                    results.add(product);
                    break;
                case 4:
                    double division = divideTwoNum(num1, num2);
                    results.add(division);
                    break;
                case 5:
                    double modulus = modulusOfTwoNum(num1, num2);
                    results.add(modulus);
                    break;
                case 6:
                    double min = findMinimum(num1, num2);
                    results.add(min);
                    break;
                case 7:
                    double max = findMaximum(num1, num2);
                    results.add(max);
                    break;
                case 8:
                    double average = calculateAverage(num1, num2);
                    results.add(average);
                    break;
                case 9:
                    if (!results.isEmpty()) {
                        System.out.println("The last result is: " + results.get(results.size() - 1)); // The size method returns the number of elements in the array. The array starts with zero. To get the last index, we subtract 1 from the number of elements.
                    } else {
                        System.out.println("No results to display.");
                    }
                    break;
                case 10:
                    if (!results.isEmpty()) {
                        System.out.println("List of all results: " + results);
                    } else {
                        System.out.println("No results to display.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting the calculator. Thank you!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
            System.out.println("Do you want to continue? (Yes / No)");
            userOption = input.next();
        } while (userOption.equalsIgnoreCase("Yes"));

        System.out.println("Thank you for using our calculator !!");
    } // end of main method

    private static double sumOfTwoNum(int num1, int num2) {
        double result = num1 + num2;
        System.out.println("The sum of number " + num1 + " and number " + num2 + " = " + result);
        return result;
    }

    private static double subtractTwoNum(int num1, int num2) {
        double result = num1 - num2;
        System.out.println("The difference between number " + num1 + " and number " + num2 + " = " + result);
        return result;
    }

    private static double multiplyTwoNum(int num1, int num2) {
        double result = num1 * num2;
        System.out.println("The product of number " + num1 + " and number " + num2 + " = " + result);
        return result;
    }

    private static double divideTwoNum(int num1, int num2) {
        if (num2 != 0) {
            double result = (double) num1 / num2;
            System.out.println("The quotient of number " + num1 + " divided by number " + num2 + " = " + result);
            return result;
        } else {
            System.out.println("Error: Division by zero is not allowed.");
            return 0;
        }
    }

    private static double modulusOfTwoNum(int num1, int num2) {
        double result = num1 % num2;
        System.out.println("The modulus of number " + num1 + " and number " + num2 + " = " + result);
        return result;
    }

    private static double findMinimum(int num1, int num2) {
        double result = Math.min(num1, num2);
        System.out.println("The minimum of number " + num1 + " and number " + num2 + " = " + result);
        return result;
    }

    private static double findMaximum(int num1, int num2) {
        double result = Math.max(num1, num2);
        System.out.println("The maximum of number " + num1 + " and number " + num2 + " = " + result);
        return result;
    }

    private static double calculateAverage(int num1, int num2) {
        double result = (num1 + num2) / 2.0;
        System.out.println("The average of number " + num1 + " and number " + num2 + " = " + result);
        return result;
    }
} // end of main class
*/