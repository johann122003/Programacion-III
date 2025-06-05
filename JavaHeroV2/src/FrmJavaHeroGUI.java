import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.Stack;

public class FrmJavaHeroGUI extends JFrame {
    private JPanel pGeneral;
    private JLabel lblMonstruo, lblPregunta, lblVidas;
    private JTextArea textAreaInsignias, textAreaRespuesta;
    private JButton btnResponder, btnAtacar;
    private Jugador jugador;
    private GestorPreguntas gestorPreguntas;
    private GestorInsignias gestorInsignias;
    private GestorVidas gestorVidas;
    private Pregunta actual;
    private int nivel = 1;
    JTextArea textArea = new JTextArea();

    public FrmJavaHeroGUI() {
        jugador = new Jugador("Jugador");
        gestorPreguntas = new GestorPreguntas();
        gestorVidas = new GestorVidas();
        gestorInsignias = new GestorInsignias();

        //lblVidas = new JLabel(gestorVidas.mostrarVidas()); solo no por ahora esta funcionando sin esto
        // entonces me voy a enfocar en las preguntas y resolver esto en la siguiete estapa.por lo menos si estan saliendo los corazones :_)

        btnResponder.addActionListener(e -> responder());
        btnAtacar.addActionListener(e -> atacarMonstruo());

        mostrarSiguientePregunta();
    }

    private void actualizarVidas() {
        lblVidas.setText(" " + gestorVidas.mostrarVidas());
    }

    private void responder() {
        String respuestaUsuario = textAreaRespuesta.getText().trim();

        if (actual != null) {
            if (actual.esRespuestaCorrecta(respuestaUsuario)) {
                jugador.aumentarPuntaje();
                Insignia nuevaInsignia = new Insignia(" "  , " Ganaste una insignia!", "Java Hero (9).png");
                jugador.agregarInsignia(nuevaInsignia);
                textAreaInsignias.append(nuevaInsignia.getNombre() + " ");
                textAreaRespuesta.setText("");
                mostrarSiguientePregunta();
            } else {
                gestorVidas.perderVida();
                actualizarVidas();

                JOptionPane.showMessageDialog(null, "❌ Respuesta incorrecta. Pierdes una vida. Vidas restantes: " + gestorVidas.mostrarVidas());

                if (gestorVidas.estaMuerto()) {
                    JOptionPane.showMessageDialog(null, "💀 Has perdido todas tus vidas. ¡Juego terminado!");
                    System.exit(0);
                }
            }
        }
    }

    private void mostrarSiguientePregunta() {
        actual = gestorPreguntas.getSiguiente();

        if (actual == null) {
            lblPregunta.setText("GANASTE!!! ERES UN HEROE DE JAVA \uD83C\uDF1F \uD83D\uDC51 \uD83C\uDF1F . Puntaje: " + jugador.getPuntaje());
            btnResponder.setEnabled(false);
            btnAtacar.setEnabled(false);
        } else {
            lblPregunta.setText(actual.getEnunciado()); // Mostramos solo la pregunta sin opciones
        }
    }

    private void atacarMonstruo() {
        if (jugador.getPuntaje() >= nivel * 3) {
            nivel++;
            lblMonstruo.setText("Monstruo " + nivel + " 🐉🔥");
            gestorVidas.ganarVida();
            actualizarVidas();
        } else {
            JOptionPane.showMessageDialog(null, "No tienes suficientes insignias para atacar.");
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Hero");
        frame.setContentPane(new FrmJavaHeroGUI().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
