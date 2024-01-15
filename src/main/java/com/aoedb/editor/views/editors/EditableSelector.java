package com.aoedb.editor.views.editors;

import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.Collection;

import java.util.Locale;

public class EditableSelector extends HorizontalLayout {

    public interface EditableChangedListener {
        void onChange(Editable newEditable);
    }

    public interface RemoveEditableListener {
        void onDelete(Editable newEditable);
    }

    private EditableChangedListener editableChangedListener;
    private RemoveEditableListener removeEditableListener;
    private Div entityLabel;

    private final Button deleteButton;

    private Editable editable;

    public EditableSelector(Editable editable){

        this.editable = editable;
        ComboBox<Editable> entitySelector =  new ComboBox<>();
        ComboBox.ItemFilter<Editable> filter = (editable1, filterString) -> editable1.getType().toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT)) || Database.getString(editable1.getName()).toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT));
        entitySelector.setItems(filter, (Collection<Editable>) Database.getEditableList(editable.getType()));
        entitySelector.setItemLabelGenerator(e -> Database.getString(e.getName()));
        entitySelector.setRenderer(new ComponentRenderer<>(e -> e.getEditableView().getLabel()));
        Icon prefixIcon = VaadinIcon.SEARCH.create();
        prefixIcon.getElement().setAttribute("slot", "prefix");
        entitySelector.getElement().appendChild(prefixIcon.getElement());
        entitySelector.addValueChangeListener(event -> {
            Editable ed = event.getValue();
            remove(entityLabel);
            entityLabel = ed.getEditableView().getLabel();
            entityLabel.setWidth("150px");
            addComponentAsFirst(entityLabel);
            if (editableChangedListener !=  null) editableChangedListener.onChange(ed);
            this.editable = ed;
        });
        entityLabel = editable.getEditableView().getLabel();
        entityLabel.setWidth("150px");
        deleteButton = new Button(VaadinIcon.TRASH.create());
        deleteButton.setVisible(false);
        deleteButton.addClickListener(event -> {
            if (removeEditableListener != null) removeEditableListener.onDelete(editable);
        });
        setAlignItems(Alignment.CENTER);
        add(entityLabel, entitySelector, deleteButton);
    }

    public void setDeleteButton(boolean b){
        deleteButton.setVisible(b);
    }

    public void setEditableChangedListener(EditableChangedListener listener){
        this.editableChangedListener = listener;
    }

    public void setRemoveEditableListener(RemoveEditableListener removeEditableListener) {
        this.removeEditableListener = removeEditableListener;
    }

    public Editable getEditable(){
        return editable;
    }
}
