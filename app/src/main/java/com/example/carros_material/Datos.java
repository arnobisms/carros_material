package com.example.carros_material;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {
    private static String db = "Carros";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private static ArrayList<Carro> carros = new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Carro c){
        databaseReference.child(db).child(c.getId()).setValue(c);
    }

    public static ArrayList<Carro> obtener(){
        return carros;
    }

    public static void setCarros(ArrayList<Carro> cars){
        carros = cars;
    }

    public static void eliminar(Carro c){
        databaseReference.child(db).child(c.getId()).removeValue();
        storageReference.child(c.getId()).delete();
     }

     public static int buscar(Carro c){
        int busqueda = 0;
        ArrayList<Carro> array = new ArrayList();
        array = carros;
         Log.i("Obtener: "+obtener().size(), "tamaño");
         Log.i("Tamaño del array "+array.size(), "tamaño");
         for (int i = 0; i < array.size(); i++) {
             if(array.get(i).getPlaca().equals(c.getPlaca())){
                busqueda = 1;
                break;
             }
         }
       return busqueda;
     }

}
