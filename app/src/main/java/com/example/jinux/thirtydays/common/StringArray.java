package com.example.jinux.thirtydays.common;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinux on 15-4-24.
 * 包装一个字符串，将其分解成一个字符串数组，能对其进行增天某一个位置的数据和获取某一个位置的数据
 */
public class StringArray {
    final static String splitor = ":";
    private String mStr;
    private List<String> mList;

    public StringArray(String s){
        mStr = s;
        mList = spliteStrings(s);
    }

    public  List<Integer> getIntList(){
        if (mList == null) return null;
        List<String> strList= mList;
        List<Integer> intList= new ArrayList<>();
        for(String s : strList){
            intList.add(TextUtils.equals("",s)?0:Integer.parseInt(s));
        }
        return intList;
    }
    public  List<String> getStringList(){
        return spliteStrings(mStr);
    }

    private List<String> spliteStrings(String str){
        if (str ==null) return null;
        String[] ratings = str.split(splitor);
        ArrayList<String> list= new ArrayList<>();
        for(String s:ratings){
            list.add(s);
        }
        return list;
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

    private String concatStrings(List<String> list){
        if (list ==null) return null;
        StringBuffer buffer = new StringBuffer();

        for (Object o : list){
            buffer.append(""+o+":");
        }
        mStr = buffer.substring(0,buffer.length()-1);
        return mStr;
    }
    private static String concatRatings(ArrayList<Integer> ratings){
        if (ratings ==null) return null;
        StringBuffer buffer = new StringBuffer();

        for (int i : ratings){
            buffer.append(""+i+":");
        }

        return buffer.substring(0,buffer.length()-1);
    }

    public void setIntAt(int pos, int n){
        setStringAt(pos,""+n);
    }
    public void setStringAt(int pos, String s) {
        List<String> list = getStringList();
        int length = 0;
        if (list == null){
            list = new ArrayList<>();
        }
        length =  list.size();

        int index = pos -1 ;
        if (index > length - 1){
            for(int i = index; i>length;i--){
                list.add("");
            }
            list.add(s);
        }else {
            list.set(index, s);
        }
        concatStrings(list);
    }

    public int getIntAt(int pos){
        List<Integer> ratings = getIntList();
        if (ratings == null) return 0;
        int index = pos - 1;
        if (index < ratings.size()) {
            return ratings.get(index);
        }else{
            return 0;
        }
    }

    public String getStrAt(int pos){
        List<String> ratings = mList;
        if (ratings == null) return "";
        int index = pos - 1;
        if (index < ratings.size()) {
            return ratings.get(index);
        }else{
            return "";
        }
    }


    public String getString(){
        return mStr;
    }



}
