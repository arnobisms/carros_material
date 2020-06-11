package com.example.carros_material;



public class Carro {
    private String placa;
    private String color;
    private String marca;
    private String motor;
    private String modelo;
    private int foto;
    private String id;

    public Carro(String placa, String color, String marca, String motor, String modelo, int foto){
        this.placa = placa;
        this.color = color;
        this.marca = marca;
        this.motor = motor;
        this.modelo = modelo;
        this.foto = foto;
    }

    public Carro(String placa, String color, String marca, String motor, String modelo, int foto, String id){
        this.placa = placa;
        this.color = color;
        this.marca = marca;
        this.motor = motor;
        this.modelo = modelo;
        this.foto = foto;
        this.id = id;
    }

    public Carro(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
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

    public void eliminar(){
        Datos.eliminar(this);
    }
}
