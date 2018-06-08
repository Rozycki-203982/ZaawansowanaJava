package Model;

import DataReader.FileReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChannelTest {

    Channel channel;

    @Before
    public void initializeChannel() {

        channel = new Channel();
    }

    @After
    public void killFileReader() {

        channel = null;
    }

    @Test
    public void shouldBeSameInstance() {

        List<Double> testSamples = new ArrayList<>();
        for (int i = 0; i < 256; i++) {

            testSamples.add(i * 1.1d);
        }

        channel.setRawData(testSamples);
        assertEquals(testSamples, channel.getRawData());
    }

    @Test
    public void shouldHaveMultipliedSize() {

        int repeatTimes = 10;
        int samplesNO = 256;

        for (int i = 0; i < repeatTimes; i++) {

            List<Double> testSamples = new ArrayList<>();
            for (int j = 0; j < samplesNO; j++) {

                testSamples.add(i * j * 1.1d);
            }
            channel.setRawData(testSamples);
        }

        assertEquals(channel.getRawData().size(), repeatTimes * samplesNO);
    }
}
