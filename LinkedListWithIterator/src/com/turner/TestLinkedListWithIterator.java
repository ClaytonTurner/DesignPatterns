package com.turner;


public class TestLinkedListWithIterator
{
    public static void main(String[] args)
    {
        LinkedList<String> names = new LinkedList<>();

        System.out.println(names.isEmpty() ? "The list is empty." : "The list is not empty.");
        System.out.println(names);
        System.out.println();

        names.add("John");
        names.add("Elvis");
        names.add("Mick");
        names.add("Curly");
        names.add("Larry");
        names.add("Moe");
        names.add("Diana");

        for (String s : names)
            System.out.println(s);
        System.out.println();

        // Note that you get a forEach() method for free
        // (default method in interface Iterable) that
        // allows a lambda expressions as the parameter.
        names.forEach(name -> System.out.println(name));
        System.out.println();

        LinkedList<String> cowboysRunningBacks = new LinkedList<>();
        System.out.println("It's 2014 so let's add the bellcow");
        cowboysRunningBacks.add(0, "Demarco Murray");
        System.out.println(cowboysRunningBacks.toString());
        System.out.println();
        System.out.println("Let's add the backup!");
        cowboysRunningBacks.add("Joseph Randle");
        System.out.println(cowboysRunningBacks.toString());
        System.out.println();
        System.out.println("It's 2015 so let's get a clean slate");
        cowboysRunningBacks.clear();
        System.out.println(cowboysRunningBacks.toString());
        System.out.println();
        System.out.println("Owner Jerry Jones has request our hash code!");
        System.out.println(String.valueOf(cowboysRunningBacks.hashCode()));
        System.out.println();
        System.out.println("Now let's add the 2015 running backs so we can " +
                "head to training camp!");
        cowboysRunningBacks.add("Joseph Randle");
        cowboysRunningBacks.add("Darren McFadden");
        cowboysRunningBacks.add("Christine Michael");
        cowboysRunningBacks.add("Lance Dunbar");
        cowboysRunningBacks.add("Tyler Clutts");
        System.out.println();
        System.out.println(cowboysRunningBacks.toString());
        System.out.println();
        System.out.println("Jerry Jones actually wanted the hash code for " +
                "the 2015 running backs, not an empty list! " +
                "We gotta fix this now!");
        System.out.println(String.valueOf(cowboysRunningBacks.hashCode()));

    }
}