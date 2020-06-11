package com.example.carros_material;

import android.icu.text.Transliterator;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorCarro extends RecyclerView.Adapter<AdaptadorCarro.CarroViewHolder>{
    private ArrayList<Carro> carros;
    private OnCarroClickListener clickListener;

    public AdaptadorCarro(ArrayList<Carro> carros, OnCarroClickListener clickListener){
        this.carros = carros;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CarroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carro, parent,false);
       return new CarroViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final CarroViewHolder holder, int position) {
        final Carro c = carros.get(position);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        storageReference.child(c.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.foto);
            }
        });


        holder.placa.setText(c.getPlaca());
        holder.color.setText(c.getColor());
        holder.marca.setText(c.getMarca());
        holder.motor.setText(c.getMotor());
        holder.modelo.setText(c.getModelo());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onCarroClick(c);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    public static class CarroViewHolder extends RecyclerView.ViewHolder{

        private ImageView foto;
        private TextView placa;
        private TextView color;
        private TextView marca;
        private TextView motor;
        private TextView modelo;
        private View v;

        public  CarroViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            placa = v.findViewById(R.id.lblPlaca);
            color = v.findViewById(R.id.lblColor);
            marca = v.findViewById(R.id.lblMarca);
            motor = v.findViewById(R.id.lblMotor);
            modelo = v.findViewById(R.id.lblModelo);

        }

    }

    public interface OnCarroClickListener{
        void onCarroClick(Carro c);
    }
}
