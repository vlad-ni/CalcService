package com.vladimir.calcservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;

public class CalcService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Integer firstNumber = extras.getInt("firstNumber");
                Integer secondNumber = extras.getInt("secondNumber");
                String operation = extras.getString("operation");

                Pair<Integer, String> result = calculate(firstNumber, secondNumber, operation, context);

                Bundle msg = new Bundle();
                msg.putString("Message", result.second);

                setResult(Activity.RESULT_OK, result.first.toString(), msg);
            }
        }
    }

    public Pair<Integer, String> calculate(Integer firstNumber, Integer secondNumber, String operation, Context context) {
        Integer calc = 0;
        String message = "";

        switch (operation) {
            case "+":
                calc = firstNumber + secondNumber;
                break;
            case "-":
                calc = firstNumber - secondNumber;
                break;
            case "*":
                calc = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    message = context.getResources().getString(R.string.DIV_ERROR);;
                } else {
                    calc = firstNumber / secondNumber;
                }
                break;
        }
        return new Pair<>(calc, message);
    }
}
