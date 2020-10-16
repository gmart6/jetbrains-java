package tictactoe;
import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        char[][] cells = new char[3][3];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // cells[i][j] = input.charAt(i * 3 + j);
                cells[i][j] = ' ';
            }
        }
        
        // Print table
        System.out.println("---------");
        for (int i = 0; i < 3; i ++) {
            System.out.println("| " + cells[i][0] + " " + cells[i][1] + " " + cells[i][2] + " |");
        }
        System.out.println("---------");
    
        boolean xToMove = true;
    
        // Make a move
        while (true) {
            System.out.print("Enter the coordinates: ");
            String moveInput = scanner.nextLine();
            String[] move = moveInput.split(" ");
            
            if (move.length != 2) {
                System.out.println("Please enter exactly two numbers.");
                continue;
            }
            
            int x0;
            int y0;
        
            if (move[0].matches("\\d+") && move[1].matches("\\d+")) {
                x0 = Integer.parseInt(move[1]);
                y0 = Integer.parseInt(move[0]);
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }
            
            if (x0 <= 3 && x0 >= 1 && y0 <= 3 && y0 >= 1) {
                x0 = 3 - x0;
                y0 = y0 - 1;
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            
            if (cells[x0][y0] == ' ') {
                if (xToMove) {
                    cells[x0][y0] = 'X';
                    xToMove = false;
                } else {
                    cells[x0][y0] = 'O';
                    xToMove = true;
                }    
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            
            // Print table
            System.out.println("---------");
            for (int i = 0; i < 3; i ++) {
                System.out.println("| " + cells[i][0] + " " + cells[i][1] + " " + cells[i][2] + " |");
            }
            System.out.println("---------");
            
            boolean impossible = false;
            boolean xWins = false;
            boolean oWins = false;
        
            int xCount = 0;
            int oCount = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cells[i][j] == 'X') xCount++;
                    else if (cells[i][j] == 'O') oCount++;
                }
            }
        
            char[] xS = {'X', 'X', 'X'};
            char[] oS = {'O', 'O', 'O'};
            int xHorzWinsCount = 0;
            int oHorzWinsCount = 0;
            
            // check horizontals arrays
            for (int i = 0; i < 3; i++) {
                if (Arrays.equals(cells[i], xS)) {
                    xWins = true;
                    xHorzWinsCount++;
                } else if (Arrays.equals(cells[i], oS)) {
                    oWins = true;
                    oHorzWinsCount++;
                }
            }
        
            // check verts
            char[][] vertCells = new char[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    vertCells[i][j] = cells[j][i];
                }
            }

            int xVertWinsCount = 0;
            int oVertWinsCount = 0;
            for (int i = 0; i < 3; i++) {
                if (Arrays.equals(vertCells[i], xS)) {
                    xWins = true;
                    xVertWinsCount++;
                } else if (Arrays.equals(vertCells[i], oS)) {
                    oWins = true;
                    oVertWinsCount++;
                }
            }
        
            // check diags
            char[] diag1 = new char[3];
            char[] diag2 = new char[3];
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) {
                        diag1[i] = cells[i][j];   
                    } 
                    if (i + j == 2) {
                        diag2[i] = cells[i][j];
                    }
                }
            }
        
            for (int i = 0; i < 3; i++) {
                if (Arrays.equals(diag1, xS) || Arrays.equals(diag2, xS)) {
                    xWins = true;
                } else if (Arrays.equals(diag1, oS) || Arrays.equals(diag2, oS)) {
                    oWins = true;
                }
            }
            
            // verify impossibles
            if (xCount - oCount > 1 || oCount - xCount > 1) {
                impossible = true;
            } else if (xVertWinsCount > 1 || xHorzWinsCount > 1 || oVertWinsCount > 1 || oHorzWinsCount > 1){
                impossible = true;
            } else if (xWins && oWins){
                impossible = true;
            }
            
            // print result
            if (impossible) {
                System.out.println("Impossible");
                break; 
            } else if (xWins) {
                System.out.println("X wins");
                break;
            } else if (oWins) {
                System.out.println("O wins");
                break;
            } else if (xCount + oCount == 9) {
                System.out.println("Draw");
                break;
            }
        }
        
    }
      
}
