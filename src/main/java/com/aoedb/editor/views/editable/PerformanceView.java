package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.simple.PerformanceElement;

public class PerformanceView extends ImageEditableView{

    private final PerformanceElement performanceElement;

    public PerformanceView(PerformanceElement pe){
        super(pe);
        this.performanceElement = pe;
    }
}
