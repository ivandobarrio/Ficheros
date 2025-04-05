package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

        // Solicitar el nombre y la edad al usuario
        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        
        System.out.print("Introduce tu edad: ");
        int edad = sc.nextInt();
	}
	private static void guardarDatos(String nombre,int edad) {
		String datos = nombre + ", " + edad;
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/modelo/datos.txt"))){
			bw.write(datos);
			bw.newLine();
			System.out.println("Datos escritos correctamente");
		}catch(IOException e) {
			System.out.println("Ocurrio un error");
		}
	}
}
