Clase Controlador

Método main
    Crea el objeto controlador
    Llama a controlador.run
Finaliza el método

Método run
    Lee el número de filas, lee el número de columnas
    Crea una nueva hoja de cálculo con un número de filas y columnas
    Llama al método de lectura de número de filas y número de columnas
Inicia un proceso de While en el que, en la medida en que existan más comandos hará lo siguiente:
    1. Leerá la línea de comando
    2. Llamará a procesar el comando
Finaliza el While
Finaliza el método

Método leerMatriz
    Recibe como parámetro hoja, filas y columnas
Para cada I desde 0 hasta las filas i-1, hará lo siguiente:
    Lee la línea
    Divide la línea con comas
    Genera un arreglo de tokens
    Para cada J desde 0 hasta la columna j-1, hará lo siguiente:
        Toma cada token que representa una fracción y lo divide con slashes
        Crea un objeto fracción
        Lo guarda en la celda correspondiente
Finaliza el ciclo de columnas
Finaliza el ciclo de filas
Finaliza el método


Método procesarComando
	Recibe como parámetro el commando y la hoja de cálculo
	Identifica qué tipo de comando ingresó el usuario de los siguientes:
	=CEL, = SUM, =MUL, =AVR, =MDN, =MAX, =SET, =PRINT

Si el comando es =CEL
1.	Extrae la referencia de celda dentro del paréntesis
2.	Convierte esa referencia a número de fila y columna
3.	Actualiza la celda active donde se van a guardar los resultados de la siguiente operación
4.	Finaliza =CEL

Si el comando es alguno de los siguientes (=SUM, =MUL, =AVR, =MDN, =MIN, =MAX)
1.	Extra eel contenido dentro del paréntesis
2.	Identifica si lo que hay es un rango (ejemplo A1:B3)
3.	Convierte el rango en coordenas iniciales y finales
4.	Dependiendo del comando, llama al método correspondiente de la hoja
a.	Si es SUM llama a hoja.sumarRango
b.	Si es MUL llama a hoja.multiplicarRango
c.	Si es AVR llama a hoja.promedioRango
d.	Si es MDN llama a hoja.medianaRango
e.	Si es MIN llama a hoja.minimoRango
f.	Si es MAX llama a hoja.maximoRango
5.	Si existe una celda active, entonces guarda el resultado en esa celda
Finaliza este caso

Si el comando empieza con =SET:

1.	Extra el nombre del conjunto
2.	Extra las referencias a cada celda del conjunto (A1, B2, C3..)
3.	Convierte todas esas referencias en coordenadas
4.	Crea una nueva lista doblemente enlazada
5.	Para cada celda indicada:
a.	Obtiene el valor de la hoja
b.	Agrega la fracción a la lista
6.	Guarda esta lista en el árbol, asociada al nombre del conjunto
7.	Finaliza

Si el parámetro dentro del comando no es un rango, pero más bien un nombre:
1.	Busca el conjunto en el árbol (porn ombre)
2.	Llama a las operaciones de lista según corresponda
3.	Si existe celda active, guarda el resultado en esa celda
4.	Finaliza operación con conjuntos

Si el comando es =PRINT
1.	Llama al método de la hoja que imprime toda la matriz en su estado actual
2.	Finaliza PRINT

Si el comando no está reconocido, entonces muestra un mensaje de comando inválido o lo ignora y finaliza

Finaliza el método

Método obtenerCelda
Recibe como parámetro una referencia de celda como A1, B3, C10, etc
Convierte la letra de la columna a un número de columna
Convierte el número de la referencia a número de fila
Devuelve la fila y la columna correspondientes
Finaliza el método

Método obtenerRango
Recibe como parámetro un texto con un rango como A1:B3
Separa la referencia inicial de la referencia final
Convierte ambas referencias usando obtenerCelda
Devuelve filaInicial, columnaInicial, filaFinal y columnaFinal
Finaliza el método

Caso PRINT(nombre)
Si el comando es =PRINT(nombre)
1.	Extrae el nombre dentro del paréntesis
2.	Busca ese nombre en el árbol
3.	Imprime la lista de fracciones asociada a ese nombre
4.	Finaliza PRINT(nombre)



Clase Hoja de Cálculo

Clase que maneja la matriz de fracciones y operaciones sobre rangos. No lee nada, no imprime comandos, no procesa textos, solo administra datos.
Atributos:
1.	Matriz de fracciones
2.	Árbol binario donde guarda los conjuntos por nombre
3.	Celda activa donde se guarda el resultado

Método setCelda
Recibe como parámetros la fila, la columna y una fracción
Coloca la fracción dentro de la matriz en esa posición
Finaliza
Metodo getCelda
Recibe fila y columna
Devuelve la fracción almacenada en esa celda
Finaliza  

Método sumarRango
Recibe una fila inicial, una columna inicial, una fila final, una columna final
Crea un acumulador que inicia en fracción 0/1

