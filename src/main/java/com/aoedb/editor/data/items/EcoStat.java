package com.aoedb.editor.data.items;


public class EcoStat extends Editable{

    private String value;

    private EcoStat(int id) {
        super(id);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
