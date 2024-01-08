package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.items.EcoStat;

public class EcoStatView extends EditableView{

    private final EcoStat ecoStat;

    public EcoStatView(EcoStat es){
        super(es);
        this.ecoStat = es;
    }
}
