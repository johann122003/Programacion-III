import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaPowerRanger {
    private class Nodo {
        PowerRanger dato;
        Nodo siguiente;

        public Nodo(PowerRanger dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo inicio;

    public ListaPowerRanger() {
        this.inicio = null;
    }

    // Insertar al final si no existe ID
    public boolean insertar(PowerRanger p) {
        if (buscarNodo(p.getID()) != null) {
            // Ya existe el ID
            return false;
        }
        Nodo nuevo = new Nodo(p);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        return true;
    }

    // Buscar Nodo por ID
    private Nodo buscarNodo(int ID) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.dato.getID() == ID) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    // Buscar PowerRanger por ID
    public PowerRanger buscar(int ID) {
        Nodo nodo = buscarNodo(ID);
        return (nodo != null) ? nodo.dato : null;
    }

    // Retornar lista de PowerRanger para mostrar
    public java.util.List<PowerRanger> listar() {
        java.util.List<PowerRanger> lista = new java.util.ArrayList<>();
        Nodo actual = inicio;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }

    // Filtrar por BaseSecreta: retorna lista de Rangers que NO pertenecen a la base dada
    public java.util.List<PowerRanger> filtrarPorBaseNoPertenece(String baseSecreta) {
        java.util.List<PowerRanger> filtrados = new java.util.ArrayList<>();
        Nodo actual = inicio;
        while (actual != null) {
            if (!actual.dato.getBaseSecreta().equals(baseSecreta)) {
                filtrados.add(actual.dato);
            }
            actual = actual.siguiente;
        }
        return filtrados;
    }

    // Ordenamiento burbuja por NivelDeEntrenamiento ascendente (en lista dada)
    public void ordenarBurbuja(java.util.List<PowerRanger> lista) {
        int n = lista.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i -1; j++) {
                if (lista.get(j).getNivelDelEntrenamiento() > lista.get(j+1).getNivelDelEntrenamiento()) {
                    PowerRanger temp = lista.get(j);
                    lista.set(j, lista.get(j+1));
                    lista.set(j+1, temp);
                }
            }
        }
    }

    // Conteo recursivo por TipoDePoder
    // Recibe arreglo con los tipos que se quieren contar
    // Retorna arreglo con conteo en mismo orden
    public int[] contarPorTipoDePoder(String[] tipos) {
        int[] resultado = new int[tipos.length];
        contarRecursivo(inicio, tipos, resultado);
        return resultado;
    }

    // Metodo recursivo auxiliar
    private void contarRecursivo(Nodo nodo, String[] tipos, int[] resultado) {
        if (nodo == null) return;
        // Contar este nodo
        for (int i=0; i < tipos.length; i++) {
            if (nodo.dato.getTipoDePoder().equals(tipos[i])) {
                resultado[i]++;
            }
        }
        // Llamada recursiva siguiente
        contarRecursivo(nodo.siguiente, tipos, resultado);
    }
}
