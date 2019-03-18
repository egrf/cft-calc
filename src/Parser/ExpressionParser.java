package Parser;

import java.util.HashMap;
import java.util.Map;

public class ExpressionParser {
    public static final Map<String, Integer> MATH_OPERATIONS;
    static {
        MATH_OPERATIONS = new HashMap<String, Integer>();
        MATH_OPERATIONS.put("*", 1);
        MATH_OPERATIONS.put("/", 1);
        MATH_OPERATIONS.put("+", 2);
        MATH_OPERATIONS.put("-", 2);
    }

}
