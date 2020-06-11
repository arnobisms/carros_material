package com.example.carros_material;


import java.util.ArrayList;

public class Datos {

    private static ArrayList<Carro> carros = new ArrayList();

    public static void guardar(Carro c){
        carros.add(c);
    }

    public static ArrayList<Carro> obtener(){
        return carros;
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
