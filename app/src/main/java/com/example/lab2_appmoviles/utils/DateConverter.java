package com.example.lab2_appmoviles.utils;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long date){
        return date==null ? null : new Date(date);
    }
    @TypeConverter
    public static Long toLong(Date date){
        return date==null ? null : date.getTime();
    }
}
