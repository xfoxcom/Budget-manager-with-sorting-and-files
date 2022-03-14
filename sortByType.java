package budget;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

public class sortByType implements sortStrategy {

    @Override
    public void sort(Map<Integer, Map<String, String>> map) {
        double totalF = 0.0001, totalC = 0.0002, totalE = 0.0003, totalO = 0.0004;
        CompareByTotal cbp = new CompareByTotal();
        TreeSet<Type> goodsList = new TreeSet<>(cbp);
        for (Map<String, String> tempMap : map.values()) {
            for (String s : tempMap.keySet()) {
                for (String a : tempMap.values()) {
                    if (s.equals("Food")) {
                        totalF += Double.parseDouble(a.substring(a.lastIndexOf("$") + 1));

                    }
                    if (s.equals("Clothes")) {
                        totalC += Double.parseDouble(a.substring(a.lastIndexOf("$") + 1));

                    }
                    if (s.equals("Entertainment")) {
                        totalE += Double.parseDouble(a.substring(a.lastIndexOf("$") + 1));

                    }
                    if (s.equals("Other")) {
                        totalO += Double.parseDouble(a.substring(a.lastIndexOf("$") + 1));

                    }
                }
            }
        }

        goodsList.add(new Type("Food", totalF));
        goodsList.add(new Type("Clothes", totalC));
        goodsList.add(new Type("Entertainment", totalE));
        goodsList.add(new Type("Other", totalO));

        System.out.println("\nTypes: ");

        for (Type t : goodsList) {
            System.out.printf(t.getType() + " - $%.2f\n", t.getTotal());

        }
    }
}

class CompareByTotal implements Comparator<Type> {

    public int compare(Type a, Type b) {

        if(a.getTotal()>b.getTotal()) return-1;
        else if(a.getTotal()<b.getTotal()) return 1;
        else return 0;
    }
}