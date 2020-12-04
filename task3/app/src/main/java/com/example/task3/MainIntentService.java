package com.example.task3;

import android.app.IntentService;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

public class MainIntentService extends IntentService {
    static final String  RESULT_KEY = "result";

    public MainIntentService() {
        super("MainIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        int numberOne = intent.getIntExtra(MainActivity.FIRST_NUMBERS_KEY, 0);
        int numberTwo = intent.getIntExtra(MainActivity.SECOND_NUMBERS_KEY, 0);
        int button = intent.getIntExtra(MainActivity.BUTTON_NAME, 0);
        int result = 0;
        switch (button) {
            case R.id.buttonNOK:
                result = findNOK(Math.abs(numberOne), Math.abs(numberTwo));
                break;
            case R.id.buttonNOD:
                result = findNOD(Math.abs(numberOne), Math.abs(numberTwo));
                break;
        }

        Intent responseIntent = new Intent("MainActivity");
        responseIntent.putExtra(RESULT_KEY, result);
        LocalBroadcastManager.getInstance(this).sendBroadcast(responseIntent);
    }

    public static int findNOD(int a, int b) {
        if(a*b == 0) return 0;
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static int findNOK(int a, int b) {
        return (a*b)/findNOD(a,b);
    }
}