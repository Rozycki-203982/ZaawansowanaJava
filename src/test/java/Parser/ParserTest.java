package Parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void shouldParseIntoListOfInts() {

        String text = "[2,5]";
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(5);

        List<Integer> result = Parser.parseStringToIntList(text);
        assertEquals(expected, result);
    }

    @Test
    public void shouldParseIntoListOfDoubles() {

        String text = "[2.2,5.5,2.5,2.6,6.2513,53524.255]";
        List<Double> expected = new ArrayList<>();
        expected.add(2.2);
        expected.add(5.5);
        expected.add(2.5);
        expected.add(2.6);
        expected.add(6.2513);
        expected.add(53524.255);

        List<Double> result = Parser.parseStringToDoubleList(text);
        assertEquals(expected, result);
    }

    @Test
    public void shouldParseInto2DListOfDoubles() {

        String text = "[[2.2,5.5],[2.5,2.6],[6.2513,53524.255]]";
        List<List<Double>> expected = new ArrayList<>();
        expected.add(new ArrayList(Arrays.asList(2.2, 5.5)));
        expected.add(new ArrayList(Arrays.asList(2.5, 2.6)));
        expected.add(new ArrayList(Arrays.asList(6.2513, 53524.255)));

        List<List<Double>> result = Parser.parseStringToDoubleListList(text);
        assertEquals(expected, result);
    }
}