Para cada celda dentro del rango indicado:
Obtiene el valor de la matriz
Suma la fracción al acumulador
Finaliza

Al terminar de recorrer todas las celdas del rango devuelve la fracción acumulada
Finaliza el método

Método multiplicarRango
Recibe un rango igual que en sumar Rango
Crea un acumulador que inicia en fracción 1/1

Para cada celda del rango:
Obtiene la fracción
Multiplica el acumulador de esa fracción

Al terminar:
Devuelve el resultado
Finaliza el método

Método promedioRango
Recibe un rango
Cuenta cuántos elementos hay en ese rango
Llama a sumar rango para obtener la suma total
Divide la suma total entre la cantidad de elementos
Devuelve la fracción resultante
Finaliza el método

Método medianaRango
Recibe un rango
Crea una lista temporal de fracciones
Para cada celda del rango agrega la fracción a la lista
Orden al a lista de menor a mayor
Si la cantidad de elementos es impar, toma la fracción que está en el centro
Si la cantidad es par toma las dos fracciones centrales, las suma y divide el resultado entre 2
Devuelve la fracción que corresponde según el caso
Finaliza el método

Método minimoRango
Recibe un rango
Inicia una fracción mínima con el primer elemento del rango
Para cada celda del rango compara la fracción con la fracción mínima actual
Si es menor, actualiza la mínima
Devuelve la fracción mínima
Finaliza el método

Método maximoRango
Recibe un rango
Inicia una fracción máxima con el primer elemento del rango
Para cada celda del rango compara la fracción con la fracción máxima actual
Si es mayor, actualiza la máxima
Devuelve la fracción máxima
Finaliza el método

Método imprimir
Calcula el ancho máximo que ocupa cualquier fracción de la matriz
Imprime los encabezados de las columnas (A,B,C…)
Para cada fila imprime el número de la fila
Para cada columna imprime la fracción alineada al ancho calculado
Finaliza el método

Método setCeldaActiva
Recibe una fila y una columna
Actualiza la celda activa a esa posición
Finaliza el método

Método getCeldaActiva
Devuelve la fila y la columna de la celda activa
Finaliza el método

Método resetCeldaActiva
Deja la celda activa en un estado vacío o nulo
Finaliza el método

Método getConjunto
Recibe un nombre de conjunto
Llama al árbol para obtener la lista asociada a ese nombre
Devuelve la lista
Finaliza el método
 

Clase Fracción

Atributos:
1.	Numerador
2.	Denominador

Método constructor
Recibe un numerador y un denominador como parámetros
Asigna esos valores a los atributos internos de la fracción
Llama a un método interno que simplifica la fracción
Finaliza el método

Método simplificar
Calcula el máximo común divisor entre el numerador y el denominador
Divide el numerador y el denominador por ese valor
Asegura que el denominador nunca sea cero
Asegura que si la fracción es negativa, el signo esté en el numerador
Finaliza el método

Método sumar
Recibe otra fracción como parámetro
Calcula un nuevo numerador de la siguiente forma:
(numerador actual * denominador de la otra fracción) + (numerador de la otra fracción*denominador actual)
Calcula un nuevo denominador como:
(denominador actual * denominador de la otra fracción)
Crea una nueva fracción con esos valores
Simplifica la fracción creada
Devuelve la fracción resultante
Finaliza el método

Método multiplicar
Recibe otra fracción
Multiplica numeradores
Multiplica denominadores
Crea una nueva fracción con esos valores
Simplifica la nueva fracción
Devuelve el resultado
Finaliza el método

Método comparar
Recibe otra fracción
Para comparar sin convertir a decimal, usa multiplicación cruzada:
a <= numerador actual * denominador de la otra fracción
b <= numerador de la otra fracción * denominador dactual
Si a es menor que b, la fracción actual es menor
Si a es mayor que b, la fracción actual es mayor
Si son iguales, las fracciones son equivalentes
Finaliza el método

Método toString
Convierte la fracción a texto con el formato “numerador/denominador”
Devuelve ese texto
Finaliza el método

 
Clase ListaDeFracciones

Atributos
-	Un nodo inicial (head)
-	Un nodo final (tail)
-	Contador

Cada nodo contiene:
-	Una fracción
-	Un enlace al nodo siguiente
-	Un enlace al nodo anterior

Método constructor
Inicializa head en null
Inicializa tail en null
Inicializa la cantidad de elementos en 0
Finaliza el método

Método addBack
Recibe una fracción
Si la lista está vacía:
	Crea un nuevo nodo con esa fracción
	Head y tail apuntan a ese nodo
Si la lista no está vacía:
	Crea un nuevo nodo
	El nodo actual de tail apunta al nuevo nodo como siguiente
	El nuevo nodo apunta a tail como el anterior
	Actualiza tail al nuevo nodo
