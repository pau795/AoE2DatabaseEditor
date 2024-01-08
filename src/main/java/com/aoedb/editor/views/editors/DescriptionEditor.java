package com.aoedb.editor.views.editors;

import com.aoedb.editor.data.components.Descriptor;
import com.aoedb.editor.data.entity.Entity;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DescriptionEditor extends VerticalLayout {

    public DescriptionEditor(Entity e){
        Descriptor descriptor = e.getDescriptor();
        StringEditor nominativeDescription = new StringEditor(descriptor.getNominative());
        StringEditor quickDescription = new StringEditor(descriptor.getQuickDescription());
        StringEditor briefDescription = new StringEditor(descriptor.getBriefDescription());
        StringEditor longDescription = new StringEditor(descriptor.getLongDescription());
        StringEditor extraDescription = new StringEditor(descriptor.getExtraDescription());
        add(nominativeDescription, quickDescription, briefDescription, longDescription, extraDescription);
    }

}
