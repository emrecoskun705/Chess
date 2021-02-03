package com.company;

import java.util.ArrayList;

public class ChessBoard {
    protected static int turn = 0;
    protected static int WHITE = 0;
    protected static int BLACK = 1;
    protected static Square[][] board = new Square[8][8];

    public ChessBoard() {

    }

// first the method fill the every location on the board with "A1","A2","A3"...
//then it puts the initial pieces to their locations
    public void initialize() {
        for(int i=0; i<8; i++) {
            char letter = 'A';
            for(int j=0; j<8; j++) {
                Square square = new Square("" + letter,"" + (8-i));
                ChessBoard.board[i][j] = square;
                letter++;
            }
        }

        for(int i=0; i<8; i++) {
            board[6][i].piece = new Pawn(0,board[6][i]);
        }
        for(int i=0; i<8; i++) {
            board[1][i].piece = new Pawn(1,board[1][i]);
        }
        board[7][0].piece = new Rook(0,board[7][0]);
        board[7][7].piece = new Rook(0,board[7][7]);
        board[0][0].piece = new Rook(1,board[0][0]);
        board[0][7].piece = new Rook(1,board[0][7]);

        board[7][2].piece = new Bishop(0,board[7][2]);
        board[7][5].piece = new Bishop(0,board[7][5]);
        board[0][2].piece = new Bishop(1,board[0][2]);
        board[0][5].piece = new Bishop(1,board[0][5]);

        board[7][3].piece = new Queen(0,board[7][3]);
        board[0][3].piece = new Queen(1,board[0][3]);

        board[7][1].piece = new Knight(0,board[7][1]);
        board[7][6].piece = new Knight(0,board[7][6]);
        board[0][1].piece = new Knight(1,board[0][1]);
        board[0][6].piece = new Knight(1,board[0][6]);

        board[7][4].piece = new King(0,board[7][4]);
        board[0][4].piece = new King(1,board[0][4]);

    }


    public boolean isWhitePlaying() {
        return turn == WHITE;
    }

    public ChessBoard getBoard() {
        return this;
    }

