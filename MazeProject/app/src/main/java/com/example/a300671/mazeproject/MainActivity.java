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

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);      // not using the default activity_main XML resource
        demoView = (DemoView) findViewById(R.id.signature_canvas);
        //setContentView(demoView);
    }

    public void resetScreen(View v) {
        demoView.resetScreen();
    }

    public void drawMaze(View v) {
        Button button = findViewById(R.id.startButton);
        button.setVisibility(View.GONE);
        demoView.drawCharacter(50, 50);
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ex) {
            System.exit(0);
        }
    }
}