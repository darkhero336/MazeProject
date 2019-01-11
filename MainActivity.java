package com.example.a300671.mazeproject;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    private DemoView demoView;
    
    Character character;
    


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);      // not using the default activity_main XML resource
        demoView = findViewById(R.id.signature_canvas);
        character = new Character();
        demoView.setCharacter(character);


        demoView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                int x = (int) event.getRawX();
                int y = (int) event.getRawY();

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        if ((x > demoView.getScreenWidth() * 0.65) && (y < demoView.getScreenHeight() * 0.65) && (y > demoView.getScreenHeight() * 0.35))
                        {
                            demoView.action = true;
                            demoView.rightCalled = true;
                        } else if ((x < demoView.getScreenWidth() * 0.35) && (y < demoView.getScreenHeight() * 0.65) && (y > demoView.getScreenHeight() * 0.35))
                        {
                            demoView.action = true;
                            demoView.leftCalled = true;
                        } else if (y > demoView.getScreenHeight() * 0.65)
                        {
                            demoView.action = true;
                            demoView.upCalled = true;
                        } else if (y < demoView.getScreenHeight() * 0.35)
                        {
                            demoView.action = true;
                            demoView.downCalled = false;
                        } else
                        {
                            demoView.action = false;
                            demoView.upCalled = false;
                            demoView.downCalled = false;
                            demoView.leftCalled = false;
                            demoView.rightCalled = false;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                    {
                        demoView.action = false;
                        demoView.upCalled = false;
                        demoView.downCalled = false;
                        demoView.leftCalled = false;
                        demoView.rightCalled = false;
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        demoView.action = false;
                        demoView.upCalled = false;
                        demoView.downCalled = false;
                        demoView.leftCalled = false;
                        demoView.rightCalled = false;
                        break;
                    }
                }

                return true;
            }
        });

    }

    public void run(View v){
        boolean running = true;
        //demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());

        //demoView.drawMaze();
        Button button = findViewById(R.id.startButton);
        button.setVisibility(View.GONE);


        while(running)
        {

            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                System.exit(0);
            }


            demoView.rightCalled = false;
            demoView.leftCalled = false;
            demoView.upCalled = false;
            demoView.downCalled = false;
            demoView.action = false;

            if(demoView.action == true)
            {
                if(demoView.upCalled)
                {
                    if(character.canMoveForward()) {
                        character.moveForward();
                    }
                    demoView.upCalled = false;
                }
                else if(demoView.downCalled){
                    if(character.canMoveBackward()) {
                        character.moveBackward();
                        demoView.downCalled = false;
                    }

                }
                else if(demoView.rightCalled){
                    character.turnRight();
                    demoView.rightCalled = false;
                }
                else if(demoView.leftCalled){
                    character.turnLeft();
                    demoView.leftCalled = false;
                }


            }
            else
            {
                // nothing happens
            }

            //demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
            //demoView.drawMaze();
            //invalidate();
        }
    }

    public void resetScreen(View v) {
        demoView.resetScreen();
    }

    public void createMaze(View v) {
        Button button = findViewById(R.id.createMazeButton);
        button.setVisibility(View.GONE);

        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
        demoView.drawMaze();

    }

}
