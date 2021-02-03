package com.company;

public class King extends Piece {
    public King(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getSquareAt(to);
//if it is same color at the target location return false
        if(!targetLocation.isEmpty() && targetLocation.getPiece().color == this.color) {
            return false;
        }

        if(this.location.possibleMovementsForKing().contains(targetLocation)) {
            return true;
        }

        return false;
    }

    @Override
    public void move(String to) {
        super.move(to);
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "K" : "k";
    }
}
