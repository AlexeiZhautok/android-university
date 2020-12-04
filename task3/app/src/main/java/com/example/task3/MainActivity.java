package com.example.task3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String FIRST_NUMBERS_KEY = "fnumber";
    static final String SECOND_NUMBERS_KEY = "snumber";
    static final String BUTTON_NAME = "button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonNOK = findViewById(R.id.buttonNOK);
        final Button buttonNOD = findViewById(R.id.buttonNOD);


        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("MainActivity"));

        View.OnClickListener oclBtn = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final EditText inputNumber = findViewById(R.id.inputNumber);
                final EditText inputSecondNumber = findViewById(R.id.inputSecondNumber);
                int number = Integer.valueOf(inputNumber.getText().toString());
                int secondNumber = Integer.valueOf(inputSecondNumber.getText().toString());
                Intent intent = new Intent(getApplicationContext(), MainIntentService.class);
                intent.putExtra(FIRST_NUMBERS_KEY, number);
                intent.putExtra(SECOND_NUMBERS_KEY, secondNumber);
                int buttonName = 0;
                switch (v.getId()) {
                    case R.id.buttonNOK:
                        buttonName = R.id.buttonNOK;
                        break;
                    case R.id.buttonNOD:
                        buttonName = R.id.buttonNOD;
                        break;
                }
                intent.putExtra(BUTTON_NAME, buttonName);
                startService(intent);
                buttonNOK.setEnabled(false);
                buttonNOD.setEnabled(false);
            }
        };

        buttonNOK.setOnClickListener(oclBtn);
        buttonNOD.setOnClickListener(oclBtn);

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            int result = intent.getIntExtra(MainIntentService.RESULT_KEY, 0);

            final TextView receiveResult = findViewById(R.id.foundDigit);
            receiveResult.setText(String.valueOf(result));
            final Button buttonNOK = findViewById(R.id.buttonNOK);
            final Button buttonNOD = findViewById(R.id.buttonNOD);
            buttonNOK.setEnabled(true);
            buttonNOD.setEnabled(true);
        }
    };
}