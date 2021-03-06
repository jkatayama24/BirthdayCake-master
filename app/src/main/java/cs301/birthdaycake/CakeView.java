package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class CakeView extends SurfaceView  {

    //implements View.OnTouchListener
    /* These are the paints we'll use to draw the birthday cake below */
    private CakeModel object;
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint greenPaint = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 80.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;
    public float x;
    public float y;
    boolean clicked;
    public Paint red;




    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.object = new CakeModel();

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFC755B5);  //violet-red
        cakePaint.setColor(0xFF215F6C);
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        greenPaint.setColor(0x226C21);
        greenPaint.setStyle(Paint.Style.FILL);
        Paint g = new Paint();
        Paint r = new Paint();
        g.setColor(Color.GREEN);
        r.setColor(Color.RED);

        setBackgroundColor(Color.WHITE);  //better than black default
        clicked = false;
        red = new Paint(Color.red(255));

    }



    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

        //draw the outer flame
        if(this.object.isLit) {
            float flameCenterX = left + candleWidth / 2;
            float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
            canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

            //draw the inner flame
            flameCenterY += outerFlameRadius / 3;
            canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
        }
        if(this.object.candles) {
            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
        }
        invalidate();



    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        if(this.object.candles) {
            //drawCandle(canvas, cakeLeft + cakeWidth / 3 - candleWidth / 2, cakeTop);
            //drawCandle(canvas, cakeLeft + 2 * cakeWidth / 3 - candleWidth / 2, cakeTop);
            for(int i = 1; i < object.numberCandles +1;i++){
                float candleDif = (float) i/(object.numberCandles + 1);
                drawCandle(canvas, cakeLeft + candleDif*cakeWidth - candleWidth/2,cakeTop);
            }
        }

        if (this.object.x != -1 && this.object.y != -1){
            String string = String.format("[%s, %s]",object.x,object.y);
            red.setTextSize(60);
            red.setColor(Color.RED);
            canvas.drawText(string, 20, 1200, red);

        }


        Paint g = new Paint();
        Paint r = new Paint();
        g.setColor(Color.GREEN);
        r.setColor(Color.RED);


        if (this.object.x != -1 && this.object.y != -1){
            //bottom left
            canvas.drawRect(object.x - 150, object.y + 100, object.x + 100, object.y, r);
            Log.d("click", "drawRect passed");
            //bottom right
            canvas.drawRect(object.x, object.y + 100, object.x + 150, object.y, g);
            //top left
            canvas.drawRect(object.x - 150, object.y, object.x, object.y - 100, g);
            //top right
            canvas.drawRect(object.x , object.y, object.x + 150, object.y - 100, r);
        }


        //invalidate();
        //invalidate();

    }//onDraw
    public CakeModel getter(){
        return this.object;
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
    //    if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
  //          x = event.getX();
    //        y = event.getY();
   //         clicked = true;
    //        return true;
  //      }
   //     return false;
   // }
}//class CakeView

