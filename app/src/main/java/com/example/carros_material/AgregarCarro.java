package com.example.carros_material;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCarro extends AppCompatActivity {

    private EditText placa, color, marca, modelo;
    private Spinner combo_motores;
    private String[] motores;
    private ArrayAdapter<String> adapter;
    private ArrayList<Integer> fotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_carro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placa = findViewById(R.id.txtPlaca);
        color = findViewById(R.id.txtColor);
        marca = findViewById(R.id.txtMarca);
        modelo = findViewById(R.id.txtModelo);
        combo_motores = findViewById(R.id.comboMotores);
        motores = getResources().getStringArray(R.array.motores);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, motores);
        combo_motores.setAdapter(adapter);

        fotos = new ArrayList<>();
        fotos.add(R.drawable.img1);
        fotos.add(R.drawable.img2);
        fotos.add(R.drawable.img3);
    }

    public void guardar(View v){
        String plac, col, marc, model, mot = "", id;
        int foto, op;
        Carro carro;
        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        plac = placa.getText().toString();
        col = color.getText().toString();
        marc = marca.getText().toString();
        model = modelo.getText().toString();
        op = combo_motores.getSelectedItemPosition();
        foto = foto_aleatoria();
        id = Datos.getId();

        if(op == 0){
            mot = "Gasolina";
        }else {
            if (op == 1) {
                mot = "Diesel";
            } else{
                 if (op == 2) {
                    mot = "Gas";
                 }
            }
        }


        carro = new Carro(plac, col, marc, mot, model, foto, id);
        carro.guardar();
        limpiar();
        imp.hideSoftInputFromWindow(placa.getWindowToken(),0);
        Snackbar.make(v,getString(R.string.mensaje_guardar), Snackbar.LENGTH_LONG).show();
    }

    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }

    public void limpiar(View v){
        limpiar();
    }
    public void limpiar(){
        placa.setText("");
        marca.setText("");
        color.setText("");
        modelo.setText("");
        combo_motores.setSelection(0);
        placa.requestFocus();

    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarCarro.this, MainActivity.class);
        startActivity(i);
    }

}
