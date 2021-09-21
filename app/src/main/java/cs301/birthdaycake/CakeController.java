package cs301.birthdaycake;

import android.support.v4.view.TintableBackgroundView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private CakeView cakeview;
    private CakeModel cakemodel;


    public CakeController(CakeView object){
        cakeview = object;
        cakemodel = cakeview.getter();
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
}
