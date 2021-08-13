package com.example.clicknotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.databinding.DataBindingUtil;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.clicknotification.databinding.ActivityMainBinding;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        getSupportActionBar().setTitle("MainActivity");

        binding.btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPushNotification();
            }
        });
        binding.btnGoToListProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sendPushNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent resultIntent = new Intent(this,DetailActivity.class);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntentWithParentStack(resultIntent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(getNotificationId(),
                PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this,MyApplication.CHANNEL_ID)
                .setContentTitle("TITLE_PUSH_NOTIFICATION")
                .setContentText("CONTENT_PUSH_NOTIFICATION")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setLargeIcon(bitmap)
                .setSound(uri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setColor(getResources().getColor(R.color.teal_700))
                .build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(getNotificationId(),notification);

    }
    private int getNotificationId(){
        return (int) new Date().getTime();
    }
}