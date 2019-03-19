package com.lifeinfinity.company.java8;


import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MyMain {

    /**
     * main method
     */
    public static void main(String[] args) {

        System.out.println("hello");

        List<TestObject> testObjects = Arrays.asList(
                new TestObject(1L, "1", 1),
                new TestObject(1L, "1", 11),
                new TestObject(2L, "2", 2),
                new TestObject(2L, "2", 2),
                new TestObject(2L, "2", 4),
                new TestObject(3L, "2", 2));
        // List<TestObject> zjkCaseIds = new ArrayList<>();

        // 去重
        /*List<TestObject> collect = testObjects.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(
                                Comparator.comparing(TestObject::getId))
                        ), ArrayList::new)
        );*/
        List<TestObject> result1= new ArrayList<>(testObjects.stream().collect(Collectors.groupingBy(TestObject::getId, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(TestObject::getType)), Optional::get))).values());

      /*  for(List<TestObject> testObjectList : collect.values()){
            zjkCaseIds.add( testObjectList.stream().max(Comparator.comparing(TestObject::getType)).get());
        }*/

    }
}
