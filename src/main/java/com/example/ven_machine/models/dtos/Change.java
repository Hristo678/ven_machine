package com.example.ven_machine.models.dtos;

import java.util.List;

public class Change {

    private List<Integer> change;

    public Change(List<Integer> change) {
        this.change = change;
    }

    public List<Integer> getChange() {
        return change;
    }

    public void setChange(List<Integer> change) {
        this.change = change;
    }


}
