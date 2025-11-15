//Siempre se debe crear src (dentro del cual está main), doc, build, bin dentro de la carpeta principal
//memortizar que el método main (public static void main(final String[] args)) arranca el programa, inicia la ejecución del programa

public class Main {

    public static void main(final String[] args) {
        // Aquí puedes iniciar tu aplicación Public siginifca que se puede usar en cualquier región, static singifica
        // que no es necesario crear un objeto de la clase Main para ejecutar el método main, void significa que no devuelve nada
        //lo que va dentro de los paréntesis es el argumento que recibe el método main, en este caso un array de String
        //final significa que no se puede modificar el argumento, args es el nombre del argumento
        //los corchetes indican es un array de String, y args significa "arguments"
       System.out.println("Hola Mundo"); //se indenta con dos espacios cada vez que se abre un bloque de código
       //System.out.println("Hola Mundo"); //esto es un comentario, no se ejecuta, sirve para explicar el código
    }
//cómo sé cuál versión de Java tengo instalada? Abro la terminal y escribo java -version
//cómo corro este código en la terminal? Abro la terminal, me ubico en la carpeta donde está el archivo Main.java y escribo:
//javac Main.java (esto compila el código y genera un archivo Main.class en la misma
}