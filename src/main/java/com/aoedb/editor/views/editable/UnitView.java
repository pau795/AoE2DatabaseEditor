package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.entity.Item;
import com.aoedb.editor.data.entity.Unit;

public class UnitView extends ItemView {

    private final Unit unit;

    public UnitView(Unit u) {
        super(u);
        this.unit = u;
    }
}
