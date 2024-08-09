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

public class DocVenta {
    private ArrayList<Venta> datosVenta = new ArrayList<>();
    private static final String RUTA_ARCHIVO = "src/modelo/Ventas.txt";

    public DocVenta() {
        // datosVenta = new ArrayList<>();
    }

    // Metodo para agregar al ArrayList datosVenta
    public void agregarVentaArr(Venta venta) {
        datosVenta.add(venta);
        grabarVenta();
        // borrarArrayListDespuesDeRegistro();
    }

    public void borrarArrayListDespuesDeRegistro() {
        datosVenta.clear();
    }

    // Metodo para devolver el ArrayList datosVenta
    public ArrayList<Venta> getDatosVentaArr() {
        return datosVenta;
    }

    // Metodo de escritura en documento "Ventas.txt"
    private void grabarVenta() {
        Path rutaArchivo = Paths.get(RUTA_ARCHIVO);
        try {
            if (!Files.exists(rutaArchivo.getParent())) {
                Files.createDirectories(rutaArchivo.getParent());
            }
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
                for (Venta venta : datosVenta) {
                    String datos = "Codigo de Venta: ".concat(aString(venta.getCodigoVenta()))
                            .concat("; Codigo Cliente: ").concat(aString(venta.getCodigoCliente()))
                            .concat("; Codigo Producto: ").concat(aString(venta.getCodigoProducto()))
                            .concat("; Cantidad: ").concat(aString(venta.getCantidad()))
                            .concat("; Precio: ").concat(aString(venta.getPrecio()))
                            .concat("; Fecha: ").concat(venta.getFecha());
                    escritor.write(datos);
                    escritor.newLine();
                }
                escritor.flush(); // Asegura que todos los datos se escriben en el archivo
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("No se pudo crear el directorio o el archivo: " + e.getMessage());
        }
    }

    // Metodo de lectura documento "Ventas.txt", los datos se guardaran en el ArrayList
    private void leerDoc() {
        String cadenaDato;
        Venta venta;
        try (BufferedReader lector = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            while ((cadenaDato = lector.readLine()) != null) {
                String[] datosDoc = cadenaDato.split("; ");

                // Dado que un String es inmutable usaremos un StringBuilder para concatenar los datos:
                StringBuilder arrayACadena = new StringBuilder();

                // Concatenamos los datos:
                for (int i = 0; i < datosDoc.length; i++) {
                    arrayACadena.append(datosDoc[i]);

                    if (i < datosDoc.length - 1) {
                        arrayACadena.append(": ");
                    }
                }

                // Separamos los datos para que solo queden los datos netamente separandolos de sus tipos
                datosDoc = arrayACadena.toString().split(": ");
                venta = new Venta(
                        aInt(datosDoc[1]), // Valor de Codigo Venta
                        aInt(datosDoc[3]), // Valor de Codigo Cliente
                        aInt(datosDoc[5]), // Valor de Codigo Producto
                        aInt(datosDoc[7]), // Valor de Cantidad
                        aDouble(datosDoc[9]), // Valor de Precio
                        datosDoc[11] // Valor de Fecha
                );
                datosVenta.add(venta);
            }
        } catch (IOException e) {
            System.err.println("No existe archivo para leer o error al leer el archivo: " + e.getMessage());
        }
    }

    public int obtenerCodUltVenta() {
        if (datosVenta.size() == 0) {
            leerDoc();
        }

        if (datosVenta.isEmpty() || datosVenta.size() == 0) {
            return -1;
        }
        return datosVenta.get(datosVenta.size() - 1).getCodigoVenta();
    }

    // Metodos de transformacion
    public <E> String aString(E dato) {
        return String.valueOf(dato);
    }

    private int aInt(String dato) {
        return Integer.parseInt(dato);
    }

    private double aDouble(String dato) {
        return Double.parseDouble(dato);
    }
}
