import javax.swing.*;
import java.awt.*;

public class Lista {

    private Nodo inicio;
    private int tamano;

    public Lista() {
        inicio = null;
        tamano = 0;
    }

    // Metodo para agregar un valor entero al final de la lista
    public void agregar(int dato, JTextArea textArea) {
        Nodo nuevoNodo = new Nodo(dato);
        if (inicio == null) {
            inicio = nuevoNodo;
        } else {
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
        actualizarTextArea(textArea);
    }

    // Metodo para eliminar un valor entero de la lista
    public boolean eliminar(int dato, JTextArea textArea) {
        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "La lista está vacía.");
            return false;
        }
        if (inicio.dato == dato) {
            inicio = inicio.siguiente;
            tamano--;
            actualizarTextArea(textArea);
            return true;
        }
        Nodo actual = inicio;
        while (actual.siguiente != null && actual.siguiente.dato != dato) {
            actual = actual.siguiente;
        }
        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            tamano--;
            actualizarTextArea(textArea);
            return true;
        }
        JOptionPane.showMessageDialog(null, "Elemento no encontrado en la lista.");
        return false;
    }

    // Metodo para realizar una búsqueda lineal
    public int buscarLineal(int dato,  JTextArea textArea) {
        Nodo actual = inicio;
        int posicion = 0;
        while (actual != null) {
            if (actual.dato == dato) {
                return posicion;
            }
            actual = actual.siguiente;
            posicion++;
        }
        return -1; // Retorna -1 si no se encuentra
    }

    //Buscar Binario
    public int buscarBinario(int valor, JTextArea textArea1) {
        int[] array = convertirAArray(); // convierte la lista en un arreglo

        int inicio = 0; // indice inicial del arreglo
        int fin = array.length - 1; // indice final del arreglo

        while (inicio <= fin) { // mientras el rango de busqueda sea valido
            int medio = inicio + (fin - inicio) / 2; // calcula el punto medio

            if (array[medio] == valor) { // si el valor en medio es igual al buscado
                return medio; // retorna la posicion donde se encontro
            } else if (array[medio] < valor) { // si el valor en medio es menor
                inicio = medio + 1; // busca en la mitad derecha
            } else { // si el valor en medio es mayor
                fin = medio - 1; // busca en la mitad izquierda
            }
        }
        return -1; // si no se encontro el valor, retorna -1
    }


    // Metodo para realizar una búsqueda interpolada (requiere lista ordenada)
    public int buscarInterpolado(int dato, JTextArea textArea) {
        ordenarBurbuja(textArea); // Ordena la lista antes de realizar la búsqueda
        int[] array = convertirAArray();
        int inicio = 0;
        int fin = tamano - 1;

        while (inicio <= fin && dato >= array[inicio] && dato <= array[fin]) {
            int pos = inicio + ((dato - array[inicio]) * (fin - inicio) / (array[fin] - array[inicio]));
            if (array[pos] == dato) {
                return pos;
            }
            if (array[pos] < dato) {
                inicio = pos + 1;
            } else {
                fin = pos - 1;
            }
        }
        return -1; // Retorna -1 si no se encuentra
    }

    // Metodo para ordenar la lista usando burbuja
    public void ordenarBurbuja(JTextArea textArea) {
        if (inicio == null || inicio.siguiente == null) return;

        boolean swapped;
        do {
            swapped = false;
            Nodo actual = inicio;
            Nodo siguiente = inicio.siguiente;
            while (siguiente != null) {
                if (actual.dato > siguiente.dato) {
                    int temp = actual.dato;
                    actual.dato = siguiente.dato;
                    siguiente.dato = temp;
                    swapped = true;
                }
                actual = siguiente;
                siguiente = siguiente.siguiente;
            }
        } while (swapped);

        actualizarTextArea(textArea); // Actualiza la vista en el JTextArea
    }

    // Metodo auxiliar para convertir la lista a un array
    private int[] convertirAArray() {
        int[] array = new int[tamano];
        Nodo actual = inicio;
        int i = 0;
        while (actual != null) {
            array[i++] = actual.dato;
            actual = actual.siguiente;
        }
        return array;
    }

    // Metodo para mostrar la  lista en el JTextArea
    public void mostrarLista(JTextArea textArea) {
        if (inicio == null) {
            textArea.setText("La lista está vacía.");
        } else {
            StringBuilder listaStr = new StringBuilder();
            Nodo actual = inicio;
            while (actual != null) {
                listaStr.append(actual.dato).append("\n");
                actual = actual.siguiente;
            }
            textArea.setText(listaStr.toString());
        }
    }

    // Metodo para actualizar el JTextArea cada vez que cambia la lista
    private void actualizarTextArea(JTextArea textArea) {
        mostrarLista(textArea);
    }
}
