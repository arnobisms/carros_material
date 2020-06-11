package com.example.carros_material;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Datos {
    private static String db = "Carros";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
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

    public static void setCarros(ArrayList<Carro> carros){
        carros = carros;
    }

    public static void eliminar(Carro c){
        for (int i = 0; i < carros.size(); i++) {
            if(carros.get(i).getPlaca().equals(c.getPlaca())){
                carros.remove(i);
                break;
            }
        }
     }

}
