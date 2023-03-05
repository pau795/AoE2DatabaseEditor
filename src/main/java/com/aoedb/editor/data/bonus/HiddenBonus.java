package com.aoedb.editor.data.bonus;

import com.aoedb.editor.database.Database;

public class HiddenBonus extends Bonus{

    public HiddenBonus(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return String.format("hidden_bonus_name_%d", this.id);
    }

    @Override
    public String getType(){
        return Database.HIDDEN_BONUS;
    }

}
