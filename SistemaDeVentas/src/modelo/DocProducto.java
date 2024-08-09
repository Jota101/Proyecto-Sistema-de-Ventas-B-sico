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

public class DocProducto {
    
    private static ArrayList<Producto> datosProductos = new ArrayList<>();
    private static final String RUTA_ARCHIVO = "src/modelo/Productos.txt";

    public static void registrarProductoArr(Producto pr) {
        datosProductos.add(pr);
        grabarProductoDoc();
    }

    public static ArrayList<Producto> getProductosArr() {
        if (datosProductos.size() == 0) {
            leerDoc();
        }
        return datosProductos;
    }

    public static void LimpiarArrayProductos() {
        datosProductos.clear();
    }

    public static void grabarProductoDoc() {
        Path rutaArchivo = Paths.get(RUTA_ARCHIVO);

        try {
            if (!Files.exists(rutaArchivo.getParent())) {
                Files.createDirectories(rutaArchivo.getParent());
            }
            try {
                BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, false));

                for (int i = 0; i < datosProductos.size(); i++) {
                    Producto producto = datosProductos.get(i);
                    String datos = "Codigo: ".concat(String.valueOf(producto.getCodigoProducto()))
                            .concat("; Nombre: ").concat(producto.getNombre())
                            .concat("; Precio: ").concat(String.valueOf(producto.getPrecio()))
                            .concat("; Stock Actual: ").concat(String.valueOf(producto.getStockActual()))
                            .concat("; Stock Minimo: ").concat(String.valueOf(producto.getStockMinimo()))
                            .concat("; Stock Maximo: ").concat(String.valueOf(producto.getStockMaximo()));
                    escritor.write(datos);
                    escritor.newLine();
                }
                escritor.close();
            } catch (IOException e) {
                System.err.println("El archivo de texto no existe, por lo tanto no se ha escrito nada!");
            }

        } catch (Exception e) {
            System.out.println("El archivo de texto no existe o no se puede escribir en el!");
        }
    }

    public static void leerDoc() {
        String[] datosDoc;
        String cadenaDato;
        Producto producto;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(RUTA_ARCHIVO));
            while ((cadenaDato = lector.readLine()) != null) {
                datosDoc = cadenaDato.split("; ");

                StringBuilder arrayACadena = new StringBuilder();

                for (int i = 0; i < datosDoc.length; i++) {
                    arrayACadena.append(datosDoc[i]);

                    if (i < datosDoc.length - 1) {
                        arrayACadena.append(": ");
                    }
                }

                datosDoc = arrayACadena.toString().split(": ");
                producto = new Producto(
                        Integer.parseInt(datosDoc[1]),  // Codigo
                        datosDoc[3],                    // Nombre
                        Double.parseDouble(datosDoc[5]),// Precio
                        Integer.parseInt(datosDoc[7]),  // Stock Actual
                        Integer.parseInt(datosDoc[9]),  // Stock Minimo
                        Integer.parseInt(datosDoc[11])  // Stock Maximo
                );
                datosProductos.add(producto);
            }
            lector.close();
        } catch (Exception e) {
            System.err.println("NO Existe archivo para leer!");
        }
    }

    public static int obtenerCodUltProducto() {
        if (datosProductos.size() == 0) {
            leerDoc();
        }

        if (datosProductos.isEmpty() || datosProductos.size() == 0) {
            return -1;
        }
        return datosProductos.get(datosProductos.size() - 1).getCodigoProducto();
    }

    public static void eliminarProductoDoc(int posicion) {
        datosProductos.remove(posicion);
        grabarProductoDoc();
    }

    public static int getPosicionProducto(String codigo) {
        int cod = Integer.parseInt(codigo);
        for (int i = 0; i < datosProductos.size(); i++) {
            Producto producto = datosProductos.get(i);
            if (producto.getCodigoProducto() == cod) {
                return i;
            }
        }
        return -1;
    }

    public static Producto devolverProducto(String codigo) {
    	if(datosProductos.isEmpty()) {
    		leerDoc();
    	}
        int cod = Integer.parseInt(codigo);
        for (int i = 0; i < datosProductos.size(); i++) {
            Producto producto = datosProductos.get(i);
            if (producto.getCodigoProducto() == cod) {
                return producto;
            }
        }
        return null;
    }
}
