package com.example.a300671.mazeproject;

public class Character
{

    float x;
    float y;
    float oldx; //these are for covering up the player's last position  with a white square
    float oldy;
    int direction;  //0 (right), 1 (up), 2 (left), 3 (down)
    
    boolean badx;
    boolean bady;

    public Character() {
        x = 20f;
        y = 20f;
        oldx = 20f;
        oldy = 20f;
        direction = 3;
    }

    public void resetCharacter() {
        x = 20f;
        y = 20f;
        oldx = 20f;
        oldy = 20f;
        direction = 3;
    }

    public boolean canMoveForward()
    {
        int tempX = (int) x;
        int tempY = (int) y;
        
        if (direction == 0) {
            if(com.example.a205037.maze.Maze.checkCollision((tempX + 20),tempY))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 1) {
            if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY - 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 2) {
            if(com.example.a205037.maze.Maze.checkCollision((tempX - 20), tempY))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 3) {
            if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY + 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            System.out.println("Logical infeasibility in canMoveForward");
            return false;
        }
    }
    
    public boolean canMoveBackward()
    {
        int tempX = (int) x;
        int tempY = (int) y;
        
        if (direction == 0) {
            if(com.example.a205037.maze.Maze.checkCollision((tempX + 20),tempY))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 1) {
            if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY - 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 2) {
            if(com.example.a205037.maze.Maze.checkCollision((tempX - 20), tempY))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else if (direction == 3) {
            if(com.example.a205037.maze.Maze.checkCollision(tempX, (tempY + 20)))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            System.out.println("Logical infeasibility in canMoveBackward");
            return false;
        }
    }
    
    public void moveForward() {

        if (direction == 0) {
            oldx = x;
            x += 20;                   // make a "speed" variable to replace magic "20" ?
        } else if (direction == 1) {
            oldy = y;
            y -= 20;
        } else if (direction == 2) {
            oldx = x;
            x -= 20;
        } else if (direction == 3) {
            oldy = y;
            y += 20;
        }

        //oldx = 0;
        //oldy = 400;




    }
    
    public void moveBackward() {


        if (direction == 0) {
            oldx = x;
            x -= 20;
        } else if (direction == 1) {
            oldy = y;
            y += 20;
        } else if (direction == 2) {
            oldx = x;
            x += 20;
        } else if (direction == 3) {
            oldy = y;
            y -= 20;
        }
    }



    public void turnLeft() {
        oldx = x;
        oldy = y;
        direction = (direction + 1) % 4;
    }
    
    public void turnRight() {
        oldx = x;
        oldy = y;

        if(direction >= 0)
            direction -= 1;
        else
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
