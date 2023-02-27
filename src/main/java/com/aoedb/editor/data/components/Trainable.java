package com.aoedb.editor.data.components;

import java.util.ArrayList;
import java.util.List;

public class Trainable {
    List<Integer> unitList;
    List<Integer> techList;

    public Trainable() {
        unitList = new ArrayList<>();
        techList = new ArrayList<>();
    }

    public List<Integer> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Integer> unitList) {
        this.unitList = unitList;
    }

    public List<Integer> getTechList() {
        return techList;
    }

    public void setTechList(List<Integer> techList) {
        this.techList = techList;
    }
}
