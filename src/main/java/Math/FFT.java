package Math;

import org.hipparchus.complex.Complex;
import org.hipparchus.transform.DftNormalization;
import org.hipparchus.transform.FastFourierTransformer;
import org.hipparchus.transform.TransformType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class responsible for fourier transforms
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

    public List<Double> doIFFT(Complex[] samples) {

        Complex[] inversedValues = fftClassInstance.transform(samples, TransformType.INVERSE);
        List<Double> realValues = new ArrayList<>();

        IntStream.range(0, inversedValues.length).forEach(
                i -> {
                    realValues.add(inversedValues[i].getReal());
                }
        );

        return realValues;
    }
}
