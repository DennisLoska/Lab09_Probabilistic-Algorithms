import java.awt.geom.Point2D;

public class Probabilistic {

    private static final int RADIUS = 5000;
    private static final int WIDTH = 10000;
    private static final int HEIGHT = 10000;
    private static int iterations = 10000;
    private static int random = (int) (Math.random() * (WIDTH));
    private static double volume_A = Math.PI * (RADIUS * RADIUS);
    private static double inside = 0;
    private static double all = 0;

    private static double computedPi = 0;
    private static double approxPi = 0;


    public static void main(String[] args) {
        computePi(random);

        for (int i = 0; i < iterations; i++) {
            Point2D p = createRndPoints();
            calcRatio(p);
//            System.out.println("x: " + p.getX() + "\t y: " + p.getY() + " " + isInCircle(p));
        }
        displayRatio();

        approximatePi();

        comparePi();
    }

    private static void computePi(int random) {
        // pi = 4(1 - 1/3 + 1/5 - 1/7 + 1/9 - 1/11 + ... + 1/2n-1 - 1/2n+1)

        double computedPi = 0.0;
        double zaehler = 1;

        for (int i = 0; i < random; i++) {
            if (i % 2 == 0) {
                computedPi = computedPi + (1.0 / zaehler);
            } else {
                computedPi = computedPi - (1.0 / zaehler);
            }
        zaehler += 2;
        }
        computedPi = computedPi * 4.0;
//        System.out.println(random);
        System.out.println("Calculated Pi is: " + computedPi);
        System.out.println("Math.PI is: " + Math.PI);
    }

    private static void approximatePi() {
        approxPi = inside * 4 / all;
        System.out.println("Approximated Pi is: " + approxPi);
        System.out.println("Math.PI is: " + Math.PI);
    }

    //AUFGABE 1.2
    private static Point2D createRndPoints() {
        int rndX = (int) (Math.random() * (WIDTH));
        int rndY = (int) (Math.random() * (HEIGHT));
        return new Point2D.Double(rndX, rndY);
    }

    //AUFGABE 1.3
    private static boolean isInCircle(Point2D rndPoint) {
        //Strecken der Punkte für Berechnung mit Pythagoras
        int circleMidX = WIDTH / 2;
        int deltaX = (int) (rndPoint.getX()) - circleMidX;
        int circleMidY = HEIGHT / 2;
        int deltaY = (int) (rndPoint.getY()) - circleMidY;
        //Hypotenuse
        double d = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
        return d < RADIUS * RADIUS;
    }

    //AUFGABE 1.4
    private static void calcRatio(Point2D rndP) {
        if (isInCircle(rndP))
            inside++;
        all++;
    }

    //AUFGABE 1.4
    private static void displayRatio() {
        System.out.println("\nIN: " + inside + " ALL: " + all);
        System.out.println("\nVerhältnis von Punkten IN/NICHT-IN Kreis: IN/ALL= " + all / inside);
    }

    //AUFGABE 1.5
    private static void comparePi(){
        String computedPiString = Double.toString(computedPi);
        byte[] computedPiArr = computedPiString.getBytes();
        int computedPiLength = computedPiArr.length;

        String approxPiString = Double.toString(approxPi);
        byte[] approxPiArr = approxPiString.getBytes();
        int approxPiLength = approxPiArr.length;

        String libPiString = Double.toString(Math.PI);
        byte[] libPiArr = libPiString.getBytes();

        int hits = 0;

//        for (int i = 0; i < approxPiLength; i++){
//            System.out.println(approxPiArr[i]);
//        }
//
//        System.out.println("######################");
//
//        for (int i = 0; i < libPiArr.length; i++){
//            System.out.println(libPiArr[i]);
//        }

        for (int i = 2; i < approxPiLength; i++){
            if (libPiArr[i] == approxPiArr[i]){
                hits++;
            }
        }

        System.out.println("\n" + "Geschätzte Zahl PI auf " + hits + " Nachkommastellen genau");
    }

}