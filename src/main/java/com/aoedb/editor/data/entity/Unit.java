package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.PerformanceContainer;
import com.aoedb.editor.database.Database;

public class Unit extends Item{

    private PerformanceContainer performance;

    public Unit(int id) {
        super(id);

    }

    @Override
    public String getType(){
        return Database.UNIT;
    }

    public PerformanceContainer getPerformance() {
        return performance;
    }

    public void setPerformance(PerformanceContainer performance) {
        this.performance = performance;
    }

    @Override
    public String getName(){
        return String.format("unit_name_%d", this.id);
    }
}
