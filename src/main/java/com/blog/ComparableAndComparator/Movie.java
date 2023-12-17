package com.blog.ComparableAndComparator;

public class Movie implements Comparable<Movie>{
    private String name;
    private int year;
    private int ratings;

    public Movie(String name, int year, int ratings){
        this.name=name;
        this.year=year;
        this.ratings=ratings;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getRatings() {
        return ratings;
    }

    @Override
    public int compareTo(Movie o) {
//        return this.ratings-o.ratings; //on the basis of int(Ratings)
//    return  this.year-o.year; //On the basis of int(year)
        return this.name.compareTo(o.name); //on the basis of name(String)
    }
}
