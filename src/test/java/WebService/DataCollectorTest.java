package WebService;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataCollectorTest {

    DataCollector dataCollector;

    @Before
    public void initialize() {

        dataCollector = new DataCollector();
    }

    @After
    public void tearDown() {

        dataCollector = null;
    }

    @Test
    public void shouldBe2ElementsArray() {

        List<Integer> expected = new ArrayList(Arrays.asList(256, 15));
        List<Integer> data = (List<Integer>) dataCollector.getRequest();
        assertEquals(expected, data);
    }

    @Test
    public void shouldBe15ChannelsListWith256Elements() {

        List<List<Double>> data = (List<List<Double>>) dataCollector.getRequest();
        data = (List<List<Double>>) dataCollector.getRequest();
        assertEquals(15, data.size());

        for (int i = 0; i < 15; i++) {

            assertEquals(256, data.get(i).size());
        }
    }

    @Test
    public void shouldBeWith256Elements() {

        List<Double> data = (List<Double>) dataCollector.getRequest(1);
        data = (List<Double>) dataCollector.getRequest(1);
        assertEquals(256, data.size());
    }
}
