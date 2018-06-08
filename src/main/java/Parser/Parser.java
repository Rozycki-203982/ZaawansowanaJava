package Parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Integer> parseStringToIntList(String values) {

        List<Integer> result = new ArrayList<>();
        values = values.substring(1, values.length() - 1);
        String valueArr[] = values.split(",");

        for (String val : valueArr) {
            result.add(Integer.parseInt(val));
        }

        return result;
    }

    public static List<Double> parseStringToDoubleList(String values) {

        List<Double> result = new ArrayList<>();
        values = values.substring(1, values.length() - 1);
        String valueArr[] = values.split(",");

        for (String val : valueArr) {
            result.add(Double.parseDouble(val));
        }

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
}
