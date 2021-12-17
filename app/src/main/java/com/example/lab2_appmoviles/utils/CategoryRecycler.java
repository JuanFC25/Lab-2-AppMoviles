package com.example.lab2_appmoviles.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_appmoviles.DataSource.RecordatorioPreferencesDataSource;
import com.example.lab2_appmoviles.MainActivity;
import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.R;
import com.example.lab2_appmoviles.RecordatorioReciver;
import com.example.lab2_appmoviles.Repository.RecordatorioRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class CategoryRecycler extends AppCompatActivity {

    private static final int CODIGO_AGREGAR_RECORDATORIO=123;

    protected RecyclerView mRecyclerView;
    protected CategoryRecyclerAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected RecordatorioRepository recordatorioRepository;
    protected RecordatorioDataSource recordatorioDataSource;
    FloatingActionButton agregarRecordatorio;
    FloatingActionButton borrarRecordatorios;
    int[] color = new int[4];
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_recycler);

        recordatorioDataSource = new RecordatorioPreferencesDataSource(getApplicationContext());
        recordatorioRepository = new RecordatorioRepository(recordatorioDataSource);

        color[0] = ContextCompat.getColor(CategoryRecycler.this,R.color.gris_claro);
        color[1] = ContextCompat.getColor(CategoryRecycler.this,R.color.gris_mas_claro);
        color[2] = ContextCompat.getColor(CategoryRecycler.this,R.color.verde_claro);
        color[3] = ContextCompat.getColor(CategoryRecycler.this,R.color.verde_mas_claro);

        agregarRecordatorio = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        borrarRecordatorios = (FloatingActionButton)findViewById(R.id.floatingActionButton2);

        navigationView = findViewById(R.id.navigation_view);

        mRecyclerView = findViewById(R.id.listaRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar((Toolbar) findViewById(R.id.topAppBar));

        drawerLayout =  findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        MenuItem menuItem = navigationView.getMenu().getItem(0);
        menuItem.setChecked(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        //Toast.makeText(CategoryRecycler.this, "Selecciono Recordatorios", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item2:
                        //Toast.makeText(CategoryRecycler.this, "Selecciono Preferencias", Toast.LENGTH_LONG).show();
                        FragmentManager fragmentManager = getFragmentManager();
                        menuItem.setChecked(true);
                        Intent i = new Intent(CategoryRecycler.this,SettingsActivity.class);
                        startActivity(i);
                        Log.i("aberga ", "sigue?");
                        redibujar(color);
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });





        List<Recordatorio> recordatorios = obtenerDatos(recordatorioRepository);

        for (int i=0 ; i<recordatorios.size(); i++){
            Log.i("Recordatorio: ", recordatorios.get(i).toString());
        }
        mAdapter = new CategoryRecyclerAdapter(recordatorios,color);
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //que quiero que pase al hacer click

            }
        });
        redibujar(color);
        agregarRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CategoryRecycler.this, MainActivity.class);
                startActivityForResult(i,CODIGO_AGREGAR_RECORDATORIO);
            }
        });

        borrarRecordatorios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(CategoryRecycler.this) // NombreDeTuActividad.this, o getActivity() si es dentro de un fragmento
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(recordatorioRepository.borrarRecordatorios()){
                                    final AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                                    Intent alarmIntent = new Intent(CategoryRecycler.this, RecordatorioReciver.class);
                                    alarmIntent.setAction("com.example.tp3.RECORDATORIO");

                                    PendingIntent pendingIntent;
                                    pendingIntent = PendingIntent.getBroadcast(CategoryRecycler.this,1,alarmIntent,PendingIntent.FLAG_CANCEL_CURRENT);
                                    alarm.cancel(pendingIntent);

                                    redibujar(color);
                                    Toast.makeText(CategoryRecycler.this, "Los recordatorios se borraron correctamente.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(CategoryRecycler.this, "Ha ocurrido un problema, intente nuevamente.", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Hicieron click en el botón negativo, no confirmaron
                                // Simplemente descartamos el diálogo
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar") // El título
                        .setMessage("¿Desea eliminar los recordatorios guardados?") // El mensaje
                        .create();// No olvides llamar a Create, ¡pues eso crea el AlertDialog!
                dialogo.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODIGO_AGREGAR_RECORDATORIO){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(CategoryRecycler.this, "El recordatorio se creo correctamente.", Toast.LENGTH_LONG).show();
            }
        }
        redibujar(color);
    }


    private List<Recordatorio> obtenerDatos(RecordatorioRepository r) {
        List<Recordatorio> resultado = null;
        resultado=r.recuperarRecordatorios();
        return resultado;
    }

    private void redibujar(int color[]){
        mRecyclerView.setAdapter(null);
        mRecyclerView.setLayoutManager(null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CategoryRecyclerAdapter(obtenerDatos(recordatorioRepository),color);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }


}
