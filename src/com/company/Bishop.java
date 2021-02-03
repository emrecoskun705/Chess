package com.company;

import java.util.Arrays;

public class Bishop extends Piece {
    public Bishop(int color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = location.getSquareAt(to);
//if it is same color at the target location return false
        if(!targetLocation.isEmpty() && targetLocation.getPiece().color == this.color) {
            return false;
        }
        Square[] between = location.getBoard().getSquaresBetween(location, targetLocation);

//if it's not same column or same row it is diagonal
        if(this.location.isDiagonal(targetLocation)) {
            for(int i=0; i<between.length; i++) {
                if(!between[i].isEmpty()) {
                    return false;
                }
            }
//if is valid location and there is no filled square between it can move
            return true;
        }

//invalid square
        return false;
    }

    @Override
    public void move(String to) {
        super.move(to);
    }

    @Override
    public String toString() {
        return color == ChessBoard.WHITE ? "B" : "b";
    }
}
