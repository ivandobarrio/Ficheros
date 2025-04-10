package modelo;
import java.io.*;
import java.util.*;

public class Agenda {
    private static final int MAX_CONTACTOS = 20;
    private static final String NOMBRE_ARCHIVO = "agenda.txt";
    private static List<Contacto> contactos;

    public static void main(String[] args) {
        contactos = new ArrayList<>();
        cargarDatos();

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de la Agenda ---");
            System.out.println("1. Nuevo contacto");
            System.out.println("2. Buscar por nombre");
            System.out.println("3. Mostrar todos");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarContacto(scanner);
                    break;
                case 2:
                    buscarPorNombre(scanner);
                    break;
                case 3:
                    mostrarTodos();
                    break;
                case 4:
                    guardarDatos();
                    System.out.println("Saliendo de la agenda...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void agregarContacto(Scanner scanner) {
        if (contactos.size() >= MAX_CONTACTOS) {
            System.out.println("La agenda está llena. No se pueden agregar más contactos.");
            return;
        }

        System.out.print("Introduce el nombre: ");
        String nombre = scanner.nextLine();

        if (buscarContacto(nombre) != null) {
            System.out.println("El contacto ya existe.");
            return;
        }

        System.out.print("Introduce el teléfono: ");
        String telefono = scanner.nextLine();

        contactos.add(new Contacto(nombre, telefono));
        System.out.println("Contacto agregado.");
    }

    private static void buscarPorNombre(Scanner scanner) {
        System.out.print("Introduce el nombre a buscar: ");
        String nombreBuscado = scanner.nextLine();
        boolean encontrado = false;

        for (Contacto contacto : contactos) {
            if (contacto.getNombre().toLowerCase().contains(nombreBuscado.toLowerCase())) {
                System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron contactos con ese nombre.");
        }
    }

    private static void mostrarTodos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }

        Collections.sort(contactos);
        System.out.println("\n--- Lista de Contactos ---");
        for (Contacto contacto : contactos) {
            System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono());
        }
    }

    private static void guardarDatos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
            for (Contacto contacto : contactos) {
                writer.write(contacto.getNombre() + "," + contacto.getTelefono());
                writer.newLine();
            }
            System.out.println("Datos guardados en " + NOMBRE_ARCHIVO);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    private static void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    contactos.add(new Contacto(partes[0], partes[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo. Se iniciará una nueva agenda.");
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    private static Contacto buscarContacto(String nombre) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }
        return null;
    }
}