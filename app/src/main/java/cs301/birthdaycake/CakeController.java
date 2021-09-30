package cs301.birthdaycake;

import android.support.v4.view.TintableBackgroundView;
import android.util.Log;
import android.view.MotionEvent;
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
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            //reports begining of touch event as user touches down
            //this.object.wasTouched = true;
            cakemodel.x = event.getX();
            cakemodel.y = event.getY();
            float bX = event.getX();
            float bY = event.getY();
            balloon.cLeft = bX;
            balloon.cTop = bY;

            Log.d("click", "onTouch working");

            cakemodel.wasTouched = true;
            cakeview.invalidate();
            v.invalidate();

            return true;
        }

        return false;
    }

}
