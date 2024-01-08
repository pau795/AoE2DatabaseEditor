package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.items.GatheringRates;
import com.aoedb.editor.data.simple.Editable;

public class GatheringRatesView extends EditableView {

    private final GatheringRates gatheringRates;

    public GatheringRatesView(GatheringRates gr){
        super(gr);
        this.gatheringRates = gr;
    }
}
