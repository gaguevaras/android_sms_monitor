package com.platanolabs.expensetracker;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by gustavoguevara on 7/28/17.
 */

public class SmsReceiverService extends IntentService {

    public SmsReceiverService() {
        super("SmsReceiverService");
    }

    private SmsListener mSMSreceiver;
    private IntentFilter mIntentFilter;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //SMS event receiver
        mSMSreceiver = new SmsListener();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mSMSreceiver, mIntentFilter);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        // Unregister the SMS receiver
        unregisterReceiver(mSMSreceiver);
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();

        // Do work here, based on the contents of dataString

    }
}