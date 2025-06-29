public class Nodo {
    public String etiqueta;
    public Nodo izquierda;
    public Nodo centro;
    public Nodo derecha;
    public int x, y;

    public Nodo(int x, int y, String etiqueta) {
        this.x = x;
        this.y = y;
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return String.format("(Izq=%s, Centro=%s, Der=%s)",
                izquierda != null ? izquierda.etiqueta : "-",
                centro != null ? centro.etiqueta : "-",
                derecha != null ? derecha.etiqueta : "-");
    }
}
