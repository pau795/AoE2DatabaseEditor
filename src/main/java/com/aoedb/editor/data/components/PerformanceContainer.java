package com.aoedb.editor.data.components;

import java.util.ArrayList;
import java.util.List;

public class PerformanceContainer {
    List<Integer> strong;
    List<Integer> weak;


    public PerformanceContainer() {}

    public PerformanceContainer(PerformanceContainer other) {
        this.strong = new ArrayList<>(other.getStrong());
        this.weak = new ArrayList<>(other.getWeak());
    }

    public List<Integer> getStrong() {
        return strong;
    }

    public void setStrong(List<Integer> strong) {
        this.strong = strong;
    }

    public List<Integer> getWeak() {
        return weak;
    }

    public void setWeak(List<Integer> weak) {
        this.weak = weak;
    }
}
