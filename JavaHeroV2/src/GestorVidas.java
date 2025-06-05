import java.util.Stack;

class GestorVidas {
    private Stack<String> vidas;

    public GestorVidas() {
        vidas = new Stack<>();
        for (int i = 0; i < 3; i++) {
            vidas.push("❤"); // 🔴 Se mantiene el corazón visual correctamente
        }
    }

    public void perderVida() {
        if (!vidas.isEmpty()) {
            vidas.pop();
        }
    }

    public void ganarVida() {
        vidas.push("❤");
    }

    public boolean estaMuerto() {
        return vidas.isEmpty();
    }

    public String mostrarVidas() {
        return vidas.toString(); // 🔴 Ahora devuelve las vidas correctamente con corazones
    }
}
