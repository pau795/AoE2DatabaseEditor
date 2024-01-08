package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.bonus.Bonus;
import com.aoedb.editor.data.bonus.HiddenBonus;

public class HiddenBonusView extends BonusView {

    private final HiddenBonus hiddenBonus;

    public HiddenBonusView(HiddenBonus hb){
        super(hb);
        this.hiddenBonus = hb;
    }

}
