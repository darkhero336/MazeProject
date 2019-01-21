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
                        if ((x > demoView.getScreenWidth() * 0.65) && (y < demoView.getScreenHeight() * 0.75) && (y > demoView.getScreenHeight() * 0.25))
                        {
                            character.turnRight();
                            demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                        } else if ((x < demoView.getScreenWidth() * 0.35) && (y < demoView.getScreenHeight() * 0.75) && (y > demoView.getScreenHeight() * 0.25))
                        {
                            character.turnLeft();
                            demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                        } else if (y < demoView.getScreenHeight() * 0.45)
                        {
                            if (character.canMoveForward()) {
                                character.moveForward();
                                demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                            }
                            else if (character.getWin())
                            {
                                demoView.drawWin();
                            }
                            else {
                                resetScreen(v);
                            }

                        } /*else if (y > demoView.getScreenHeight() * 0.55)
                        {
                            if (character.canMoveBackward()) {
                                character.moveBackward();
                                demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                            }
                            else{
                                resetScreen(v);
                            }
                            demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
                        }*/ else
                        {
                            demoView.action = false;
                            demoView.upCalled = false;
                            demoView.downCalled = false;
                            demoView.leftCalled = false;
                            demoView.rightCalled = false;
                        }
                        break;
            /*case MotionEvent.ACTION_MOVE:
            {
                /*demoView.action = false;
                demoView.upCalled = false;
                demoView.downCalled = false;
                demoView.leftCalled = false;
                demoView.rightCalled = false;
                break;
                character.moveForward();
                demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());
            }*/
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

        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection()); // for safe measure


    }

    /*@Override
    public void onStart() {
        demoView.run();
    }*/

    public void resetScreen(View v) {
        demoView.resetScreen();
        character.resetCharacter();
        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());

        demoView.drawMaze();

    }

   /* public void drawMaze(View v) {
        Button button = findViewById(R.id.startButton);
        button.setVisibility(View.GONE);


        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());

        demoView.drawMaze();

        //demoView.run();
    }*/
}
