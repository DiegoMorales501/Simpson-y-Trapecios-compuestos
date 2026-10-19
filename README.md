# Integración numérica: Trapecio vs Simpson

Proyecto en Java que calcula integrales de forma aproximada con dos métodos y los compara: **trapecio compuesto** y **Simpson 1/3 compuesto**.

La idea: se parte el intervalo [a, b] en `n` subintervalos y se va duplicando `n` hasta que la diferencia entre dos aproximaciones seguidas sea menor o igual que la tolerancia.

## Estructura del proyecto

```
README.md
src/
└── dif_e_Integracion/
    ├── TrapecioCompuesto.java   -> método del trapecio + la función a integrar f(x)
    ├── SimpsonCompuesto.java    -> método de Simpson 1/3
    └── MainComparativo.java         -> corre los dos métodos y muestra resultados
```

- **`TrapecioCompuesto`**: además del método, contiene `funcion(x)` (la que se integra) y `calcularH`. Simpson las reutiliza, así ambos integran siempre lo mismo.
- **`SimpsonCompuesto`**: usa parábolas en vez de rectas, por eso converge más rápido.
- **`MainComparativo`**: define el intervalo [0, 2] y la tolerancia 0.0001, y llama a los dos métodos.

## Cómo compilar y ejecutar

Necesitas tener el JDK instalado (para revisar: `java -version` y `javac -version`).

> **Ojo:** si en `MainComparativo` el `main` está como `static void main` (sin `public`), solo arranca en Java 25 o superior. En versiones anteriores usa `public static void main(String[] args)`.

### Opción A: desde la terminal

1. Abre la terminal y ubícate en la raíz del proyecto (donde está este README).
2. Compila todas las clases (los `.class` se guardan en la carpeta `out`):
   ```bash
   javac -d out src/dif_e_Integracion/*.java
   ```
   En Windows (CMD o PowerShell) funciona igual. Si el comodín `*.java` te da problema, escribe los tres archivos uno por uno.
3. Ejecuta el programa:
   ```bash
   java -cp out dif_e_Integracion.MainComparativo
   ```

### Opción B: desde un IDE (IntelliJ IDEA, Eclipse, NetBeans)

1. Abre o importa el proyecto (en IntelliJ: *File > Open* y elige la carpeta del proyecto).
2. Verifica que las tres clases estén dentro del paquete `dif_e_Integracion`.
3. Abre `MainComparativo.java`.
4. Ejecútalo con el botón verde de *Run* junto al `main` (o clic derecho sobre el archivo > *Run 'MainComparativo.main()'*).
5. La salida aparece en la consola del IDE.

## Ejemplo de salida en consola

Con `f(x) = x²`, intervalo [0, 2] y tolerancia `0.0001` (valor exacto: 8/3 ≈ 2.6667):

```
--- ITERACIONES (TRAPECIO COMPUESTO) ---
n = 2| Integral = 3.0
n = 4| Integral = 2.75| Error = 0.25
n = 8| Integral = 2.6875| Error = 0.0625
n = 16| Integral = 2.671875| Error = 0.015625
n = 32| Integral = 2.66796875| Error = 0.00390625
n = 64| Integral = 2.6669921875| Error = 9.765625E-4
n = 128| Integral = 2.666748046875| Error = 2.44140625E-4
n = 256| Integral = 2.66668701171875| Error = 6.103515625E-5

Trapecio final obtenido : 2.66668701171875

--- ITERACIONES (SIMPSON COMPUESTO) ---
n = 2| Integral = 2.6666666666666665
n = 4| Integral = 2.6666666666666665

Simpson final obtenido : 2.6666666666666665
```

## Comparación

| Método   | Subintervalos      | Resultado          | Qué pasó                |
|----------|--------------------|--------------------|-------------------------|
| Trapecio | 256                | 2.66668701171875   | Necesitó 8 iteraciones  |
| Simpson  | 2 (confirma con 4) | 2.6666666666666665 | Exacto desde la primera |

¿Por qué Simpson sale exacto? Porque integra sin error cualquier polinomio de grado 3 o menos, y `x²` es de grado 2. Con otras funciones ya no sería exacto, pero sigue necesitando muchos menos subintervalos que el trapecio (error O(h⁴) contra O(h²)).

## Fórmulas

- **Trapecio:** `I ≈ (h/2) · [f(x0) + 2·Σf(xk) + f(xn)]`
- **Simpson 1/3:** `I ≈ (h/3) · [f(x0) + 4·Σf(impares) + 2·Σf(pares) + f(xn)]`, con `n` par.

## Probar con otra función

1. En `TrapecioCompuesto`, cambia el `return` de `funcion`, por ejemplo a `Math.sin(x)`.
2. En `MainComparativo`, usa `b = Math.PI` (el resultado exacto es **2**).

Ahí Simpson ya iterará varias veces, pero con muchos menos subintervalos que el trapecio.

- La tolerancia mide la diferencia entre dos iteraciones seguidas, no el error real (normalmente el error real es menor).