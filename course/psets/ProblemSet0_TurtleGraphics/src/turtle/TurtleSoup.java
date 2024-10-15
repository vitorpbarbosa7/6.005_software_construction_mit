/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;

import java.util.Random;


public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {

        int RIGHT_ANGLE = 90;
        int SIDES_SQUARE = 4;

        for (int i = 0; i < SIDES_SQUARE; i++){
            // moves up
            turtle.forward(sideLength);
            // rotate 90 degrees
            turtle.turn(RIGHT_ANGLE);
        }
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {

        assert sides > 2;

        double INITIAL_MIN_ANGLE = 180.0;
        int SUB = 2;

        double angle;

        angle = ((sides - SUB)*INITIAL_MIN_ANGLE)/sides;

        assert angle >= 0 && angle < 360;

        return angle;

    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {

        double NUMERATOR_NUM = 360.0;
        double DENOMINATOR_NUM = 180;
        double value;

        value = NUMERATOR_NUM/(DENOMINATOR_NUM - angle);

        int sides = (int) Math.round(value);

        assert angle > 0 && angle < 180;

        return sides;
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {

        double angle = 180 - calculateRegularPolygonAngle(sides);

        for (int i = 0; i < sides; i++) {
            turtle.forward(sideLength);
            turtle.turn(angle);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    double differenceAngle = Math.toDegrees(Math.atan2(targetY - currentY, targetX - currentX));

    double turnAngle = - currentHeading + 90 - differenceAngle;

    while (turnAngle < 0){
        turnAngle += 360;
    }

    assert turnAngle >= 0 && turnAngle < 360;

    return turnAngle;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {

        double currentAngle = 0.0;
        double angleAdd;
        int currentX;
        int targetX;
        int currentY;
        int targetY;

        List<Double> angleList = new ArrayList<Double>();

        for (int i = 0; i < xCoords.size()-1; i ++) {

            // x
            targetX = xCoords.get(i+1);
            currentX = xCoords.get(i);

            // y
            targetY = yCoords.get(i+1);
            currentY = yCoords.get(i);

            angleAdd = calculateHeadingToPoint(
                currentAngle, 
                //y
                currentX,
                currentY,
                //x
                targetX, 
                targetY);

            currentAngle = angleAdd;

            angleList.add(angleAdd);

        }

        return angleList;
    
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {

        Random random = new Random();
        int random_step;
        int random_angle;
        for (int i = 0; i < 15; i++){
            random_step = 30 + random.nextInt(41); 
            random_angle = random.nextInt(180);
            turtle.forward(random_step);
            turtle.turn(random_angle);
        }

    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        // drawSquare(turtle, 40);

        // TurtleSoup.drawRegularPolygon(turtle, 5, 40);


        TurtleSoup.drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
    }

}
