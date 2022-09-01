package com.example.final_task_epam.model.state;

public enum EnrollmentState {

   OPEN(1),
    CLOSED(2);


    private int number;

    EnrollmentState(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
