package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.ImageEditableView;
import com.aoedb.editor.views.editable.PerformanceView;

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

    @Override
    public EditableView getEditableView() {
        return new PerformanceView(this);
    }
}
