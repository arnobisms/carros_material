package com.example.carros_material;



public class Carro {
    private String placa;
    private String color;
    private String marca;
    private String motor;
    private int modelo;
    private int foto;

    public Carro(String placa, String color, String marca, String motor, int modelo, int foto){
        this.placa = placa;
        this.color = color;
        this.marca = marca;
        this.motor = motor;
        this.modelo = modelo;
        this.foto = foto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void guardar(){
        Datos.guardar(this);
    }
}
