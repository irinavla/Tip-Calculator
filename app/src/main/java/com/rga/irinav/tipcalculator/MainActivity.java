package com.rga.irinav.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText enteredAmount;
    private SeekBar seekBar;
    private Button calculateTip;
    private TextView totalResult;
    private TextView tipPercentage;
    private TextView totalBillAmount;

    private int seekBarPercentage;
    private float enteredBillFloat;

    public static final String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmount = (EditText) findViewById(R.id.billAmountId);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        calculateTip = (Button) findViewById(R.id.calculateButton);
        totalResult = (TextView) findViewById(R.id.resultId);
        tipPercentage = (TextView) findViewById(R.id.tipPercentage);
        totalBillAmount = (TextView) findViewById(R.id.totalBillAmount);


        Toast.makeText(getApplication(), "Hello", Toast.LENGTH_LONG).show();


        calculateTip.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage.setText(String.valueOf(seekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();
            }
        });

    }

    @Override
    public void onClick(View v) {
        calculate();

    }

    public void calculate() {
        float result = 0.0f;
        float totalAmount = 0.0f;

        if ( !enteredAmount.getText().toString().equals("") ) {
            enteredBillFloat = Float.parseFloat(enteredAmount.getText().toString());

            result = enteredBillFloat * seekBarPercentage / 100;
            totalAmount = enteredBillFloat + result;

            totalResult.setText("Your tip will be $" + String.valueOf(result));
            totalBillAmount.setText("$" + String.valueOf(totalAmount));

        } else {
            Toast.makeText(MainActivity.this, "Please enter a bill amount", Toast.LENGTH_LONG).show();
        }


    }
}
