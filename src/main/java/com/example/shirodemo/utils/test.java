package com.example.shirodemo.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class test {

    public static void main(String args[]){
        List<String> languages = Arrays.asList("java","scala","python");
        //before java8
        for(String each:languages) {
            System.out.println(each);
        }
        //after java8
       // languages.forEach(x -> System.out.println(x));
        languages.forEach(System.out::println);

        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        System.out.println(nowTime);
        System.out.println(new Date());
    }

}
