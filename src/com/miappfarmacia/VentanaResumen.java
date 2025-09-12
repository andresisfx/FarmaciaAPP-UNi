package com.miappfarmacia;

import javax.swing.*;
import java.awt.*;

public class VentanaResumen extends JFrame {
    public VentanaResumen(Pedido pedido) {
        setTitle("Pedido al distribuidor " + pedido.getDistribuidor());
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel lblMedicamento = new JLabel(
                pedido.getCantidad() + " unidades del " + pedido.getTipoMedicamento().toLowerCase() +
                        " " + pedido.getNombreMedicamento()
        );
        panel.add(lblMedicamento);

        StringBuilder direccion = new StringBuilder("Para la farmacia situada en ");
        if (pedido.isSucursalPrincipal()) {
            direccion.append("Calle de la Rosa n.28 ");
        }
        if (pedido.isSucursalSecundaria()) {
            direccion.append("y para la situada en Calle Alcazabilla n.3");
        }
        panel.add(new JLabel(direccion.toString()));

        JPanel botones = new JPanel(new FlowLayout());
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEnviar = new JButton("Enviar");

        btnCancelar.addActionListener(e -> dispose());
        btnEnviar.addActionListener(e -> {
            System.out.println("Pedido enviado");
            JOptionPane.showMessageDialog(this, "¡Pedido enviado con éxito!");
            dispose();
        });

        botones.add(btnCancelar);
        botones.add(btnEnviar);
        panel.add(botones);

        add(panel);
    }
}
