package controlador;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Deportistas {
    public static void main(String[] args) {
        String nombreArchivo = "deportistas.txt";
        File archivo = new File(nombreArchivo);

        try (Scanner scanner = new Scanner(archivo)) {
            // Leer el encabezado
            if (scanner.hasNextLine()) {
                String encabezado = scanner.nextLine();
                System.out.println(encabezado);
            }

            int totalEdad = 0;
            double totalPeso = 0;
            double totalEstatura = 0;
            int contador = 0;

            // Leer los datos de los deportistas
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(",");

                if (datos.length == 4) {
                    String nombre = datos[0];
                    int edad = Integer.parseInt(datos[1]);
                    double peso = Double.parseDouble(datos[2]);
                    double estatura = Double.parseDouble(datos[3]);

                    // Mostrar los datos
                    System.out.printf("Nombre: %s, Edad: %d, Peso: %.2f, Estatura: %.2f%n", nombre, edad, peso, estatura);

                    // Acumular los totales
                    totalEdad += edad;
                    totalPeso += peso;
                    totalEstatura += estatura;
                    contador++;
                }
            }

            // Calcular y mostrar los promedios
            if (contador > 0) {
                double promedioEdad = (double) totalEdad / contador;
                double promedioPeso = totalPeso / contador;
                double promedioEstatura = totalEstatura / contador;

                System.out.printf("Promedio Edad: %.2f%n", promedioEdad);
                System.out.printf("Promedio Peso: %.2f%n", promedioPeso);
                System.out.printf("Promedio Estatura: %.2f%n", promedioEstatura);
            } else {
                System.out.println("No se encontraron datos de deportistas.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encontró: " + e.getMessage());
        }
    }
}