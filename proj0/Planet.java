import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Planet {
    //    current x position
    double xxPos;
    // current y postion
    double yyPos;
    //    current velocity in x direction
    double xxVel;
    // current vleocity in y direction
    double yyVel;
    // mass 质量
    double mass;
    // 行星图片的文件名 planet
    String imgFileName;

    static final double G = 6.67E-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    // 以 Planet 实例为基准再构造一个实例
    public Planet(Planet planet) {
        this.xxPos = planet.xxPos;
        this.yyPos = planet.yyPos;
        this.xxVel = planet.xxVel;
        this.yyVel = planet.yyVel;
        this.mass = planet.mass;
        this.imgFileName = planet.imgFileName;
    }

    /**
     * calculates the distance between two Planets
     *
     * @param planet
     * @return
     */
    public double calcDistance(Planet planet) {
        double xxAbsDistance = Math.abs(this.xxPos - planet.xxPos);
        double yyAbsDistance = Math.abs(this.yyPos - planet.yyPos);
        return Math.sqrt(xxAbsDistance * xxAbsDistance + yyAbsDistance * yyAbsDistance);
    }

    /**
     * returns a double describing the force exerted on this planet by the given planet
     *
     * @param planet
     * @return
     */
    public double calcForceExertedBy(Planet planet) {
        return this.mass * planet.mass * Planet.G / (this.calcDistance(planet) * this.calcDistance(planet));
    }

    /**
     * describe the force exerted in the X directions
     *
     * @param planet
     * @return
     */
    public double calcForceExertedByX(Planet planet) {
        double xxDistance = planet.xxPos - this.xxPos;
        double calcDistance = this.calcDistance(planet);
        return this.calcForceExertedBy(planet) * xxDistance / calcDistance;
    }

    /**
     * describe the force exerted in the Y directions
     *
     * @param planet
     * @return
     */
    public double calcForceExertedByY(Planet planet) {
        double yyDistance = planet.yyPos - this.yyPos;
        double calcDistance = this.calcDistance(planet);
        return this.calcForceExertedBy(planet) * yyDistance / calcDistance;
    }

    /**
     * calculate the net X force exerted by all planets in that array upon the current Planet
     *
     * @param planets
     * @return
     */
    public double calcNetForceExertedByX(Planet[] planets) {
        double netforceX = 0;
        List<Planet> filteredPlanets = this.filterOthersPlanets(planets);
        for (Planet planet : filteredPlanets) {
            netforceX += this.calcForceExertedByX(planet);
        }
        return netforceX;
    }

    /**
     * calculate the net Y force exerted by all planets in that array upon the current Planet
     *
     * @param planets
     * @return
     */
    public double calcNetForceExertedByY(Planet[] planets) {
        double netforceY = 0;
        List<Planet> filteredPlanets = this.filterOthersPlanets(planets);
        for (Planet planet : filteredPlanets) {
            netforceY += this.calcForceExertedByY(planet);
        }
        return netforceY;
    }

    private List<Planet> filterOthersPlanets(Planet[] planets) {
        List<Planet> filteredPlanets = new ArrayList<>();
        for (Planet planet : planets) {
            if (this.equals(planet)) {
                continue;
            } else {
                filteredPlanets.add(planet);
            }
        }
        return filteredPlanets;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel += (fX / this.mass) * dt;
        this.yyVel += (fY / this.mass) * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
}