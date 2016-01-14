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
                Long firstNumber = extras.getLong("firstNumber");
                Long secondNumber = extras.getLong("secondNumber");
                String operation = extras.getString("operation");

                Pair<Long, String> result = calculate(firstNumber, secondNumber, operation, context);

                Bundle msg = new Bundle();
                msg.putString("Message", result.second);

                setResult(Activity.RESULT_OK, result.first.toString(), msg);
            }
        }
    }

    public Pair<Long, String> calculate(Long firstNumber, Long secondNumber, String operation, Context context) {
        Long calc = 0L;
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
                    message = context.getResources().getString(R.string.DIV_ERROR);
                } else {
                    calc = firstNumber / secondNumber;
                }
                break;
        }
        return new Pair<>(calc, message);
    }
}
