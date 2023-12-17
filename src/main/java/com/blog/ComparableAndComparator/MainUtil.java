package com.blog.ComparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;

public class MainUtil {
    public static void main(String[] args) {
        Movie m1=new Movie("AAA",2000, 8);
        Movie m2=new Movie("CCC",1999,9);
        Movie m3=new Movie("BBBBb", 2001, 6);

        ArrayList<Movie> m=new ArrayList<>();
        m.add(m1);
        m.add(m2);
        m.add(m3);
//        MovieRatings mr=new MovieRatings();
//        MovieYear my=new MovieYear();
        MovieName mn=new MovieName();
        Collections.sort(m,mn);

        for (Movie mm:m
             ) {
            System.out.println(mm.getName());
            System.out.println(mm.getRatings());
            System.out.println(mm.getYear());

        }
    }
}
