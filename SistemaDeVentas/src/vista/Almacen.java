package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane; // Importante para usar JOptionPane

import controlador.*;
import modelo.*;

public class Almacen extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JLabel lblCodProducto;
    private JLabel lblCantidadProducto;
    private JTextField txtCodigoProducto;
    private JTextField txtCantidad;
    private JButton btnIngresarStock;
    private JScrollPane scrollPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            Almacen dialog = new Almacen();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Almacen() {
        setBounds(100, 100, 552, 395);
        getContentPane().setLayout(null);
        
        panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 11, 516, 93);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        lblCodProducto = new JLabel("Codigo de producto:");
        lblCodProducto.setBounds(73, 24, 123, 14);
        panel.add(lblCodProducto);
        
        lblCantidadProducto = new JLabel("Cantidad:");
        lblCantidadProducto.setBounds(133, 60, 57, 14);
        panel.add(lblCantidadProducto);
        
        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(200, 21, 86, 20);
        panel.add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);
        
        txtCantidad = new JTextField();
        txtCantidad.setBounds(200, 57, 86, 20);
        panel.add(txtCantidad);
        txtCantidad.setColumns(10);
        
        btnIngresarStock = new JButton("Ingresar Stock");
        btnIngresarStock.addActionListener(this);
        btnIngresarStock.setBounds(308, 56, 132, 23);
        panel.add(btnIngresarStock);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 115, 516, 230);
        getContentPane().add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null},
            },
            new String[] {
                "Codigo", "Nombre", "Stock Actual", "Stock Minimo", "Stock Maximo"
            }
        ));
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        scrollPane.setViewportView(table);
        
        mostrarListaProductos();
        
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresarStock) {
            actionPerformedBtnIngresarStock(e);
        }
    }
    
    protected void actionPerformedBtnIngresarStock(ActionEvent e) {
    	mostrarListaProductos();
        // Obtener el código del producto desde el campo de texto
        String codigoProducto = txtCodigoProducto.getText();
        // Supongamos que también tienes un campo de texto para ingresar la cantidad de stock a agregar
        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText()); // Intentamos obtener la cantidad ingresada
        } catch (NumberFormatException ex) {
            // Manejo de error si no se ingresa un número válido
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        mostrarListaProductos();
        // Llamar al controlador para obtener el producto
        Producto producto = ProductoControlador.buscarProducto(codigoProducto);
        if (producto == null) {
            // Mostrar mensaje de error si no se encuentra el producto
            JOptionPane.showMessageDialog(this, "El producto no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si la cantidad ingresada excede el stock máximo permitido
        if (cantidad > producto.getStockMaximo() - producto.getStockActual()) {
            // Mostrar mensaje de alerta si la cantidad excede el stock máximo
            JOptionPane.showMessageDialog(this, "La cantidad ingresada excede el stock máximo permitido.", "Alerta", JOptionPane.WARNING_MESSAGE);
            return; // Salir del método sin realizar ningún cambio
        }

        // Llamar al controlador para ingresar el stock
        boolean ingresoExitoso = ProductoControlador.ingresarStock(codigoProducto, cantidad);

        if (ingresoExitoso) {
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Stock ingresado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Mostrar lista actualizada de productos
            mostrarListaProductos();
        } else {
            // Mostrar mensaje de error
            JOptionPane.showMessageDialog(this, "No se pudo ingresar el stock. Verifique los límites de stock.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        mostrarListaProductos();
    }


    
    private boolean validarCantidad(String codigoProducto, int cantidad) {
        Producto producto = ProductoControlador.buscarProducto(codigoProducto);
        if (producto != null) {
            if (cantidad < producto.getStockMinimo() || cantidad > producto.getStockMaximo()) {
                // Mostrar mensaje de error si la cantidad está fuera de los límites
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida entre " + producto.getStockMinimo() + " y " + producto.getStockMaximo() + ".", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            // Mostrar mensaje de error si no se encuentra el producto
            JOptionPane.showMessageDialog(this, "El producto no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void mostrarListaProductos() {
        // Obtener lista actualizada de productos desde el archivo
        ArrayList<Producto> productos = DocProducto.getProductosArr();

        // Limpiar la tabla antes de mostrar los datos actualizados
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpiar filas existentes

        // Agregar filas con los datos de los productos
        for (Producto producto : productos) {
            Object[] row = { 
                producto.getCodigoProducto(), 
                producto.getNombre(), 
                producto.getStockActual(),
                producto.getStockMinimo(),
                producto.getStockMaximo()
            };
            model.addRow(row);
        }
    }


    

}
