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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_numbers);

        buttonSum = (Button)findViewById(R.id.buttonSum);

        oldNumber = (TextView)findViewById(R.id.oldNumber);
        String firstNumber = getIntent().getStringExtra(MainActivity.NUM_KEY);
        oldNumber.setText(firstNumber);

        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputAnswer = (EditText)findViewById(R.id.addNumber);
                Intent returnIntent = new Intent();
                int summa=Integer.valueOf(oldNumber.getText().toString()) + Integer.valueOf(inputAnswer.getText().toString());
                returnIntent.putExtra("num", Integer.toString(summa));
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}