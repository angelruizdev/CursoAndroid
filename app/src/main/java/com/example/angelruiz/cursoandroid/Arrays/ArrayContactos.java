package com.example.angelruiz.cursoandroid.Arrays;
//creamos nuestro array para llenar nuestro ArrayList
public class ArrayContactos {
 private String nombre;
 private String telefono;
 private String email;

 //creamos constructor para validar que existe el objeto, con los 3 atributos.
    public ArrayContactos(String nombre, String telefono, String email){
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
//creamos sus metodos geter y seter de cada atributo
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
