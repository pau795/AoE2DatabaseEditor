package com.aoedb.editor.data.simple;

import com.aoedb.editor.database.Database;

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
}
