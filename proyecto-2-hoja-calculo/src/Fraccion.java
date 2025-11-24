/**
 * Clase que representa una fracción con numerador y denominador enteros.
 * Incluye operaciones básicas como suma, multiplicación, comparación
 * y conversión a texto. Todas las fracciones se almacenan en forma simplificada.
 */

public class Fraccion {

    private int numerador;

    private int denominador;

    /**
     * Constructor de la clase Fraccion.
     * Crea una nueva fracción y la simplifica automáticamente.
     * @param numerador Valor entero para el numerador.
     * @param denominador Valor entero para el denominador. No puede ser cero.
     */
    public Fraccion(final int numerador, final int denominador) {
        if (denominador == 0) {
            throw new IllegalArgumentException("El denominador no puede ser cero.");
        }

        this.numerador = numerador;
        this.denominador = denominador;
        this.simplificar();
    }

    /**
     * Devuelve el numerador.
     * @return numerador de la fracción.
     */
    public int getNumerador() {
        return numerador;
    }

    /**
     * Devuelve el denominador.
     * @return denominador de la fracción.
     */
    public int getDenominador() {
        return denominador;
    }

    /**
     * Simplifica la fracción utilizando el máximo común divisor.
     * También ajusta el signo para que el denominador sea siempre positivo.
     */
    private void simplificar() {
        int mcd = mcd(Math.abs(numerador), Math.abs(denominador));

        numerador = numerador / mcd;
        denominador = denominador / mcd;

        if (denominador < 0) {
            numerador = -numerador;
            denominador = -denominador;
        }
    }

    /**
     * Calcula el máximo común divisor entre dos enteros.
     * @param a Primer número.
     * @param b Segundo número.
     * @return El máximo común divisor entre a y b.
     */
    private int mcd(final int a, final int b) {
        if (b == 0) {
            return a;
        }
        return mcd(b, a % b);
    }

    /**
     * Suma esta fracción con otra fracción.
     * @param otra La fracción que se sumará a esta.
     * @return Una nueva fracción que representa la suma.
     */
    public Fraccion sumar(final Fraccion otra) {
        int nuevoNumerador =
                this.numerador * otra.denominador
                + otra.numerador * this.denominador;

        int nuevoDenominador = this.denominador * otra.denominador;

        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }

    /**
     * Multiplica esta fracción con otra fracción.
     * @param otra La fracción que se multiplicará con esta.
     * @return Una nueva fracción que representa el producto.
     */
    public Fraccion multiplicar(final Fraccion otra) {
        int nuevoNumerador = this.numerador * otra.numerador;
        int nuevoDenominador = this.denominador * otra.denominador;

        return new Fraccion(nuevoNumerador, nuevoDenominador);
    }

    /**
     * Compara esta fracción con otra sin convertir a decimal.
     * Usamos multiplicación cruzada.
     * @param otra La fracción a comparar.
     * @return -1 si esta < otra, 0 si son iguales, 1 si esta > otra.
     */
    public int comparar(final Fraccion otra) {
        int a = this.numerador * otra.denominador;
        int b = otra.numerador * this.denominador;

        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Convierte la fracción al formato "numerador/denominador".
     * @return Representación en texto de la fracción.
     */
    public String toString() {
        return numerador + "/" + denominador;
    }
}
