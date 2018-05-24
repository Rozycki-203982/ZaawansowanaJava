package WaveTransformations;

import org.hipparchus.complex.Complex;
import Math.FFT;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Class responsible for signal filtration
 */
public class SignalFiltration {

    private FFT fftTransformations;
    private Complex[] fftSpectrum;
    private Double[] frequencyAxis;

    public SignalFiltration() {

        fftTransformations = new FFT();
    }

    public void generateFourierTransform(List<Double> data, int samplingRate) {

        fftSpectrum = fftTransformations.doFFT(data);
        frequencyAxis = defineFrequencyAxis(fftSpectrum.length, samplingRate);
    }

    public List<Double> alfaWaveFiltration() {

        Complex[] filteredSpectrum = fftSpectrum;
        for (int i = 0; i < frequencyAxis.length; i++) {

            if (frequencyAxis[i] < 8 || frequencyAxis[i] > 13) {

                filteredSpectrum[i] = new Complex(0, 0);
            }
        }

        return fftTransformations.doIFFT(filteredSpectrum);
    }

    public List<Double> betaWaveFiltration() {

        Complex[] filteredSpectrum = fftSpectrum;
        for (int i = 0; i < frequencyAxis.length; i++) {

            if (frequencyAxis[i] < 12 || frequencyAxis[i] > 30) {

                filteredSpectrum[i] = new Complex(0, 0);
            }
        }

        return fftTransformations.doIFFT(filteredSpectrum);
    }

    public List<Double> thetaWaveFiltration() {

        Complex[] filteredSpectrum = fftSpectrum;
        for (int i = 0; i < frequencyAxis.length; i++) {

            if (frequencyAxis[i] < 4 || frequencyAxis[i] > 8) {

                filteredSpectrum[i] = new Complex(0, 0);
            }
        }

        return fftTransformations.doIFFT(filteredSpectrum);
    }

    public List<Double> deltaWaveFiltration() {

        Complex[] filteredSpectrum = fftSpectrum;
        for (int i = 0; i < frequencyAxis.length; i++) {

            if (frequencyAxis[i] < 1 || frequencyAxis[i] > 4) {

                filteredSpectrum[i] = new Complex(0, 0);
            }
        }

        return fftTransformations.doIFFT(filteredSpectrum);
    }

    private Double[] defineFrequencyAxis(int signalLength, int samplingRate) {

        Double[] frequencyAxis = new Double[signalLength];
        double fs = samplingRate / (1f * signalLength);

        IntStream.range(0, frequencyAxis.length).forEach(
                i -> {
                    frequencyAxis[i] = i * fs;
                }
        );

        return frequencyAxis;
    }
}
