package com.aoedb.editor.data.bonus;

public class HiddenBonus extends Bonus{

    public HiddenBonus(int id) {
        super(id);
    }

    @Override
    protected String getNamePattern() {
        return String.format("hidden_bonus_name_%d", this.id);
    }


}
