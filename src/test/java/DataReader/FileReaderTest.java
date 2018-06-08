package DataReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileReaderTest {

    private FileReader fileReader;

    @Before
    public void initializeFileReader() {

        fileReader = new FileReader("eeg.txt");
    }

    @After
    public void killFileReader() {

        fileReader = null;
    }

    @Test
    public void shouldHave256SamplingRate() {

        fileReader.loadData();
        assertEquals(fileReader.getSamplingRate(), 256);
    }

    @Test
    public void shouldHave15Channels() {

        fileReader.loadData();
        assertEquals(fileReader.getChannelsNum(), 15);
    }

    @Test
    public void shouldHaveSizeOfChannelsNum() {

        fileReader.loadData();
        assertEquals(fileReader.getRawData().size(), fileReader.getChannelsNum());
    }

    @Test
    public void eachChannelShouldHaveMoreThanFSSamples() {

        fileReader.loadData();
        for (int i = 0; i < fileReader.getChannelsNum(); i++) {

            assertTrue(fileReader.getRawData().get(i).size() > fileReader.getSamplingRate());
        }
    }


}
