package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import controlador.ProductoControlador;
import modelo.Cliente;
import modelo.Producto;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Productos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtCodigoC;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtStockActual;
	private JTextField txtStockMin;
	private JTextField txtStockMax;
	private JButton btnAgregar;
	
	//Instanciamos el modelo de nuestra tabla
	private DefaultTableModel tablaProducto = new DefaultTableModel();
	
	private JButton btnModificar = new JButton("Modificar");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Productos dialog = new Productos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Productos() {
		setBounds(100, 100, 581, 428);
		getContentPane().setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 171, 545, 207);
			getContentPane().add(scrollPane);
			{
				/*DefaultTableModel tablaCliente = new DefaultTableModel();
				tablaCliente.setColumnIdentifiers(new String[] {
						"Codigo", "Nombres", "Apellidos", "Direccion", "Telefono", "DNI"
					});*/
				//DefaultTableModel tablaCliente = crearTablaModelo();
				definirTablaModelo();
				table = new JTable();
				table.setEnabled(false);
				table.setModel(tablaProducto);
				setValoresTabla();
				//tablaCliente.addRow(getValoresTabla());
				
				//table.setModel(tabla);
				scrollPane.setViewportView(table);
				
				
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 11, 545, 149);
		getContentPane().add(panel);
		panel.setLayout(null);
		{
			JLabel lblCodigoC = new JLabel("Código");
			lblCodigoC.setBounds(20, 11, 46, 14);
			panel.add(lblCodigoC);
		}
		{
			JLabel lblNombresC = new JLabel("Nombre");
			lblNombresC.setBounds(189, 11, 67, 14);
			panel.add(lblNombresC);
		}
		{
			JLabel lblApellidosC = new JLabel("Precio");
			lblApellidosC.setBounds(440, 11, 86, 14);
			panel.add(lblApellidosC);
		}
		{
			JLabel lblDniC = new JLabel("Stock Actual");
			lblDniC.setBounds(223, 69, 74, 14);
			panel.add(lblDniC);
		}
		{
			txtStockActual = new JTextField();
			txtStockActual.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if(tecla<'0' || tecla>'9') {
						e.consume();
					}
				}
			});
			txtStockActual.setBounds(223, 88, 86, 20);
			panel.add(txtStockActual);
			txtStockActual.setColumns(10);
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if((tecla<'0' || tecla>'9') && tecla!='.') {
						e.consume();
					}
				}
			});
			txtPrecio.setBounds(440, 30, 86, 20);
			panel.add(txtPrecio);
			txtPrecio.setColumns(10);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if((tecla<'a' || tecla>'z') && (tecla<'A' || tecla>'Z') && tecla!=' ') {
						e.consume();
					}
				}
			});
			txtNombre.setBounds(189, 30, 145, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
		}
		{
			txtCodigoC = new JTextField();
			txtCodigoC.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if(tecla<'0' || tecla>'9') {
						e.consume();
					}
				}
			});
			txtCodigoC.setBounds(20, 30, 67, 20);
			panel.add(txtCodigoC);
			txtCodigoC.setColumns(10);
			txtCodigoC.setText(ProductoControlador.codigoActual());
		}
		{
			txtStockMin = new JTextField();
			txtStockMin.setBounds(20, 88, 89, 20);
			panel.add(txtStockMin);
			txtStockMin.setColumns(10);
		}
		{
			JLabel lblDireccionC = new JLabel("Stock Minimo");
			lblDireccionC.setBounds(20, 69, 112, 14);
			panel.add(lblDireccionC);
		}
		{
			JLabel lblTelefonoC = new JLabel("Stock Maximo");
			lblTelefonoC.setBounds(413, 69, 73, 14);
			panel.add(lblTelefonoC);
		}
		{
			txtStockMax = new JTextField();
			txtStockMax.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if(tecla<'0' || tecla>'9') {
						e.consume();
					}
				}
			});
			txtStockMax.setBounds(414, 87, 112, 20);
			panel.add(txtStockMax);
			txtStockMax.setColumns(10);
		}
		{
			btnAgregar = new JButton("Agregar");
			btnAgregar.setBounds(239, 119, 89, 23);
			panel.add(btnAgregar);
			{
				//JButton btnModificar = new JButton("Modificar");//Boton Accion Modificar
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int tipoOcurrencia=filtrosTxt(
								txtAStringTrim(txtNombre),
								txtAStringTrim(txtPrecio),
								txtAStringTrim(txtStockMin),
								txtAStringTrim(txtStockMax),
								txtAStringTrim(txtStockActual)
								);
						switch(tipoOcurrencia) {
						case 0: 
							JOptionPane.showMessageDialog(panel, "Hay campos de texto vacios!");
							break;
						case 1:
							JOptionPane.showMessageDialog(panel, "El campo precio no puede estar vacio");
							break;							
						case 2:
							JOptionPane.showMessageDialog(panel, "El campo stock Minimo no puede estar vacio");
							break;
						case 3:
							JOptionPane.showMessageDialog(panel, "El campo stock Maximo no puede estar vacio");
							break;
						case 4:
							JOptionPane.showMessageDialog(panel, "El campo stock Actual no puede estar vacio");
							break;
						case 5:
							//Recibimos los datos y lo mandamos al controlador para que ubique los datos y los modifique
							boolean verificador = ProductoControlador.modificarProducto(
									txtAStringTrim(txtCodigoC),
									txtAStringTrim(txtNombre),
									aDouble(txtAStringTrim(txtPrecio)),
									aInt(txtAStringTrim(txtStockMin)),
									aInt(txtAStringTrim(txtStockMax)),
									aInt(txtAStringTrim(txtStockActual))
									);
							setValoresTabla();
							
							if(!verificador) {
								JOptionPane.showMessageDialog(panel,"Error en la modificacion!\nEl codigo ingresado no esta registrado!");
							}
							else {
								JOptionPane.showMessageDialog(panel,"Datos modificados con Exito!");
							}
							break;
						}
						
					}
				});
				btnModificar.setBounds(338, 119, 89, 23);
				panel.add(btnModificar);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");//Boton Eliminar Cliente
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnModificar.setEnabled(false);
						if(!ProductoControlador.eliminarProducto(txtAStringTrim(txtCodigoC))) {
							JOptionPane.showMessageDialog(panel, "Error! No se pudo eliminar al cliente solicitado!\nRevise el codigo ingresado!");
						}
						else {
							borrarCampos();
							ingresarNeoCodigo();
							setValoresTabla();
						}
					}
				});
				btnEliminar.setBounds(437, 119, 89, 23);
				panel.add(btnEliminar);
			}
			
			JButton btnConsultar = new JButton("Consultar");
			btnConsultar.addActionListener(new ActionListener() {//Accion Boton Consultar
				public void actionPerformed(ActionEvent e) {
					btnModificar.setEnabled(true);
					String[]datosClXCod=ProductoControlador.obtenerDatosProducto(txtAStringTrim(txtCodigoC));
					if(!(datosClXCod==null)) {
						insertarDatos(datosClXCod);
					}
					else {
						JOptionPane.showMessageDialog(panel,"El codigo ingresado no coincide con ningun Cliente registrado!","Error en busqueda por codigo",JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			});
			btnConsultar.setBounds(10, 119, 89, 23);
			panel.add(btnConsultar);
			btnAgregar.addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {//Boton Agregar Producto
		btnModificar.setEnabled(false);
		int tipoOcurrencia=filtrosTxt(
				txtAStringTrim(txtCodigoC),
				txtAStringTrim(txtNombre),
				txtAStringTrim(txtPrecio),
				txtAStringTrim(txtStockMin),
				txtAStringTrim(txtStockMax),
				txtAStringTrim(txtStockActual)
				);
		switch(tipoOcurrencia) {
		case 0: 
			JOptionPane.showMessageDialog(this, "El codigo ingresado no es el correcto!\nRecuerda debe de seguir la secuencia de ingreso!\n".concat("Codigo actual: "+ProductoControlador.codigoActual()));
			break;
		case 1:
			JOptionPane.showMessageDialog(this, "Hay campos de texto vacios!");
			break;
		case 2:
			JOptionPane.showMessageDialog(this, "El precio no puede estar vacio!!");
			break;
		case 3:
			JOptionPane.showMessageDialog(this, "El Stock Minimo no puede estar vacio!");
			break;
		case 4:
			JOptionPane.showMessageDialog(this, "El Stock Maximo no puede estar vacio!");
			break;
		case 5:
			JOptionPane.showMessageDialog(this, "El Stock Actual no puede estar vacio!");
			break;
		case 6:
			if(ProductoControlador.ingresarProducto(
					txtAStringTrim(txtNombre),
					aDouble(txtAStringTrim(txtPrecio)),
					aInt(txtAStringTrim(txtStockMin)),
					aInt(txtAStringTrim(txtStockMax)),
					aInt(txtAStringTrim(txtStockActual))
					)) {
				borrarCampos();
				JOptionPane.showMessageDialog(this, "Datos agregados correctamente!");
				ingresarNeoCodigo();
				setValoresTabla();
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Este cliente ya esta registrado!","Error de ingreso de nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}
	
	private void borrarCampos() {
		txtNombre.setText("");
		txtPrecio.setText("");
		txtStockMin.setText("");
		txtStockMax.setText("");
		txtStockActual.setText("");
	}
	
	private void ingresarNeoCodigo() {
		txtCodigoC.setText(ProductoControlador.codigoActual());
	}
	
	
	public void insertarDatos(String[]datos) {
		txtNombre.setText(datos[1]);
		txtPrecio.setText(datos[2]);
		txtStockMin.setText(datos[4]);
		txtStockMax.setText(datos[5]);
		txtStockActual.setText(datos[3]);
	}
	
	//Filtro de Textos en el caso de ingresar un Producto
	public int filtrosTxt(String codigo,String nombres,String precio,String stockMin,String stockMax,String stockActual) {
		//Este metodo nos permitira saber si el codigo ingresado es correcto(si es mayor al codigo actual o menor)
		//Codigo actual = ultimo codigo del cliente registrado + 1.
		if(!(ProductoControlador.verificarCodCorrecto(codigo))) {
			return 0;
		} 
		else {
			String [] datosRec = {nombres,precio,stockMin,stockMax,stockActual};
			
			for(int i = 0; i<datosRec.length;i++) {
				if(datosRec[i].equals("") || precio.length()<0 || stockMin.length()<0 || stockMax.length()<0 || stockActual.length()<0) {
					if(precio.length()<0) {
						return 2;
					}
					if(stockMin.length()<0) {
						return 3;
					}
					if(stockMax.length()<0) {
						return 4;
					}
					if(stockActual.length()<0) {
						return 5;
					}
					return 1;
				}
			}
			
			return 6;
		}
			
	}
	
	public int filtrosTxt(String nombres,String precio,String stockMin,String stockMax,String stockActual ) {
		String [] datosRec = {nombres,precio,stockMin,stockMax,stockActual};
		
		for(int i = 0; i<datosRec.length;i++) {
			if(datosRec[i].equals("") || precio.length()<0 || stockMin.length()<0 || stockMax.length()<0 || stockActual.length()<0) {
				if(precio.length()<0) {
					return 1;
				}
				if(stockMin.length()<0) {
					return 2;
				}
				if(stockMax.length()<0) {
					return 3;
				}
				if(stockActual.length()<0) {
					return 4;
				}
				return 0;
			}
		}
		
		return 5;
	}
	
	public void setValoresTabla() {
		while(tablaProducto.getRowCount()>0) {
			tablaProducto.removeRow(0);
		}
		
		Producto[]datosRec = ProductoControlador.getValoresTabla();
		
		for (int i = 0; i < datosRec.length; i++) {
			Producto producto = datosRec[i];
			String[]datos= {String.valueOf(producto.getCodigoProducto()),producto.getNombre(),aString(producto.getPrecio()),aString(producto.getStockMinimo()),aString(producto.getStockMaximo()),aString(producto.getStockActual())};
			tablaProducto.addRow(datos);
		}
	}
	
	public String intString(int dato){
		return String.valueOf(dato);
	}
	
	public void definirTablaModelo() {
		tablaProducto.setColumnIdentifiers(new String[] {
				"Codigo", "Nombre", "Precio", "Stock Minimo", "Stock Maximo", "Stock Actual"
			});
	}
	
	//Metodos de Modificacion de datos
	public String txtAStringTrim(JTextField txt) {
		return txt.getText().trim();
	}
	
	public double aDouble(String dato) {
		return Double.parseDouble(dato);
	}
	
	public int aInt(String dato) {
		return Integer.parseInt(dato);
	}
	
	public <E> String aString(E dato) {
		return String.valueOf(dato);
	}
}
