package com.miappfarmacia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
//creamos la clase que representa a la ventana principal del pedido
public class VentanaPedido extends JFrame {
    // Declaramos los componentes de la interfaz:
    // campos de texto, lista desplegable, botones de opción y casillas de verificación
    private JTextField txtMedicamento;
    private JComboBox<String> cmbTipo;
    private JTextField txtCantidad;
    private JRadioButton rbCofarma, rbEmpsephar, rbCemefar;
    private JCheckBox chkPrincipal, chkSecundaria;

    public VentanaPedido() {
        // Establece el título de la ventana
        setTitle("Sistema de pedidos - Farmacia");
        //Establece el alto y el ancho de la ventana
        setSize(800, 400);
        // Indica que la aplicación debe finalizar cuando se cierre la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Centra la ventana en la pantalla al abrirla
        setLocationRelativeTo(null);

        // Creamos un panel con un GridLayout ( basicamente una especie de tabla ) de 8 filas, 2 columnas,
        // y un espacio de 10 píxeles entre filas y columnas
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        //da un borde vacio para que loss elementos no queden pegados al borde del panel
        panel.setBorder(new EmptyBorder(20, 10, 20, 10));


        // ------------------------- Sección Medicamento ------------------,
        // Agregamos una etiqueta para el campo "Nombre del Medicamento" intanciado un nuevo JLabel
        panel.add(new JLabel("Nombre del Medicamento:"));
        // Creamos un campo de texto para escribir el nombre del medicamento
        txtMedicamento = new JTextField();
        // Añadimos el campo de texto al panel
        panel.add(txtMedicamento);

        // ----------------------- Sección Tipo de Medicamento ---
        // Agregamos una etiqueta para el campo "Tipo de Medicamento"
        panel.add(new JLabel("Tipo de Medicamento:"));
        // Creamos un combo box con una lista de opciones de tipos de medicamento
        cmbTipo = new JComboBox<>(new String[]{
                "", "Analgésico", "Analéptico", "Anestésico", "Antiácido", "Antidepresivo", "Antibiótico"
        });
        // Añadimos el combo box al panel
        panel.add(cmbTipo);

        // --- -----------------Sección Cantidad ---
        // Agregamos una etiqueta para el campo "Cantidad"
        panel.add(new JLabel("Cantidad:"));
        // Creamos un campo de texto para ingresar la cantidad
        txtCantidad = new JTextField();

        // Añadimos el campo de texto al panel
        panel.add(txtCantidad);

        // -------------------------------- Sección Distribuidor ---
        // Agregamos una etiqueta "Distribuidor:" al panel principal
        panel.add(new JLabel("Distribuidor:"));
        // Creamos un subpanel con un FlowLayout (los componentes se colocan en fila)
        JPanel distribuidorPanel = new JPanel(new FlowLayout());
        // Creamos los botones de opción (radio buttons) para seleccionar el distribuidor
        rbCofarma = new JRadioButton("Cofarma");
        rbEmpsephar = new JRadioButton("Empsephar");
        rbCemefar = new JRadioButton("Cemefar");

        // Creamos un grupo de botones para que solo se pueda elegir uno a la vez
        ButtonGroup grupoDistribuidor = new ButtonGroup();
        grupoDistribuidor.add(rbCofarma);
        grupoDistribuidor.add(rbEmpsephar);
        grupoDistribuidor.add(rbCemefar);
        // Añadimos los botones de opción al subpanel
        distribuidorPanel.add(rbCofarma);
        distribuidorPanel.add(rbEmpsephar);
        distribuidorPanel.add(rbCemefar);
        panel.add(distribuidorPanel);

        // -------------------------- Sección Sucursal ------------
        // Agregamos una etiqueta "Sucursal:" al panel principal
        panel.add(new JLabel("Sucursal:"));
        // Creamos un subpanel con un FlowLayout para organizar las casillas de verificación en línea
        JPanel sucursalPanel = new JPanel(new FlowLayout());
        // Creamos una casilla de verificación para indicar que la sucursal es Principal
        chkPrincipal = new JCheckBox("Principal");
        // Creamos una casilla de verificación para indicar que la sucursal es Secundaria
        chkSecundaria = new JCheckBox("Secundaria");
        // Añadimos ambas casillas de verificación al subpanel
        sucursalPanel.add(chkPrincipal);
        sucursalPanel.add(chkSecundaria);
        // Agregamos el subpanel de sucursal al panel principal
        panel.add(sucursalPanel);

        // --- Sección Botones ---
        // Creamos un botón para borrar los datos ingresados en el formulario
        JButton btnBorrar = new JButton("Borrar");
        JButton btnConfirmar = new JButton("Confirmar");

        // --- Acción del botón Borrar ---
        // Agregamos un ActionListener al botón "Borrar"
        btnBorrar.addActionListener(e -> {
            // Limpia los campos y quita las selecciones
            txtMedicamento.setText("");
            cmbTipo.setSelectedIndex(0);
            txtCantidad.setText("");
            grupoDistribuidor.clearSelection();
            chkPrincipal.setSelected(false);
            chkSecundaria.setSelected(false);
        });
        // ---------------------- Acción del botón Confirmar------------- ---
        // Agregamos un ActionListener al botón "Confirmar"
        btnConfirmar.addActionListener(e -> {
            try {
                // Obtiene el texto ingresado en el campo "Medicamento"
                String nombre = txtMedicamento.getText();
                // Obtiene el tipo de medicamento seleccionado en el combo box
                String tipo = (String) cmbTipo.getSelectedItem();
                // Convierte el valor ingresado en "Cantidad" a número entero
                int cantidad = Integer.parseInt(txtCantidad.getText());
                // Determina qué distribuidor fue seleccionado entre los radio buttons
                String distribuidor = rbCofarma.isSelected() ? "Cofarma" :
                        rbEmpsephar.isSelected() ? "Empsephar" :
                                rbCemefar.isSelected() ? "Cemefar" : null;
                // Verifica si la casilla "Principal" está seleccionada
                boolean principal = chkPrincipal.isSelected();
                // Verifica si la casilla "Secundaria" está seleccionada
                boolean secundaria = chkSecundaria.isSelected();

                // Crea un objeto Pedido con los datos ingresados
                Pedido pedido = new Pedido(nombre, tipo, cantidad, distribuidor, principal, secundaria);
                // Llama al servicio de validación del pedido (puede lanzar excepciones)
                PedidoService.validarPedido(pedido);

                // Abre una nueva ventana de resumen con los datos del pedido
                new VentanaResumen(pedido).setVisible(true);


                // Captura el error si la cantidad no es un número entero válido
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                // Captura el error si la validación del pedido lanza una excepción
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        // Añadimos los botones al panel principal
        panel.add(btnBorrar);
        panel.add(btnConfirmar);
        // Finalmente, agregamos el panel completo a la ventana
        add(panel);
    }
}

