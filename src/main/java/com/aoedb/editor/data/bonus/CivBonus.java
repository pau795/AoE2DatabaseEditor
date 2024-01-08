package com.aoedb.editor.data.bonus;


import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.BuildingView;
import com.aoedb.editor.views.editable.CivBonusView;
import com.aoedb.editor.views.editable.EditableView;

public class CivBonus extends Bonus{
    public CivBonus(int id) {
        super(id);
    }

    public String getName() {
        return String.format("bonus_tech_tree_description_%d", this.id);
    }

    public String getBonusItemPattern() {
        return String.format("bonus_item_description_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.BONUS;
    }

    @Override
    public EditableView getEditableView() {
        return new CivBonusView(this);
    }

}
