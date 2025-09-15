package com.miappfarmacia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Clase que representa la ventana de resumen del pedido
public class VentanaResumen extends JFrame {
    // El constructor recibe un objeto Pedido y muestra su información
    public VentanaResumen(Pedido pedido) {
        //se agrega el título  de la ventana: incluye el distribuidor del pedido
        setTitle("Pedido al distribuidor " + pedido.getDistribuidor());
        // Define el tamaño de la ventana
        setSize(500, 300);
        // Aquí se le da la posición a la ventana en este caso centrada
        setLocationRelativeTo(null);
        //Se crea un layout o tabla divisoria dentro de la ventana de 3 filas y 1 columna para ubicar los elementos
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(new EmptyBorder(20, 10, 20, 10));
        // se agrega la etiqueta que muestra la cantidad, tipo y nombre del medicamento pedido
        JLabel lblMedicamento = new JLabel(
                pedido.getCantidad() + " unidades del " + pedido.getTipoMedicamento().toLowerCase() +
                        " " + pedido.getNombreMedicamento()
        );
        //se agrega la etiueta al panelprincipal
        panel.add(lblMedicamento);

        // Construimos el texto de la dirección según las sucursales seleccionadas
        StringBuilder direccion = new StringBuilder("Para la farmacia situada en:  ");

        if (pedido.isSucursalPrincipal() & pedido.isSucursalSecundaria()) {
            direccion.append("Calle Laureano Gomez Av.11 -#45-16 y  Kenedy Kra 24 #34-10");
        }
        if (pedido.isSucursalPrincipal()) {
            direccion.append("Calle Laureano Gomez Av.11 -#45-16,     ");
        }
        if (pedido.isSucursalSecundaria()) {
            direccion.append("  Kenedy Kra 24 #34-10");
        }
        // Agregamos la dirección al panel en una etiqueta
        panel.add(new JLabel(direccion.toString()));

        // Panel para los botones de acción con FlowLayout (alineados en fila)
        JPanel botones = new JPanel(new FlowLayout());
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEnviar = new JButton("Enviar");

        // Acción del botón "Cancelar": cierra la ventana de resumen
        btnCancelar.addActionListener(e -> dispose());

        // Acción del botón "Enviar": muestra un mensaje de éxito y cierra la ventana
        btnEnviar.addActionListener(e -> {
            System.out.println("Pedido enviado");
            JOptionPane.showMessageDialog(this, "¡Pedido enviado con éxito!");
            dispose();
        });
        // Añadimos los botones al subpanel
        botones.add(btnCancelar);
        botones.add(btnEnviar);
        panel.add(botones);
        // Finalment  agregamos el panel principal a la ventana
        add(panel);
    }
}
