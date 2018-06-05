package Math;

/**
 * Class responsible for math operations
 */
public class MathHelper {

    public static int closestPowerOf2(final int a) {

        int b = 1;
        while (b < a) {

            b = b << 1;
        }

        if (b > a) {

            b = b >> 1;
        }

        return b;
    }
}
