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

    private int oldx;
    private int oldy;

    Context context;

    private Paint paintSand;
    private Paint paintBlack;
    private Paint paintWhite;

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

        oldx = 50;
        oldy = 50;


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


    void drawCharacter(int x, int y) {

        coverPath.moveTo(oldx * stretchValue, oldy * stretchValue);
        coverPath.addRect(oldx * stretchValue, (oldy + 20) * stretchValue, (oldx + 20) * stretchValue, oldy * stretchValue, Path.Direction.CCW);


        path.moveTo(x * stretchValue, y * stretchValue);
        path.lineTo((x + 20) * stretchValue, y * stretchValue);


        path.moveTo(x * stretchValue, y * stretchValue);
        path.lineTo((x + 10) * stretchValue, (y - 20) * stretchValue);

        path.moveTo((x + 20) * stretchValue, y * stretchValue);
        path.lineTo(((x + 20) - 10) * stretchValue, (y - 20) * stretchValue);

        oldx = x;
        oldy = y;


        invalidate();

    }

    void gameLoop() {
        boolean infinite = true;
        while (infinite == true) {
            try {
                Thread.sleep(100);
                invalidate();
            }
            catch(InterruptedException ex) {
                System.exit(0);
            }
        }
    }
}
