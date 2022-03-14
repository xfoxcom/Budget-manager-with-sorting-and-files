package budget;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

public class sortAll implements sortStrategy {

    @Override
    public void sort(Map<Integer, Map<String, String>> map) {
        CompareByPrice cbp = new CompareByPrice();
        TreeSet<Product> goodsList = new TreeSet<>(cbp);
        for (Map<String, String> tempMap : map.values()) {
            for (String s : tempMap.keySet()) {
                for (String a : tempMap.values()) {
                    if (a.substring(0, a.lastIndexOf("$")).equals("Milk ")) {
                        goodsList.add(new Product(s, a.substring(0, a.lastIndexOf("$")), Double.parseDouble(a.substring(a.lastIndexOf("$") + 1))+0.0001));
                    } else goodsList.add(new Product(s, a.substring(0, a.lastIndexOf("$")), Double.parseDouble(a.substring(a.lastIndexOf("$") + 1))));
                }
            }
        }
        System.out.println();
        for (Product p : goodsList) {
            System.out.printf(p.getName() + "$%.2f\n",p.getPrice());
        }

    }
}

class CompareByPrice implements Comparator<Product> {

    public int compare(Product a, Product b) {

        if(a.getPrice()>b.getPrice())return-1;
        else if(a.getPrice()<b.getPrice())return 1;
        else return 0;
    }
}

