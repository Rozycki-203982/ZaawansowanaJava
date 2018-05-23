package Math;

import org.hipparchus.complex.Complex;
import org.hipparchus.transform.DftNormalization;
import org.hipparchus.transform.FastFourierTransformer;
import org.hipparchus.transform.TransformType;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Class responsible for math operations
 */
public class FFT {

    private FastFourierTransformer fftClassInstance;

    public FFT() {

        fftClassInstance = new FastFourierTransformer(DftNormalization.STANDARD);
    }

    public Complex[] doFFT(List<Double> samples) {

        double[] sampleArray = samples.stream().mapToDouble(Double::doubleValue).toArray();
        return fftClassInstance.transform(sampleArray, TransformType.FORWARD);
    }

    public Double[] doIFFT(Complex[] samples) {

        Complex[] inversedValues = fftClassInstance.transform(samples, TransformType.INVERSE);
        Double[] realValues = new Double[inversedValues.length];

        IntStream.range(0, inversedValues.length).forEach(
                i -> {
                    realValues[i] = inversedValues[i].getReal();
                }
        );

        return realValues;
    }
}