    public Square getSquareAt(String destination) {
        int[] arrayLocation = arrayLocation(destination);
        int arrayRow = arrayLocation[0];
        int arrayColumn = arrayLocation[1];
        System.out.println("Location: " + arrayRow + " " + arrayColumn);
        System.out.println(board[arrayRow][arrayColumn]);
        return board[arrayRow][arrayColumn];
    }


//this method returns the given square's location as a board[i][j]
//if we put destination "A2" it returns {1,0}
    public int[] arrayLocation(String destination) {
        String fakeColumn = destination.substring(0, 1);
        String fakeRow = destination.substring(1);
        int arrayColumn = 0;
        int arrayRow = 0;
        Integer reverseRow = Integer.parseInt(fakeRow);
        if (fakeColumn.equals("A")) {
            arrayColumn = 0;
            arrayRow = 8 - reverseRow;
        } else if (fakeColumn.equals("B")) {
            arrayColumn = 1;
            arrayRow = 8 - reverseRow;
        } else if (fakeColumn.equals("C")) {
            arrayColumn = 2;
            arrayRow = 8 - reverseRow;
        } else if (fakeColumn.equals("D")) {
            arrayColumn = 3;
            arrayRow = 8 - reverseRow;
        } else if (fakeColumn.equals("E")) {
            arrayColumn = 4;
            arrayRow = 8 - reverseRow;
        } else if (fakeColumn.equals("F")) {
            arrayColumn = 5;
            arrayRow = 8 - reverseRow;
        } else if (fakeColumn.equals("G")) {
            arrayColumn = 6;
            arrayRow = 8 - reverseRow;
        } else if (fakeColumn.equals("H")) {
            arrayColumn = 7;
            arrayRow = 8 - reverseRow;
        }
        int[] answer = {arrayRow, arrayColumn};
        return answer;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();

        answer.append("     A     B     C     D     E     F     G     H   \n");
        answer.append("   -------------------------------------------------\n");
        int k = 8;
        for (int i=0; i<8; i++) {
            answer.append(k);

            for (int j=0; j<8; j++) {
                answer.append(" |  " + (board[i][j].piece == null ? " " : board[i][j].piece)
                        + " ");
            }
            answer.append(" |  " + k);
            k--;
            answer.append("\n   -------------------------------------------------\n");
        }
        answer.append("     A     B     C     D     E     F     G     H\n");

        return answer.toString();
    }


//This method returns the squares between square1 to square2 if these two square are not at the
//same column, row or diagonal it returns empty array
//also there is no square between these two squares it again returns empty array
    public Square[] getSquaresBetween(Square square1, Square square2) {
        ArrayList<Square> answer = new ArrayList<>();
        int[] arrayLocation1 = arrayLocation(square1.getColumn() + square1.getRow());
        int[] arrayLocation2 = arrayLocation(square2.getColumn() + square2.getRow());
        int arrayRow1 = arrayLocation1[0];
        int arrayRow2 = arrayLocation2[0];
        int arrayColumn1 = arrayLocation1[1];
        int arrayColumn2 = arrayLocation2[1];

        if(arrayColumn1 == arrayColumn2) {
            if (arrayRow1 >= arrayRow2) {
                for (int i = arrayRow2 + 1; i < arrayRow1; i++) {
                    answer.add(board[i][arrayColumn1]);
                }
            } else {
                for (int i = arrayRow1 + 1; i < arrayRow2; i++) {
                    answer.add(board[i][arrayColumn1]);
                }
            }
        } else if(arrayRow1 == arrayRow2) {
            if (arrayColumn1 > arrayColumn2) {
                for (int i = arrayColumn2 + 1; i < arrayColumn1; i++) {
                    answer.add(board[arrayRow1][i]);
                }
            } else {
                for (int i = arrayColumn1 + 1; i < arrayColumn2; i++) {
                    answer.add(board[arrayRow1][i]);
                }
            }
        }  else {/*in the else section it looks for the diagonal squares*/
            int newX = arrayRow1 - 1;//left upper
            int newY = arrayColumn1 - 1;//left upper

            while (newX >= 0 && newY >= 0) {

                if(board[newX][newY].getRow().equals(square2.getRow()) && board[newX][newY].getColumn().equals(square2.getColumn())) {
//                    System.out.println("Left Upper Squares : " + answer.toString());
                    return answer.toArray(Square[]::new);
                }
                answer.add(board[newX][newY]);
                newX--;
                newY--;
            }
            answer = new ArrayList<>();
            newX = arrayRow1 + 1;//left lower
            newY = arrayColumn1 -1;//left lower
            while (newX < 8 && newY >= 0) {

                if(board[newX][newY].getRow().equals(square2.getRow()) && board[newX][newY].getColumn().equals(square2.getColumn())) {
//                    System.out.println("Left Lower Squares : " + answer.toString());
                    return answer.toArray(Square[]::new);
                }
                answer.add(board[newX][newY]);
                newY--;
                newX++;
            }
            answer = new ArrayList<>();

            newX = arrayRow1 + 1;//right lower
            newY = arrayColumn1 + 1;//right lower
            while (newX < 8 && newY < 8) {

                if(board[newX][newY].getRow().equals(square2.getRow()) && board[newX][newY].getColumn().equals(square2.getColumn())) {
//                    System.out.println("Right Lower Squares : " + answer.toString());
                    return answer.toArray(Square[]::new);
                }
                answer.add(board[newX][newY]);
                newY++;
                newX++;
            }
            answer = new ArrayList<>();

            newX = arrayRow1 - 1;//right upper
            newY = arrayColumn1 + 1;//right upper
            while (newX >= 0 && newY < 8) {

                if(board[newX][newY].getRow().equals(square2.getRow()) && board[newX][newY].getColumn().equals(square2.getColumn())) {
//                    System.out.println("Right Upper Squares : " + answer.toString());
                    return answer.toArray(Square[]::new);
                }
                answer.add(board[newX][newY]);
                newY++;
                newX--;
            }
            answer = new ArrayList<>();
        }

        Square[] squares = new Square[answer.size()];
        for (int i = 0; i < squares.length; i++) {
            squares[i] = answer.get(i);
        }

        return squares;

    }

    public static void nextPlayer() {
        if(turn==1) turn = 0;
        else if(turn==0) turn = 1;
    }

// if there is no white or black piece it returns true
    public boolean isGameEnded() {
        int white = 0;
        int black = 0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                if(!board[i][j].isEmpty() && board[i][j].getPiece().getColor() == 0) {
                    white++;
                } else if(!board[i][j].isEmpty() && board[i][j].getPiece().getColor() == 1) {
                    black++;
                }
            }
        }

        return white == 0 || black == 0;
    }

// it returns the piece at the given location
    public Piece getPieceAt(String from) {
        int[] arrayLocation = arrayLocation(from);
        int arrayRow = arrayLocation[0];
        int arrayColumn = arrayLocation[1];
        System.out.println( board[arrayRow][arrayColumn].getPiece() == null ? "empty":board[arrayRow][arrayColumn].getPiece().toString());
        return board[arrayRow][arrayColumn].getPiece();
    }

}
