package com.miappfarmacia;

public class Pedido {
    private String nombreMedicamento;
    private String tipoMedicamento;
    private int cantidad;
    private String distribuidor;
    private boolean sucursalPrincipal;
    private boolean sucursalSecundaria;

    // Constructor
    public Pedido(String nombreMedicamento, String tipoMedicamento, int cantidad,
                  String distribuidor, boolean sucursalPrincipal, boolean sucursalSecundaria) {
        this.nombreMedicamento = nombreMedicamento;
        this.tipoMedicamento = tipoMedicamento;
        this.cantidad = cantidad;
        this.distribuidor = distribuidor;
        this.sucursalPrincipal = sucursalPrincipal;
        this.sucursalSecundaria = sucursalSecundaria;
    }

    // Getters
    public String getNombreMedicamento() { return nombreMedicamento; }
    public String getTipoMedicamento() { return tipoMedicamento; }
    public int getCantidad() { return cantidad; }
    public String getDistribuidor() { return distribuidor; }
    public boolean isSucursalPrincipal() { return sucursalPrincipal; }
    public boolean isSucursalSecundaria() { return sucursalSecundaria; }
}
