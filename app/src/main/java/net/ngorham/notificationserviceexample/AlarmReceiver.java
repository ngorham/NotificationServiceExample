package net.ngorham.notificationserviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Set variables for notification service
        int notificationID = intent.getIntExtra("notificationID", 0);
        String notificationTitle = intent.getStringExtra("notificationTitle");
        String notificationText = intent.getStringExtra("notificationText");
        //Create intent
        Intent notificationIntent = new Intent(context, NotificationService.class);
        notificationIntent.putExtra("notificationID", notificationID);
        notificationIntent.putExtra("notificationTitle", notificationTitle);
        notificationIntent.putExtra("notificationText", notificationText);
        context.startService(notificationIntent);
    }
}
