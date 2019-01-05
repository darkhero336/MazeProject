package com.example.a300671.mazeproject;

public class Character
{

    float x;
    float y;
    float oldx; //these are for covering up the player's last position  with a white square
    float oldy;
    int direction;  //0 (right), 1 (up), 2 (left), 3 (down)

    public Character() {
        x = 10f;
        y = 10f;
        oldx = 0f;
        oldy = 0f;
        direction = 3;
    }

    public void moveForward() {
        if (direction == 0) {
            oldx = x;
            x += 50;
        }
        else if (direction == 1) {
            oldy = y;
            y -= 50;
        }
        else if (direction == 2) {
            oldx = x;
            x -= 50;
        }
        else if (direction == 3) {
            oldy = y;
            y += 50;
        }
    }

    public void turnLeft() {
        direction = (direction + 1) % 4;
    }

    public void resetCharacter() {
        x = 0f;
        y = 0f;
        direction = 3;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getOldx()
    {
        return oldx;
    }

    public float getOldy()
    {
        return oldy;
    }

    public int getDirection()
    {
        return direction;
    }
}
