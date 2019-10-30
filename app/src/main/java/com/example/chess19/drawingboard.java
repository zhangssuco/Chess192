package com.example.chess19;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

public class drawingboard extends View {

    private int pathIndex = 0;
    private ArrayList<Path> pathLists = new ArrayList<>();
    private ArrayList<Paint> paintLists = new ArrayList<>();
    private float startX = 0F;
    private float startY = 0F;

    ArrayList<Point> cs;

    Paint paintp;

    Context c;

    public drawingboard(Context context, AttributeSet attrs){


        super(context, attrs);

        c=context;

        //cb=new chessgame();
        cb=new chess();


        setBackgroundColor(Color.parseColor("#ffff00"));

        paintp = new Paint();
        paintp.setStyle(Paint.Style.STROKE);
        paintp.setStrokeWidth(3F);

        cs=new ArrayList<Point>();

        loadallimages();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText(c,"clicked",Toast.LENGTH_SHORT).show();
                Point p=new Point( (int)event.getX(), (int)event.getY());
                Toast.makeText(c,String.valueOf((int)event.getX()) +","+String.valueOf((int)event.getY()),Toast.LENGTH_SHORT).show();

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
    //chessgame cb;
    chess cb;

    void reset(int row)
    {

        Toast.makeText(c,"in reset",Toast.LENGTH_SHORT).show();

        //cb.side=row;

        invalidate();

    }

    private MyEventListener mEventListener;

    public void setEventListener(MyEventListener mEventListener) {
        this.mEventListener = mEventListener;
    }


    void justshowhowtodraw(Canvas canvas)
    {
        canvas.drawColor(Color.GREEN);//whatever color you want, make sure it's not the same as your image
        //draw the View
        Toast.makeText(c,"repaint",Toast.LENGTH_SHORT).show();
        canvas.drawColor(Color.YELLOW);
        canvas.drawText(String.valueOf(cb.step), 20,20,new Paint(Color.RED));
        String imagename;
        imagename="dk.png";
        int id = c.getResources().getIdentifier(imagename, "drawable",c.getPackageName());
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(c.getResources(), id);
        Toast.makeText(c,String.valueOf(id),Toast.LENGTH_SHORT).show();



        //canvas.drawBitmap(bitmap, new Rect(0,0,60,60), new Rect( 30,30,90,90), null);

        int imageid=R.drawable.dk;
        int imageidforln=R.drawable.ln;

        //Toast.makeText(c,String.valueOf(imageid),Toast.LENGTH_SHORT).show();
        //imageid=c.getResources().getIdentifier("dk", "drawable",c.getPackageName());
        Drawable d = getResources().getDrawable(imageid, null);

        d.setBounds(50, 50, 100,100);
        d.draw(canvas);

        Drawable dln = getResources().getDrawable(imageidforln, null);

        dln.setBounds(200, 200, 260,400);
        dln.draw(canvas);


        for(Point p:cs)
        {

            canvas.drawCircle(p.x,p.y, 10,new Paint(Color.red(100)));

        }

        //get half of the width and height as we are working with a circle
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        int radius=40;
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius,new Paint(Color.red(100)));
        //paint object for drawing in onDraw
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);
        //set the paint color using the circle color specified
        circlePaint.setColor(Color.blue(233));
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius-20, circlePaint);


        canvas.drawCircle(20,20, 10, circlePaint);

        circlePaint.setColor(Color.red(233));
        //set text properties
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);
        //draw the text using the string attribute and chosen properties
        canvas.drawText("Oneonta", viewWidthHalf, viewHeightHalf, circlePaint);

        /*

        canvas.drawColor(Color.WHITE);
        for (int index = 0; index < pathIndex; index++) {
            Path path = pathLists.get(index);
            Paint paint = paintLists.get(index);

            canvas.drawPath(path, paint);
        }

        */

    }

    int margin=10;
    int gridheight=10;
    int gridwidth=10;

    @Override
    protected void onDraw(Canvas canvas) {

        //justshowhowtodraw(canvas);

        //get half of the width and height as we are working with a circle
        gridwidth = (this.getMeasuredWidth()-2*margin)/8;
        gridheight = (this.getMeasuredHeight()-2*margin)/8;

        //drawboard draw lines (optional)
        for(int row=0; row<9; row++) {
            canvas.drawLine(margin, gridheight * row + margin, margin+gridwidth*8, gridheight * row + margin, new Paint(Color.RED));
            canvas.drawLine(margin+gridwidth*row, margin, margin+gridwidth*row, margin+gridheight*8, new Paint(Color.RED));
        }
        // drawboard, checkers
        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                if ((row+col)%2==1)
                    canvas.drawRect(margin+gridwidth*col, gridheight * row + margin, margin+gridwidth*col+gridwidth, gridheight * row + margin+gridheight, new Paint(Color.GREEN));
            }
        }
        //draw game
        updategamevisualization(canvas);


    }
    Drawable imglb =null; //light bishop
    Drawable imglk=null; // light king
    Drawable imgdk=null; // dark king
    Drawable imgdq=null; // dark queen
    //!!! Expand this part

    public void loadallimages()
    {
        try{
            imglb=getResources().getDrawable(R.drawable.lb, null);
            imglk=getResources().getDrawable(R.drawable.lk, null);
            imgdk=getResources().getDrawable(R.drawable.dk, null);
            imgdq=getResources().getDrawable(R.drawable.dq, null);

            //!!! Expand this part

            // alternatively you can also download all images to local computer, and read them from there. faster
            //Image imgbl=ImageIO.read(new File("c:\\courses\\csci268\\Chess_blt60.png"));

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }



    }


    void updategamevisualization(Canvas canvas)
    {
        //co.display();

        int x;
        int y;

        Drawable imgcurrent=null;

        for(int row=0;  row<8;  row++)
            for(int  col=0; col<8; col++)
            {
                if (cb.game[row][col]==cb.EMPTY)
                    continue;

                x= margin+col*gridwidth;
                y = margin+row*gridheight;

                if (cb.game[row][col]==cb.LIGHTBISHOP)
                    imgcurrent = imglb;
                else if (cb.game[row][col]==cb.LIGHTKING)
                    imgcurrent = imglk;
                else if (cb.game[row][col]==cb.DARKKING)
                    imgcurrent = imgdk;
                else if (cb.game[row][col]==cb.DARKQUEEN)
                    imgcurrent = imgdq;
                    //!!! Expand this part

                else
                    imgcurrent=null;

                if (imgcurrent!=null) {
                    imgcurrent.setBounds(x, y, x+gridwidth,y+gridheight);
                    imgcurrent.draw(canvas);

                }
                else
                    canvas.drawText(String.valueOf(cb.game[row][col]),
                            x+gridwidth/2,y+gridheight/2, new Paint(Color.BLUE));



            }

    }

}
