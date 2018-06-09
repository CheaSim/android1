package com.deitel.flagquiz.data;

/**
 * Created by Chea Sim on 2018/4/16.
 */

public class Person {
    public String name;
    public double phone;
    public int id;
    public Person(int id,String name,double phone){
        this.id=id;
        this.name=name;
        this.phone=phone;
    }
    public String getName(){
        return name;
    }
    public double getPhone(){
        return phone;
    }
    public int getPersonid(){
        return id;
    }
}
