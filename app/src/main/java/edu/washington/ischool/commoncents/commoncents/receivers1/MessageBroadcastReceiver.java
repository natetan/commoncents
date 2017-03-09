package edu.washington.ischool.commoncents.commoncents.receivers1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by iguest on 3/8/17.
 */

public class MessageBroadcastReceiver extends BroadcastReceiver {

    private String phoneNumber;
    private String message;

    public void onReceive(Context context, Intent intent) {
        SmsManager smsManager = SmsManager.getDefault();

        phoneNumber = intent.getStringExtra("Phone");
        message = intent.getStringExtra("Message");

        Log.i("TAG", "Text sent!");
        Log.v("PHONE NUMBERS", phoneNumber.toString());
        Toast.makeText(context, phoneNumber + " " + message, Toast.LENGTH_SHORT).show();

//        for (int i = 0; i < phoneNumber.length(); i ++) {
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
//
//        }

        smsManager.sendTextMessage("2063837211", null, message, null, null);
    }
}
