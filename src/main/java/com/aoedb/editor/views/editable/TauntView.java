package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.simple.TauntElement;

public class TauntView extends EditableView{

    private final TauntElement tauntElement;

    public TauntView(TauntElement te){
        super(te);
        this.tauntElement = te;
    }
}
