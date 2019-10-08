package com.example.chess19;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Toast;

import java.util.ArrayList;

public class drawingboard extends View {

    private int pathIndex = 0;
    private ArrayList<Path> pathLists = new ArrayList<>();
    private ArrayList<Paint> paintLists = new ArrayList<>();
    private float startX = 0F;
    private float startY = 0F;
    Paint paintp;

    Context c;

    public drawingboard(Context context, AttributeSet attrs){

        super(context, attrs);

        c=context;
        cb=new chessgame();

        setBackgroundColor(Color.parseColor("#ffff00"));

        paintp = new Paint();
        paintp.setStyle(Paint.Style.STROKE);
        paintp.setStrokeWidth(3F);

        cs=new ArrayList<Point>();

    }


    ArrayList<Point> cs;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(c,"clicked",Toast.LENGTH_SHORT).show();
                Point p=new Point( (int)event.getX(), (int)event.getY());


                cs.add(p);

                if (mEventListener != null) {
                    mEventListener.onEventOccurred(p);
                }

                //updateIndex(createPath(event));

                //invalidate();

                break;

            case MotionEvent.ACTION_MOVE:
/*
                float x = event.getX();
                float y = event.getY();
                Path path = pathLists.get(pathIndex - 1);;
                path.lineTo(x, y);
*/
                break;
            default:
                break;
        }
        // Invalidate the whole view. If the view is visible.
        invalidate();
        return true;
    }

    int row;
    chessgame cb;

    void reset(int row)
    {

        Toast.makeText(c,"in reset",Toast.LENGTH_SHORT).show();

        cb.side=row;
        invalidate();

    }

    private MyEventListener mEventListener;

    public void setEventListener(MyEventListener mEventListener) {
        this.mEventListener = mEventListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View
        Toast.makeText(c,"repaint",Toast.LENGTH_SHORT).show();

        canvas.drawColor(Color.YELLOW);
        canvas.drawText(String.valueOf(cb.side), 20,20,new Paint(Color.RED));

        for(Point p:cs)
        {

            canvas.drawCircle(p.x,p.y, 10,new Paint(Color.red(100)));

        }
        /*

    //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        //get the radius as half of the width or height, whichever is smaller
//subtract ten so that it has some space around it
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;

        //paint object for drawing in onDraw
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Style.FILL);
        circlePaint.setAntiAlias(true);
        //set the paint color using the circle color specified
        circlePaint.setColor(Color.blue(233));
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);

        canvas.drawCircle(20,20, 10, circlePaint);

        circlePaint.setColor(Color.red(233));
        //set text properties
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);
        //draw the text using the string attribute and chosen properties
        canvas.drawText("Oneonta", viewWidthHalf, viewHeightHalf, circlePaint);



*/
        /*
        canvas.drawColor(Color.WHITE);

        for (int index = 0; index < pathIndex; index++) {
            Path path = pathLists.get(index);
            Paint paint = paintLists.get(index);

            canvas.drawPath(path, paint);
        }
        */
    }
}
