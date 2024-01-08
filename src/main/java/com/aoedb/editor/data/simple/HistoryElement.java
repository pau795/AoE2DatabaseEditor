package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.HistoryView;

public class HistoryElement extends ImageEditable{

    public HistoryElement(int id) {
        super(id);
    }

    @Override
    public String getName(){
        return String.format("history_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.HISTORY;
    }

    @Override
    public EditableView getEditableView() {
        return new HistoryView(this);
    }


}