Incrementa el Contador de elementos
Finaliza el método

Método remove
Recibe una posición o un nodo específico
Si la lista está vacía, no hace nada
Si es el primer nodo:
	Mueve tail al segundo nodo
	Si el nuevo primer nodo existe, le quita el enlace anterior
Si es el último nodo:
	Mueve tail al nodo anterior
	Si el nuevo último nodo existe le quita el enlance siguiente
Si es un nodo en medio:
	El nodo anterior apunta al nodo siguiente
	El nodo siguiente apunta al nodo anterior
Reduce el Contador de elementos
Finaliza el método

Método get
Recibe una posición
Empieza desde head y avanza hasta esa posición
Devuelve la fracción almacenada en el nodo correspondiente
Finaliza el método

Método ordenar
Convierte la lista en un arreglo temporal de fracciones
Ordena el arreglo utilizando el método de comparer la clase Fracción
Vacía la lista actual
Vuelve a agregar cada fracción en orden al final
Finaliza el método

Método sumarElementos
Crea un acumulador con la fracción 0/1
Para cada nodo de la lista:
Suma la fracción del nodo al acumulador
Devuelve el acumulador
Finaliza el método

Método convertirEnArreglo
Crea un arreglo vacío
Recorre la lista desde head hasta tail
Agrega cada fracción al arreglo
Devuelve el arreglo con todas las fracciones
Finaliza el método


Método multiplicarElementos
Crea un acumulador con la fracción 1/1
Para cada nodo de la lista:
    Multiplica la fracción del nodo con el acumulador
Devuelve el acumulador
Finaliza el método

Método promedio
Si la lista está vacía, devuelve 0/1
    Llama a sumarElementos
    Divide el resultado entre la cantidad de elementos
    Devuelve la fracción resultante
Finaliza el método

Método mediana
    Convierte la lista en un arreglo temporal
    Ordena ese arreglo
    Si la cantidad de elementos es impar, devuelve el del centro
    Si es par, suma los dos del centro y divide entre dos
Finaliza el método

Método toString
    Crea un texto vacío
Para cada fracción de la lista:
    Agrega la fracción convertida en texto
    Agrega una coma
Devuelve el texto resultante sin la última coma
Finaliza el método
 
Clase ArbolDeListas

Atributos
La clase ArbolDeListas tiene:
•	un nodo raíz del árbol
•	cada nodo del árbol contiene:
o	un nombre (cadena)
o	una ListaDeFracciones asociada a ese nombre
o	un hijo izquierdo
o	un hijo derecho

Método constructor
    Inicializa la raíz en null
Finaliza el método

Método insertar
    Recibe un nombre y una ListaDeFracciones
Si la raíz es null:
    Crea un nuevo nodo con ese nombre y esa lista
    La raíz apunta a ese nodo
    Finaliza
Si la raíz no es null:
    Inicia un proceso de recorrido para encontrar la posición correcta usando orden alfabético
Mientras no se encuentre una posición vacía:
    Si el nombre es menor al del nodo actual (alfabéticamente):
        Si el hijo izquierdo está vacío:
            Inserta el nodo ahí
            Finaliza
        Si no está vacío, continúa por el hijo izquierdo
    Si el nombre es mayor al del nodo actual:
        Si el hijo derecho está vacío:
            Inserta el nodo ahí
            Finaliza
        Si no está vacío, continúa por el hijo derecho
Si el nombre es igual al del nodo actual:
    Actualiza la lista asociada a ese nombre (sobrescribe la anterior)
    Finaliza
Finaliza el método

Método buscar
    Recibe un nombre
Comienza desde la raíz
Mientras el nodo no sea null:
    Si el nombre buscado es igual al nombre del nodo actual:
        Devuelve la ListaDeFracciones asociada
    Si el nombre buscado es menor:
        Avanza al hijo izquierdo
    Si el nombre buscado es mayor:
        Avanza al hijo derecho
Si el recorrido termina y no se encontró coincidencia:
    Devuelve null
Finaliza el método

Método imprimirInOrden
    Recibe un nodo (inicia en la raíz)
Si el nodo no es null:
    Llama a imprimirInOrden con el hijo izquierdo
    Imprime el nombre del conjunto
    Llama a imprimirInOrden con el hijo derecho
Finaliza elm étodo

Método existe
    Recibe un nombre
    Llama al método buscar
    Si buscar devuelve una lista válida, entonces existe
    Si buscar devuelve null, no existe
Finaliza el método

Método reemplazar
    Recibe un nombre y una ListaDeFracciones
    Busca el nodo con ese nombre
    Si existe, actualiza la lista asociada
    Si no existe, lo inserta como si fuera el método insertar
Finaliza el método

Clase NodoDelArbol
Contiene un nombre
Contiene una ListaDeFracciones asociada
Contiene un hijo izquierdo
Contiene un hijo derecho



 
