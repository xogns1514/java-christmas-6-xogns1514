package christmas.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {

    private static final String COMMA = ",";
    private static final String HYPHEN = "-";


    public static List<String> splitWithComma(String input) {
        return Stream.of(input.split(COMMA))
                .collect(Collectors.toList());
    }

    public static List<String> splitWithHyphen(String input) {
        return Stream.of(input.split(HYPHEN))
                .collect(Collectors.toList());
    }
}
