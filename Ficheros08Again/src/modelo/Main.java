package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Integer menor = null;
		Integer mayor = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader("src/modelo/numeros.txt"))) {
			String linea;
			while((linea=br.readLine())!=null) {
				try {
					int numero = Integer.parseInt(linea.trim());
					if(menor==null || numero < menor) {
						menor=numero;
					}
					if(mayor==null || numero > mayor) {
						mayor = numero;
					}
				}catch(NumberFormatException e) {
					System.out.println("La línea '" + linea + "' no es un número válido.");
				}
			}
			
		}catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
		if(menor!=null && mayor!=null) {
			System.out.println("El numero menor es: " + menor);
			System.out.println("El numero mayor es: " + mayor);
		}else {
			System.out.println("No se encontraron numero en el archivo");
		}
	}

}
