package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.simple.ClassElement;
import com.aoedb.editor.data.simple.ImageEditable;

public class ClassView extends ImageEditableView {

    private final ClassElement classElement;

    public ClassView(ClassElement ce){
        super(ce);
        this.classElement = ce;
    }
}
