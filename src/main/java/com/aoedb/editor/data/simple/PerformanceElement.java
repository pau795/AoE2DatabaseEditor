package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;

public class PerformanceElement extends ImageEditable{

    public PerformanceElement(int id) {
        super(id);
    }

    @Override
    public String getName(){
        return String.format("performance_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.PERFORMANCE;
    }
}
