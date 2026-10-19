package dif_e_Integracion;

/** Compara Simpson y trapecio sobre el mismo intervalo y tolerancia. */
public class MainSimpson {
    static void main(String[] args) {
        double a = 0.0;
        double b = 2.0;
        double tolerancia = 0.0001;

        double trapecio = TrapecioCompuesto.integrarConTolerancia(a, b, tolerancia);
        System.out.println("\nTrapecio final obtenido : " + trapecio);
        System.out.println();
        double Simpson = SimpsonCompuesto.integrarConTolerancia(a, b, tolerancia);
        System.out.println("\nSimpson final obtenido : " + Simpson);

    }
}
