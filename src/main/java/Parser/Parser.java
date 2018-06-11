package Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class which is responsible for parsing data that are transfered from a server
 */
public class Parser {

    public static List<Integer> parseStringToIntList(String values) {

        List<Integer> result = new ArrayList<>();
        values = values.substring(1, values.length() - 1);
        String valueArr[] = values.split(",");

        Arrays.asList(valueArr).forEach(
                val -> {
                    result.add(Integer.parseInt(val));
                }
        );

        return result;
    }

    public static List<Double> parseStringToDoubleList(String values) {

        List<Double> result = new ArrayList<>();
        values = values.substring(1, values.length() - 1);
        String valueArr[] = values.split(",");

        Arrays.asList(valueArr).forEach(
                val -> {
                    result.add(Double.parseDouble(val));
                }
        );

        return result;
    }

    public static List<List<Double>> parseStringToDoubleListList(String values) {

        List<List<Double>> result = new ArrayList<>();
        values = values.substring(1, values.length() - 1);
        String valuesArr[] = values.split("\\],\\[");

        for (String vals : valuesArr) {

            if (!vals.startsWith("["))
                vals = "[" + vals;

            if (!vals.endsWith("]"))
                vals += "]";

            result.add(parseStringToDoubleList(vals));
        }

        return result;
    }

    /**
     * Returns string request into List which includes requestID and channelID
     */
    public static List<Integer> parseRequest(String values) {

        List<Integer> result = new ArrayList<>();
        String valuesArr[] = values.split("d");

        for (String val : valuesArr) {

            result.add(Integer.parseInt(val));
        }

        return result;
    }
}
