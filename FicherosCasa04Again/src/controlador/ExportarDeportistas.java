package controlador;
import java.io.*;
import java.util.Scanner;

public class ExportarDeportistas {
    public static void main(String[] args) {
        String nombreArchivo = "deportistas.txt";
        String archivoNombresEdades = "nombres_edades.txt";
        String archivoNombresPesos = "nombres_pesos.txt";
        String archivoNombresEstaturas = "nombres_estaturas.txt";

        try (Scanner scanner = new Scanner(new File(nombreArchivo));
             BufferedWriter writerEdades = new BufferedWriter(new FileWriter(archivoNombresEdades));
             BufferedWriter writerPesos = new BufferedWriter(new FileWriter(archivoNombresPesos));
             BufferedWriter writerEstaturas = new BufferedWriter(new FileWriter(archivoNombresEstaturas))) {

            // Leer el encabezado
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Ignorar la línea del encabezado
            }

            // Leer los datos de los deportistas
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");

                if (datos.length == 4) {
                    String nombre = datos[0];
                    int edad = Integer.parseInt(datos[1]);
                    double peso = Double.parseDouble(datos[2]);
                    double estatura = Double.parseDouble(datos[3]);

                    // Escribir en los archivos correspondientes
                    writerEdades.write(nombre + "," + edad);
                    writerEdades.newLine();

                    writerPesos.write(nombre + "," + peso);
                    writerPesos.newLine();

                    writerEstaturas.write(nombre + "," + estatura);
                    writerEstaturas.newLine();
                }
            }

            System.out.println("Datos exportados a los archivos:");
            System.out.println("- " + archivoNombresEdades);
            System.out.println("- " + archivoNombresPesos);
            System.out.println("- " + archivoNombresEstaturas);

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al escribir en los archivos: " + e.getMessage());
        }
    }
}