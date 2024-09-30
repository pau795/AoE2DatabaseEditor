package com.aoedb.editor.data.components;

import java.util.ArrayList;
import java.util.List;

public class Availability {
    private List<Integer> availableCivs;

    public Availability() {}

    public Availability(Availability other){
        this.availableCivs =  new ArrayList<>(other.getAvailableCivs());
    }

    public List<Integer> getAvailableCivs() {
        return availableCivs;
    }

    public void setAvailableCivs(List<Integer> availableCivs) {
        this.availableCivs = availableCivs;
    }
}
