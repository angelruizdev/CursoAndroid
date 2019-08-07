package com.example.angelruiz.cursoandroid.FireBaseNotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.example.angelruiz.cursoandroid.Activitys.WebServiceMysql;
import com.example.angelruiz.cursoandroid.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notification extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE_MESSAGE";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String token = remoteMessage.getFrom();
        if (remoteMessage.getNotification() != null){
            //Log.d(TAG, "Mensaje recibido de : "+token);
            //Log.d(TAG, "Titulo : "+remoteMessage.getNotification().getTitle());
            //Log.d(TAG, "Notificación: "+remoteMessage.getNotification().getBody());
            Log.d(TAG, "\n"+"Mensaje de :"+token+" Titulo :"+remoteMessage.getNotification().getTitle()+" Notificación :"+remoteMessage.getNotification().getBody());
            mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    public void mostrarNotificacion(String title, String body){
        Intent i = new Intent(this, WebServiceMysql.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, i, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this, "n")
                .setSmallIcon(R.drawable.email)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(sonido)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());
    }
}
