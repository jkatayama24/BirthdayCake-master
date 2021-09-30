package cs301.birthdaycake;

import android.graphics.Color;
import android.graphics.Paint;

public class Balloon
{
    /* First add the information the class needs to know */
    // center x, center y
    public float cLeft, cTop, cRight, cBottom;
    // radius
    public final float radius = 250;
    // color of balloon
    public final Paint color;
    public boolean wasTouched = false;


    // initialize the Balloon constructor
    public Balloon(){
        this.cLeft = 100;
        this.cTop = 50;
        this.cRight = 200;
        this.cBottom = 150;
        color = new Paint();
        color.setColor(Color.BLUE);
        }
    // not letting user pick color, just need to know where tapped
    public Balloon(float x, float y)
    {
        // initialize the member variables
        this.cLeft      = x;
        this.cTop       = y;
        this.cRight     = x + ( this.radius ) * 2;
        this.cBottom    = y + ( this.radius ) * 2;
        color = new Paint();
        color.setColor(Color.BLUE);
    }
/*    // create public method that anyone who has a reference to the object can access
    public void draw(Canvas canvas)
    {
        // draw the oval based on the parameters passed in (should be center of where touched)
        canvas.drawOval(this.cLeft, this.cTop, this.cRight, this.cBottom, color);
    }*/
}
