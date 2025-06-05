public class Insignia {
    private String nombre;
    private String descripcion;
    private String rutaImagen;

    public Insignia(String nombre, String descripcion, String rutaImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
    }

    public String getNombre() { return nombre + "⚡"; } //este es el loguito de la insignia creo jeje
    public String getDescripcion() { return descripcion; }
    public String getRutaImagen() { return rutaImagen; }
}