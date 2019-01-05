package com.example.a205037.maze;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;

public class Maze
{
    public static int numWalls = 36;                // number of "walls" in the whole maze
    public static int[][][] walls = {                                          // constructed array, clone of desired walls[][][]
    {{0, 0}, {400, 0}},             // W1                    //
    {{400, 0}, {400, 400}},         // W2                    // overall capsule {} is the array of walls, each wall is its own capsule, containing two point coordinates as arrays
    {{400,400}, {0, 400}},          // W3                    // {wall 1, wall 2...}  ->  {  {W1 point A , W1 point B}, {W2 Point A , W2 Point B} ...}  ->  {   { {Xval A, Yval A} , {Xval B, Yval B} }   }
    {{0,400}, {0,0}},               // W4

    {{40,0}, {40,160}},                       // W5
    {{40,160}, {80,160}},                       // W6
    {{80,160}, {80,40}},                       // W7
    {{80,40}, {120,40}},                       // W8
    {{120,40}, {120,160}},                       // W9
    {{160,160}, {160,0}},                       // W10
    {{200,200}, {200,40}},                       // W11
    {{200,40}, {280,40}},                       // W12
    {{240,80}, {240,200}},                       // W13
    {{280,40}, {280,160}},                       // W14
    {{320,160}, {320,40}},                       // W15
    {{320,40}, {400,40}},                       // W16
    {{360,80}, {360,200}},                       // W17
    {{360,200}, {360,360}},                       // W18
    {{360,360}, {240,360}},                       // W19
    {{240,360}, {240,320}},                       // W20
    {{240,320}, {320,320}},                       // W21
    {{320,320}, {320,240}},                       // W22
    {{360,200}, {240,200}},                       // W23
    {{280,240}, {280,280}},                       // W24
    {{280,280}, {240,280}},                       // W25
    {{240,280}, {240,200}},                       // W26
    {{240,200}, {200,200}},                       // W27
    {{200,200}, {40,200}},                       // W28
    {{200,240}, {40,240}},                       // W29
    {{40,240}, {40,360}},                       // W30
    {{40,360}, {120,360}},                       // W31
    {{120,360}, {120,320}},                       // W32
    {{80,320}, {80,280}},                       // W33
    {{80,280}, {160,280}},                       // W34
    {{160,280}, {160,400}},                       // W35
    {{200,400}, {200,240}},                       // W36
    };             // array of walls[][], wall is an array of points[], a point is an array with [xVal, yVal]
    public static int mazeWidth = 400;
    public static int mazeHeight = 400;

    public int[][] getWall(int wallIndex)                   // returns array of points given the index of the wall
    {
        return walls[wallIndex];
    }

    public int[] getWallPoint(int wallIndex, int pointIndex)     // returns point given index of wall and index of point (0 is point A,  is point B)
    {
        return walls[wallIndex][pointIndex];
    }

    public int[] getWallPointA(int wallIndex)     // returns point A of a given wall (wall index)
    {
        return walls[wallIndex][0];
    }

    public int[] getWallPointB(int wallIndex)     // returns point B of a given wall (wall index)
    {
        return walls[wallIndex][1];
    }

    public static int[][][] getAllWalls()
    {
        return walls;
    }

    public static int getMazeWidth()
    {
        return mazeWidth;
    }

    public static int getMazeHeight()
    {
        return mazeHeight;
    }

    public static int getNumWalls() {
        return numWalls;
    }

    public static boolean checkCollision(int x, int y) { //returns true if character is colliding w/ maze
        boolean collision = false;

        int[][][] characterBox = { //creates a rudamentry array that is a box of all the walls surrounding character
                {{x, y}, {x + 20, y}}, //20 is the width/height of box
                {{x + 20, y}, {x + 20, y + 20}},
                {{x + 20, y + 20}, {x, y + 20}},
                {{x, y + 20}, {x, y}},
        };

        for (int i = 0; i < numWalls; i +=1) { //goes through each wall in maze
            for (int j = walls[i][0][0]; j < walls[i][1][0]; j +=1) { //goes through each x cord in individual wall
                for (int k = walls[i][0][1]; k < walls[i][1][1]; k +=1) { //goes through each y cord in individual wall

                    for (int l = 0; l < 4; l += 1) { //goes through each wall in character
                        for (int m = characterBox[i][0][0]; m < characterBox[i][1][0]; m += 1) { //goes through each x cord in individual wall of character
                            for (int n = characterBox[i][0][1]; n < characterBox[i][1][1]; n += 1) { //goes through each y cord in individual wall of character
                                if (j == m || k == n) { //if point in wall is the same as a point in character
                                    collision = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return collision;
    }

}
