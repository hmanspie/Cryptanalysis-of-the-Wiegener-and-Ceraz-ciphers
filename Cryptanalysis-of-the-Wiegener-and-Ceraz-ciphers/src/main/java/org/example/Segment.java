package org.example;

public class Segment {

    private String segment;
    private int positionOne;
    private int positionTwo;

    public Segment(String segment, int positionOne, int positionTwo) {
        this.segment = segment;
        this.positionOne = positionOne;
        this.positionTwo = positionTwo;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public int getPositionOne() {
        return positionOne;
    }

    public void setPositionOne(int positionOne) {
        this.positionOne = positionOne;
    }

    public int getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(int positionTwo) {
        this.positionTwo = positionTwo;
    }
}
