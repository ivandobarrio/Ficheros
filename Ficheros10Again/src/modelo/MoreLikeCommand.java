package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MoreLikeCommand {
	 public static void mostrarArchivoEnPaginas(String nombreArchivo) {
	        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
	            String linea;
	            int contador = 0;
	            Scanner scanner = new Scanner(System.in);

	            while ((linea = br.readLine()) != null) {
	                System.out.println(linea);
	                contador++;

	                // Si hemos mostrado 24 líneas, esperamos a que el usuario presione Enter
	                if (contador == 24) {
	                    System.out.print("Presiona Enter para continuar...");
	                    scanner.nextLine(); // Espera a que el usuario presione Enter
	                    contador = 0; // Reinicia el contador
	                }
	            }
	            scanner.close();
	        } catch (IOException e) {
	            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
	        }
	 }
}