package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainService mainService;
    private boolean isMainServiceBound = false;

    private final ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainService.MainServiceBinder binder = (MainService.MainServiceBinder) iBinder;
            mainService = binder.getService();
            isMainServiceBound = true; }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isMainServiceBound = false; }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MainService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isMainServiceBound) {
            unbindService(serviceConnection);
            isMainServiceBound = false; }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EditText firstEditText = findViewById(R.id.firstNumber);
        EditText secondEditText = findViewById(R.id.secondNumber);
        int firstNumber;
        int secondNumber;

        if (firstEditText.getText().toString().equals("")) firstNumber = 0;
        else firstNumber = Integer.parseInt(firstEditText.getText().toString());
        if (secondEditText.getText().toString().equals("")) secondNumber = 0;
        else secondNumber = Integer.parseInt(secondEditText.getText().toString());

        int id = item.getItemId();
        switch(id){
            case R.id.nod_settings:
                mainService.resultNOD((TextView) findViewById(R.id.resultView), firstNumber, secondNumber);
                break;
            case R.id.nok_settings:
                mainService.resultNOK((TextView) findViewById(R.id.resultView), firstNumber, secondNumber);
                break;
            case R.id.max_settings:
                mainService.resultMax((TextView) findViewById(R.id.resultView), firstNumber, secondNumber);
                break;
            case R.id.min_settings:
                mainService.resultMin((TextView) findViewById(R.id.resultView), firstNumber, secondNumber);
                break;  }

        return super.onOptionsItemSelected(item);
    }

}