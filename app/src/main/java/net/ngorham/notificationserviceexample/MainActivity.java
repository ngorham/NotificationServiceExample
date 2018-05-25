package net.ngorham.notificationserviceexample;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {
    //Private variables
    private String notificationTitle = "Notification Title";
    private String notificationText = "This is the message that appears in the notification";
    private int notificationID = 1;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingAlarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        calendar = Calendar.getInstance();
    }

    public void setAlarm(View view){
        //Set time for alarm
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + 1);
        calendar.set(Calendar.SECOND, 0);

        //Create intent for alarm
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmIntent.putExtra("notificationID", notificationID);
        alarmIntent.putExtra("notificationTitle", notificationTitle);
        alarmIntent.putExtra("notificationText", notificationText);
        pendingAlarmIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Set alarm
        //alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingAlarmIntent);
        //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingAlarmIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60, pendingAlarmIntent);
    }

    public void cancelAlarm(View view){
        if(pendingAlarmIntent != null){
            alarmManager.cancel(pendingAlarmIntent);
            Toast.makeText(this,"Alarm canceled", Toast.LENGTH_SHORT).show();
        } else {
            Intent alarmIntent = new Intent(this, AlarmReceiver.class);
            pendingAlarmIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingAlarmIntent);
            Toast.makeText(this,"Create equivalent pendingIntent, Alarm canceled", Toast.LENGTH_SHORT).show();
        }
    }
}
