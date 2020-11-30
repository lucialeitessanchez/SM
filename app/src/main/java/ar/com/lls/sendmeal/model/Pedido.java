package ar.com.lls.sendmeal.model;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

@Entity
public class Pedido {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String altura;
    private Boolean casa;
    private String dpto;
    private Boolean departamento;
    private String direccion;
    @ColumnInfo(name = "donde_enviamos")
    private String dondeEnviamos;
    @ColumnInfo(name = "envio_domicilio")
    private Boolean envioDomicilio;
    private String email;
    @ColumnInfo(name = "precio_total")
    private Double precioTotal;
    private String piso;
    @ColumnInfo(name = "teke_away")
    private Boolean takeAway;
    @ColumnInfo(name = "total_pedido")
    public Double totalPedido;

    @Ignore
    public ArrayList<String> listaPlatosSeleccionados = new ArrayList<>();

    public Pedido(Long id, String altura, Boolean casa, String dpto, Boolean departamento, String direccion, String dondeEnviamos, Boolean envioDomicilio, String email, Double precioTotal, String piso, Boolean takeAway, Double totalPedido) {
        this.id = id;
        this.altura = altura;
        this.casa = casa;
        this.dpto = dpto;
        this.departamento = departamento;
        this.direccion = direccion;
        this.dondeEnviamos = dondeEnviamos;
        this.envioDomicilio = envioDomicilio;
        this.email = email;
        this.precioTotal = precioTotal;
        this.piso = piso;
        this.takeAway = takeAway;
        this.totalPedido = totalPedido;
    }

    //Getters
    public Double getPrecioTotal() { return precioTotal; }
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }
    public String getAltura() { return altura; }
    public String getPiso() { return piso; }
    public String getDpto() { return dpto; }
    public String getDondeEnviamos() { return dondeEnviamos; }
    public Boolean getEnvioDomicilio() { return envioDomicilio; }
    public Boolean getTakeAway() { return takeAway; }
    public Boolean getCasa() { return casa; }
    public Boolean getDepartamento() { return departamento; }
    public ArrayList<String> getListaPlatosSeleccionados() { return listaPlatosSeleccionados; }
    public Double getTotalPedido() { return totalPedido; }

    //Setters
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
    public void setListaPlatosSeleccionados(ArrayList<String> listaPlatosSeleccionados) {
        this.listaPlatosSeleccionados = listaPlatosSeleccionados;
    }
    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }

    @Ignore //constructs
    public Pedido( ArrayList<String> listaPlatosSeleccionados, Double precioTotal, String email, String direccion, String altura, String piso, String dpto,
                  String dondeEnviamos,  Boolean envioDomicilio, Boolean takeAway, Boolean casa, Boolean departamento){
        this.listaPlatosSeleccionados = listaPlatosSeleccionados;
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

    @Ignore
    public Pedido(Long id, Double precioTotal, ArrayList<String> listaPlatosSeleccionados){
        this.id = id;
        this.precioTotal = precioTotal;
        this.listaPlatosSeleccionados = listaPlatosSeleccionados;
    }


}
