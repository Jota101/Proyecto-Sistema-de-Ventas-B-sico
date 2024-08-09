package controlador;

import modelo.Producto;
import modelo.Cliente;
import modelo.DocProducto;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProductoControlador {

    public static boolean ingresarProducto(String nombre, double precio, int stockMinimo, int stockMaximo, int stockActual) {
        String siguienteCodigo = codigoActual();
        Producto pr = new Producto(Integer.parseInt(siguienteCodigo), nombre, precio, stockActual, stockMinimo, stockMaximo);
        DocProducto.registrarProductoArr(pr);
        return true;
    }

    public static String codigoActual() {
        int codigoUltPr = DocProducto.obtenerCodUltProducto();
        if (codigoUltPr == -1) {
            return "2001"; // Si no hay productos registrados, devuelve "2001"
        }
        return String.valueOf(codigoUltPr + 1);
    }

    public static Producto buscarProducto(String codigoStr) {
        return DocProducto.devolverProducto(codigoStr);
    }

    public static String[] obtenerDatosProducto(String codigoStr) {
        Producto producto = buscarProducto(codigoStr);
        if (producto != null) {
            String[] datos = {
                String.valueOf(producto.getCodigoProducto()),
                producto.getNombre(),
                String.valueOf(producto.getPrecio()),
                String.valueOf(producto.getStockActual()),
                String.valueOf(producto.getStockMinimo()),
                String.valueOf(producto.getStockMaximo())
            };
            return datos;
        }
        return null;
    }

    public static boolean verfSiPrExisteXCodigo(String codigo) {
        return DocProducto.getPosicionProducto(codigo) != -1;
    }

    public static boolean modificarProducto(String codigo, String nombre, double precio, int stockMinimo, int stockMaximo, int stockActual) {
        if (verfSiPrExisteXCodigo(codigo)) {
            Producto producto = buscarProducto(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setStockActual(stockActual);
            producto.setStockMinimo(stockMinimo);
            producto.setStockMaximo(stockMaximo);
            DocProducto.grabarProductoDoc();
            return true;
        }
        return false;
    }

    public static ArrayList<Producto> obtenerListaArrProductos() {
        return DocProducto.getProductosArr();
    }

    public static Producto[] getValoresTabla() {
        ArrayList<Producto> datosPr = obtenerListaArrProductos();
        Producto[] datos = new Producto[datosPr.size()];
        datos = datosPr.toArray(datos);
        return datos;
    }

    public static boolean eliminarProducto(String codigo) {
        int posicion = DocProducto.getPosicionProducto(codigo);
        if (posicion != -1) {
            DocProducto.eliminarProductoDoc(posicion);
            return true;
        }
        return false;
    }

    public static void LimpiarArrayProductos() {
        DocProducto.LimpiarArrayProductos();
    }

    public static int obtenerCodUltProducto() {
        return DocProducto.obtenerCodUltProducto();
    }

    public static boolean verificarCodCorrecto(String codigo) {
        int codUltPr = Integer.parseInt(codigoActual()) - 1;
        int cod = Integer.parseInt(codigo);
        if(!(codUltPr<cod) || ((codUltPr)>cod) || cod==codUltPr) {
			return false;
		}
		return true;
    }

	    public static boolean ingresarStock(String codigo, int cantidad) {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            int nuevoStock = producto.getStockActual() + cantidad;

            // Verificar límites de stock mínimo y máximo
            if (nuevoStock < producto.getStockMinimo()) {
                // Si el nuevo stock es menor al mínimo permitido, mostrar mensaje y no actualizar
                JOptionPane.showMessageDialog(null, "La cantidad ingresada no cumple con el stock mínimo.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (nuevoStock > producto.getStockMaximo()) {
                // Si el nuevo stock es mayor al máximo permitido, mostrar mensaje y ajustar al máximo permitido
                JOptionPane.showMessageDialog(null, "La cantidad ingresada excede el stock máximo permitido.", "Error", JOptionPane.ERROR_MESSAGE);
                nuevoStock = producto.getStockMaximo();
            }

            // Actualizar el stock actual del producto
            producto.setStockActual(nuevoStock);

            // Actualizar los datos en el archivo Productos.txt
            DocProducto.grabarProductoDoc();

            return true;
        }
        return false; // Producto no encontrado
    }
	
}
