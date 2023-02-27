package com.aoedb.editor.data.bonus;


public class CivBonus extends Bonus{
    public CivBonus(int id) {
        super(id);
    }

    protected String getNamePattern() {
        return String.format("bonus_tech_tree_description_%d", this.id);
    }

    private String getBonusItemPattern() {
        return String.format("bonus_item_description_%d", this.id);
    }

}
