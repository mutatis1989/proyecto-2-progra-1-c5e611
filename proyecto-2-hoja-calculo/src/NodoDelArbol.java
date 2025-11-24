/**
 * Clase que representa un nodo del árbol binario que almacena conjuntos
 * de listas de fracciones asociadas a un nombre.
 */
public class NodoDelArbol {
    private String nombre;
    private ListaDeFracciones lista;
    private NodoDelArbol izquierdo;
    private NodoDelArbol derecho;

    /**
     * Constructor del nodo del árbol.
     * @param nombre Nombre del conjunto.
     * @param lista Lista de fracciones asociada al nombre.
     */
    public NodoDelArbol(final String nombre, final ListaDeFracciones lista) {
        this.nombre = nombre;
        this.lista = lista;
        this.izquierdo = null;
        this.derecho = null;
    }

    /** @return nombre del nodo. */
    public String getNombre() {
        return nombre;
    }

    /** @return lista asociada a este nodo. */
    public ListaDeFracciones getLista() {
        return lista;
    }

    /** @return hijo izquierdo. */
    public NodoDelArbol getIzquierdo() {
        return izquierdo;
    }

    /** @return hijo derecho. */
    public NodoDelArbol getDerecho() {
        return derecho;
    }

    /**
     * Asigna el hijo izquierdo.
     * @param nodo nuevo hijo izquierdo.
     */
    public void setIzquierdo(final NodoDelArbol nodo) {
        this.izquierdo = nodo;
    }

    /**
     * Asigna el hijo derecho.
     * @param nodo nuevo hijo derecho.
     */
    public void setDerecho(final NodoDelArbol nodo) {
        this.derecho = nodo;
    }

    /**
     * Reemplaza la lista asociada al nodo.
     * @param nueva nueva lista para este nombre.
     */
    public void setLista(final ListaDeFracciones nueva) {
        this.lista = nueva;
    }
}
