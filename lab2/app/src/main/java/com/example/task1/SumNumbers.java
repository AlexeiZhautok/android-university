package com.example.task1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SumNumbers extends AppCompatActivity {

    static final String SUM_KEY = "sum";

    private TextView oldNumber;
    private Button buttonSum;
    private EditText inputAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String firstNumber = intent.getStringExtra(MainActivity.NUM_KEY);

        buttonSum = (Button)findViewById(R.id.buttonSum);
        oldNumber = (TextView)findViewById(R.id.outputNumber);
        inputAnswer = (EditText)findViewById(R.id.inputNumber);

        oldNumber.setText(firstNumber);

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                int sum = Integer.parseInt(oldNumber.getText().toString()) + Integer.parseInt(inputAnswer.getText().toString());
                returnIntent.putExtra("num", Integer.toString(sum));
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}