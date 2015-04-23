package com.example.jinux.thirtydays;

import java.util.ArrayList;

/**
 * Created by jinux on 15-4-24.
 */
public class AppUtils {
    final static String splitor = ":";
    public static ArrayList<Integer> getRatings(String ratingString){
        return spliteRatings(ratingString);
    }
    private static ArrayList<Integer> spliteRatings(String ratingString){
        if (ratingString ==null) return null;
        String[] ratings = ratingString.split(splitor);
        ArrayList<Integer> list= new ArrayList<>();
        for(String s:ratings){
            list.add(Integer.parseInt(s));
        }
        return list;
    }

    private static String concatRatings(ArrayList<Integer> ratings){
        if (ratings ==null) return null;
        StringBuffer buffer = new StringBuffer();

        for (int i : ratings){
            buffer.append(""+i+":");
        }

        return buffer.substring(0,buffer.length()-1);
    }

    public static String setRating(String origin, int day, int rating){
        ArrayList<Integer> ratings = spliteRatings(origin);
        int length = 0;
        if (ratings == null){
            ratings = new ArrayList<>();
        }
        length =  ratings.size();

        int index = day -1 ;
        if (index > length - 1){
            for(int i = index; i>length;i--){
                ratings.add(0);
            }
            ratings.add(rating);
        }else {
            ratings.set(index, rating);
        }
        return concatRatings(ratings);
    }

    public static int getRating(String origin,int day){
        ArrayList<Integer> ratings = spliteRatings(origin);
        if (ratings == null) return 0;
        int index = day - 1;
        if (index < ratings.size()) {
            return ratings.get(index);
        }else{
            return 0;
        }
    }
}
