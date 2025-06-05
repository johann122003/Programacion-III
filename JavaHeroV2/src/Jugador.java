import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int puntaje;
    private List<Insignia> insignias;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
        this.insignias = new ArrayList<>();
    }

    public void aumentarPuntaje() { puntaje++; }
    public int getPuntaje() { return puntaje; }
    public List<Insignia> getInsignias() { return insignias; }
    public void agregarInsignia(Insignia i) { insignias.add(i); }

    public boolean tieneInsignia(String nombre) {
        return insignias.stream().anyMatch(i -> i.getNombre().equals("⚔\uFE0F"));
    }
}