package com.aoedb.editor.data.items;


public class Stat extends Editable {
    boolean addition;

    public Stat(int id) {
        super(id);
    }

    public boolean isAddition() {
        return addition;
    }

    public void setAddition(boolean addition) {
        this.addition = addition;
    }
}
