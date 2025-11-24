/**
 * Clase que representa un árbol binario de búsqueda donde cada nodo almacena
 * un nombre y una ListaDeFracciones asociada a ese nombre.
 */
public class ArbolDeListas {

    /** Nodo raíz del árbol. */
    private NodoDelArbol raiz;

    /**
     * Constructor del árbol.
     * Inicializa la raíz en null.
     */
    public ArbolDeListas() {
        this.raiz = null;
    }

    /**
     * Inserta un nuevo nombre y su lista asociada en el árbol.
     * Si el nombre ya existe, reemplaza la lista anterior.
     *
     * @param nombre nombre del conjunto.
     * @param lista lista de fracciones asociada al nombre.
     */
    public void insertar(final String nombre, final ListaDeFracciones lista) {

        if (raiz == null) {
            raiz = new NodoDelArbol(nombre, lista);
            return;
        }

        NodoDelArbol actual = raiz;

        while (true) {

            int cmp = nombre.compareToIgnoreCase(actual.getNombre());

            if (cmp < 0) {
    
                if (actual.getIzquierdo() == null) {
                    actual.setIzquierdo(new NodoDelArbol(nombre, lista));
                    return;
                }
                actual = actual.getIzquierdo();
            } else if (cmp > 0) {
                if (actual.getDerecho() == null) {
                    actual.setDerecho(new NodoDelArbol(nombre, lista));
                    return;
                }
                actual = actual.getDerecho();
            } else {
                actual.setLista(lista);
                return;
            }
        }
    }

    /**
     * Busca un nodo por nombre dentro del árbol.
     * @param nombre nombre del conjunto a buscar.
     * @return la lista asociada al nombre, o null si no existe.
     */
    public ListaDeFracciones buscar(final String nombre) {

        NodoDelArbol actual = raiz;

        while (actual != null) {

            int cmp = nombre.compareToIgnoreCase(actual.getNombre());

            if (cmp == 0) {
                return actual.getLista();
            } else if (cmp < 0) {
                actual = actual.getIzquierdo();
            } else {
                actual = actual.getDerecho();
            }
        }

        return null;
    }

    /**
     * Verifica si un nombre existe dentro del árbol.
     * @param nombre nombre a verificar.
     * @return true si existe, false si no existe.
     */
    public boolean existe(final String nombre) {
        return buscar(nombre) != null;
    }

    /**
     * Reemplaza una lista asociada a un nombre.
     * Si no existe, lo inserta como un nuevo nodo.
     * @param nombre nombre del conjunto.
     * @param nuevaLista nueva lista asociada.
     */
    public void reemplazar(final String nombre, final ListaDeFracciones nuevaLista) {

        NodoDelArbol actual = raiz;

        while (actual != null) {

            int cmp = nombre.compareToIgnoreCase(actual.getNombre());

            if (cmp == 0) {
                actual.setLista(nuevaLista);
                return;
            } else if (cmp < 0) {
                actual = actual.getIzquierdo();
            } else {
                actual = actual.getDerecho();
            }
        }
        insertar(nombre, nuevaLista);
    }

    /**
     * Imprime en orden los nombres del árbol para depuración.
     * @param nodo nodo desde el cual iniciar el recorrido.
     */
    public void imprimirInOrden(final NodoDelArbol nodo) {

        if (nodo != null) {
            imprimirInOrden(nodo.getIzquierdo());
            System.out.println(nodo.getNombre());
            imprimirInOrden(nodo.getDerecho());
        }
    }

    /**
     * Devuelve la raíz (útil solo para depuración).
     * @return nodo raíz.
     */
    public NodoDelArbol getRaiz() {
        return raiz;
    }
}
