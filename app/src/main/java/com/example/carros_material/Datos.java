package com.example.carros_material;


import java.util.ArrayList;

public class Datos {

    public static ArrayList<Carro> carros = new ArrayList<>();

    public static void guardar(Carro c){
        carros.add(c);
    }

    public static ArrayList<Carro> obtener(){
        return carros;
    }

}
