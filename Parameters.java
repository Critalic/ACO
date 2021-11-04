package schmitt.joao.aco;


public class Parameters {

    public static double rho = 0.6;

    public static double alpha = 2.0;

    public static double beta = 4.0;

    public static int antPopSize = 45;

    public static int NNSize = 20;

    public static int iterationsMax = 300;

    private static double lMin;

    public static void setlMin(double lMin) {
        Parameters.lMin = lMin;
    }

    public static double getlMin() {
        return lMin;
    }
}
