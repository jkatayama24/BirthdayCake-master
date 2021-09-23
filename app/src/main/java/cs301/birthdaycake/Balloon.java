package cs301.birthdaycake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Balloon
{
    /* First add the information the class needs to know */
    // center x, center y
    private float cLeft, cTop, cRight, cBottom;
    // color of balloon
    private final Paint color;

    // initialize the Balloon constructor
    // not letting user pick color, just need to know where tapped
    public Balloon(float x, float y, float r, float b)
    {
        // initialize the member variables
        this.cLeft = x;
        this.cTop = y;
        this.cRight = r;
        this.cBottom = b;
        color = new Paint();
        color.setColor(Color.BLUE);
    }
    // create public method that anyone who has a reference to the object can access
    public void draw(Canvas canvas)
    {
        // draw the oval based on the parameters passed in (should be center of where touched)
        canvas.drawOval(this.cLeft, this.cTop, this.cRight, this.cBottom, color);
    }
}
