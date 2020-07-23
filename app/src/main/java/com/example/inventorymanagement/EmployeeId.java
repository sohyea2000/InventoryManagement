package com.example.inventorymanagement;

public class EmployeeId {

    public static String EmployeeId;
    public static String name;//Stores id of the employee

    public EmployeeId(){

    }
    public EmployeeId(String EmployeeId){
        this.EmployeeId = EmployeeId;

    }

    public static String getEmployeeId() {
        return EmployeeId;
    }

    public static void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }
}
