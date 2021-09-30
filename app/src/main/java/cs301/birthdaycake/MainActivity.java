package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        CakeView t =(CakeView) findViewById(R.id.cakeview);
        CakeController controller = new CakeController(t);
        Button blowOut = (Button) findViewById(R.id.blowOut);
        blowOut.setOnClickListener(controller);

        Switch candles = (Switch) findViewById(R.id.Candles);
        candles.setOnCheckedChangeListener(controller);

        SeekBar numcandles = (SeekBar) findViewById(R.id.numCandles);
        numcandles.setOnSeekBarChangeListener(controller);
        CakeView cakeView = (CakeView) findViewById(R.id.cakeview);
        cakeView.setOnTouchListener(t);
    }
    public void goodbye(View button){
        Log.i("button","Goodbye");
    }
}
