package controlador;

import modelo.Cliente;
import modelo.DocCliente;
import vista.Clientes;
import static modelo.DocCliente.*;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClienteControlador {
	public static boolean ingresarCliente(String nombres, String apellidos, String direccion, String telefono, String dni) {
		//Buscamos si cliente esta registrado en la BD por su numero de dni
		boolean verificador = verfSiClExisteXDni(dni);
		if(!verificador) {
			Cliente cl = new Cliente(nombres,apellidos,direccion,telefono,dni);
			registrarClienteArr(cl);//Metodo registrar cliente de la clase DocCliente
			return true;
		}	
		return false;
	}
	
	public static String codigoActual() {
		int codigoUltCl=obtenerCodUltCliente();
		if(codigoUltCl==-1) {
			return "1000";
		}
		
		return String.valueOf(codigoUltCl+1);
	}
	
	public static Cliente buscarCliente(String codigoStr) {
		int codigoInt = Integer.parseInt(codigoStr);
		ArrayList <Cliente> datosCl=getClientesArr();
		for (int i = 0; i < datosCl.size(); i++) {
			Cliente cl = datosCl.get(i);
			int codCl = cl.getCodigoCliente();
			if(codCl==codigoInt) {
				return cl;
			}
		}
		
		return null;
	}
	
	public static String[] obtenerDatosCliente(String codigoStr) {
		Cliente cliente;
		if(!((cliente=buscarCliente(codigoStr))==null)) {
			String[]datos= {cliente.getNombres(),cliente.getApellidos(),cliente.getDireccion(),cliente.getTelefono(),cliente.getDni()};
			return datos;
		}
		return null;
	}
	
	public static boolean verfSiClExisteXDni(String dni) {
		ArrayList<Cliente>datos=getClientesArr();
		for (int i = 0; i < datos.size(); i++) {
			Cliente cliente=datos.get(i);
			String dniCl=cliente.getDni();
			if(dniCl.equals(dni)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean verfSiClExisteXCodigo(String codigo) {
		ArrayList<Cliente>datos=getClientesArr();
		for (int i = 0; i < datos.size(); i++) {
			Cliente cliente=datos.get(i);
			String codCl= String.valueOf(cliente.getCodigoCliente());
			if(codCl.equals(codigo)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean modificarCliente(String codigo, String nombres, String apellidos, String direccion, String telefono, String dni) {
		
		if(verfSiClExisteXCodigo(codigo)) {
			Cliente cliente = buscarCliente(codigo);
			cliente.setNombres(nombres);
			cliente.setApellidos(apellidos);
			cliente.setDireccion(direccion);
			cliente.setTelefono(telefono);
			cliente.setDni(dni);
			grabarClienteDoc();
			/*ArrayList<Cliente>datosClientes=getClientesArr();
			for (int i = 0; i < datosClientes.size(); i++) {
				Cliente cliente = datosClientes.get(i);
				String codCl= String.valueOf(cliente.getCodigoCliente());
				if(codCl.equals(codigo)) {
					cliente.setNombres(nombres);
					cliente.setApellidos(apellidos);
					cliente.setDireccion(direccion);
					cliente.setTelefono(telefono);
					cliente.setDni(dni);
					grabarClienteDoc();
					break;
				}
			}*/
			
			return true;
		}
		return false;
	}
	
	public static boolean verificarCodCorrecto(String codigo) {//1007
		int codUltCl=Integer.parseInt(codigoActual())-1;//1006
		int cod = Integer.parseInt(codigo);
		if(!(codUltCl<cod) || ((cod+1)<cod) || cod==codUltCl) {
			return false;
		}
		return true;
	}
	
	public static ArrayList<Cliente> obtenerListaArrClientes() {
		return getClientesArr();
	}
	
	public static Cliente[] getValoresTabla() {
		//Para evitar que el arrayList datosClientes se sobrecargue
		//de datos repetidos, previamente lo limpio y lo actualizo
		//con los nuevos datos ingresados
		LimpiarArrayClientes();//Limpiamos el ArrayList
		leerDoc();//Actualizamos datosClientes
		ArrayList<Cliente>datosCl = getClientesArr();
		Cliente[] datos=new Cliente[datosCl.size()];
		datos=datosCl.toArray(datos);
		/*int i = 0;
		for (Cliente cl : datosCl) {
			datos[i]=cl;
			i++;
		}*/
		return datos; 
	}
	
	
	
	public static boolean eliminarCliente(String codigo) {
		int posicion = getPosicionCliente(codigo);
		if(!(posicion==-1)) {
			eliminarClienteDoc(posicion);
			return true;
		}
		return false;
	}
}
