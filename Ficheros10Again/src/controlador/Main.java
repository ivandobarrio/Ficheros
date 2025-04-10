package controlador;

import java.util.Scanner;

import modelo.MoreLikeCommand;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre del archivo: ");
        String nombreArchivo = scanner.nextLine();
        MoreLikeCommand.mostrarArchivoEnPaginas(nombreArchivo);
        scanner.close();
	}

}
