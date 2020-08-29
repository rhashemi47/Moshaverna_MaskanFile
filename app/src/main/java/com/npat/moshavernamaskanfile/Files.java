package com.npat.moshavernamaskanfile;

import java.lang.reflect.Constructor;

public class Files {
//    private String CodeFile;//کد فایل
//    private String Building;//نوع سفارش
//    private String CalendarOrder;//تاریخ ثبت فایل
//    private String Address;//آدرس
//    private String Area;//متراژ
//    private String Foundation;//زیر بنا
//    private String Floor;//طبقه
//    private String Number_of_floors;//تعداد طبقات
//    private String Number_of_units;//تعداد واحد ها
//    private String Building_age;//سن بنا
//    private String Wall_Plugs;//دیوار پوش
//    private String Building_View;//نما
//    private String Floor_Covering;//کف پوش
//    private String Heating;//گرمایش
//    private String Cooling;//سرمایش
//    private String Cupboards;//کابینت
//    private String Ability_to_exchange;//قابلیت معاوضه
//    private String Building_direction;//جهت ملک
//    private String description;//توضیحات ملک
//    private String description;//توضیحات ملک
    private String Content;//توضیحات ملک
    private int Id;//شماره ردیف

    public Files(String content, int id) {
        Content = content;
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}