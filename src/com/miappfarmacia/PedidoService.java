package com.miappfarmacia;

public class PedidoService {

    public static void validarPedido(Pedido pedido) throws IllegalArgumentException {
        if (pedido.getNombreMedicamento() == null || pedido.getNombreMedicamento().isBlank()) {
            throw new IllegalArgumentException("El nombre del medicamento no puede estar vacío.");
        }
        if (!pedido.getNombreMedicamento().matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("El nombre del medicamento debe ser alfanumérico.");
        }
        if (pedido.getTipoMedicamento() == null || pedido.getTipoMedicamento().isBlank()) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de medicamento.");
        }
        if (pedido.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
        }
        if (pedido.getDistribuidor() == null) {
            throw new IllegalArgumentException("Debe seleccionar un distribuidor.");
        }
        if (!pedido.isSucursalPrincipal() && !pedido.isSucursalSecundaria()) {
            throw new IllegalArgumentException("Debe seleccionar al menos una sucursal.");
        }
    }
}
