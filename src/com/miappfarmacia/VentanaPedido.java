package com.miappfarmacia;

import javax.swing.*;
import java.awt.*;

public class VentanaPedido extends JFrame {
    private JTextField txtMedicamento;
    private JComboBox<String> cmbTipo;
    private JTextField txtCantidad;
    private JRadioButton rbCofarma, rbEmpsephar, rbCemefar;
    private JCheckBox chkPrincipal, chkSecundaria;

    public VentanaPedido() {
        setTitle("Sistema de pedidos - Farmacia");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        // Medicamento
        panel.add(new JLabel("Nombre del Medicamento:"));
        txtMedicamento = new JTextField();
        panel.add(txtMedicamento);

        // Tipo
        panel.add(new JLabel("Tipo de Medicamento:"));
        cmbTipo = new JComboBox<>(new String[]{
                "", "Analgésico", "Analéptico", "Anestésico", "Antiácido", "Antidepresivo", "Antibiótico"
        });
        panel.add(cmbTipo);

        // Cantidad
        panel.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        // Distribuidor
        panel.add(new JLabel("Distribuidor:"));
        JPanel distribuidorPanel = new JPanel(new FlowLayout());
        rbCofarma = new JRadioButton("Cofarma");
        rbEmpsephar = new JRadioButton("Empsephar");
        rbCemefar = new JRadioButton("Cemefar");
        ButtonGroup grupoDistribuidor = new ButtonGroup();
        grupoDistribuidor.add(rbCofarma);
        grupoDistribuidor.add(rbEmpsephar);
        grupoDistribuidor.add(rbCemefar);
        distribuidorPanel.add(rbCofarma);
        distribuidorPanel.add(rbEmpsephar);
        distribuidorPanel.add(rbCemefar);
        panel.add(distribuidorPanel);

        // Sucursal
        panel.add(new JLabel("Sucursal:"));
        JPanel sucursalPanel = new JPanel(new FlowLayout());
        chkPrincipal = new JCheckBox("Principal");
        chkSecundaria = new JCheckBox("Secundaria");
        sucursalPanel.add(chkPrincipal);
        sucursalPanel.add(chkSecundaria);
        panel.add(sucursalPanel);

        // Botones
        JButton btnBorrar = new JButton("Borrar");
        JButton btnConfirmar = new JButton("Confirmar");

        btnBorrar.addActionListener(e -> {
            txtMedicamento.setText("");
            cmbTipo.setSelectedIndex(0);
            txtCantidad.setText("");
            grupoDistribuidor.clearSelection();
            chkPrincipal.setSelected(false);
            chkSecundaria.setSelected(false);
        });

        btnConfirmar.addActionListener(e -> {
            try {
                String nombre = txtMedicamento.getText();
                String tipo = (String) cmbTipo.getSelectedItem();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                String distribuidor = rbCofarma.isSelected() ? "Cofarma" :
                        rbEmpsephar.isSelected() ? "Empsephar" :
                                rbCemefar.isSelected() ? "Cemefar" : null;
                boolean principal = chkPrincipal.isSelected();
                boolean secundaria = chkSecundaria.isSelected();

                Pedido pedido = new Pedido(nombre, tipo, cantidad, distribuidor, principal, secundaria);
                PedidoService.validarPedido(pedido);

                new VentanaResumen(pedido).setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(btnBorrar);
        panel.add(btnConfirmar);

        add(panel);
    }
}

