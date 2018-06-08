package DataTransfer;

import DataReader.FileReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

public class HTTPClientTest {

    HTTPClient httpClient;

    @Before
    public void initializeHTTPClient() {

        httpClient = mock(HTTPClient.class);
    }

    @After
    public void killHTTPClient() {

        httpClient = null;
    }

    @Test
    public void shouldSet256FSAnd15Channels() {

        Integer[] resultArray = {256, 15};
        Mockito.when(httpClient.getFirstRespond()).thenReturn(new ArrayList(Arrays.asList(resultArray)));
        List<Integer> results = httpClient.getFirstRespond();

        assertEquals(256, (Object) results.get(0));
        assertEquals(15, (Object) results.get(1));
    }

    @Test
    public void shouldBeEqaulArrays() {

        List<List<Double>> samples = new ArrayList<>();
        Mockito.when(httpClient.getData()).thenReturn(samples);

        assertEquals(samples, httpClient.getData());
    }

    @Test
    public void shouldNotChangeTime() {

        int time = 2;
        Mockito.when(httpClient.setAcquisitionTimePeriod(time)).thenReturn("Success");

        assertEquals("Success", httpClient.setAcquisitionTimePeriod(time));
        assertNotEquals(time, httpClient.getAcquisitionTimePeriod());
    }
}
