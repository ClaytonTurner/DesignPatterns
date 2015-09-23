package com.turner;


public class TestLinkedList
{
    public static void main(String[] args)
    {
        LinkedList<String> l = new LinkedList<>();

        System.out.println(l.isEmpty() ? "The list is empty." : "The list is not empty.");
        System.out.println(l);
        System.out.println();

        l.add(0, "John");
        l.add(0, "Elvis");
        System.out.println(l);
        System.out.println();

        System.out.println(l.isEmpty() ? "The list is empty." : "The list is not empty.");
        System.out.println();

        LinkedList<String> l2 = new LinkedList<String>();
        l2.add("Elvis");
        l2.add("John");
        System.out.println("l.equals(l2) is " + l.equals(l2));
        System.out.println();

        l.add("Mick");
        System.out.println(l);
        System.out.println();

        l.add(0, "Curly");
        l.add(3, "Larry");
        l.add(2, "Diana");

        System.out.println(l);
        System.out.println();

        l.add(0, "Moe");
        l.set(4, "Bruce");
        System.out.println(l);
        System.out.println("size = " + l.size());
        System.out.println("index of \"Curly\" = " + l.indexOf("Curly"));
        System.out.println("index of \"Larry\" = " + l.indexOf("Larry"));
        System.out.println("index of \"Moe\" = " + l.indexOf("Moe"));
        System.out.println("index of \"Mick\" = " + l.indexOf("Mick"));
        System.out.println();

        // remove first and last elements plus an element in the middle
        System.out.println("Removing " + l.remove(0));
        System.out.println("Removing " + l.remove(l.size() - 1));
        System.out.println("Removing " + l.remove(3));
        System.out.println(l);
        System.out.println("size = " + l.size());
        System.out.println("index of \"Diana\" = " + l.indexOf("Diana"));
        System.out.println("index of \"Bruce\" = " + l.indexOf("Bruce"));
        System.out.println();

        for (int i = 1;  i <= 2;  ++i)
            System.out.println(l.remove(0));
        System.out.println(l);
        System.out.println();

        l.add(1, "Angela");
        l.add(l.size(), "Robert");
        System.out.println(l);
        System.out.println("size = " + l.size());
        System.out.println();

        // add a null data value and see if the list can be printed
        l.add(null);
        System.out.println(l);
        System.out.println("size = " + l.size());
        System.out.println();


        l.clear();
        System.out.println("The list has been cleared.");
        System.out.println(l.isEmpty() ? "The list is empty." : "The list is not empty.");
        System.out.println("index of \"Larry\" = " + l.indexOf("Larry"));
        System.out.println();

        try
        {
            l.remove(0);   // should throw an exception
        }
        catch (Exception e)
        {
            System.out.println("Exception information to follow:");
            e.printStackTrace(System.out);
        }
        System.out.println();

        LinkedList<String> l3 = new LinkedList<>();
        l3.add("Paul");
        l3.add(0, "Pete");
        l3.add(1, "George");
        l3.add("John");
        l3.add("Stuart");
        System.out.println("Let's view the members:");
        System.out.println(l3.toString());
        System.out.println();
        System.out.println("Clearing out the list. 5 Beatles just doesn't work");
        l3.clear();
        System.out.println();

        l3.add("Paul");
        l3.add("Ringo");
        l3.add("George");
        l3.add(2, "John");
        int my_size = l3.size();
        System.out.println("Added "+String.valueOf(my_size)+" members. Now we can 'Meet the Beatles'");
        System.out.println(l3.toString());
        System.out.println();
        System.out.println("Unfortunately, John has died, so let's remove him");
        l3.remove(2);
        System.out.println();
        System.out.println("Now let's view our " + String.valueOf(l3.size()) + " members");
        System.out.println(l3.toString());
        System.out.println();
        System.out.println("Is our band empty? true = Yes. false = No.");
        System.out.println(String.valueOf(l3.isEmpty()));

        System.out.println();
        System.out.println("We removed John by a hard-coded index earlier. " +
                "Let's make sure we don't need that");
        l3.clear();
        System.out.println("Band cleared");
        l3.add("Paul");
        l3.add("Ringo");
        l3.add("George");
        l3.add("John");
        System.out.println();
        System.out.println("Members added back");
        System.out.println();
        System.out.println("John died again! Remove him. Luckily we only have one John." +
                " We would need last names otherwise.");
        l3.remove(l3.indexOf("John"));
        System.out.println();
        System.out.println("Let's verify by printing out the living band members after" +
                " John's death.");
        System.out.println(l3.toString());
    }
}