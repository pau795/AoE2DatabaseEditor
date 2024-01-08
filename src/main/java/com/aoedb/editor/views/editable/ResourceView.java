package com.aoedb.editor.views.editable;


import com.aoedb.editor.data.simple.ResourceElement;

public class ResourceView extends EditableView {

    private final ResourceElement resourceElement;

    public ResourceView(ResourceElement re) {
        super(re);
        this.resourceElement = re;
    }
}
