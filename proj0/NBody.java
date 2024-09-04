import java.awt.geom.RoundRectangle2D;
import java.io.File;

public class NBody {
    public static void main(String[] args) {
        /**
         * 绘制星系运动轨迹
         */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        // 获取文件名
        String filename = args[2];
        // 通过读取文件名获取饼储存行星列表
        double radius = readRadius(filename);
        Planet[] planetsList = readPlanets(filename);
        // 设置画布范围
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
//        StdDraw.enableDoubleBuffering();

        double time = 0;
        while (time < T) {
            int count = planetsList.length;
            // 初始化一个长度为 count 的一维数组
            // Fx 储存所有的x轴方向的分力
//            double[] xFouces = new double[count];
            // Fy 储存所有的y轴方向的分力
//            double[] yFouces = new double[count];
            // 循环存储行星和它们的分力
            for (int i = 0; i < count; i++) {
                Planet planet = planetsList[i];
//                xFouces[i] = planet.calcForceExertedByX(planet);
//                yFouces[i] = planet.calcForceExertedByY(planet);
            }
            for (int i = 0; i < count; i++) {
//                double forceX = xFouces[i];
//                double forceY = yFouces[i];
//                Planet planet = planetsList[i];
                // 更新行星位置和速度
//                planet.update(dt, forceX, forceY);
            }
//            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet planet : planetsList) {
                planet.draw();
            }

//            StdDraw.show();
//            StdDraw.pause(10);
//            time += dt;
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
