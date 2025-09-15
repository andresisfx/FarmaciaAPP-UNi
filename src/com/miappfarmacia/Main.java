package com.miappfarmacia;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // estae método de la librería swing  hace que java interprete: “Cuando Swing tenga tiempo libre en el hilo de la interfaz gráfica, crea una nueva ventana de tipo VentanaPedido y muéstrala en pantalla”
        SwingUtilities.invokeLater(() -> new VentanaPedido().setVisible(true));
    }
}
