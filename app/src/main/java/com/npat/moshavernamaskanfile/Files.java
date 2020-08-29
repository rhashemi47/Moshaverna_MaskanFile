package com.npat.moshavernamaskanfile;

import java.lang.reflect.Constructor;

public class Files {
    private String CodeFile;//کد فایل
    private String Building_Type;//نوع سفارش
    private String CalendarOrder;//تاریخ ثبت فایل
    private String Address;//آدرس
    private String Area;//متراژ
    private String Foundation;//زیر بنا
    private String Floor;//طبقه
    private String Number_of_floors;//تعداد طبقات
    private String Number_of_units;//تعداد واحد ها
    private String Building_age;//سن بنا
    private String Wall_Plugs;//دیوار پوش
    private String Building_View;//نما
    private String Floor_Covering;//کف پوش
    private String Heating;//گرمایش
    private String Cooling;//سرمایش
    private String Cupboards;//کابینت
    private String Ability_to_exchange;//قابلیت معاوضه
    private String Building_direction;//جهت ملک
    private String description;//توضیحات ملک
    private String Currency_Title;//نوع معامله
    private String Currency;//مبلغ
    private String Time;//سال ساخت
    private String Document;//سند

    public Files(String codeFile, String building_Type, String calendarOrder, String address, String area, String foundation, String floor, String number_of_floors, String number_of_units, String building_age, String wall_Plugs, String building_View, String floor_Covering, String heating, String cooling, String cupboards, String ability_to_exchange, String building_direction, String description, String currency_Title, String currency, String time, String document) {
        CodeFile = codeFile;
        Building_Type = building_Type;
        CalendarOrder = calendarOrder;
        Address = address;
        Area = area;
        Foundation = foundation;
        Floor = floor;
        Number_of_floors = number_of_floors;
        Number_of_units = number_of_units;
        Building_age = building_age;
        Wall_Plugs = wall_Plugs;
        Building_View = building_View;
        Floor_Covering = floor_Covering;
        Heating = heating;
        Cooling = cooling;
        Cupboards = cupboards;
        Ability_to_exchange = ability_to_exchange;
        Building_direction = building_direction;
        this.description = description;
        Currency_Title = currency_Title;
        Currency = currency;
        Time = time;
        Document = document;
    }

    public Files(String s, int i) {
    }

    public String getCodeFile() {
        return CodeFile;
    }

    public void setCodeFile(String codeFile) {
        CodeFile = codeFile;
    }

    public String getBuilding_Type() {
        return Building_Type;
    }

    public void setBuilding_Type(String building) {
        Building_Type = building;
    }

    public String getCalendarOrder() {
        return CalendarOrder;
    }

    public void setCalendarOrder(String calendarOrder) {
        CalendarOrder = calendarOrder;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getFoundation() {
        return Foundation;
    }

    public void setFoundation(String foundation) {
        Foundation = foundation;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getNumber_of_floors() {
        return Number_of_floors;
    }

    public void setNumber_of_floors(String number_of_floors) {
        Number_of_floors = number_of_floors;
    }

    public String getNumber_of_units() {
        return Number_of_units;
    }

    public void setNumber_of_units(String number_of_units) {
        Number_of_units = number_of_units;
    }

    public String getBuilding_age() {
        return Building_age;
    }

    public void setBuilding_age(String building_age) {
        Building_age = building_age;
    }

    public String getWall_Plugs() {
        return Wall_Plugs;
    }

    public void setWall_Plugs(String wall_Plugs) {
        Wall_Plugs = wall_Plugs;
    }

    public String getBuilding_View() {
        return Building_View;
    }

    public void setBuilding_View(String building_View) {
        Building_View = building_View;
    }

    public String getFloor_Covering() {
        return Floor_Covering;
    }

    public void setFloor_Covering(String floor_Covering) {
        Floor_Covering = floor_Covering;
    }

    public String getHeating() {
        return Heating;
    }

    public void setHeating(String heating) {
        Heating = heating;
    }

    public String getCooling() {
        return Cooling;
    }

    public void setCooling(String cooling) {
        Cooling = cooling;
    }

    public String getCupboards() {
        return Cupboards;
    }

    public void setCupboards(String cupboards) {
        Cupboards = cupboards;
    }

    public String getAbility_to_exchange() {
        return Ability_to_exchange;
    }

    public void setAbility_to_exchange(String ability_to_exchange) {
        Ability_to_exchange = ability_to_exchange;
    }

    public String getBuilding_direction() {
        return Building_direction;
    }

    public void setBuilding_direction(String building_direction) {
        Building_direction = building_direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency_Title() {
        return Currency_Title;
    }
    public void setCurrency_Title(String title) {
        this.Currency_Title = title;
    }

    public String getCurrency() {
        return Currency;
    }
    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String document) {
        Document = document;
    }
    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}