package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.entity.Civilization;

public class CivilizationView extends ImageEditableView{

    private final Civilization civilization;

    public CivilizationView(Civilization c) {
        super(c);
        this.civilization = c;
    }
}
