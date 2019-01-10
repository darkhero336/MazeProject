package com.example.a300671.mazeproject;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    private DemoView demoView;
    
    Character character;
    
     private View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int x = (int) event.getRawX();
            int y = (int) event.getRawY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if((x > getScreenWidth() * 0.65) && (y < getScreenHeight() * 0.65) && (y > getScreenHeight() * 0.35)){
                        demoView.action = true;
                        demoView.rightCalled = true;
                    }
                    else if((x < getScreenWidth() * 0.35) && (y < getScreenHeight() * 0.65) && (y > getScreenHeight() * 0.35)){
                        demoView.action = true;
                        demoView.leftCalled = true;
                    }
                    else if(y > getScreenHeight * 0.65){
                        demoView.action = true;
                        demoView.upCalled = true;
                    }
                    else if(y < getScreenHeight * 0.35){
                        demoView.action = true;
                        demoView.downCalled = false;
                    }
                    else{
                        demoView.action = false;
                        demoView.upCalled = false;
                        demoView.downCalled = false;
                        demoView.leftCalled = false;
                        demoView.rightCalled = false;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:{
                    demoView.action = false;
                    demoView.upCalled = false;
                    demoView.downCalled = false;
                    demoView.leftCalled = false;
                    demoView.rightCalled = false;
                    break;
                };
                case MotionEvent.ACTION_UP:{
                    demoView.action = false;
                    demoView.upCalled = false;
                    demoView.downCalled = false;
                    demoView.leftCalled = false;
                    demoView.rightCalled = false;
                    break;
                };
            };

            return true;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);      // not using the default activity_main XML resource
        demoView = (DemoView) findViewById(R.id.signature_canvas);
        character = new Character();
        demoView.setCharacter(character);
        demoView.setOnTouchListener(handleTouch);   // !!! Might be weird
        //setContentView(demoView);
        drawMaze();
        demoView.run();
    }

    public void resetScreen(View v) {
        demoView.resetScreen();
    }

    public void drawMaze(View v) {
        Button button = findViewById(R.id.startButton);
        button.setVisibility(View.GONE);


        demoView.drawCharacter(character.getX(), character.getY(), character.getOldx(), character.getOldy(), character.getDirection());

        demoView.drawMaze();
    }
}
