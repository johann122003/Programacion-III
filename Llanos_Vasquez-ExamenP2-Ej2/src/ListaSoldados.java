
class ListaSoldados {
    private Nodo cabeza;
    private Nodo cola;

    public boolean existeID(int ID) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.ID == ID) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean insertarOrdenado(SoldadoVirtual soldado) {
        if (existeID(soldado.ID)) return false;
        Nodo nuevo = new Nodo(soldado);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else if (soldado.ID < cabeza.dato.ID) {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.dato.ID < soldado.ID) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            if (actual.siguiente != null) {
                actual.siguiente.anterior = nuevo;
            } else {
                cola = nuevo;
            }
            actual.siguiente = nuevo;
            nuevo.anterior = actual;
        }
        return true;
    }

    public Nodo[] toArray() {
        int size = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            size++;
            actual = actual.siguiente;
        }
        Nodo[] arr = new Nodo[size];
        actual = cabeza;
        for (int i = 0; i < size; i++) {
            arr[i] = actual;
            actual = actual.siguiente;
        }
        return arr;
    }

    public Nodo busquedaBinaria(int ID) {
        Nodo[] arr = toArray();
        int ini = 0, fin = arr.length - 1;
        while (ini <= fin) {
            int mid = (ini + fin) / 2;
            if (arr[mid].dato.ID == ID) return arr[mid];
            else if (ID < arr[mid].dato.ID) fin = mid - 1;
            else ini = mid + 1;
        }
        return null;
    }

    public ListaSoldados filtrarPorTecnologia(String tecnologia) {
        ListaSoldados filtrada = new ListaSoldados();
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.TecnologiaEspecial.equals(tecnologia)) {
                filtrada.insertarOrdenado(actual.dato);
            }
            actual = actual.siguiente;
        }
        return filtrada;
    }

    public void ordenarPorNivelDescendente() {
        if (cabeza == null) return;
        Nodo actual = cabeza;
        while (actual != null) {
            Nodo max = actual;
            Nodo temp = actual.siguiente;
            while (temp != null) {
                if (temp.dato.NivelVirtualidad > max.dato.NivelVirtualidad) {
                    max = temp;
                }
                temp = temp.siguiente;
            }
            if (max != actual) {
                SoldadoVirtual aux = actual.dato;
                actual.dato = max.dato;
                max.dato = aux;
            }
            actual = actual.siguiente;
        }
    }

    public int contarPorDimensionRec(String dimension) {
        return contarRecursivo(cabeza, dimension);
    }

    private int contarRecursivo(Nodo actual, String dimension) {
        if (actual == null) return 0;
        if (actual.dato.DimensionOperativa.equals(dimension)) return 1 + contarRecursivo(actual.siguiente, dimension);
        return contarRecursivo(actual.siguiente, dimension);
    }

}
