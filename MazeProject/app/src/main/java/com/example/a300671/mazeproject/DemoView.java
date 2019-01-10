package com.example.a300671.mazeproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

class DemoView extends View
{

    private int width;
    private int height;
    private Bitmap bitmap;
    private Canvas canvas;
    private Path path;
    private Path coverPath;

    Context context;

    private Paint paintSand;
    private Paint paintBlack;
    private Paint paintWhite;
    
    
    public boolean rightCalled;
    public boolean leftCalled;
    public boolean upCalled;
    public boolean downCalled;
                
    public boolean action;
    
    Character character;

    private float stretchValue;
    
    

    public DemoView(Context c, AttributeSet attributeSet)
    {
        super(c, attributeSet);
        context = c;
        //canvas = new Canvas();

        path = new Path();
        coverPath = new Path();

        stretchValue = 2.7f;

        paintSand = new Paint();
        paintSand.setColor(0xffffd417);
        paintSand.setStyle(Paint.Style.STROKE);  // creating a "pen" that does not fill in regions
        paintSand.setStrokeWidth(10);            // width of the pen's stroke
        paintSand.setAntiAlias(true);

        paintBlack = new Paint();
        paintBlack.setColor(Color.BLACK);

        paintWhite = new Paint();
        paintWhite.setColor(Color.WHITE);
        paintWhite.setStyle(Paint.Style.FILL);
        
        
        
        rightCalled = false;
        leftCalled = false;
        upCalled = false;
        downCalled = false;
                
        action = false;

        
    }
    
    

    @Override protected
    void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }


    @Override protected
    void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawPath(path, paintSand);
        canvas.drawPath(coverPath, paintWhite);
    }

    void resetScreen() {
        path.reset();
        coverPath.reset();
        invalidate();
    }


    void drawCharacter(float x, float y, float oldx, float oldy, int direction) {
        float xSlant; //how much the base moves from left to right
        float ySlant;
        float topX; //coordinates of the top of the triangle
        float topY;

        if (direction == 0) {
            xSlant = 0f;
            ySlant = 20f;
            topX = x + 20f;
            topY = y + 10f;
        }
        else if (direction == 1) {
           xSlant = 20f;
           ySlant = 0f;
           topX = x + 10f;
           topY = y - 20f;
        }
        else if (direction == 2) {
            xSlant = 0f;
            ySlant = 20f;
            topX = x - 20f;
            topY = y + 10f;
        }
        else if (direction == 3) {
            xSlant = 20f;
            ySlant = 0f;
            topX = x + 10f;
            topY = y + 20f;
        }
        else {
            return;
        }

        coverPath.moveTo(oldx * stretchValue, oldy * stretchValue);
        coverPath.addRect(oldx * stretchValue, (oldy + 20) * stretchValue, (oldx + 20) * stretchValue, oldy * stretchValue, Path.Direction.CCW);


        path.moveTo(x * stretchValue, y * stretchValue); //draws base
        path.lineTo((x + xSlant) * stretchValue, (y + ySlant) * stretchValue); //bottom right of triangle


        path.moveTo(x * stretchValue, y * stretchValue); //draws line from left base to top
        path.lineTo(topX * stretchValue, topY * stretchValue);

        path.moveTo((x + xSlant) * stretchValue, (y + ySlant) * stretchValue); //draws line from right base to top
        path.lineTo(topX * stretchValue, topY * stretchValue);

        invalidate();

    }

    void drawMaze() {
        int[][][] walls = com.example.a205037.maze.Maze.getAllWalls();
        int numWalls = com.example.a205037.maze.Maze.getNumWalls();

        int[][] wall;
        int[] point1;
        int[] point2;
        for (int i = 0; i < numWalls; i += 1) {
            wall = walls[i];
            point1 = wall[0];
            point2 = wall[1];

            path.moveTo(point1[0] * stretchValue, point1[1] * stretchValue);
            path.lineTo(point2[0] * stretchValue, point2[1] * stretchValue);
        }


        invalidate();
    }


    void run() 
    {
        boolean running = true;
        
        while(running)
        {
            try
            {
                thread.sleep(10);
                
                rightCalled = false;
                leftCalled = false;
                upCalled = false;
                downCalled = false;
                action = false;
                
                if(action == true)
                {
                    if(upCalled)
                    {
                        if(character.canMoveForward)
                            character.moveForward();
                        upCalled = false;
                    }
                    else if(downCalled){
                        if(character.canMoveBackward()
                            character.moveBackward();
                        downCalled = false;
                    }
                    else if(rightCalled){
                        character.turnRight();
                        rightCalled = false;
                    }
                    else if(leftCalled){
                        character.turnLeft();
                        leftCalled = false;
                    }
                        
                        
                }
                else
                {
                    // nothing happens
                }
                
                invalidate();
            }
            catch{
                System.exit(0);
            }
        }
    }
    
    public static int getScreeenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    
    public static int getScreeenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
    
    public void setCharacter(Character character)
    {
        this.character = character;
    }
     
   
}
