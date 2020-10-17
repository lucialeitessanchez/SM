package ar.com.lls.sendmeal.model;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    @PrimaryKey ( autoGenerate  =  true )
    Long id;

    public static List<Plato> listaPlatos = new ArrayList<>();
    private Double precioTotal;

    //Getters
    public static List<Plato> getListaPlatos() { return listaPlatos; }
    public Double getPrecioTotal() { return precioTotal; }

    //Setters
    public static void setListaPlatos(List<Plato> listaPlatos) { Pedido.listaPlatos = listaPlatos; }
    public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }

    //constructs
    public Pedido(List<Plato> listaPlatos, Double precioTotal){
        this.listaPlatos = listaPlatos;
        this.precioTotal = precioTotal;
    }

    public Pedido(Long id, List<Plato> listaPlatos, Double precioTotal){
        this.id = id;
        this.listaPlatos = listaPlatos;
        this.precioTotal = precioTotal;
    }


}
