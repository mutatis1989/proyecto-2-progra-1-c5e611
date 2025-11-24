**Proyecto 2 – Hoja de cálculo con operaciones sobre fracciones**
Descripción del programa

Este programa implementa un prototipo de extensión para un programa de hojas de cálculo tipo Google Sheets o Microsoft Excel, pero que sea capaz de trabajar con fracciones exactas, evitando así los errores de redondeo que típicamente se asocian a números con punto flotante.

Este sistema simula el comportamiento de los programas de hoja de cálculo ya que trabaja sobre una matriz (filas y columnas) cuyas celdas pueden utilizarse en la ejecución de operaciones que incluyen:

1. Suma y multiplicación de rangos

2. Cálculo de máximo, mínimo, promedio y mediana

3. Creación y manipulación de subconjuntos de celdas utilizando listas doblemente enlazadas

4. Almacenamiento de conjuntos mediante un árbol binario de búsqueda

5. Impresión completa del estado de la hoja de cálculo

El programa lee datos y comandos desde la entrada estándar y el resultado de cada operación puede almacenarse en una celda específica. El usuario puede imprimir la matriz en su estado actual para ver cómo esta ha cambiado.

**Guía de uso**

Este programa está programado en el lenguaje de programación Java. Utiliza una estructura estándar que se puede ejecutar en Windows o Linux y no requiere de un IDE específico. Puede correr desde:

- Visual Studio Code
- PowerShell (Windows)
- CMD (Windows)
- Terminal/Bash (Linux)

Para ejecutar el programa la persona usuaria debe tener instalado Java JDK 17 o superior. Esto puede verificarse de las siguientes maneras:

1. En Windows (PowerShell o CMD)

Escriba:

java -version
javac -version

2. En Linux

Escriba:

java -version
javac -version

Si ambos comandos corren, entonces ya usted puede ejecutar el proyecto.

**Estructura del proyecto**

El proyecto tiene la siguiente estructura antes de compilar:

```
proyecto2-hoja-calculo/
│
├── src/
│ ├── Controlador.java
│ ├── HojaDeCalculo.java
│ ├── Fraccion.java
│ ├── ListaDeFracciones.java
│ ├── ArbolDeListas.java
│ └── NodoDelArbol.java
│
├── tests/
│ ├── caso1.txt
│ ├── caso2.txt
│ └── caso3.txt
│
├── design/
│ ├── pseudocodigo.md
│ └── uml.png
│
├── build/
├── .gitignore
└── readme.md
```

**Cómo compilar el proyecto**

Para compilar el programa desde Windows y Linux se debe ejecutar el siguiente comando desde la carpeta raíz del proyecto (proyecto2-hoja-calculo/):

1. Windows (PowerShell o CMD)
javac -d build src/*.java

2. Linux (Bash)
javac -d build src/*.java

Esto compilará todo el código fuente y generará los archivos .class dentro de la carpeta build/.

**Otra opción para compilar sin utilizar .jar**

1. Windows
java -cp build Controlador < tests/caso1.txt

2. Linux
java -cp build Controlador < tests/caso1.txt

El comando -cp build indica la carpeta donde están los archivos .class.
El comando < tests/caso1.txt redirige el archivo al programa como entrada estándar.

**Cómo crear el archivo .jar ejecutable**

Una vez que haya compilado el código fuente:

1. Windows:
jar cfe hoja-calculo.jar Controlador -C build .

2. Linux:
jar cfe hoja-calculo.jar Controlador -C build .

Esto generará el ejecutable hoja-calculo.jar.

**Cómo ejecutar el proyecto usando el .jar**
1. Windows
java -jar hoja-calculo.jar < tests/caso1.txt

2. Linux
java -jar hoja-calculo.jar < tests/caso1.txt

**Cómo ejecutar desde Visual Studio Code**

1. Abra el proyecto en VSC
Archivo → Open Folder → seleccionar proyecto2-hoja-calculo/.

2. Abra la terminal integrada
Ctrl + ñ

3. Compile

javac -d build src/*.java

4. Ejecute

java -cp build Controlador < tests/caso1.txt

5. Alternativamente, cree y ejecute el .jar

jar cfe hoja-calculo.jar Controlador -C build .
java -jar hoja-calculo.jar < tests/caso1.txt

**Programas utilizados**

Se utilizó VSC como IDE para la ejecución del proyecto.
El sitio www.plantuml.com se utilizó para crear el diagrama UML del proyecto.
Como repositorio se utilizó la plataforma de Github. La dirección del repositorio es: https://github.com/mutatis1989/proyecto-2-progra-1-c5e611

**Créditos**

Ignacio Cubero Hernández
Carné C5E611
Correo: ignacio.cubero@ucr.ac.cr
