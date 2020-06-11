package com.example.carros_material;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class DetalleCarro extends AppCompatActivity {
    private Carro c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView foto;
        TextView placa, color, marca, motor, modelo;
        Bundle bundle;
        Intent intent;
        String id, plac, col, marc, mot, model;
        int fot;
        StorageReference storageReference;

        foto = findViewById(R.id.imgFotoDetalle);
        placa = findViewById(R.id.lblPlacaDetalle);
        color = findViewById(R.id.lblColorDetalle);
        marca = findViewById(R.id.lblMarcaDetalle);
        motor = findViewById(R.id.lblMotorDetalle);
        modelo = findViewById(R.id.lblModeloDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        id = bundle.getString("id");
        plac = bundle.getString("placa");
        col = bundle.getString("color");
        marc = bundle.getString("marca");
        mot = bundle.getString("motor");
        model = bundle.getString("modelo");


        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });
        placa.setText(plac);
        color.setText(col);
        marca.setText(marc);
        motor.setText(mot);
        modelo.setText(model);

        c = new Carro(plac, col, marc, mot, model, 0, id);


    }

    public void OnBackPressed(){
        finish();
        Intent i = new Intent(DetalleCarro.this, MainActivity.class);
        startActivity(i);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.eliminar_carro);
        builder.setMessage(R.string.mensaje_eliminar_persona);
        positivo = getString(R.string.opcion_si);
        negativo = getString(R.string.opcion_no);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                c.eliminar();
                OnBackPressed();

            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
