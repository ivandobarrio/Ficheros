package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		        Scanner scanner = new Scanner(System.in);
		        HashSet<String> firmas = new HashSet<>();

		        // Cargar firmas existentes
		         cargarFirmas(firmas);

		        // Mostrar firmas
		        System.out.println("Libro de Firmas:");
		        for (String firma : firmas) {
		            System.out.println(firma);
		        }

		        // Insertar nueva firma
		        System.out.print("Introduce un nuevo nombre para firmar: ");
		        String nuevoNombre = scanner.nextLine().trim();

		        if (firmas.contains(nuevoNombre)) {
		            System.out.println("El nombre '" + nuevoNombre + "' ya está registrado.");
		        } else {
		            // Agregar nueva firma
		            firmas.add(nuevoNombre);
		            guardarFirmas(firmas);
		            System.out.println("Nombre '" + nuevoNombre + "' registrado con éxito.");
		        }

		        scanner.close();
		    }

		    private static void cargarFirmas(HashSet<String> firmas) {
		        try (BufferedReader br = new BufferedReader(new FileReader("src/modelo/firmas.txt"))) {
		            String linea;
		            while ((linea = br.readLine()) != null) {
		                firmas.add(linea.trim());
		            }
		        } catch (IOException e) {
		            System.err.println("Error al leer el archivo: " + e.getMessage());
		        }
		    }

		    private static void guardarFirmas(HashSet<String> firmas) {
		        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/modelo/firmas.txt"))) {
		            for (String firma : firmas) {
		                bw.write(firma);
		                bw.newLine();
		            }
		        } catch (IOException e) {
		            System.err.println("Error al escribir en el archivo: " + e.getMessage());
		        }
		    }
		
	}


