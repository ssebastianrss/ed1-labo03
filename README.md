# Laboratorio 03

## Instrucciones

1. Para iniciar, debe crear un *Fork* del repositorio:

![fork button](images/fork.png)

2. Vaya la pestaña de **actions** de su repositorio. Si ve un botón verde, haga clic en él para poder ejecutar las pruebas en el futuro.

![actions tab](images/actions.png)

3. Clone el nuevo repositorio en su computadora y ábralo en IntelliJ.

4. Construya/compile la aplicación en IntelliJ.

5. Ejecute las pruebas unitarias.

6. No se preocupe si todas o la mayoría de las pruebas fallan. Al terminar el laboratorio, todas las pruebas deberían funcionar.
___

## Introducción

- Todos los ejercicios deben compilar para poder ser probados. Si por lo menos uno de los ejercicios no compila, la nota sera de **cero** puntos.
- Si el código original de un ejercicio no se modifica o la intención de su resolución no es clara, la nota del ejercicicio será de **cero puntos**, aún si hay pruebas que sí pasen para dicho ejercicio.
- NO agregue nuevos métodos `main()`, de lo contrario ninguna prueba podrá ejecutarse.
- NO cambie la firma de los métodos existentes (no agrege más parámetros ni cambie el nombre), estos son utilizados para probar su código.
- NO haga cambios en las pruebas, esto resulta en un **cero inmediato**.
- Puede agregar nuevas clases y/o archivos, como sea necesario.
- En la pestaña de **Actions** podrá ver como las pruebas se ejecutan con su código implementado (si hace `git push` de un nuevo commit previamente).
___

## Ejercicio 1

Dado un arreglo de números enteros `nums` y un entero `k`, retorne **los `k` elementos más frecuentes**. Puede retornar la respuesta en **cualquier orden**.

### Ejemplo 1.1:

```shell
Entrada: nums = [1,1,1,2,2,3], k = 2
Salida: [1,2]
```

### Ejemplo 1.2:

```shell
Entrada: nums = [1], k = 1
Salida: [1]
```

### Sugerencia Avanzada:

Debe **optimizar** su algoritmo más allá de la complejidad `O(n log n)`, donde `n` es la longitud del arreglo.

___

## Ejercicio 2

Diseñe un sistema de autocompletado para un motor de búsqueda. El sistema debe sugerir hasta tres oraciones basadas en las oraciones que el usuario ha escrito previamente.

Cada vez que el usuario ingresa un nuevo carácter, excepto el carácter de finalización `'#'`, se actualiza una cadena de búsqueda. Las sugerencias deben basarse en una combinación de dos factores:

1. La **frecuencia** de las oraciones anteriores (cuántas veces fueron ingresadas).
2. El **orden lexicográfico** (alfabético) si hay dos oraciones con la misma frecuencia.

Implemente la clase `E02AutocompleteSystem`:

- `E02AutocompleteSystem(String[] sentences, int[] times)` Inicializa el sistema con una lista de oraciones `sentences` y la cantidad de veces `times` que cada oración ha sido ingresada por el usuario.
- `List<String> input(char c)` Este método:
    - Retorna las **tres oraciones más frecuentes** que comienzan con la cadena formada por los caracteres ingresados hasta ahora.
    - Si se ingresa el carácter `'#'`, finaliza la entrada actual:
        - Guarda la cadena formada hasta ese punto como una nueva oración (o incrementa su frecuencia si ya existe).
        - Retorna una lista vacía `[]`.

### Ejemplo 2.1:

```java
AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
obj.input("i"); // Retorna ["i love you", "island", "i love leetcode"]. Existen cuatro oraciones con prefijo "i". Entre ellas, "ironman" e "i love leetcode" tiene la misma frecuencia. Dado que ' ' tiene código ASCII de 32 y 'r' tiene código ASCII code 114, "i love leetcode" debería ser retornado en frente de "ironman". Además, solo es necesario retornar las top 3 oraciones más frecuentes, así que "ironman" será ignorada.
obj.input(" "); // Retorna ["i love you", "i love leetcode"]. Existen únicamente dos oraciones con prefijo "i ".
obj.input("a"); // Retorna []. No existen oraciones con prefijo "i a".
obj.input("#"); // Retorna []. El usuario finalizó la escritura, la oración "i a" debería ser almacenada en el sistema. Dicha entrada será contada como una nueva búsqueda.
```

**Explicación:**

- El sistema comienza con cuatro oraciones y sus respectivas frecuencias.
- Después de cada llamada a `input(c)`, se retorna una lista con las tres sugerencias más relevantes, o una lista vacía si el carácter es `'#'`.

### Restricciones:

- `1 <= sentences.length <= 100`
- `1 <= sentences[i].length <= 100`
- `sentences[i]` contiene solo letras minúsculas, espacios o el carácter `'#'`
- `times.length == sentences.length`
- `0 <= times[i] <= 100`
- `1 <= c <= 100`, donde `c` es un carácter ASCII válido
- Se garantiza que cada llamada a `input` representa un solo carácter válido

### Nota:

- Las oraciones almacenadas pueden contener espacios.
- El orden de las sugerencias debe basarse primero en la frecuencia (más alta primero), y luego por orden alfabético en caso de empate.