package com.aoedb.editor.views.create;
import com.aoedb.editor.views.editors.EditableSelector;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class NewEditableView {
    protected Dialog dialog;


    public NewEditableView(Dialog dialog) {
        this.dialog = dialog;

    }
    public abstract Component getView();

    public abstract void createNewEditable();

    protected VerticalLayout getEntitySelectorTarget(String label, EditableSelector selector) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(false);
        layout.setPadding(false);
        Label l = new Label(label);
        l.addClassNames("selector-title");
        layout.add(l, new Hr(), selector);
        return layout;
    }
}