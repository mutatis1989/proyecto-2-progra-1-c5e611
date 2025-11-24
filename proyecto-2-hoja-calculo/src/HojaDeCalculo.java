//2.	La clase HojaDeCalculo debe de poseer un atributo para almacenar los valores (matriz de fracciones) 
//y métodos para manipular los atributos.
//3.	La clase HojaDeCalculo necesita un atributo de tipo árbol binario que asocie nombres con listas de fracciones.


public class HojaDeCalculo {

    private Fraccion[][] matrizDeFracciones;

    private ArbolDeListas arbolDeListas;

    private int celdaActivaFila;

    private int celdaActivaColumna;

    private boolean tieneCeldaActiva;

    /**
     * Constructor de la hoja de cálculo.
     * @param filas cantidad de filas.
     * @param columnas cantidad de columnas.
     */
    public HojaDeCalculo(final int filas, final int columnas) {
        matrizDeFracciones = new Fraccion[filas][columnas];
        arbolDeListas = new ArbolDeListas();
        tieneCeldaActiva = false;

        // Inicializar todas las celdas con 0/1
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizDeFracciones[i][j] = new Fraccion(0, 1);
            }
        }
    }

    /**
     * Coloca una fracción en una celda específica.
     * @param fila fila de la celda.
     * @param columna columna de la celda.
     * @param fraccion fracción a colocar.
     */
    public void setCelda(final int fila, final int columna, final Fraccion fraccion) {
        matrizDeFracciones[fila][columna] = fraccion;
    }

    /**
     * Obtiene la fracción de una celda.
     * @param fila fila de la celda.
     * @param columna columna de la celda.
     * @return fracción almacenada en esa celda.
     */
    public Fraccion getCelda(final int fila, final int columna) {
        return matrizDeFracciones[fila][columna];
    }

    /**
     * Suma un rango de la matriz.
     */
    public Fraccion sumarRango(final int fi, final int ci, final int ff, final int cf) {
        Fraccion acumulador = new Fraccion(0, 1);

        for (int fila = fi; fila <= ff; fila++) {
            for (int col = ci; col <= cf; col++) {
                acumulador = acumulador.sumar(matrizDeFracciones[fila][col]);
            }
        }
        return acumulador;
    }

    /**
     * Multiplica un rango de la matriz.
     */
    public Fraccion multiplicarRango(final int fi, final int ci, final int ff, final int cf) {
        Fraccion acumulador = new Fraccion(1, 1);

        for (int fila = fi; fila <= ff; fila++) {
            for (int col = ci; col <= cf; col++) {
                acumulador = acumulador.multiplicar(matrizDeFracciones[fila][col]);
            }
        }
        return acumulador;
    }

    /**
     * Calcula el promedio de un rango.
     */
  public Fraccion promedioRango(final int fi, final int ci, final int ff, final int cf) {

    int cantidad = (ff - fi + 1) * (cf - ci + 1);
    Fraccion suma = sumarRango(fi, ci, ff, cf);

    // promedio = suma * (1/cantidad)
    return suma.multiplicar(new Fraccion(1, cantidad));
}

    /**
     * Calcula la mediana de un rango.
     */
    public Fraccion medianaRango(final int fi, final int ci, final int ff, final int cf) {

        ListaDeFracciones tmp = new ListaDeFracciones();

        for (int fila = fi; fila <= ff; fila++) {
            for (int col = ci; col <= cf; col++) {
                tmp.addBack(matrizDeFracciones[fila][col]);
            }
        }

        tmp.ordenar();
        return tmp.mediana();
    }

    /**
     * Devuelve el mínimo en un rango.
     */
    public Fraccion minimoRango(final int fi, final int ci, final int ff, final int cf) {
        Fraccion minimo = matrizDeFracciones[fi][ci];

        for (int fila = fi; fila <= ff; fila++) {
            for (int col = ci; col <= cf; col++) {
                if (matrizDeFracciones[fila][col].comparar(minimo) < 0) {
                    minimo = matrizDeFracciones[fila][col];
                }
            }
        }

        return minimo;
    }

    /**
     * Devuelve el máximo en un rango.
     */
    public Fraccion maximoRango(final int fi, final int ci, final int ff, final int cf) {
        Fraccion maximo = matrizDeFracciones[fi][ci];

        for (int fila = fi; fila <= ff; fila++) {
            for (int col = ci; col <= cf; col++) {
                if (matrizDeFracciones[fila][col].comparar(maximo) > 0) {
                    maximo = matrizDeFracciones[fila][col];
                }
            }
        }

        return maximo;
    }

    /**
     * Imprime la hoja de cálculo alineada.
     */
    public void imprimir() {

        int maxAncho = 0;

        // Calcular ancho máximo
        for (Fraccion[] fila : matrizDeFracciones) {
            for (Fraccion f : fila) {
                int largo = f.toString().length();
                if (largo > maxAncho) {
                    maxAncho = largo;
                }
            }
        }

        // Encabezados
        System.out.print("    ");
        for (int col = 0; col < matrizDeFracciones[0].length; col++) {
            System.out.printf("%" + maxAncho + "s ", (char) ('A' + col));
        }
        System.out.println();

        // Filas
        for (int fila = 0; fila < matrizDeFracciones.length; fila++) {
            System.out.printf("%3d ", fila + 1);
            for (int col = 0; col < matrizDeFracciones[fila].length; col++) {
                System.out.printf("%" + maxAncho + "s ", matrizDeFracciones[fila][col]);
            }
            System.out.println();
        }
    }

    /**
     * Activa una celda para guardar resultados.
     */
    public void setCeldaActiva(final int fila, final int columna) {
        celdaActivaFila = fila;
        celdaActivaColumna = columna;
        tieneCeldaActiva = true;
    }

    /**
     * Devuelve fila y columna de la celda activa.
     * @return arreglo con fila y columna.
     */
    public int[] getCeldaActiva() {
        if (!tieneCeldaActiva) {
            return null;
        }
        return new int[] {celdaActivaFila, celdaActivaColumna};
    }

    /**
     * Quita la celda activa.
     */
    public void resetCeldaActiva() {
        tieneCeldaActiva = false;
    }

    /**
     * Obtiene un conjunto por nombre desde el árbol.
     * @param nombre nombre del conjunto.
     * @return lista de fracciones asociada.
     */
    public ListaDeFracciones getConjunto(final String nombre) {
        return arbolDeListas.buscar(nombre);
    }

    /**
     * Inserta o reemplaza una lista en el árbol.
     * @param nombre nombre del conjunto.
     * @param lista lista de fracciones.
     */

    /**
     * Devuelve el árbol que almacena los conjuntos de listas.
     * @return instancia de ArbolDeListas.
     */
    public ArbolDeListas getArbol() {
        return arbolDeListas;
    }

    public void guardarConjunto(final String nombre, final ListaDeFracciones lista) {
        arbolDeListas.reemplazar(nombre, lista);
    }
}
