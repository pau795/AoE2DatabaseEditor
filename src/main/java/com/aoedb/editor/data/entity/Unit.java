package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.PerformanceContainer;

public class Unit extends Item{

    PerformanceContainer performance;

    public Unit(int id) {
        super(id);

    }

    public PerformanceContainer getPerformance() {
        return performance;
    }

    public void setPerformance(PerformanceContainer performance) {
        this.performance = performance;
    }

    @Override
    protected String getNamePattern(){
        return String.format("unit_name_%d", this.id);
    }
}
