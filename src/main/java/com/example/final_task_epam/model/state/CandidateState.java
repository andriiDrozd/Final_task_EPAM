package com.example.final_task_epam.model.state;

public enum CandidateState {

    APPLIED(1),
    ENROLLED(2),
    NOT_ENROLLED(3);

    private int ordinalNumber;

    CandidateState(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }
}
