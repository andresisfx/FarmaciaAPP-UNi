package com.miappfarmacia;

public class PedidoService {

    public static void validarPedido(Pedido pedido) throws IllegalArgumentException {
        //valida que el nombre del medicamento no esté vacio
        if (pedido.getNombreMedicamento() == null || pedido.getNombreMedicamento().isBlank()) {
            throw new IllegalArgumentException("El nombre del medicamento no puede estar vacío.");
        }
        //validamos con una expresión regular que el nombre no tenga caracteres especiales
        if (!pedido.getNombreMedicamento().matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("El nombre del medicamento debe ser alfanumérico.");
        }
        //se valida que la casilla del tipo de medicamento se seleccione una opción
        if (pedido.getTipoMedicamento() == null || pedido.getTipoMedicamento().isBlank()) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de medicamento.");
        }
        //validamos que el campo  de cantidad del pedido no admita números negativos
        if (pedido.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
        }
        //validamos que en la casilla de distribuidor se seleccione una opción
        if (pedido.getDistribuidor() == null) {
            throw new IllegalArgumentException("Debe seleccionar un distribuidor.");
        }
        //validamos que se marque almenos una opción de la casilla
        if (!pedido.isSucursalPrincipal() && !pedido.isSucursalSecundaria()) {
            throw new IllegalArgumentException("Debe seleccionar al menos una sucursal.");
        }
    }
}
