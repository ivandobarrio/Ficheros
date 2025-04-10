package controlador;
import java.io.*;
import java.util.Scanner;

public class BuscadorDeCadenas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre del archivo
        System.out.print("Introduce el nombre del archivo: ");
        String nombreArchivo = scanner.nextLine();

        // Solicitar la cadena a buscar
        System.out.print("Introduce la cadena a buscar: ");
        String cadenaABuscar = scanner.nextLine();

        buscarCadenaEnArchivo(nombreArchivo, cadenaABuscar);

        scanner.close();
    }

    private static void buscarCadenaEnArchivo(String nombreArchivo, String cadenaABuscar) {
        File archivo = new File(nombreArchivo);

        // Verificar si el archivo existe
        if (!archivo.exists()) {
            System.out.println("El archivo no existe: " + nombreArchivo);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 0;
            boolean encontrado = false;

            // Leer el archivo línea por línea
            while ((linea = reader.readLine()) != null) {
                numeroLinea++;
                int ocurrencias = contarOcurrencias(linea, cadenaABuscar);

                if (ocurrencias > 0) {
                    System.out.printf("Línea %d: %s (Ocurrencias: %d)%n", numeroLinea, linea, ocurrencias);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("No se encontraron ocurrencias de la cadena: " + cadenaABuscar);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static int contarOcurrencias(String linea, String cadenaABuscar) {
        int contador = 0;
        int index = 0;

        // Buscar todas las ocurrencias de la cadena en la línea
        while ((index = linea.indexOf(cadenaABuscar, index)) != -1) {
            contador++;
            index += cadenaABuscar.length(); // Mover el índice hacia adelante
        }

        return contador;
    }
}