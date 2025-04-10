package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String archivo1 = "lista1.txt"; // Nombre del primer archivo
        String archivo2 = "lista2.txt"; // Nombre del segundo archivo
        String archivoSalida = "resultado.txt"; // Nombre del archivo de salida

        List<Integer> listaNumeros = new ArrayList<>();
        
     // Leer los números del primer archivo
        leerNumerosDesdeArchivo(archivo1, archivoSalida, listaNumeros);
        // Leer los números del segundo archivo
        leerNumerosDesdeArchivo(archivo2, archivoSalida, listaNumeros);

        // Ordenar la lista
        Collections.sort(listaNumeros);

        // Guardar los números ordenados en el archivo de salida
        guardarNumerosEnArchivo(archivoSalida, listaNumeros);
	}
	private static void leerNumerosDesdeArchivo(String nombreArchivo1,String nombreArchivo2, List<Integer>lista) {
		try(BufferedReader br1 = new BufferedReader(new FileReader("src/modelo/lista1.txt"));
			BufferedReader br2= new BufferedReader(new FileReader("src/modelo/lista2.txt"))){
			String linea;
			while((linea=br1.readLine())!=null){
				try {
					int numero = Integer.parseInt(linea.trim());
					lista.add(numero);
				}catch(NumberFormatException e) {
					System.out.println("La línea '" + linea + "' no es un número válido y será ignorada.");
				}
			}
			while ((linea = br2.readLine()) != null) {
	            try {
	                int numero = Integer.parseInt(linea.trim());
	                lista.add(numero);
	            } catch (NumberFormatException e) {
	                System.out.println("La línea '" + linea + "' no es un número válido y será ignorada.");
	            }
	        }
		} catch (IOException e) {
			System.out.println("Ocurrió un error al intentar leer los archivos: " + e.getMessage());
		}
	}
	
	private static void guardarNumerosEnArchivo(String nombreArchivo, List<Integer>lista) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/modelo/resultado.txt"))){
			for(int numero: lista) {
				bw.write(String.valueOf(numero));
				bw.newLine();
			}
			System.out.println("Los números ordenados se han guardado en '" + nombreArchivo + "'.");
		}catch (IOException e) {
            System.out.println("Ocurrió un error al intentar guardar los datos en el archivo '" + nombreArchivo + "': " + e.getMessage());
		}
	}
}
