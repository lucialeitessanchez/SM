package ar.com.lls.sendmeal.model;

import androidx.appcompat.view.menu.MenuBuilder;

import java.util.ArrayList;
import java.util.List;

public class Plato {

    private String titulo;
    private String descripcion;
    private Double precio;
    private Integer calorias;
    public static List<Plato> listaPlatos = new ArrayList<>();

    //construct
    public Plato(String titulo, String descripcion, Double precio, Integer calorias) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
    }

    public Plato(){

    }

    public void cargarDatosPlato(String titulo,String descripcion, Double precio, Integer calorias) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.calorias = calorias;
        }

    //Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public List<Plato> getListaPlatos() {
        return listaPlatos;
    }

    //Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public void setListaPlatos(List<Plato> listaPlatos) {
        this.listaPlatos = listaPlatos;
    }

    public void guardarPlato(Plato unPlato){
        this.listaPlatos.add(unPlato);
    }

    //el relleno por si encuentra la lista vacia
    public void inicializarPlatos(){
        if(listaPlatos.isEmpty()){
            listaPlatos.add(new Plato("Pollo", "Pollo a la parrilla con papas", 230.0, 200));
            listaPlatos.add(new Plato("Sushi", "8 rolls de sushi clàsico", 300.50, 480));
            listaPlatos.add(new Plato("Canelones", "Canelones de verdura", 500.0, 393));
            listaPlatos.add(new Plato("Asado", "tira de asado a la parrilla", 900.0, 471));
        }
    }
}
