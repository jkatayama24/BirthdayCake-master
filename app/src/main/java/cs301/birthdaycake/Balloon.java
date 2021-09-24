package cs301.birthdaycake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Balloon
{
    /* First add the information the class needs to know */
    // center x, center y
    private float cLeft, cTop, cRight, cBottom;
    // radius
    private float radius;
    // color of balloon
    private final Paint color;

    // initialize the Balloon constructor
    // not letting user pick color, just need to know where tapped
    public Balloon(float x, float y)
    {
        // initialize the member variables
        this.cLeft      = x;
        this.cTop       = x - ( this.radius ) / 2;
        this.cRight     = y;
        this.cBottom    = y - ( this.radius ) / 2;;
        this.radius = 50;
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
