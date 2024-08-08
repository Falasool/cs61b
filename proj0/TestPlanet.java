import jdk.internal.org.jline.reader.impl.history.DefaultHistory;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

import java.util.Objects;

/**
 * creates two planets
 * and prints out the pairwise force between them.
 */
public class TestPlanet {
    public static void main(String[] args) {
        printPairwiseForce();
    }

    private static void printPairwiseForce() {
        Planet p1 = new Planet(1.0, 3.0, 1.0, 2.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        double pairwiseForce = p1.calcForceExertedBy(p2);
        System.out.println(pairwiseForce);
    }
}
