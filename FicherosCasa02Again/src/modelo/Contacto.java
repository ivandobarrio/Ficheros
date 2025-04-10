package modelo;

public class Contacto implements Comparable<Contacto> {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public int compareTo(Contacto otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }
}
