package com.aoedb.editor.data.bonus;

import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.editable.EditableView;
import com.aoedb.editor.views.editable.HiddenBonusView;

public class HiddenBonus extends Bonus{

    public HiddenBonus(int id) {
        super(id);
    }

    public HiddenBonus(int id, HiddenBonus mainBonus){
        super(id, mainBonus);
    }

    @Override
    public String getName() {
        if (id == 0) return "none";
        return String.format("hidden_bonus_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.HIDDEN_BONUS;
    }

    @Override
    public EditableView getEditableView() {
        return new HiddenBonusView(this);
    }

    public static HiddenBonus getNone(){
        return new HiddenBonus(0);
    }

}
