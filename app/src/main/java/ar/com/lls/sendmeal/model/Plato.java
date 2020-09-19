package ar.com.lls.sendmeal.model;


public class Plato {
    private String titulo;
    private String descripcion;
    private Integer precio;
    private Integer calorias;

    //construct
    public Plato(String titulo, String descripcion, Integer precio, Integer calorias) {
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

    public Integer getPrecio() {
        return precio;
    }

    public Integer getCalorias() {
        return calorias;
    }

    //Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }
}
