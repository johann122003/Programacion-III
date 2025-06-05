import java.util.*;

public class GestorInsignias {
    private List<Insignia> lista = new ArrayList<>();

    public GestorInsignias() {
        lista.add(new Insignia("Nivel 1", "🏆🏆🏆 - Has respondido 3 preguntas correctamente", "img/nivel1.png"));
        lista.add(new Insignia("Nivel 2", "🏆🏆🏆🏆🏆 - Has respondido 5 preguntas correctamente", "img/nivel2.png"));
        lista.add(new Insignia("Nivel 3", "🏆🏆🏆🏆🏆🏆🏆 - Has respondido 7 preguntas correctamente", "img/nivel3.png"));
    }

    public List<Insignia> verificarNuevas(int puntaje, Jugador jugador) {
        List<Insignia> nuevas = new ArrayList<>();
        for (Insignia ins : lista) {
            if (puntaje >= 3 && ins.getNombre().contains("Nivel 1") && !jugador.tieneInsignia("Nivel 1"))
                nuevas.add(ins);
            if (puntaje >= 5 && ins.getNombre().contains("Nivel 2") && !jugador.tieneInsignia("Nivel 2"))
                nuevas.add(ins);
            if (puntaje >= 7 && ins.getNombre().contains("Nivel 3") && !jugador.tieneInsignia("Nivel 3"))
                nuevas.add(ins);
        }
        return nuevas;
    }
}