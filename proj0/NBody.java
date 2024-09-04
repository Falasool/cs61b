import java.awt.geom.RoundRectangle2D;
import java.io.File;

public class NBody {
    public static void main(String[] args) {
        double T = new Double(args[0]);
        double dt = new Double(args[1]);
        String filename = args[2];
        readRadius(filename);
        readPlanets(filename);
    }

    /**
     * Given a file name, it should return a double corresponding to the radius of the universe in that file
     *
     * @param fileName
     * @return
     */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /**
     * Given a file name, it should return an array of Planets corresponding to the planets in the file
     *
     * @param fileName
     * @return
     */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
        }
        return planets;
    }

}
