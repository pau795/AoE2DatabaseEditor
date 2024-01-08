package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.items.Stat;

public class StatView extends EditableView {

    private final Stat stat;

    public StatView(Stat s){
        super(s);
        this.stat = s;
    }


}
