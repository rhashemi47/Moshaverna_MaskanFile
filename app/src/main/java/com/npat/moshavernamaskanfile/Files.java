package com.npat.moshavernamaskanfile;

import java.lang.reflect.Constructor;

public class Files {
    private String CodeFile;//کد فایل
    private String FileType;// شخص یا املاکی
    private String HouseType;// (هدر نمایش فایل ها در لیست)رهن و اجاره یا فروش
    private String Created;//تاریخ ثبت فایل
    private String Address;//آدرس
    private String Plauqe;//پلاک
    private String Area;//متراژ
    private String Foundation;//زیربنا
    private String IsDeleted;//1- واگذار شده - 0 واگذار نشده
    private String PriceSell;//مبلغ فروش
    private String PriceMortgage;//مبلغ رهن
    private String PriceRent;//مبلغ اجاره
    private String Floor;//طبقه
    private String RoomCount;//تعداد خواب
    private String Year;//سال ساخت
    private String Elevator;//آسانسور
    private String DocType;//نوع سند
    private String AmlakFileStatus;//
    private String Type;//رهن و اجاره - فروش (شرط نمایش مقادیر براساس این فیلد می باشد.)

    public Files(String codeFile, String fileType, String houseType, String created, String address, String plauqe, String area, String foundation, String isDeleted, String priceSell, String priceMortgage, String priceRent, String floor, String roomCount, String year, String elevator, String docType, String amlakFileStatus, String type) {
        CodeFile = codeFile;
        FileType = fileType;
        HouseType = houseType;
        Created = created;
        Address = address;
        Plauqe = plauqe;
        Area = area;
        Foundation = foundation;
        IsDeleted = isDeleted;
        PriceSell = priceSell;
        PriceMortgage = priceMortgage;
        PriceRent = priceRent;
        Floor = floor;
        RoomCount = roomCount;
        Year = year;
        Elevator = elevator;
        DocType = docType;
        AmlakFileStatus = amlakFileStatus;
        Type = type;
    }

    public String getCodeFile() {
        return CodeFile;
    }

    public void setCodeFile(String codeFile) {
        CodeFile = codeFile;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getHouseType() {
        return HouseType;
    }

    public void setHouseType(String houseType) {
        HouseType = houseType;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPlauqe() {
        return Plauqe;
    }

    public void setPlauqe(String plauqe) {
        Plauqe = plauqe;
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

    public String getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getPriceSell() {
        return PriceSell;
    }

    public void setPriceSell(String priceSell) {
        PriceSell = priceSell;
    }

    public String getPriceMortgage() {
        return PriceMortgage;
    }

    public void setPriceMortgage(String priceMortgage) {
        PriceMortgage = priceMortgage;
    }

    public String getPriceRent() {
        return PriceRent;
    }

    public void setPriceRent(String priceRent) {
        PriceRent = priceRent;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getRoomCount() {
        return RoomCount;
    }

    public void setRoomCount(String roomCount) {
        RoomCount = roomCount;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getElevator() {
        return Elevator;
    }

    public void setElevator(String elevator) {
        Elevator = elevator;
    }

    public String getDocType() {
        return DocType;
    }

    public void setDocType(String docType) {
        DocType = docType;
    }

    public String getAmlakFileStatus() {
        return AmlakFileStatus;
    }

    public void setAmlakFileStatus(String amlakFileStatus) {
        AmlakFileStatus = amlakFileStatus;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}