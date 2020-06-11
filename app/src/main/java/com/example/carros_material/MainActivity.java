package com.example.carros_material;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorCarro.OnCarroClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView lstCarros;
        ArrayList<Carro> carros;
        LinearLayoutManager llm;
        FloatingActionButton fab;
        AdaptadorCarro adapter;

        lstCarros = findViewById(R.id.lstCarros);
        carros = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorCarro(carros, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCarros.setLayoutManager(llm);
        lstCarros.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);

    }

    public void agregar(View v){
        Intent i;
        i = new Intent(MainActivity.this, AgregarCarro.class);
        startActivity(i);

    }


    @Override
    public void onCarroClick(Carro c) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("placa", c.getPlaca());
        bundle.putString("color", c.getColor());
        bundle.putString("marca", c.getMarca());
        bundle.putString("motor", c.getMotor());
        bundle.putString("modelo", c.getModelo());
        bundle.putInt("foto", c.getFoto());

        intent = new Intent(MainActivity.this, DetalleCarro.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);

    }
}
