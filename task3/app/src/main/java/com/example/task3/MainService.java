package com.example.task3;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

public class MainService extends Service {

    public class MainServiceBinder extends Binder {
        public MainService getService() {
            return MainService.this; }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MainServiceBinder();
    }

    @SuppressLint("SetTextI18n")
    public void resultNOD(TextView textView, Integer firstNumber, Integer secondNumber) {
        textView.setText(Integer.toString(findNOD(firstNumber, secondNumber)));
    }

    @SuppressLint("SetTextI18n")
    public void resultNOK(TextView textView, Integer firstNumber, Integer secondNumber) {
        textView.setText(Integer.toString(findNOK(firstNumber, secondNumber)));
    }

    @SuppressLint("SetTextI18n")
    public void resultMax(TextView textView, Integer firstNumber, Integer secondNumber) {
        textView.setText(Integer.toString(findMax(firstNumber, secondNumber)));
    }

    @SuppressLint("SetTextI18n")
    public void resultMin(TextView textView, Integer firstNumber, Integer secondNumber) {
        textView.setText(Integer.toString(findMin(firstNumber, secondNumber)));
    }

    public static int findNOD(int a, int b) {
        if(a*b == 0) return 0;
        while (a != b) {
            if (a > b) a -= b;
            else b -= a;
        }
        return a;
    }

    public static int findNOK(int a, int b) {
        if(a*b == 0) return 0;
        return (a*b)/findNOD(a,b);
    }

    public static int findMax(int a, int b) {
        return Math.max(a, b);
    }

    public static int findMin(int a, int b) {
        return Math.min(a, b);
    }

}