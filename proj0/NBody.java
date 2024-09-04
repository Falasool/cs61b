import java.awt.geom.RoundRectangle2D;
import java.io.File;

public class NBody {
    public static void main(String[] args) {
        /**
         * 绘制行星轨迹动画：
         * 1. 绘制静止的单个行星
         * 2. 绘制静止的多个行星
         * 3. 每 10ms 重新计算一次行星的值，重新绘制宇宙（动画）
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        // 行星列表
        Planet[] planets = readPlanets(filename);

        // set the universe scale
        StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        int num = planets.length;
        while(t <= T){
            // X轴方向的力的数组 && Y轴方向的力的数组
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i = 0; i < num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            // 更新每个行星的位置和速度和加速度
            for(int i = 0; i < num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // draw the backgroud picture
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // draw all the planets
            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
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
