import java.awt.geom.Point2D;

public class Probabilistic {

    private static final int RADIUS = 5000;
    private static final int WIDTH = 10000;
    private static final int HEIGHT = 10000;
    private static int random = (int) (Math.random() * (WIDTH));
    private static double volume_A = Math.PI*(RADIUS*RADIUS);
    private static double inside = 0;
    private static double all = 0;

    public static void main(String[] args) {
        computePi(random);
        for (int i = 0; i < 10000; i++) {
            Point2D p = createRndPoints();
            calcRatio(p);
            System.out.println("x: " + p.getX() + "\t y: " + p.getY() + " " + isInCircle(p));
        }
        displayRatio();
    }

    private static void computePi(int random) {
        //TODO implement this method @Tony
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
        if (d < RADIUS * RADIUS)
            return true;
        else return false;
    }

    //AUFGABE 1.4
    private static void calcRatio(Point2D rndP){
        if (isInCircle(rndP))
            inside++;
        all++;
    }

    //AUFGABE 1.4
    private static void displayRatio(){
        System.out.println("\nIN: " + inside + " ALL: " + all);
        System.out.println("\nVerhältnis von Punkten IN/NICHT-IN Kreis: IN/ALL= " + all/inside);
    }

    //AUFGABE 1.5
    //TODO @tony Aufgabe 1.5 machen

}