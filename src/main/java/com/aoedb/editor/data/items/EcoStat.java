package com.aoedb.editor.data.items;


import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EcoStatView;
import com.aoedb.editor.views.editable.EditableView;

public class EcoStat extends Editable {

    private String value;

    public EcoStat(int id) {
        super(id);
    }

    public EcoStat(int id, EcoStat mainEcoStat) {
        super(id, mainEcoStat);
        this.value = mainEcoStat.value;
    }

    @Override
    public String getName(){
        if (id == 0) return "none";
        return String.format("eco_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.ECO_STAT;
    }

    @Override
    public EditableView getEditableView() {
        return new EcoStatView(this);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static EcoStat getNone(){
        return new EcoStat(0);
    }
}
