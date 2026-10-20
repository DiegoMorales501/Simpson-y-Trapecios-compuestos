package dif_e_Integracion;

/** Simpson 1/3 compuesto: I ≈ (h/3)[f(x0) + 4·Σf(impar) + 2·Σf(par) + f(xn)]. n par. Error O(h⁴). */
public class SimpsonCompuesto {

    /** Simpson compuesto con n fijo (n par). */
    static double calcularSimpson(double a, double b, int n) {
        double h = TrapecioCompuesto.calcularH(a, b, n);
        double sumaImpares = 0.0;
        double sumaPares = 0.0;

        for (int k = 1; k < n; k++) {
            double xk = a + k * h;
            if(k % 2 != 0){
                sumaImpares += TrapecioCompuesto.funcion(xk);// se aplica la condicion de acumulacion para el termino impares
            }
            else{
                sumaPares += TrapecioCompuesto.funcion(xk);// se aplica la condicion de acumulacion para el termino pares
            }
        }
        double sumaTotal = TrapecioCompuesto.funcion(a) + 4 * sumaImpares + 2 * sumaPares + TrapecioCompuesto.funcion(b);
        return (h / 3) * sumaTotal;
    }

    /** Duplica n hasta que la diferencia entre iteraciones sea <= tolerancia. */
    public static double integrarConTolerancia(double a, double b, double Tolerancia){
        int n = 2;
        double integralAnterior = calcularSimpson(a, b, n);
        double errorAbsoluto = 999.0;

        System.out.println("--- ITERACIONES (SIMPSON COMPUESTO) ---");
        System.out.println("n = " + n + "| Integral = " + integralAnterior);

        while(errorAbsoluto > Tolerancia){
            n *= 2;
            double integralNueva = calcularSimpson(a, b, n);
            errorAbsoluto = Math.abs(integralNueva - integralAnterior);
            integralAnterior = integralNueva;

            System.out.println("n = " + n + "| Integral = " + integralNueva );
        }
        return integralAnterior;
    }
}