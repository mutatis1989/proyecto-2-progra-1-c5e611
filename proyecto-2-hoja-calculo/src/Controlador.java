//1.	La clase controladora es la que se encarga de manejar la lógica del programa. 
//Esta clase posee el método main y run. También puede encargarse de la lectura de datos e impresión de los datos.

import java.util.Scanner;

public class Controlador {

    /**
     * Método principal que inicia la ejecución del programa.
     * @param args Argumentos de línea de comando (no utilizados).
     */
    public static void main(final String[] args) {
        Controlador controlador = new Controlador();
        controlador.run();
    }

    /**
     * Método principal que controla el flujo del programa.
     * Lee las dimensiones, la matriz inicial y procesa los comandos.
     */
    public void run() {

        Scanner input = new Scanner(System.in);

        int filas = input.nextInt();
        int columnas = input.nextInt();
        input.nextLine();

        HojaDeCalculo hoja = new HojaDeCalculo(filas, columnas);

        leerMatriz(hoja, filas, columnas, input);

        while (input.hasNextLine()) {
            String comando = input.nextLine().trim();
            if (!comando.isEmpty()) {
                procesarComando(comando, hoja);
            }
        }

        input.close();
    }

    /**
     * Lee la matriz inicial de fracciones.
     * @param hoja     hoja donde se almacenan las fracciones.
     * @param filas    cantidad de filas.
     * @param columnas cantidad de columnas.
     * @param input    lector Scanner desde donde se lee la entrada.
     */
    private void leerMatriz(final HojaDeCalculo hoja,
                            final int filas,
                            final int columnas,
                            final Scanner input) {

        for (int i = 0; i < filas; i++) {
            String linea = input.nextLine().trim();
            String[] tokens = linea.split(",");

            for (int j = 0; j < columnas; j++) {
                String t = tokens[j].trim();
                String[] partes = t.split("/");
                int num = Integer.parseInt(partes[0]);
                int den = Integer.parseInt(partes[1]);

                Fraccion f = new Fraccion(num, den);
                hoja.setCelda(i, j, f);
            }
        }
    }

    /**
     * Procesa un comando individual ingresado por el usuario.
     * @param comando cadena con un comando (ej: "=SUM(A1:B3)").
     * @param hoja    hoja de cálculo sobre la cual se ejecuta el comando.
     */
    private void procesarComando(final String comando, final HojaDeCalculo hoja) {

        //  PRINT()
        if (comando.startsWith("=PRINT()")) {
            hoja.imprimir();
            return;
        }

        //  PRINT(nombre)
        if (comando.startsWith("=PRINT(")) {
            String nombre = comando.substring(7, comando.length() - 1).trim();
            ListaDeFracciones lista = hoja.getConjunto(nombre);

            if (lista != null) {
                System.out.println(lista.toString());
            }
            return;
        }

        // CEL(celda)
        if (comando.startsWith("=CEL(")) {
            String ref = comando.substring(5, comando.length() - 1).trim();
            int[] pos = obtenerCelda(ref);
            hoja.setCeldaActiva(pos[0], pos[1]);
            return;
        }

        // SET
        if (comando.startsWith("=SET(")) {

            String contenido = comando.substring(5, comando.length() - 1);
            String[] partes = contenido.split(",");

            String nombre = partes[0].trim();

            ListaDeFracciones lista = new ListaDeFracciones();

            for (int i = 1; i < partes.length; i++) {
                int[] pos = obtenerCelda(partes[i].trim());
                Fraccion f = hoja.getCelda(pos[0], pos[1]);
                lista.addBack(f);
            }

            hoja.getArbol().insertar(nombre, lista);
            return;
        }

        //SUM, MUL, AVR, MDN, MIN, MAX
        if (comando.startsWith("=")) {

            int idx = comando.indexOf("(");
            String operacion = comando.substring(1, idx);
            String argumento = comando.substring(idx + 1, comando.length() - 1).trim();

            Fraccion resultado = null;

            // Rangos (A1:B3)
            if (argumento.contains(":")) {

                int[] rango = obtenerRango(argumento);

                switch (operacion) {
                    case "SUM":
                        resultado = hoja.sumarRango(rango[0], rango[1], rango[2], rango[3]);
                        break;

                    case "MUL":
                        resultado = hoja.multiplicarRango(rango[0], rango[1], rango[2], rango[3]);
                        break;

                    case "AVR":
                        resultado = hoja.promedioRango(rango[0], rango[1], rango[2], rango[3]);
                        break;

                    case "MDN":
                        resultado = hoja.medianaRango(rango[0], rango[1], rango[2], rango[3]);
                        break;

                    case "MIN":
                        resultado = hoja.minimoRango(rango[0], rango[1], rango[2], rango[3]);
                        break;

                    case "MAX":
                        resultado = hoja.maximoRango(rango[0], rango[1], rango[2], rango[3]);
                        break;

                    default:
                        return;
                }

            } else {
                // Nombre de conjunto
                ListaDeFracciones lista = hoja.getConjunto(argumento);
                if (lista == null) {
                    return;
                }

                switch (operacion) {
                    case "SUM":
                        resultado = lista.sumarElementos();
                        break;

                    case "MUL":
                        resultado = lista.multiplicarElementos();
                        break;

                    case "AVR":
                        resultado = lista.promedio();
                        break;

                    case "MDN":
                        resultado = lista.mediana();
                        break;

                    case "MIN":
                        resultado = lista.get(0); // luego se ordena
                        break;

                    case "MAX":
                        resultado = lista.get(lista.size() - 1); // luego se ordena
                        break;

                    default:
                        return;
                }
            }

            // Guarda
            int[] active = hoja.getCeldaActiva();
            if (active != null) {
                hoja.setCelda(active[0], active[1], resultado);
            }

            return;
        }
    }

    /**
     * Convierte una referencia como "A1" en coordenadas numéricas.
     * @param referencia la referencia de celda.
     * @return arreglo {fila, columna}.
     */
    private int[] obtenerCelda(final String referencia) {

        String colStr = referencia.replaceAll("[0-9]", "");
        String filaStr = referencia.replaceAll("[A-Za-z]", "");

        int col = colStr.charAt(0) - 'A';
        int fila = Integer.parseInt(filaStr) - 1;

        return new int[]{fila, col};
    }

    /**
     * Convierte un rango como "A1:B3" en coordenadas numéricas.
     * @param rango texto del rango.
     * @return arreglo {filaInicial, colInicial, filaFinal, colFinal}.
     */
    private int[] obtenerRango(final String rango) {

        String[] partes = rango.split(":");

        int[] inicio = obtenerCelda(partes[0].trim());
        int[] fin = obtenerCelda(partes[1].trim());

        return new int[]{inicio[0], inicio[1], fin[0], fin[1]};
    }
}
