package com.company;

import java.util.ArrayList;

public class Square extends ChessBoard {
    protected Piece piece = null;
    private String column;
    private String row;


    public Square(String column, String row) {
        this.column = column;
        this.row = row;
    }

    public void addPiece(Piece piece) {
        this.piece = piece;
    }


    @Override
    public ChessBoard getBoard() {
        return super.getBoard();
    }

    @Override
    public String toString() {
        return column+row;
    }

    public int getRowDistance(Square location) {
        Integer currentRow = Integer.parseInt(this.row);
        Integer otherRow = Integer.parseInt(location.getRow());

        return currentRow-otherRow;
    }

    public String getColumn() {
        return column;
    }

    public String getRow() {
        return row;
    }

    public boolean isAtSameColumn(Square targetLocation) {
        int columnLocation = arrayLocation(targetLocation.getColumn() + targetLocation.getRow())[1];
        int columnThis = arrayLocation(column + row)[1];
        return columnLocation == columnThis;
    }

    public boolean isDiagonal(Square targetLocation) {
        ArrayList<Square> answer = new ArrayList<>();
        int[] arrayLocation = arrayLocation(this.getColumn() + this.getRow());
        int x = arrayLocation[0];//array row
        int y = arrayLocation[1];//array column

        int xNew = x - 1;
        int yNew = y - 1;

        while (isValid(xNew, yNew)) {
            answer.add(board[xNew][yNew]);
            xNew--;
            yNew--;
        }

        xNew = x + 1;
        yNew = y - 1;
        while (isValid(xNew, yNew)) {
            answer.add(board[xNew][yNew]);
            yNew--;
            xNew++;
        }
        xNew = x + 1;
        yNew = y + 1;

        while (isValid(xNew, yNew)) {
            answer.add(board[xNew][yNew]);
            yNew++;
            xNew++;
        }

        xNew = x - 1;
        yNew = y + 1;
        while (isValid(xNew, yNew)) {
            answer.add(board[xNew][yNew]);
            yNew++;
            xNew++;
        }


        return answer.contains(targetLocation);
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    // TODO: 24.04.2020 bütün çaprazlara bakıyo düzelmesi lazım
    public boolean isNeighborColumn(Square targetLocation) {
        int[] arrayLocationOfTarget = arrayLocation(targetLocation.getColumn() + targetLocation.getRow());
        int[] arrayLocationOfThis = arrayLocation(column + row);
        int targetRow = arrayLocationOfTarget[0];
        int targetColumn = arrayLocationOfTarget[1];
        int thisRow = arrayLocationOfThis[0];
        int thisColumn = arrayLocationOfThis[1];
        return Math.abs(targetRow - thisRow) == 1 && (Math.abs(targetColumn - thisColumn) == 1 ||
                Math.abs(targetColumn - thisColumn) == 2);

    }

    public Piece getPiece() {
        return piece;
    }

    public void clear() {
        this.piece = null;
    }

    public boolean isAtLastRow(int color) {
        color = turn;
        if (turn == 0) {
            return getRow().equals("8");
        } else {
            return getRow().equals("1");
        }
    }

    public void putNewQueen(int color) {
        this.piece = new Queen(color, this);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }


    @Override
    public int[] arrayLocation(String destination) {
        return super.arrayLocation(destination);
    }

    public boolean isAtSameRow(Square targetLocation) {
        int rowLocation = arrayLocation(targetLocation.getColumn() + targetLocation.getRow())[0];
        int rowThis = arrayLocation(column + row)[0];

        return rowLocation == rowThis;
    }

    public ArrayList<Square> possibleMovementsForKnight() {
        ArrayList<Square> answer = new ArrayList<>();
        int[] arrayLocation = arrayLocation(column + row);
        int currentRow = arrayLocation[0];
        int currentColumn = arrayLocation[1];

        for (int row=-2; row <= 2; row++) {
            for(int column=-2; column<=2; column++) {
                if(Math.abs(row * column) == 2 && isValid(row+currentRow,column+currentColumn)) {
                    answer.add(board[row+currentRow][column+currentColumn]);
                }
            }
        }

        return answer;
    }

    public ArrayList<Square> possibleMovementsForKing() {
        ArrayList<Square> answer = new ArrayList<>();
        int[] arrayLocation = arrayLocation(column + row);
        int currentRow = arrayLocation[0];
        int currentColumn = arrayLocation[1];


        int[][] sets = {{1, 0}, {0, 1},{-1, 0},{0, -1},{1, 1},{-1, 1},{-1, -1},{1, -1}};
        for(int[] set : sets) {
            int newRow = currentRow+set[0];
            int newColumn = currentColumn+set[1];
            if(isValid(newRow, newColumn )) {
                answer.add(board[newRow][newColumn]);
            }
        }

        return answer;
    }

    public boolean isValid(int row, int column) {
        return row >= 0 && row <= 7 && column >= 0 && column <= 7;
    }
}
