package com.aoedb.editor.data.entity;

import com.aoedb.editor.data.components.Descriptor;
import com.aoedb.editor.data.components.PerformanceContainer;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.UnitView;

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
        if (id == 0) return "none";
        return String.format("unit_name_%d", this.id);
    }



    @Override
    public EditableView getEditableView(){
        return new UnitView(this);
    }
    @Override
    public String getCreatorType(){
        return Database.BUILDING;
    }

    public static Unit getNone(){
        Unit none = new Unit(0);
        none.setImagePath("t_white");
        return none;
    }
}
