//4.	La clase ListaDeFracciones debe poder almacenar nodos con fracciones y debe 
//poseer métodos para poder manipular los datos (p. ej., agregar nodo, eliminar nodo, ordenar lista, toString, etc.).
public class ListaDeFracciones {


    private Nodo head;
    private Nodo tail;
    private int contador;

    private class Nodo {

        private Fraccion valor;

        private Nodo siguiente;

        private Nodo anterior;

        /**
         * Constructor del nodo.
         * @param valor Fraccion almacenada en el nodo.
         */
        Nodo(final Fraccion valor) {
            this.valor = valor;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    /**
     * Constructor de ListaDeFracciones.
     * Inicializa la lista vacía.
     */
    public ListaDeFracciones() {
        this.head = null;
        this.tail = null;
        this.contador = 0;
    }

    /**
     * Devuelve la cantidad de elementos de la lista.
     * @return cantidad de nodos.
     */
    public int size() {
        return contador;
    }

    /**
     * Agrega una fracción al final de la lista.
     * @param fraccion La fracción a agregar.
     */
    public void addBack(final Fraccion fraccion) {

        Nodo nuevo = new Nodo(fraccion);

        if (head == null) {
            // Lista vacía
            head = nuevo;
            tail = nuevo;
        } else {
            // Insertar al final
            tail.siguiente = nuevo;
            nuevo.anterior = tail;
            tail = nuevo;
        }
        contador++;
    }

    /**
     * Elimina un nodo dado por posición.
     * @param posicion posición del nodo que se desea eliminar.
     */
    public void remove(final int posicion) {

        if (head == null || posicion < 0 || posicion >= contador) {
            return; // Nada que eliminar
        }

        Nodo actual = head;

        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }

        // Caso 1: eliminar el primero
        if (actual == head) {
            head = actual.siguiente;
            if (head != null) {
                head.anterior = null;
            } else {
                tail = null; // lista queda vacía
            }
        }
        // Caso 2: eliminar el último
        else if (actual == tail) {
            tail = actual.anterior;
            tail.siguiente = null;
        }
        // Caso 3: nodo en medio
        else {
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }

        contador--;
    }

    /**
     * Obtiene la fracción ubicada en la posición indicada.
     * @param posicion índice de la fracción que se desea obtener.
     * @return Fracción almacenada en esa posición.
     */
    public Fraccion get(final int posicion) {

        if (posicion < 0 || posicion >= contador) {
            return null;
        }

        Nodo actual = head;

        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }

        return actual.valor;
    }

    /**
     * Convierte la lista en un arreglo de fracciones.
     * @return Arreglo con todas las fracciones de la lista.
     */
    public Fraccion[] convertirEnArreglo() {

        Fraccion[] arreglo = new Fraccion[contador];
        Nodo actual = head;
        int idx = 0;

        while (actual != null) {
            arreglo[idx++] = actual.valor;
            actual = actual.siguiente;
        }

        return arreglo;
    }

    /**
     * Ordena la lista utilizando el método comparar de la clase Fraccion.
     */
    public void ordenar() {

        Fraccion[] arreglo = convertirEnArreglo();

        // Ordenamiento burbuja
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1 - i; j++) {
                if (arreglo[j].comparar(arreglo[j + 1]) > 0) {
                    Fraccion temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                }
            }
        }

        // Reconstruir la lista
        head = null;
        tail = null;
        contador = 0;

        for (Fraccion fr : arreglo) {
            addBack(fr);
        }
    }

    /**
     * Suma todas las fracciones de la lista.
     * @return Una fracción con la suma total.
     */
    public Fraccion sumarElementos() {

        Fraccion acumulador = new Fraccion(0, 1);
        Nodo actual = head;

        while (actual != null) {
            acumulador = acumulador.sumar(actual.valor);
            actual = actual.siguiente;
        }

        return acumulador;
    }

    /**
     * Multiplica todas las fracciones de la lista.
     * @return Una fracción con el producto total.
     */
    public Fraccion multiplicarElementos() {

        Fraccion acumulador = new Fraccion(1, 1);
        Nodo actual = head;

        while (actual != null) {
            acumulador = acumulador.multiplicar(actual.valor);
            actual = actual.siguiente;
        }

        return acumulador;
    }

    /**
     * Calcula el promedio de los elementos de la lista.
     * @return fracción equivalente al promedio.
     */
    public Fraccion promedio() {

        if (contador == 0) {
            return new Fraccion(0, 1);
        }

        Fraccion suma = sumarElementos();

        return new Fraccion(
            suma.getNumerador(),
            suma.getDenominador() * contador
        );
    }

    /**
     * Calcula la mediana de la lista.
     * @return fracción equivalente a la mediana.
     */
    public Fraccion mediana() {

        if (contador == 0) {
            return new Fraccion(0, 1);
        }

        Fraccion[] arreglo = convertirEnArreglo();

        // Ordenar
        for (int i = 0; i < arreglo.length - 1; i++) {
            for (int j = 0; j < arreglo.length - 1 - i; j++) {
                if (arreglo[j].comparar(arreglo[j + 1]) > 0) {
                    Fraccion tmp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = tmp;
                }
            }
        }

        int medio = contador / 2;

        if (contador % 2 == 1) {
            return arreglo[medio];
        }

        // Si es par: (f1 + f2)/2
        Fraccion f1 = arreglo[medio - 1];
        Fraccion f2 = arreglo[medio];

        Fraccion suma = f1.sumar(f2);

        return new Fraccion(
            suma.getNumerador(),
            suma.getDenominador() * 2
        );
    }

    /**
     * Convierte la lista en texto separado por comas.
     * @return Representación en texto de la lista.
     */
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Nodo actual = head;

        while (actual != null) {
            sb.append(actual.valor.toString());
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }

        return sb.toString();
    }
}
