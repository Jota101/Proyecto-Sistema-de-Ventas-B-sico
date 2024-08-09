package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class DocCliente {
	
	private  static ArrayList<Cliente> datosClientes=new ArrayList<>();
	private static final String RUTA_ARCHIVO ="src/modelo/Clientes.txt";
	
	public static void registrarClienteArr(Cliente cl) {
			datosClientes.add(cl);
			grabarClienteDoc();
	}
	
	public static ArrayList<Cliente> getClientesArr(){
		if(datosClientes.size()==0) {
			leerDoc();
		}
		return datosClientes;
	}
	
	public static void LimpiarArrayClientes() {
		datosClientes.clear();
	}
	
	public static void grabarClienteDoc() {
		Path rutaArchivo = Paths.get(RUTA_ARCHIVO);
		
		try {
			if(!Files.exists(rutaArchivo.getParent())) {
				Files.createDirectories(rutaArchivo.getParent());
			}
			try {
				BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_ARCHIVO,false));
				
				for (int i = 0; i < datosClientes.size(); i++) {
					Cliente cliente=datosClientes.get(i);
					String datos="Codigo: ".concat(String.valueOf(cliente.getCodigoCliente()))
							.concat("; Nombres: ").concat(cliente.getNombres())
							.concat("; Apellidos: ").concat(cliente.getApellidos())
							.concat("; Direccion: ").concat(cliente.getDireccion())
							.concat("; Telefono: ").concat(cliente.getTelefono())
							.concat("; Numero DNI: ").concat(cliente.getDni());
					escritor.write(datos);
					escritor.newLine();
				}
				escritor.close();
			}
			catch(IOException e) {
				System.err.println("El archivo de texto no existe, por lo tanto no se ha escrito nada!");
			}
			
			
		} catch (Exception e) {
			System.out.println("El archivo de texto no existe o no se puede escribir en el!");
		}
		
		
		
	}
	
	public static void leerDoc() {
		String[]datosDoc;
		String cadenaDato;
		Cliente cliente;
		try {
			BufferedReader lector = new BufferedReader(new FileReader("src/modelo/Clientes.txt"));
			while((cadenaDato=lector.readLine())!=null) {
				datosDoc=cadenaDato.split("; ");
				
				//Dado que un String es inmutable usaremos un StringBuilder para concatenar los datos:
				StringBuilder arrayACadena = new StringBuilder();
				
				//Concatenamos los datos:
				for (int i = 0; i < datosDoc.length; i++) {
					arrayACadena.append(datosDoc[i]);
					
					if(i<datosDoc.length-1) {
						arrayACadena.append(": ");
					}
				}
				
				//Separamos los datos para que solo queden los datos netamente separandolos de sus tipos
				datosDoc=arrayACadena.toString().split(": ");
				cliente = new Cliente(
						Integer.parseInt(datosDoc[1]),//Valor de Codigo
						datosDoc[3],//Valor de Nombres
						datosDoc[5],//Valor de Apellidos
						datosDoc[7],//Valor de Direccion
						datosDoc[9],//Valor de Telefono
						datosDoc[11]//Valor de Dni
						);
				datosClientes.add(cliente);
				//Codigo / 1000 / nombres / jair / apellidos / fernandez Bocanegra / direccion / jr.micaela b. / telefono / 13456789 / numero dni / 13245678
				//   0       1       2        3         4               5                6            7              8          9           10         11
			}
			lector.close();
		} catch (Exception e) {
			System.err.println("NO Existe archivo para leer!");
		}
	}
	
	public static int obtenerCodUltCliente() {
		if(datosClientes.size()==0) {
			leerDoc();
		}
		
		if(datosClientes.isEmpty()||datosClientes.size()==0) {
			return -1;
		}
		int codUltCliente = datosClientes.get(datosClientes.size()-1).getCodigoCliente();
		return codUltCliente;
	}
	
	public static void eliminarClienteDoc(int posicion) {
		datosClientes.remove(posicion);
		grabarClienteDoc();
	}
	
	public static int getPosicionCliente(String codigo) {
		int cod=Integer.parseInt(codigo);
		for (int i = 0; i < datosClientes.size(); i++) {
			Cliente cliente = datosClientes.get(i);
			if(cliente.getCodigoCliente()==cod) {
				return i;
			}
		}
		return -1;
	}
	
	public static Cliente devolverCliente(String codigo) {
		if(datosClientes.isEmpty()) {
			leerDoc();
		}
		for (int i = 0; i < datosClientes.size(); i++) {
			Cliente cliente;
			cliente = datosClientes.get(i);
			//String cod = String.valueOf(cliente.getCodigoCliente());
			if((String.valueOf(cliente.getCodigoCliente())).equals(codigo)) {
				return cliente;
			}
		}
		
		return null;
	}
}
