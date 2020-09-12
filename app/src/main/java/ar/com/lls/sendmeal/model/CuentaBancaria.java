package ar.com.lls.sendmeal.model;

public class CuentaBancaria {
    public CuentaBancaria(String cbu, String alias) {
        this.cbu = cbu;
        this.alias = alias;
    }

    public CuentaBancaria(String cbu) {
        this.cbu = cbu;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    private String cbu;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    private String alias;
}
