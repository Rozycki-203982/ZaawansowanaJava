package Math;

import org.hipparchus.complex.Complex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FFTTest {

    private FFT fft;

    @Before
    public void initializeFFT() {

        fft = new FFT();
    }

    @After
    public void killFFT() {

        fft = null;
    }

    @Test
    public void fftAndOriginShouldHaveSameLength() {

        List<Double> list = new ArrayList<>();
        for (int i = 0; i < 256; i++) {

            list.add(i * 1.2d);
        }

        Complex[] dataTransformed = fft.doFFT(list);
        assertEquals(dataTransformed.length, list.size());
    }

    @Test
    public void originAndTargetShouldHaveSameLength() {

        List<Double> list = new ArrayList<>();
        for (int i = 0; i < 256; i++) {

            list.add(i * 1.2d);
        }

        List<Double> dataTransformed = fft.doIFFT(fft.doFFT(list));
        assertEquals(dataTransformed.size(), list.size());
    }
}
