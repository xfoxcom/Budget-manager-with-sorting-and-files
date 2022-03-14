package budget;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        double[] totalSum = new double[1];
        int enter = 9;
        int[] num = {1};
        double[] Balance = new double[1];
        Map<Integer, Map<String, String>> allGoods = new HashMap<>();
        String path = "C:\\Users\\Alexander\\Downloads\\purchases.txt";
        File file = new File("purchases.txt");

        while (enter!=0) {
            System.out.println("\nChoose your action: \n1) Add income\n2) Add purchase \n3) Show list of purchases \n4) Balance \n5) Save \n6) Load \n7) Analyze (Sort) \n0) Exit");
            enter = scr.nextInt();
            switch (enter) {
                case 1: addIncome(Balance);
                    System.out.println(); break;
                case 2: saveToMap(Balance, allGoods, num);  System.out.println(); break;
                case 3: showPurchases(totalSum, allGoods); break;
                case 4:  System.out.println();
                    System.out.printf("Balance: $%.2f\n", Balance[0]); break;
                case 5:
                    System.out.println(); saveToFile(file, allGoods, Balance); break;
                case 6:
                    System.out.println(); loadFromFile(file, allGoods, Balance, totalSum); break;
                case 7: System.out.println();
                    sortMenu(allGoods, totalSum); break;
                case 0:  System.out.println();
                    System.out.println("Bye!"); break;
                default:
                    System.out.println("Error");
            }
        }
    }

    public static void saveToFile(File f, Map<Integer, Map<String, String>> allGoods, double[] Balance) {
        try(PrintWriter writer = new PrintWriter(f)) {
            writer.println(Balance[0]);
            for (Map<String , String> temp : allGoods.values()) {
                for (String s : temp.keySet()) {
                    for (String c : temp.values()) {
                        writer.println(s + " " + c);
                    }
                }
            }
        }catch (IOException e) {

        }
        System.out.println("\nPurchases were saved!");
    }

    public static void loadFromFile(File f, Map<Integer, Map<String, String>> allGoods, double[] Balance, double[] total) {
        int count = 1;
        try(Scanner scr = new Scanner(f).useLocale(Locale.US)) {
            while (scr.hasNextLine()){
                Map<String, String> map = new HashMap<>();
                if (scr.hasNextDouble()) {
                    Balance[0] = scr.nextDouble();
                }
                String a = scr.next();
                String b = scr.nextLine().trim();
                map.put(a,b);
                allGoods.put(count, map);
                count++;
            }
        }catch (FileNotFoundException e) {

        }
        System.out.println("Purchases were loaded!");
        calcTotalSum(allGoods, total);
    }

    public static void addIncome(double[] Bal) {
        Scanner scr = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter income: ");
        int income = scr.nextInt();
        Bal[0] = Bal[0] + income;
        System.out.println("Income was added!");
    }
    public static void addPurchase(double[] Bal, String key, Map<Integer, Map<String, String>> allGoods, int[] num) {
        Map<String, String> tempMap = new HashMap<>();
        Scanner scr = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter purchase name: ");
        String a = scr.nextLine();
        System.out.println("Enter its price: ");
        String b = scr.nextLine();
        tempMap.put(key, a + " $" + b);
        allGoods.put(num[0],tempMap);
        System.out.println("Purchase was added!");
        num[0]++;
        Bal[0] = Bal[0] - Double.parseDouble(b);
    }

    public static void saveToMap(double[] Bal, Map<Integer, Map<String, String>> allGoods, int[] num) {
        int enter = 9;
        String key = "";
        Scanner scr = new Scanner(System.in);
        while (enter!=5) {
            System.out.println("\nChoose the type of purchase\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n5) Back");
            enter = scr.nextInt();
            switch (enter) {
                case 1: key = "Food";
                    addPurchase(Bal , key, allGoods, num); break;
                case 2: key = "Clothes";
                    addPurchase(Bal , key, allGoods, num); break;
                case 3: key = "Entertainment";
                    addPurchase(Bal , key, allGoods, num); break;
                case 4: key = "Other";
                    addPurchase(Bal , key, allGoods, num); break;
                case 5: break;
                default:
                    System.out.println("Error");
            }
        }
    }

    public static void showPurchases(double[] totalSum, Map<Integer, Map<String, String>> allGoods) {
        int enter = 9;
        Scanner scr = new Scanner(System.in);
        while (enter!=6) {
            System.out.println("\nChoose the type of purchase\n1) Food\n2) Clothes\n3) Entertainment\n4) Other\n5) All\n6) Back");
            enter = scr.nextInt();
            switch (enter) {
                case 1: if(!checkProd(allGoods, "Food")) {
                    System.out.println("The purchase list is empty!");
                } else {
                  showAllGoods(allGoods, "Food");
                } break;
                case 2: if(!checkProd(allGoods, "Clothes")) {
                    System.out.println("The purchase list is empty!");
                } else {
                  showAllGoods(allGoods, "Clothes");
                } break;
                case 3: if(!checkProd(allGoods, "Entertainment")) {
                    System.out.println("The purchase list is empty!");
                } else {
                   showAllGoods(allGoods, "Entertainment");
                } break;
                case 4: if(!checkProd(allGoods, "Other")) {
                    System.out.println("The purchase list is empty!");
                } else {
                  showAllGoods(allGoods, "Other");
                } break;
                case 5: if (allGoods.isEmpty()) {
                    System.out.println("The purchase list is empty!");
                } else {
                    System.out.println("\nAll: ");
                    for (Map<String, String> temp : allGoods.values()) {
                        for (String s : temp.values()) {
                            System.out.printf(s.substring(0, s.lastIndexOf("$")) + "$%.2f\n", Double.parseDouble(s.substring(s.lastIndexOf("$")+1)));
                        }
                    }
                    System.out.printf("Total sum: $%.2f\n", totalSum[0]);
                } break;
                case 6: break;
                default:
                    System.out.println("Error");
            }
        }
    }
    public static double calcTotalSum(Map<Integer, Map<String, String>> allGoods, double[] total) {

        for(Map<String, String> tempMap: allGoods.values()) {
            for (String s : tempMap.values()) {
              total[0]+= Double.parseDouble(s.substring(s.lastIndexOf("$")+1));
            }
        }
        return total[0];
    }

    public static boolean checkProd(Map<Integer, Map<String, String>> allGoods, String product) {
        for (int i = 1; i <= allGoods.size(); i++) {
            if (allGoods.get(i).containsKey(product)) return true;
        }
        return false;
    }
    public static void showAllGoods(Map<Integer, Map<String, String>> allGoods, String product) {
        double total = 0;
        System.out.println("\n" + product + ":");
        for (int i = 1; i <= allGoods.size(); i++) {
            if (allGoods.get(i).containsKey(product)) {
                System.out.println(allGoods.get(i).get(product));
                total += Double.parseDouble(allGoods.get(i).get(product).substring(allGoods.get(i).get(product).lastIndexOf("$") + 1));
            }
        }
        System.out.printf("Total sum: $%.2f\n", total);
    }
    public static void sortMenu(Map<Integer, Map<String, String>> allGoods, double[] total) {
        int enter = 9;
        Scanner scr = new Scanner(System.in);
        while (enter!=4) {
            System.out.println("How do u want to sort?\n1) Sort all purchases\n2) Sort by type\n3) Sort certain type\n4) Back");
            enter = scr.nextInt();
            switch (enter) {
                case 1: if (allGoods.isEmpty()) { System.out.println("\nThe purchase list is empty!\n"); } else { new sortAll().sort(allGoods);
                    System.out.println();  /* System.out.printf("Total: $%.2f\n\n", total[0]);*/ } break;
                case 2: new sortByType().sort(allGoods);
                    System.out.printf("Total sum: $%.2f\n\n", total[0]);
                break;
                case 3: new sortByCertainType().sort(allGoods); break;
                case 4: break;
                default:
                    System.out.println("Error");
            }
        }
    }
}

