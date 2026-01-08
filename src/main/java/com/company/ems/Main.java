package com.company.ems;

import com.company.ems.db.DatabaseUtils;

public class Main {
    public static void main(String[] args) {
        System.out.println("Employee Management System Started");
        boolean reachable = DatabaseUtils.isDatabaseAvailable();
        System.out.println("Database reachable: " + reachable);
        System.exit(reachable ? 0 : 1);
    }
}
