package com.company;


public abstract class Piece {
    protected int color;
    protected Square location;

    public Piece(int color, Square location) {
        this.color = color;
        this.location = location;
    }

    public abstract boolean canMove(String destination);
// this move method doesn't include Pawn.move()
    public void move(String to) {
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(location.piece);
//clear previous location
        location.clear();
//update current location
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    public int getColor() {
        return color;
    }

    public Square getLocation() {
        return location;
    }

}
