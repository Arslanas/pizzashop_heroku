package pizzaShop.temp;

import java.util.ArrayList;
import java.util.List;

public class TempClass {
    public static void main(String[] args) {

        String a = "C";
        String b = "B";
        String c = "A";
        String d = "H";

        List<String> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        if (array.contains(d)){
            System.out.println("Allready contains a");
        }else{
            System.out.println("Not contains");
        }

    }
}
