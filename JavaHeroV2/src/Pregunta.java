import java.util.List;

public class Pregunta {
    private String enunciado;
    private List<String> respuestasCorrectas;

    public Pregunta(String enunciado, List<String> respuestasCorrectas) {
        this.enunciado = enunciado;
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public String getEnunciado() { return enunciado; }

    public boolean esRespuestaCorrecta(String respuestaUsuario) {
        return respuestasCorrectas.stream()
                .anyMatch(respuesta -> respuesta.equalsIgnoreCase(respuestaUsuario.trim()));
    }
}