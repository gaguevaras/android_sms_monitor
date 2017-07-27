package com.platanolabs.expensetracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

import java.io.FileOutputStream;

public class SmsListener extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
    GoogleAccountCredential mCredential;

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);

                    if (message.contains("Bancolombia")) {

                        String FILENAME =  "notification_file";
                        FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        fos.write(message.getBytes());
                        fos.close();

                        // Show Alert
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(
                            context,
                            "senderNum: " + senderNum + ", message: " + message,
                            duration
                        );
                        toast.show();
                        Intent spreadsheetIntent = new Intent();
                        spreadsheetIntent.putExtra("message", message);
                        spreadsheetIntent.putExtra("sender", senderNum);
                        spreadsheetIntent.setClassName("com.platanolabs.expensetracker", "com.platanolabs.expensetracker.SpreadsheetActivity");
                        spreadsheetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(spreadsheetIntent);
                    }
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }


}