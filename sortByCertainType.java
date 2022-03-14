package budget;

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class sortByCertainType implements sortStrategy {

    @Override
    public void sort(Map<Integer, Map<String, String>> map) {
        double totalF = 0, totalC = 0, totalE = 0, totalO = 0;
        int countF = 0, countC = 0, countE = 0, countO = 0;
        Scanner scr = new Scanner(System.in);
        CompareByPrice cbp = new CompareByPrice();
        TreeSet<Product> goodsList = new TreeSet<>(cbp);
        for (Map<String, String> tempMap : map.values()) {
            for (String s : tempMap.keySet()) {
                for (String a : tempMap.values()) {
                    goodsList.add(new Product(s, a.substring(0, a.lastIndexOf("$")), Double.parseDouble(a.substring(a.lastIndexOf("$")+1))));
                    if (s.equals("Food")) {
                        totalF+= Double.parseDouble(a.substring(a.lastIndexOf("$")+1));
                        countF++;
                    }
                    if (s.equals("Clothes")) {
                        totalC+= Double.parseDouble(a.substring(a.lastIndexOf("$")+1));
                        countC++;
                    }
                    if (s.equals("Entertainment")) {
                        totalE+= Double.parseDouble(a.substring(a.lastIndexOf("$")+1));
                        countE++;
                    }
                    if (s.equals("Other")) {
                        totalO+= Double.parseDouble(a.substring(a.lastIndexOf("$")+1));
                        countO++;
                    }
                }
            }
        }
        System.out.println("\nChoose the type of purchase\n1) Food\n2) Clothes\n3) Entertainment\n4) Other");
        switch (scr.nextInt()) {
            case 1: if (countF>0) {
                System.out.println("\nFood: ");
                for (Product p : goodsList) {
                    if (p.getCategory().equals("Food")) {
                        System.out.printf(p.getName() + "$%.2f\n", p.getPrice());
                    }
                }
                System.out.printf("Total sum: $%.2f\n\n", totalF);
            } else System.out.println("\nThe purchase list is empty!\n");
                break;
            case 2: if (countC>0) {
                System.out.println("\nClothes: ");
                for (Product p : goodsList) {
                    if (p.getCategory().equals("Clothes")) {
                        System.out.printf(p.getName() + "$%.2f\n", p.getPrice());
                    }
                }
                System.out.printf("Total sum: $%.2f\n\n", totalC);
            } else System.out.println("\nThe purchase list is empty!\n");
                break;
            case 3: if (countE>0) {
                System.out.println("\nEntertainment: ");
                for (Product p : goodsList) {
                    if (p.getCategory().equals("Entertainment")) {
                        System.out.printf(p.getName() + "$%.2f\n", p.getPrice());
                    }
                }
                System.out.printf("Total sum: $%.2f\n\n", totalE);
            } else System.out.println("\nThe purchase list is empty!\n");
                break;
            case 4: if (countO>0) {
                System.out.println("\nOther: ");
                for (Product p : goodsList) {
                    if (p.getCategory().equals("Other")) {
                        System.out.printf(p.getName() + "$%.2f\n", p.getPrice());
                    }
                }
                System.out.printf("Total sum: $%.2f\n\n", totalO);
            } else System.out.println("\nThe purchase list is empty!\n");
                break;
            default:
                System.out.println("Error");
        }
    }
}

class CompareByCertain implements Comparator<Product> {

    public int compare(Product a, Product b) {

        if(a.getPrice()>b.getPrice())return-1;
        else if(a.getPrice()<b.getPrice())return 1;
        else return 0;
    }
}
