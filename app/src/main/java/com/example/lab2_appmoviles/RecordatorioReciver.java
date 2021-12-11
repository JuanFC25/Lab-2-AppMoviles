package com.example.lab2_appmoviles;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RecordatorioReciver extends BroadcastReceiver {

    public static String RECORDATORIO = "com.example.tp3.RECORDATORIO";
    final String CHANNEL_ID= "ID_CANAL";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // Solo nos interesa la accion que hemos definido nosotros
        if(intent.getAction().equals(RECORDATORIO)){
            // Toast de ejemplo, acá debería armarse la notificación
            //Toast.makeText(context, "LLEGUE! " + intent.getStringExtra(TEXTO), Toast.LENGTH_LONG).show();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Lab 2 App moviles")
                    .setContentText(intent.getStringExtra("Contenido notificacion"))
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(intent.getStringExtra("Contenido notificacion")))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);

            //Log.i("notificacion", intent.getStringExtra("Contenido notificacion"));
            NotificationManagerCompat notificationManager =  NotificationManagerCompat.from(context.getApplicationContext());
            notificationManager.notify(1,builder.build());
        }
    }
}
