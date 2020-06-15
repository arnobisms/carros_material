package com.example.carros_material;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
        final ArrayList<Carro> carros;
        LinearLayoutManager llm;
        FloatingActionButton fab;
        final AdaptadorCarro adapter;
        DatabaseReference databaseReference;
        String db = "Carros";

        lstCarros = findViewById(R.id.lstCarros);
        carros = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorCarro(carros, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstCarros.setLayoutManager(llm);
        lstCarros.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                carros.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:  dataSnapshot.getChildren() ){
                        Carro c = snapshot.getValue(Carro.class);
                        carros.add(c);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setCarros(carros);

                Log.i("Tamaño de carros: "+carros.size(),"Tamaño");
                Log.i("Obtener "+Datos.obtener().size(),"Obtener");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        bundle.putString("id", c.getId());

        intent = new Intent(MainActivity.this, DetalleCarro.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);

    }
}
