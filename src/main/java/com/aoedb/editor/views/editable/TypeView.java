package com.aoedb.editor.views.editable;

import com.aoedb.editor.data.simple.ImageEditable;
import com.aoedb.editor.data.simple.TypeElement;

public class TypeView extends ImageEditableView {

    private final TypeElement typeElement;
    public TypeView(TypeElement te) {
        super(te);
        this.typeElement = te;
    }
}
