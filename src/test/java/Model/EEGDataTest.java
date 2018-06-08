package Model;

import DataReader.FileReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EEGDataTest {

    EEGData eegData;

    @Before
    public void initializeEEGData() {

        eegData = new EEGData();
    }

    @After
    public void killEEGData() {

        eegData = null;
    }

    @Test
    public void shouldHave256SamplingRate() {

        eegData.setSamplingRate(256);
        assertEquals(256, eegData.getSamplingRate());
    }

    @Test
    public void shouldNotAddLastChannel() {

        int channelNO = 4;
        eegData.setChannelsNum(channelNO - 1);

        for (int i = 0; i < channelNO; i++) {

            eegData.saveData(i, new ArrayList<Double>());
        }

        assertEquals(channelNO - 1, eegData.getChannelsNum());
    }

    @Test
    public void shouldAddChannelsWithSamples() {

        int channelNum = 15;
        eegData.setChannelsNum(channelNum);

        for (int i = 0; i < channelNum; i++) {

            eegData.saveData(i, new ArrayList<Double>());
        }

        assertEquals(channelNum, eegData.getChannelsNum());
    }
}
