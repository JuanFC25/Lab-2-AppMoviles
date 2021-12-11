package com.example.lab2_appmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {


    final String DATE_PICKER= "DATE_PICKER";
    final String TIME_PICKER= "TIME_PICKER";
    final String CHANNEL_ID= "ID_CANAL";

    TextView fecha;
    TextView hora;
    Button agregar;
    TextInputEditText recordatorio;
    LinearLayout linearLayout;
    ScrollView scrollView;
    ConstraintLayout constraintLayout;

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fecha = (TextView) findViewById(R.id.textViewFecha);
        agregar = (Button) findViewById(R.id.botonAgregar);
        hora = (TextView)  findViewById(R.id.textViewHora);
        recordatorio= (TextInputEditText) findViewById(R.id.textInput);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        createNotificationChannel();

        MaterialDatePicker.Builder builder =MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Seleccione una Fecha");
        MaterialDatePicker materialDatePicker = builder.build();

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordatorio.clearFocus();
                materialDatePicker.show(getSupportFragmentManager(),DATE_PICKER);
            }
        });


        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                fecha.setText("Fecha: " + materialDatePicker.getHeaderText());

               // fechaSeleccionada.set(Calendar.YEAR,materialDatePicker.getHeaderText());
            }
        });


        MaterialTimePicker.Builder builderTime =   new MaterialTimePicker.Builder();
        builderTime.setTitleText("Ingrese la hora");

        MaterialTimePicker pickerTime = builderTime.build();

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordatorio.clearFocus();
                pickerTime.show(getSupportFragmentManager(),TIME_PICKER);
            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordatorio.clearFocus();
            }
        });

        scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordatorio.clearFocus();
            }
        });

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordatorio.clearFocus();
            }
        });

        pickerTime.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer hour = pickerTime.getHour();
                Integer min = pickerTime.getMinute();


                String hourString;
                String minString;

                if(hour<10) hourString = "0"+ hour.toString(); else hourString = hour.toString();
                if(min<10) minString = "0"+ min.toString(); else minString = min.toString();

                hora.setText("Hora: " + hourString + ":" + minString);
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recordatorio.getText().length() == 0){
                    Toast.makeText(MainActivity.this,"El recordatorio esta vacio.", Toast.LENGTH_LONG).show();
                }else if(fecha.getText().equals("Ingrese la Fecha")){
                    Toast.makeText(MainActivity.this,"Seleccione una fecha.", Toast.LENGTH_LONG).show();
                }else if(hora.getText().equals("Ingrese la Hora")){
                    Toast.makeText(MainActivity.this,"Seleccione una hora.", Toast.LENGTH_LONG).show();
                }else{
                    final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    Long fechaselec = materialDatePicker.todayInUtcMilliseconds();
                    Integer horaSelec = pickerTime.getHour();
                    Integer minSelec = pickerTime.getMinute();

                    Log.i("FECHA EN MILISEG", fechaselec.toString());
                    Log.i("Hora", horaSelec.toString());
                    Log.i("min", minSelec.toString());

                    Long horaMilisec = new Long(horaSelec)  * 3600000;
                    Long minMilisec = new  Long(minSelec) * 60000;
                    Long total = fechaselec+horaMilisec+minMilisec + 10800000;
                    Log.i("tiempo total", total.toString());

                    Intent alarmIntent = new Intent(MainActivity.this, RecordatorioReciver.class);
                    alarmIntent.setAction("com.example.tp3.RECORDATORIO");
                    alarmIntent.putExtra("Contenido notificacion", recordatorio.getText().toString());
                    //alarmIntent.putExtra("RECORDATORIO","com.example.tp3.RECORDATORIO");
                    PendingIntent pendingIntent;
                    pendingIntent = PendingIntent.getBroadcast(MainActivity.this,1,alarmIntent,PendingIntent.FLAG_ONE_SHOT);
                    alarm.set(AlarmManager.RTC_WAKEUP, total, pendingIntent);
                    Log.i("noti", recordatorio.getText().toString());
                    fecha.setText(R.string.Fecha);
                    hora.setText(R.string.Hora);
                    recordatorio.setText("");
                    Toast.makeText(MainActivity.this, "El recordatorio se creo correctamente.", Toast.LENGTH_LONG).show();

                }
            }
        });


        //Inicio ejercicio 3

        // Recuperando el alarm manager
        //final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);




         // Seteo de la alarma
        //alarm.set(AlarmManager.RTC_WAKEUP, tiempoEnMillis, pendingIntentConActionParaMiBroadcastReceiver);

    }

}