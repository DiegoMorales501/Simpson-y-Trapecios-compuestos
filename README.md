# Integración numérica: Trapecio vs Simpson 

Proyecto en Java que calcula integrales de forma aproximada con dos métodos y los compara: **trapecio compuesto** y **Simpson 1/3 compuesto**.

La idea es sencilla: en vez de integrar a mano, el programa parte el intervalo [a, b] en `n` pedacitos y va duplicando `n` hasta que el resultado deja de cambiar (según una tolerancia que tú eliges).

## Qué hay en el proyecto

```
dif_e_Integracion/
├── TrapecioCompuesto.java   -> método del trapecio + la función a integrar
├── SimpsonCompuesto.java    -> método de Simpson 1/3
└── MainSimpson.java         -> corre los dos y muestra los resultados
```

- **`TrapecioCompuesto`**: además del método, aquí vive `funcion(x)` (la que se integra) y `calcularH`. Simpson las reutiliza, así los dos métodos siempre integran lo mismo.
- **`SimpsonCompuesto`**: usa parábolas en lugar de rectas, por eso converge mucho más rápido.
- **`MainSimpson`**: define el intervalo y la tolerancia, y llama a los dos métodos.

## Cómo funciona

Los dos métodos hacen lo mismo:

1. Empiezan con `n = 2` subintervalos.
2. Calculan la integral.
3. Duplican `n` y la calculan otra vez.
4. Si la diferencia entre las dos últimas aproximaciones es menor o igual que la tolerancia, paran. Si no, repiten.

Las fórmulas:

- **Trapecio:** `I ≈ (h/2) · [f(x0) + 2·Σf(xk) + f(xn)]`, con error de orden O(h²).
- **Simpson 1/3:** `I ≈ (h/3) · [f(x0) + 4·Σf(impares) + 2·Σf(pares) + f(xn)]`, con error de orden O(h⁴). Necesita que `n` sea par (por eso empezamos en 2 y duplicamos).

## Cómo correrlo

1. Mete las tres clases en el paquete `dif_e_Integracion`.
2. Ejecuta `MainSimpson`.

> **Ojo con Java:** si el `main` está como `static void main` (sin `public`), solo arranca en Java 25 o superior. En versiones anteriores usa `public static void main(String[] args)`.

## Qué debería salir

Con `f(x) = x²` en [0, 2] y tolerancia `0.0001` (el valor exacto es 8/3 ≈ 2.6667):

```
--- ITERACIONES (TRAPECIO COMPUESTO) ---
n = 2| Integral = 3.0
n = 4| Integral = 2.75| Error = 0.25
...
n = 256| Integral = 2.66668701171875| Error = 6.103515625E-5

Trapecio final obtenido : 2.66668701171875

--- ITERACIONES (SIMPSON COMPUESTO) ---
n = 2| Integral = 2.6666666666666665
n = 4| Integral = 2.6666666666666665

Simpson final obtenido : 2.6666666666666665
```

## La comparación

| Método   | Subintervalos | Resultado             | Qué pasó                         |
|----------|---------------|-----------------------|----------------------------------|
| Trapecio | 256           | 2.66668701171875      | Necesitó 8 iteraciones           |
| Simpson  | 2 (confirma con 4) | 2.6666666666666665 | Exacto desde la primera          |

¿Por qué Simpson sale exacto? Porque integra sin error cualquier polinomio de grado 3 o menos, y `x²` es de grado 2. Con otras funciones ya no será exacto, pero sigue ganándole al trapecio por mucho.

## Probar con otra función

1. Cambia el `return` de `funcion` en `TrapecioCompuesto`, por ejemplo:
   ```java
   return Math.sin(x);
   ```
2. Cambia el intervalo en `MainSimpson`: `b = Math.PI;` (el resultado exacto de ∫sin(x) en [0, π] es **2**).

Ahí sí vas a ver a Simpson iterar varias veces, pero con muchos menos subintervalos que el trapecio.

## Cosas a tener en cuenta

- La tolerancia mide la diferencia entre dos iteraciones seguidas, no el error real. Normalmente el error real es menor que ese valor.
- Si `f` es muy rara (con picos o discontinuidades), los métodos pueden tardar mucho o no converger bien.