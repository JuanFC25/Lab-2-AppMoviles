package com.example.lab2_appmoviles;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.lab2_appmoviles.DataSource.RecordatorioPreferencesDataSource;
import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.Repository.RecordatorioRepository;
import com.example.lab2_appmoviles.utils.RecordatorioDataSource;

import java.util.List;

public class RecordatorioReciver extends BroadcastReceiver {

    public static String RECORDATORIO = "com.example.tp3.RECORDATORIO";
    final String CHANNEL_ID= "ID_CANAL";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        // Solo nos interesa la accion que hemos definido nosotros
        if(intent.getAction().equals(RECORDATORIO)){


            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Lab 2 App moviles")
                    .setContentText(intent.getStringExtra("Contenido notificacion"))
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(intent.getStringExtra("Contenido notificacion")))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context, 0,new Intent(),0));


            NotificationManagerCompat notificationManager =  NotificationManagerCompat.from(context.getApplicationContext());
            notificationManager.notify(1,builder.build());

            RecordatorioDataSource recordatorioDataSource = new RecordatorioPreferencesDataSource(context);
            RecordatorioRepository recordatorioRepository = new RecordatorioRepository(recordatorioDataSource);

            List<Recordatorio> l = recordatorioRepository.recuperarRecordatorios();

            for (int i=0 ; i<l.size(); i++){
                Log.i("Recordatorio: ", l.get(i).toString());
            }
        }
    }
}

