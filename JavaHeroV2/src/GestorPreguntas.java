import java.util.*;

public class GestorPreguntas {
    private List<Pregunta> preguntas = new ArrayList<>();
    private int indiceActual = 0;

    public GestorPreguntas() {
        preguntas.add(new Pregunta("Java Hero necesita manejar grandes cantidades \n de datos en su armadura, no sabe que tipo de enlazada\n usar: simple o circular doble", Arrays.asList("circular doble")));
        preguntas.add(new Pregunta("Java Hero encontró un mapa y debe encontar rápido\n a su enemigo oculto. qué tipo de búsqueda debe usar?", Arrays.asList("binaria", "Binaria")));
        preguntas.add(new Pregunta("Hay que almacenar ataques pendientes en orden de\n llegada, que estructura de datos debe usar?", Arrays.asList("FIFO", "Cola")));
        preguntas.add(new Pregunta("Java Hero necesita priorizar sus ataques, para eso\n debe usar una cola de: ", Arrays.asList("prioridad", "Prioridad")));
        preguntas.add(new Pregunta("Java Hero debe recordar ataques recientes y deshacerlos\n si es necesario, para eso debe usar la técnica de: ", Arrays.asList("Pila", "LIFO")));
        preguntas.add(new Pregunta("La casa de el heroe esta muy desordenada y no puede\n encontrar sus artefatos mágicos... como podria \n encontrarlos rápidamente?", Arrays.asList("Tabla Hash", "hash table", "tabla hash")));
        preguntas.add(new Pregunta("Java Hero ha llegado a un campo de batalla lleno de\n enemigos ¿cómo puede nuestro héroe decidir a \n quien atacar primero?", Arrays.asList("árbol avl", "arbol avl", "Árbol AVL", "AVL")));
        preguntas.add(new Pregunta("Java Hero ha llegado a una mazmorra, como se asegura\n de recorrerla toda sin repetir lugares innecesarios?", Arrays.asList("busqueda profunda")));
        preguntas.add(new Pregunta("Java Hero ha visto un castillo a lo lejos, pero hay\n un bosque con muchos caminos, qué algoritmo \n le puede ayudar a encontrar la ruta más corta para llegar?", Arrays.asList("Dijkstra", "dijkstra")));
        preguntas.add(new Pregunta("Java Hero necesita un plan, como puede optimizar sus\n ataques para ser más rápidos y eficientes?", Arrays.asList("bubble sort", "Bubble Sort")));
        preguntas.add(new Pregunta("Java Hero ha derrotado a casi todos los enemigos, \n ahora debe eliminarlos de su lista de amenazas \n y para eso usa un algoritmo de: ", Arrays.asList("Eliminación")));
        preguntas.add(new Pregunta("Vaya, Java Hero está destruyendo al último enemigo con\n una técnica que tiene múltiples formas y no\n parece tener fin... eso si que es: ", Arrays.asList("recursividad", "Recursividad")));

        Collections.shuffle(preguntas);
    }

    public Pregunta getSiguiente() {
        if (indiceActual < preguntas.size()) {
            return preguntas.get(indiceActual++);
        }
        return null;
    }
}