package controlador;

import modelo.Cliente;
import modelo.DocVenta;
import modelo.Producto;
import modelo.Venta;
import modelo.DocCliente;
import modelo.DocProducto;
import modelo.DocCliente;
import static modelo.DocVenta.*;

import java.util.ArrayList;


public class VentaControlador {
	private DocVenta ventaBd = new DocVenta();
	public VentaControlador() {}
	
	public void agregarVenta(ArrayList<String>ventas) {
		for (int i = 0; i < ventas.size(); i++) {
			//Venta ventaReg=null;
			/*try {//                   codigo venta                    codigo cliente                  codigo Producto                 cantidad                        precio producto       fecha
				Venta ventaReg = new Venta(Integer.parseInt(ventas.get(0)),Integer.parseInt(ventas.get(1)),Integer.parseInt(ventas.get(2)),Integer.parseInt(ventas.get(3)),aDouble(ventas.get(4)),ventas.get(5));

			} catch (Exception e) {
				System.err.println("Error inesperado");
			}*/
			Venta ventaReg = new Venta(Integer.parseInt(ventas.get(0)),Integer.parseInt(ventas.get(1)),Integer.parseInt(ventas.get(2)),Integer.parseInt(ventas.get(3)),Double.parseDouble(ventas.get(4)),ventas.get(5));

			ventaBd.agregarVentaArr(ventaReg);
		}
	}
	
	public Cliente getClienteXCodigo(String codigo) {
		return DocCliente.devolverCliente(codigo);
	}
	
	private Venta[] getVentasArrayList() {
		ArrayList <Venta> datos =ventaBd.getDatosVentaArr();
		Venta[] ventaArr = new Venta[datos.size()];
		for (int i = 0; i < datos.size(); i++) {
			ventaArr[i]=datos.get(i);
		}
		return ventaArr;
	}
	
	public String[] retornarValoresArray() {
		Venta[]datos = getVentasArrayList();
		String[] valores = new String[datos.length];
		for (int i = 0; i < datos.length; i++) {
			//StringBuilder cadena = new StringBuilder();
			Venta venta =  datos[i];
			String cadena = aString(venta.getCodigoVenta()).concat(", ")
							.concat(aString(venta.getCodigoCliente())).concat(", ")
							.concat(aString(venta.getCodigoProducto())).concat(", ")
							.concat(aString(venta.getCantidad())).concat(", ")
							.concat(aString(venta.getPrecio())).concat(", ")
							.concat(venta.getFecha());
			valores[i]=cadena;
		}
		
		return valores;
	}
	
	public String[] retornarValoresGUI() {
		Venta[]datos = getVentasArrayList();
		String[] valores = new String[datos.length];
		for (int i = 0; i < datos.length; i++) {
			//StringBuilder cadena = new StringBuilder();
			Venta venta =  datos[i];
			String cadena = aString(venta.getCodigoVenta()).concat(", ")
							.concat(aString(venta.getCodigoCliente())).concat(", ")
							.concat(aString(venta.getCodigoProducto())).concat(", ")
							.concat(aString(venta.getCantidad())).concat(", ")
							.concat(aString(venta.getPrecio())).concat(", ")
							.concat(venta.getFecha());
			valores[i]=cadena;
		}
		
		return valores;
	}
	
	//Metodo que recibe el Cliente y que devuelve los datos del cliente a la GUI
	public String retornarValoresCliente(String codigo) {
		Cliente cliente;
		if((cliente = DocCliente.devolverCliente(codigo))!=null) {
			String datos = cliente.getNombres().concat(" ").concat(cliente.getApellidos());
			return datos;
		}
		return null;
	}
	
	//Metodo que recibe el Producto y que devuelve los datos del producto a la GUI
	public String[] retornarValoresProducto(String codigo) {
		Producto producto;
		if((producto=DocProducto.devolverProducto(codigo))!=null) {
			String[]datos = {producto.getNombre(),aString(producto.getPrecio())};
			return datos;
		}
		return null;
	}
	
	//Metodo que genera codigo
	public String generarCodigo() {
		int codigo;
		if((codigo=ventaBd.obtenerCodUltVenta())!=-1) {
			return aString(codigo);
		}
		return "3001";
	}
	
	//Metodos de transformacion
		private <E> String aString (E dato){
			return String.valueOf(dato);
		}
		
		private int aInt(String dato) {
			return Integer.parseInt(dato);
		}
		
		private double aDouble(String dato) {
			return Double.parseDouble(dato);
		}
}
