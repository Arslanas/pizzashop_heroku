package pizzaShop.temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        String text = "+7 (960) 132-60-68";
        Pattern p = Pattern.compile("^\\+7 \\([0-9]{3}\\) [0-9]{3}-[0-9]{2}-[0-9]{2}$");
        Matcher matcher = p.matcher(text);
        System.out.println(matcher.matches());

    }
}
