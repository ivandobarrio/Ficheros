package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println();
		String nombreFichero = sc.nextLine();
		
		if(nombreFichero.isEmpty()) {
			nombreFichero = "prueba.txt";
		}
		mostrarContenidoFichero(nombreFichero);
		sc.close();
	}
	private static void mostrarContenidoFichero(String nombreFichero) {
		try(BufferedReader br = new BufferedReader(new FileReader("src/modelo/fichero.txt"))){
			String linea;
			while((linea=br.readLine())!=null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			System.out.println("Ocurrió un error al intentar leer el fichero: " + e.getMessage());
			
		}
	}

}
