package cs301.birthdaycake;

import android.support.v4.view.TintableBackgroundView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener,
        View.OnTouchListener {
    private CakeView cakeview;
    private CakeModel cakemodel;
   // add a new Balloon variable
    private Balloon balloon;


    public CakeController(CakeView object){
        cakeview = object;
        cakemodel = cakeview.getter();
        // add a new call to constructor for the balloon
        this.balloon = new Balloon(100,100);

    }

    public void onClick(View view) {
        Log.d("hello", "test");
        switch(view.getId()) {
            case R.id.blowOut:
            cakemodel.isLit = !cakemodel.isLit;
            break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    Log.d("button", "clicked");
        cakemodel.candles = isChecked;
    cakeview.invalidate();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        cakemodel.numberCandles = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            // set the was touched instance to true
            balloon.wasTouched = true;

            // this is reporting the beginning of a touch event as the user touches down
            float x = motionEvent.getX();
            float y = motionEvent.getY();

            // instantiate the new coordinates for drawing
            balloon.cLeft = x;
            balloon.cTop = y;
            //balloon.cRight = x + ( balloon.radius ) * 2;
            //balloon.cBottom = y + ( balloon.radius ) * 2;

            // need to invalidate after
            cakeview.invalidate();

            // return true since handled event
            return true;
        }
        return false;
    }
}
