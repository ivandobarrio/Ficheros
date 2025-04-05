package modelo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Main {

		public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Introduce el nombre del archivo a duplicar: ");
	        String nombreArchivo = scanner.nextLine();
	        
	        // Crear el nombre del archivo de copia
	        String nombreCopia = "copia_de_" + nombreArchivo;

	        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
	             BufferedWriter bw = new BufferedWriter(new FileWriter(nombreCopia))) {
	             
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                bw.write(linea);
	                bw.newLine(); // Escribir una nueva línea en el archivo de copia
	            }
	            System.out.println("El archivo ha sido duplicado como: " + nombreCopia);
	        } catch (IOException e) {
	            System.out.println("Error al procesar el archivo: " + e.getMessage());
	        } finally {
	            scanner.close();
	        }
	    }
	}