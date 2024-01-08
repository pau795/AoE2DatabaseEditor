package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.simple.HistoryElement;

public class HistoryView extends ImageEditableView{

    private final HistoryElement historyElement;

    public HistoryView(HistoryElement he){
        super(he);
        this.historyElement = he;
    }
}
