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

import controlador.ClienteControlador;
import modelo.Cliente;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Clientes extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtCodigoC;
	private JTextField txtNombresC;
	private JTextField txtApellidosC;
	private JTextField txtDniC;
	private JTextField txtDireccionC;
	private JTextField txtTelefonoC;
	private JButton btnNewButton;
	
	//Instanciamos el modelo de nuestra tabla
	private DefaultTableModel tablaCliente = new DefaultTableModel();
	
	private JButton btnModificar = new JButton("Modificar");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
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
				table.setModel(tablaCliente);
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
			lblCodigoC.setBounds(10, 11, 46, 14);
			panel.add(lblCodigoC);
		}
		{
			JLabel lblNombresC = new JLabel("Nombres");
			lblNombresC.setBounds(101, 11, 67, 14);
			panel.add(lblNombresC);
		}
		{
			JLabel lblApellidosC = new JLabel("Apellidos");
			lblApellidosC.setBounds(274, 11, 86, 14);
			panel.add(lblApellidosC);
		}
		{
			JLabel lblDniC = new JLabel("DNI");
			lblDniC.setBounds(440, 11, 46, 14);
			panel.add(lblDniC);
		}
		{
			txtDniC = new JTextField();
			txtDniC.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if(tecla<'0' || tecla>'9') {
						e.consume();
					}
					if(txtDniC.getText().length()>7) {
						e.consume();
					}
				}
			});
			txtDniC.setBounds(440, 30, 86, 20);
			panel.add(txtDniC);
			txtDniC.setColumns(10);
		}
		{
			txtApellidosC = new JTextField();
			txtApellidosC.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if((tecla<'a' || tecla>'z') && (tecla<'A' || tecla>'Z') && tecla!=' ') {
						e.consume();
					}
				}
			});
			txtApellidosC.setBounds(274, 30, 145, 20);
			panel.add(txtApellidosC);
			txtApellidosC.setColumns(10);
		}
		{
			txtNombresC = new JTextField();
			txtNombresC.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if((tecla<'a' || tecla>'z') && (tecla<'A' || tecla>'Z') && tecla!=' ') {
						e.consume();
					}
				}
			});
			txtNombresC.setBounds(101, 30, 145, 20);
			panel.add(txtNombresC);
			txtNombresC.setColumns(10);
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
			txtCodigoC.setBounds(10, 30, 67, 20);
			panel.add(txtCodigoC);
			txtCodigoC.setColumns(10);
			txtCodigoC.setText(ClienteControlador.codigoActual());
		}
		{
			txtDireccionC = new JTextField();
			txtDireccionC.setBounds(10, 88, 381, 20);
			panel.add(txtDireccionC);
			txtDireccionC.setColumns(10);
		}
		{
			JLabel lblDireccionC = new JLabel("Dirección");
			lblDireccionC.setBounds(10, 63, 112, 14);
			panel.add(lblDireccionC);
		}
		{
			JLabel lblTelefonoC = new JLabel("Teléfono");
			lblTelefonoC.setBounds(401, 70, 73, 14);
			panel.add(lblTelefonoC);
		}
		{
			txtTelefonoC = new JTextField();
			txtTelefonoC.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char tecla = e.getKeyChar();
					if(tecla<'0' || tecla>'9') {
						e.consume();
					}
					if(txtTelefonoC.getText().length()>8) {
						e.consume();
					}
				}
			});
			txtTelefonoC.setBounds(402, 88, 112, 20);
			panel.add(txtTelefonoC);
			txtTelefonoC.setColumns(10);
		}
		{
			btnNewButton = new JButton("Agregar");
			btnNewButton.setBounds(239, 119, 89, 23);
			panel.add(btnNewButton);
			{
				//JButton btnModificar = new JButton("Modificar");//Boton Accion Modificar
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int tipoOcurrencia=filtrosTxt(
								txtAStringTrim(txtNombresC),
								txtAStringTrim(txtApellidosC),
								txtAStringTrim(txtDireccionC),
								txtAStringTrim(txtTelefonoC),
								txtAStringTrim(txtDniC)
								);
						switch(tipoOcurrencia) {
						case 0: 
							JOptionPane.showMessageDialog(panel, "Hay campos de texto vacios!");
							break;
						case 1:
							JOptionPane.showMessageDialog(panel, "El numero de Dni no puede tener menos de 8 caracteres!");
							break;							
						case 2:
							JOptionPane.showMessageDialog(panel, "El numero de telefono no puede tener menos de 9 caracteres!");
							break;
						case 3:
							//Recibimos los datos y lo mandamos al controlador para que ubique los datos y los modifique
							boolean verificador = ClienteControlador.modificarCliente(
									txtAStringTrim(txtCodigoC),
									txtAStringTrim(txtNombresC),
									txtAStringTrim(txtApellidosC),
									txtAStringTrim(txtDireccionC),
									txtAStringTrim(txtTelefonoC),
									txtAStringTrim(txtDniC)
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
						if(!ClienteControlador.eliminarCliente(txtAStringTrim(txtCodigoC))) {
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
					String[]datosClXCod=ClienteControlador.obtenerDatosCliente(txtAStringTrim(txtCodigoC));
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
			btnNewButton.addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(e);
		}
	}
	protected void actionPerformedBtnNewButton(ActionEvent e) {//Boton Agregar Cliente
		btnModificar.setEnabled(false);
		int tipoOcurrencia=filtrosTxt(
				txtAStringTrim(txtCodigoC),
				txtAStringTrim(txtNombresC),
				txtAStringTrim(txtApellidosC),
				txtAStringTrim(txtDireccionC),
				txtAStringTrim(txtTelefonoC),
				txtAStringTrim(txtDniC)
				);
		switch(tipoOcurrencia) {
		case 0: 
			JOptionPane.showMessageDialog(this, "El codigo ingresado no es el correcto!\nRecuerda debe de seguir la secuencia de ingreso!\n".concat("Codigo actual: "+ClienteControlador.codigoActual()));
			break;
		case 1:
			JOptionPane.showMessageDialog(this, "Hay campos de texto vacios!");
			break;
		case 2:
			JOptionPane.showMessageDialog(this, "El numero de Dni no puede tener menos de 8 caracteres!");
			break;
		case 3:
			JOptionPane.showMessageDialog(this, "El numero de telefono no puede tener menos de 9 caracteres!");
			break;
		case 4:
			if(ClienteControlador.ingresarCliente(
					txtNombresC.getText(),
					txtApellidosC.getText(),
					txtDireccionC.getText(),
					txtTelefonoC.getText(),
					txtDniC.getText()
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
		txtNombresC.setText("");
		txtApellidosC.setText("");
		txtDireccionC.setText("");
		txtTelefonoC.setText("");
		txtDniC.setText("");
	}
	
	private void ingresarNeoCodigo() {
		txtCodigoC.setText(ClienteControlador.codigoActual());
	}
	
	public String txtAStringTrim(JTextField txt) {
		return txt.getText().trim();
	}
	public void insertarDatos(String[]datos) {
		txtNombresC.setText(datos[0]);
		txtApellidosC.setText(datos[1]);
		txtDireccionC.setText(datos[2]);
		txtTelefonoC.setText(datos[3]);
		txtDniC.setText(datos[4]);
	}
	
	public int filtrosTxt(String codigo, String nombres,String apellidos,String direccion,String telefono,String dni ) {
		//Este metodo nos permitira saber si el codigo ingresado es correcto(si es mayor al codigo actual o menor)
		//Codigo actual = ultimo codigo del cliente registrado + 1.
		if(!(ClienteControlador.verificarCodCorrecto(codigo))) {
			return 0;
		} 
		else {
			//"datosRec = Datos Recibidos"
			String [] datosRec = {nombres,apellidos,direccion,telefono,dni};
			
			for(int i = 0; i<datosRec.length;i++) {
				if(datosRec[i].equals("") || dni.length()<8 || telefono.length()<9) {
					if(dni.length()<8) {
						return 2;
					}
					if(telefono.length()<9) {
						return 3;
					}
					return 1;
				}
			}
		}
		
		return 4;
	}
	
	public int filtrosTxt(String nombres,String apellidos,String direccion,String telefono,String dni ) {
		//Este metodo nos permitira saber si el codigo ingresado es correcto(si es mayor al codigo actual o menor)
		//Codigo actual = ultimo codigo del cliente registrado + 1.
		String [] datosRec = {nombres,apellidos,direccion,telefono,dni};
		
		for(int i = 0; i<datosRec.length;i++) {
			if(datosRec[i].equals("") || dni.length()<8 || telefono.length()<9) {
				if(dni.length()<8) {
					return 1;
				}
				if(telefono.length()<9) {
					return 2;
				}
				return 0;
			}
		}
		
		return 3;
	}
	
	public void setValoresTabla() {
		while(tablaCliente.getRowCount()>0) {
			tablaCliente.removeRow(0);
		}
		
		Cliente[]datosRec = ClienteControlador.getValoresTabla();
		
		for (int i = 0; i < datosRec.length; i++) {
			Cliente cliente = datosRec[i];
			String[]datos= {String.valueOf(cliente.getCodigoCliente()),cliente.getNombres(),cliente.getApellidos(),cliente.getDireccion(),cliente.getTelefono(),cliente.getDni()};
			tablaCliente.addRow(datos);
		}
	}
	
	public String intString(int dato){
		return String.valueOf(dato);
	}
	
	public void definirTablaModelo() {
		tablaCliente.setColumnIdentifiers(new String[] {
				"Codigo", "Nombres", "Apellidos", "Direccion", "Telefono", "DNI"
			});
	}
	
}
