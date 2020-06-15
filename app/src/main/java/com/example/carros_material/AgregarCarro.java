package com.example.carros_material;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Random;

public class AgregarCarro extends AppCompatActivity {

    private EditText placa, color, marca, modelo;
    private Spinner combo_motores;
    private String[] motores;
    private ArrayAdapter<String> adapter;
    private ArrayList<Integer> fotos;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;


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

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("Carros");



    }


    public void guardar(View v){
        String plac, col, marc, model, mot = "", id;
        int foto, op;
        Carro carro;

        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            id = Datos.getId();
            plac = placa.getText().toString();
            col = color.getText().toString();
            marc = marca.getText().toString();
            model = modelo.getText().toString();
            op = combo_motores.getSelectedItemPosition();
            foto = foto_aleatoria();


            if (op == 0) {
                mot = "Gasolina";
            } else {
                if (op == 1) {
                    mot = "Diesel";
                } else {
                    if (op == 2) {
                        mot = "Gas";
                    }
                }
            }


            carro = new Carro(plac, col, marc, mot, model, foto, id);


                if (validar()) {

                    if(carro.buscar()==0) {
                        carro.guardar();
                        subir_foto(id, foto);
                        limpiar();
                        imp.hideSoftInputFromWindow(placa.getWindowToken(), 0);
                        Snackbar.make(v, getString(R.string.mensaje_guardar), Snackbar.LENGTH_LONG).show();
                    }
                    else{
                        placa.setError("Carro ya existe con esa placa");
                        placa.requestFocus();
                    }
                }

    }

    public boolean validar(){
        String pla;


        int año_modelo;
        if(placa.getText().toString().isEmpty()){
            placa.setError(getString(R.string.mensaje_error_placa));
            placa.requestFocus();
            return false;
        }else{
            if(color.getText().toString().isEmpty()){
                color.setError(getString(R.string.mensaje_error_color));
                color.requestFocus();
                return false;
            }else{
                if(marca.getText().toString().isEmpty()){
                    marca.setError(getString(R.string.mensaje_error_marca));
                    marca.requestFocus();
                    return false;
                }else{
                    if(modelo.getText().toString().isEmpty()){
                        modelo.setError(getString(R.string.mensaje_error_modelo));
                        modelo.requestFocus();
                        return false;
                    }
                }
            }

        }


        return true;
    }

    public void subir_foto(String id, int foto){
        StorageReference child = storageReference.child(id);
        Uri uri = Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask = child.putFile(uri);
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
