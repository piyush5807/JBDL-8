package com.company;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //primitive
        int a = 5;
        //Wrapper class
        //Integer a = new Integer(5);

//        Scanner scanner = new Scanner(System.in);
//
//        while(scanner.hasNext()){
//            System.out.print(scanner.next());
//        }

//        BufferedReader bufferedReader
//                = new BufferedReader(new InputStreamReader(System.in));
//
//        try {
//            String string  = bufferedReader.readLine();
//            System.out.println(string);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        File file = new File("test.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file );
            String string = "Happy coding";
            for(char c  : string.toCharArray()){
                fileWriter.append(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(3);

//        list.add(5);
//        list.add(10);
       // list.add(1);

//        Iterator iterator = list.iterator();
//
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

//        for(int i = 0 ; i<list.size();i++){
//            System.out.println(list.get(i));
//        }

//        for(int i =1 ; i<=100;i++){
//            list.add(i);
//        }
//
//        List<Integer> newList = list.subList(0,9);

//        for(int i = 0 ; i<list.size();i++){
//            System.out.println(list.get(i));
//        }
//        //for each loop
//        for(Integer i : newList){
//            System.out.println(i);
//        }

//        newList
//                .forEach(element -> {
//                    System.out.println(element);
//                });

//        for(int i = 0; i < newList.size();i++){
//            newList.set(i,  newList.get(i)*2);
//        }
//        newList = newList
//                .stream()
//                .filter(x->x%2!=0)
//                .map(x->x*2)
//                .collect(Collectors.toList());
//
//
//
//
//        newList
//                .forEach(element -> {
//                    System.out.println(element);
//                });

        Set<Integer> set = new LinkedHashSet<>();
        set.add(500);
        set.add(2);
        set.add(250);

        System.out.println("Size of list  : "+ list.size());
        System.out.println("Size of set : "+ set.size());
//
//        if(set.contains(5)){
//            System.out.println("item is there");
//        }
//
//
        set.forEach(x->{
            System.out.println(x);
        });

        Map<String,Customer> map = new HashMap<>();

       // map.put("customer1", new Customer(1,"customer"));
        //map.put("key2",2);


        if(map.containsKey("customer1")){
            //customer object
            System.out.println(map.get("customer1").name);
        }

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(5,"customer1",4 ));
        customers.add(new Customer(1,"customer2",6 ));
        customers.add(new Customer(2, "customer3",3 ));




        customers.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                        if(o1.rating < o2.rating){
                            return 1;
                        }
                        else if(o1.rating==o2.rating){
                            return 0;
                        }
                        else
                            return -1;
            }
        });


        customers
                .forEach(x->{
                    System.out.println(String.format("customer %s %s %s", x.name,x.id,x.rating) );
                });

    }
}
