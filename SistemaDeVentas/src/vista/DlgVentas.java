package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ClienteControlador;
import controlador.VentaControlador;
import modelo.Cliente;
import modelo.DocCliente;
import modelo.Venta;
import modelo.DocVenta;
import modelo.Producto;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgVentas extends JFrame implements ActionListener {
	//Declaracion de objetos universales
	//ArregloVenta v = new ArregloVenta();
	VentaControlador controlador = new VentaControlador();
	ClienteControlador ap = new ClienteControlador();
	
	//ArrayList<DetalleVenta> temp = new ArrayList<>();
	
	//ArrayList que guarda todos los elementos locales para la tabka
	ArrayList<String> datosTxt = new ArrayList<>();
	
	//ArrayList que guarda los datos de la venta para enviarlos al controlador y posterior al BD
	ArrayList<String> registroVenta=new ArrayList<>();
	
	//ArrayList que guarde datos temporalmente
	ArrayList<String> temp=new ArrayList<>();

	DefaultTableModel modelo = new DefaultTableModel();
	
	DecimalFormat df = new DecimalFormat("#.000");
	
	private JPanel contentPane;
	private JPanel panel;
	private JTextField txtNomCliente;
	private JButton btnBuscarCliente;
	private JLabel label;
	private JTextField txtCodCliente;
	private JLabel label_1;
	private JPanel panel_1;
	private JLabel label_2;
	private JTextField txtCodProducto;
	private JLabel label_3;
	private JTextField txtNomProducto;
	private JButton btnBuscarProd;
	private JLabel label_4;
	private JTextField txtPrecioProd;
	private JLabel label_5;
	private JTextField txtCantidad;
	private JButton btnAgregar;
	private JLabel lblCodVenta;
	private JTextField txtCodVenta;
	private JButton btnRegistrarVenta;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JLabel label_9;
	private JLabel lblIgv;
	private JTextField txtIgv;
	private JLabel label_11;
	private JTextField txtTotal;
	private JButton btnQuitar;
	private JTextField txtSubtotal;
	private JTable tblDetalle;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JScrollPane scrollPane_1;
	private JTextArea txtS;
	private JButton btnFactura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgVentas frame = new DlgVentas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DlgVentas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(10, 45, 608, 39);
		contentPane.add(panel);

		txtNomCliente = new JTextField();
		txtNomCliente.setEditable(false);
		txtNomCliente.setColumns(10);
		txtNomCliente.setBounds(329, 8, 269, 23);
		panel.add(txtNomCliente);

		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(this);
		btnBuscarCliente.setBounds(176, 8, 75, 23);
		panel.add(btnBuscarCliente);

		label = new JLabel("Cod. Cliente:");
		label.setBounds(10, 8, 92, 23);
		panel.add(label);

		txtCodCliente = new JTextField();
		txtCodCliente.setColumns(10);
		txtCodCliente.setBounds(80, 8, 86, 23);
		panel.add(txtCodCliente);

		label_1 = new JLabel("Cliente");
		label_1.setBounds(279, 8, 67, 23);
		panel.add(label_1);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(10, 87, 608, 65);
		contentPane.add(panel_1);

		label_2 = new JLabel("Cod. Producto");
		label_2.setBounds(10, 8, 92, 23);
		panel_1.add(label_2);

		txtCodProducto = new JTextField();
		txtCodProducto.setColumns(10);
		txtCodProducto.setBounds(86, 8, 67, 23);
		panel_1.add(txtCodProducto);

		label_3 = new JLabel("Producto");
		label_3.setBounds(274, 8, 67, 23);
		panel_1.add(label_3);

		txtNomProducto = new JTextField();
		txtNomProducto.setEditable(false);
		txtNomProducto.setColumns(10);
		txtNomProducto.setBounds(330, 8, 267, 23);
		panel_1.add(txtNomProducto);

		btnBuscarProd = new JButton("Buscar Prod");
		btnBuscarProd.addActionListener(this);
		btnBuscarProd.setBounds(163, 8, 101, 23);
		panel_1.add(btnBuscarProd);

		label_4 = new JLabel("Precio");
		label_4.setBounds(10, 35, 57, 23);
		panel_1.add(label_4);

		txtPrecioProd = new JTextField();
		txtPrecioProd.setEditable(false);
		txtPrecioProd.setColumns(10);
		txtPrecioProd.setBounds(86, 35, 86, 23);
		panel_1.add(txtPrecioProd);

		label_5 = new JLabel("Cantidad");
		label_5.setBounds(274, 35, 57, 23);
		panel_1.add(label_5);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(330, 35, 67, 23);
		panel_1.add(txtCantidad);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(480, 34, 117, 24);
		panel_1.add(btnAgregar);

		lblCodVenta = new JLabel("Cod. Venta");
		lblCodVenta.setBounds(21, 11, 92, 23);
		contentPane.add(lblCodVenta);

		txtCodVenta = new JTextField();
		txtCodVenta.setColumns(10);
		txtCodVenta.setBounds(115, 11, 86, 23);
		contentPane.add(txtCodVenta);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_2.setBounds(10, 155, 608, 218);
		contentPane.add(panel_2);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 585, 113);
		panel_2.add(scrollPane);

		tblDetalle = new JTable();
		tblDetalle.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblDetalle);

		label_9 = new JLabel("Subtotal");
		label_9.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		label_9.setBounds(415, 132, 92, 23);
		panel_2.add(label_9);

		lblIgv = new JLabel("IGV");
		lblIgv.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblIgv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIgv.setBounds(415, 157, 92, 23);
		panel_2.add(lblIgv);

		txtIgv = new JTextField();
		txtIgv.setColumns(10);
		txtIgv.setBounds(519, 158, 76, 23);
		panel_2.add(txtIgv);

		label_11 = new JLabel("Total");
		label_11.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		label_11.setBounds(415, 184, 92, 23);
		panel_2.add(label_11);

		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(519, 184, 76, 23);
		panel_2.add(txtTotal);

		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(this);
		btnQuitar.setBounds(10, 135, 122, 23);
		panel_2.add(btnQuitar);

		txtSubtotal = new JTextField();
		txtSubtotal.setColumns(10);
		txtSubtotal.setBounds(519, 132, 76, 23);
		panel_2.add(txtSubtotal);

		btnRegistrarVenta = new JButton("Registrar Venta");
		btnRegistrarVenta.addActionListener(this);
		btnRegistrarVenta.setBounds(10, 158, 154, 23);
		panel_2.add(btnRegistrarVenta);

		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(429, 11, 92, 23);
		contentPane.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(523, 11, 86, 23);
		contentPane.add(txtFecha);

		modelo.addColumn("Codigo");
		modelo.addColumn("Producto");
		modelo.addColumn("Precio");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Importe");
		tblDetalle.setModel(modelo);
		
		btnFactura = new JButton("Generar Factura");
		btnFactura.addActionListener(this);
		btnFactura.setBounds(10, 184, 154, 23);
		panel_2.add(btnFactura);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 384, 598, 166);
		contentPane.add(scrollPane_1);
		
		txtS = new JTextArea();
		scrollPane_1.setViewportView(txtS);
		nuevo();
	}

	// Definir m�todos
	void nuevo() {	
		txtCodVenta.setText(controlador.generarCodigo());
		txtFecha.setText(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFactura) {
			actionPerformedBtnFactura(e);
		}
		if (e.getSource() == btnRegistrarVenta) {
			actionPerformedBtnRegistrarVenta(e);
		}
		if (e.getSource() == btnQuitar) {
			actionPerformedBtnQuitar(e);
		}
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
		if (e.getSource() == btnBuscarProd) {
			actionPerformedBtnBuscar(e);
		}
		if (e.getSource() == btnBuscarCliente) {
			actionPerformedBtnBuscarCliente(e);
		}
	}

	// Entrada
	int leerCodVenta() {
		return Integer.parseInt(txtCodVenta.getText());
	}

	int leerCodProducto() {
		return Integer.parseInt(txtCodProducto.getText());
	}

	int leerCodCliente() {
		return Integer.parseInt(txtCodCliente.getText());
	}

	int leerCantidad() {
		return Integer.parseInt(txtCantidad.getText());
	}

	String leerFecha() {
		return txtFecha.getText().trim();
	}

	double leerPrecio() {
		return Double.parseDouble(txtPrecioProd.getText());
	}

	String leerNombre() {
		return txtNomProducto.getText();
	}

	void mensaje(String txt) {
		JOptionPane.showMessageDialog(this, txt);
	}

	protected void actionPerformedBtnBuscarCliente(ActionEvent e) {
		try {
			/*Cliente cliente = controlador.getClienteXCodigo(String.valueOf(leerCodCliente()));
			if (cliente != null) {
				txtNomCliente.setText(cliente.getNombres() + " " + cliente.getApellidos());
			} else {
				mensaje("Cliente no Existe");
				txtCodCliente.setText("");
				txtCodCliente.requestFocus();
			}*/
			/*String datoCliente = controlador.retornarValoresCliente(aString(leerCodCliente()));
			txtNomCliente.setText(datoCliente);*/
			String datoCliente;
			if((datoCliente = controlador.retornarValoresCliente(aString(leerCodCliente())))!=null) {
				txtNomCliente.setText(datoCliente);
			}
			
			else {
				mensaje("Cliente no Existe");
				txtCodCliente.setText("");
				txtCodCliente.requestFocus();
				txtNomCliente.setText("");
			}
		} catch (Exception e2) {
			mensaje("Codigo de Cliente no valido");
			txtCodCliente.setText("");
			txtCodCliente.requestFocus();
		}

	}

	protected void actionPerformedBtnBuscar(ActionEvent e) {
		try {
			String[] datos;
			if((datos=controlador.retornarValoresProducto(aString(leerCodProducto())))!=null) {
				txtNomProducto.setText(datos[0]);
				txtPrecioProd.setText(datos[1]);
			}
			
			else {
				mensaje("Producto no Existe");
				txtCodProducto.setText("");
				txtCodProducto.requestFocus();
				txtNomProducto.setText("");
				txtPrecioProd.setText("");
			}
		} catch (Exception e2) {
			mensaje("Codigo de Producto no valido");
			txtCodProducto.setText("");
			txtCodProducto.requestFocus();
		}
	}

	protected void actionPerformedBtnAgregar(ActionEvent e) {
		
		datosTxt.add(String.valueOf(leerCodProducto())+", "+
				String.valueOf(leerNombre())+", "+
				String.valueOf(leerPrecio())+", "+
				String.valueOf(leerCantidad())+", "+
				String.valueOf(df.format(leerCantidad()*leerPrecio()))
				);
		
		Listar();
		calcularPreciosTxts();
		//agregarItem(new Venta(leerCodVenta(), leerCodCliente(), leerCodProducto(), leerCantidad(), leerPrecio(),leerFecha()));
		//listar();
		//totales();
	}

	/*private void agregarItem() {
		
		controlador.agregarVenta(venta);
	}*/

	/*void listar() {
		while(modelo.getRowCount()>0) {
			modelo.removeRow(0);
		}
		String[]datosVenta = controlador.retornarValoresArray();
		for (int i = 0; i < datosVenta.length; i++) {
			String [] valores = datosVenta[i].split(", ");
			modelo.addRow(valores);
		}
	}*/
	
	void Listar() {
		while(modelo.getRowCount()>0) {
			modelo.removeRow(0);
		}
		for (int i = 0; i < datosTxt.size(); i++) {
			String [] valores = datosTxt.get(i).split(", ");
			modelo.addRow(valores);
		}
		calcularPreciosTxts();
	}
	
	

	void calcularPreciosTxts() {
		double subTotal = 0, igv, t = 0;
		//double contadorTotal =0; 
		
		for (int i = 0; i < datosTxt.size(); i++) {
			String[]valores = datosTxt.get(i).split(", ");
			subTotal += Double.parseDouble(valores[4]);
		}
		igv = subTotal * 0.18;
		t = subTotal + igv;
		txtSubtotal.setText(df.format(subTotal) + "");
		txtIgv.setText(df.format(igv) + "");
		txtTotal.setText(df.format(t) + "");
	}

	protected void actionPerformedBtnQuitar(ActionEvent e) {
		if (datosTxt.size() > 0) {
			int i = tblDetalle.getSelectedRow();
			if (i >= 0) {
				datosTxt.remove(i);
				Listar();
				//totales();
			} else {
				mensaje("Seleccionar una fila.");
			}
		}

	}

	protected void actionPerformedBtnRegistrarVenta(ActionEvent e) {
		//String [] datosRec;
		if(!registroVenta.isEmpty()) {
			/*for (int i = 0; i < registroVenta.size(); i++) {
				registroVenta.remove(i);
			}*/
			registroVenta.clear();
		}
		for (int i = 0; i < datosTxt.size(); i++) {
			String [] valores = datosTxt.get(i).split(", ");
			registroVenta.add(aString(leerCodVenta()).concat(", ")
					.concat(aString(leerCodCliente())).concat(", ")
					.concat(valores[0]).concat(", ")
					.concat(valores[3]).concat(", ")
					.concat(valores[2]).concat(", ")
					.concat(leerFecha())
				);
			controlador.agregarVenta(registroVenta);
		}
		
		
		//registroVenta.add(aString(leerCodCliente()));
		
		
		/*Venta x = null;
		for (int i = 0; i < datosTxt.size(); i++) {
			x = new Venta();
			x.setCodigoVenta(leerCodVenta());
			x.setCodigoCliente(leerCodCliente());
			x.setFecha(leerFecha());
			DetalleVenta d = datosTxt.get(i);
			x.setCodigoProducto(d.getCodigoProducto());
			x.setPrecio(d.getPrecio());
			x.setCantidad(d.getCantidad());
			v.adicionar(x);
		}
		v.grabar();*/
	

	
	}
	/* void imprimirTableModel() {
		 for (DetalleVenta x : temp) {
			 txtS.setText("");
	        imprimir("   F A C T U R A  \n ");
	        imprimir("   Codigo de venta      \t : "+x.getCodigoVenta());
	        imprimir("   Código Producto      \t : "+x.getCodigoProducto());
	        imprimir("   Nombre de producto   \t : "+x.getNombre());
	        imprimir("   Precio de producto   \t : "+x.getPrecio());
	        imprimir("   Cantidad de Productos\t : "+x.getCantidad());
	        imprimir("   Importe a pagar      \t : "+x.importe());
	        imprimir("");
	        imprimir("   Subtotal: " + txtSubtotal.getText());
	        imprimir("   IGV: " + txtIgv.getText());
	        imprimir("   Total: " + txtTotal.getText());
	    }}*/
	
		
	void imprimir() {
		imprimir("");
	}
	//  
	void imprimir(String s) {
		txtS.append(s + "\n");
	}	
	protected void actionPerformedBtnFactura(ActionEvent e) {
		//imprimirTableModel();
	}
	
	//Metodo con generico que ingresa cualquier tipo de dato y lo convierte a String
	<E> String aString (E dato){
		return String.valueOf(dato);
	}
}
