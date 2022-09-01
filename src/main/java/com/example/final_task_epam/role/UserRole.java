package com.example.final_task_epam.role;

public enum UserRole {

    ADMIN(1),
    CANDIDATE(2);

    private int number;
    UserRole(int ordinalNumber){
        this.number=ordinalNumber;
    }

    public int getOrdinalNumber(){
        return number;
    }
}
