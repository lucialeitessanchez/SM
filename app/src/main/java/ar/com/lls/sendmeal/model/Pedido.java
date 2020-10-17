package ar.com.lls.sendmeal.model;

import androidx.room.PrimaryKey;
import androidx.room.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    @PrimaryKey( autoGenerate  =  true )
    Long id;

    public static List<Plato> listaPlatos = new ArrayList<>();
    private Double precioTotal;
    private String email, direccion, altura, piso, dpto;
    private String dondeEnviamos;
    private Boolean envioDomicilio,takeAway;
    private Boolean casa, departamento;


    public ArrayList<String> listaPlatosSeleccionados = new ArrayList<>();
    public Double totalPedido;

    //Getters
    public static List<Plato> getListaPlatos() { return listaPlatos; }
    public Double getPrecioTotal() { return precioTotal; }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getAltura() {
        return altura;
    }

    public String getPiso() {
        return piso;
    }

    public String getDpto() {
        return dpto;
    }

    public String getDondeEnviamos() {
        return dondeEnviamos;
    }

    public Boolean getEnvioDomicilio() {
        return envioDomicilio;
    }

    public Boolean getTakeAway() {
        return takeAway;
    }

    public Boolean getCasa() {
        return casa;
    }

    public Boolean getDepartamento() {
        return departamento;
    }

    //Setters
    public static void setListaPlatos(List<Plato> listaPlatos) { Pedido.listaPlatos = listaPlatos; }
    public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public void setDondeEnviamos(String dondeEnviamos) {
        this.dondeEnviamos = dondeEnviamos;
    }

    public void setEnvioDomicilio(Boolean envioDomicilio) {
        this.envioDomicilio = envioDomicilio;
    }

    public void setTakeAway(Boolean takeAway) {
        this.takeAway = takeAway;
    }

    public void setCasa(Boolean casa) {
        this.casa = casa;
    }

    public void setDepartamento(Boolean departamento) {
        this.departamento = departamento;
    }

    //constructs
    public Pedido(List<Plato> listaPlatos, Double precioTotal, String email, String direccion, String altura, String piso, String dpto,
                  String dondeEnviamos,  Boolean envioDomicilio, Boolean takeAway, Boolean casa, Boolean departamento){
        this.listaPlatos = listaPlatos;
        this.precioTotal = precioTotal;
        this.email = email;
        this.direccion = direccion;
        this.altura = altura;
        this.piso = piso;
        this.dpto = dpto;
        this.dondeEnviamos = dondeEnviamos;
        this.takeAway = takeAway;
        this.envioDomicilio = envioDomicilio;
        this.casa = casa;
        this.departamento = departamento;
    }

    public Pedido(Long id, List<Plato> listaPlatos, Double precioTotal){
        this.id = id;
        this.listaPlatos = listaPlatos;
        this.precioTotal = precioTotal;
    }


}
