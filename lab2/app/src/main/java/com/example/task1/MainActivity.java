package com.example.task1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String NUM_KEY = "num";
    private static final int REQUEST_ANSWER = 10;

    private EditText writeNumber;
    private Button buttonNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeNumber = (EditText)findViewById(R.id.inputNumber);
        buttonNum = (Button)findViewById(R.id.buttonSum);

        buttonNum.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SumNumbers.class);
                TextView input = (TextView)findViewById(R.id.inputNumber);
                int firstSum = Integer.parseInt(writeNumber.getText().toString()) + Integer.parseInt(input.getText().toString());
                intent.putExtra(NUM_KEY, Integer.toString(firstSum));
                startActivityForResult(intent, REQUEST_ANSWER);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ANSWER) {
            if (resultCode == RESULT_OK){
                String newNumber = data.getStringExtra(NUM_KEY);
                final TextView textView = (TextView) findViewById(R.id.outputNumber);
                textView.setText(newNumber);
            }
        }
    }


}
