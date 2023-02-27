package com.aoedb.editor.views.components;

import com.aoedb.editor.data.items.Editable;
import com.aoedb.editor.data.components.ImageIdentifier;
import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class IDSelector extends HorizontalLayout implements UndoRedo {
    private Editable editable;

    private final String type;
    private final Label name;
    private final Image image;
    private final Stack<Editable> undo;
    private final Stack<Editable> redo;

    boolean undoing, redoing;


    ComboBox<Editable> search;
    private UndoStack undoListener;

    private ValueChanged valueListener;

    boolean hasImage;



    public IDSelector(String type, int id, ValueChanged listener){
        this.type = type;
        this.editable = Database.getEditable(type, id);
        this.undo = new Stack<>();
        this.redo = new Stack<>();
        this.valueListener = listener;
        undoing = false;
        redoing = false;
        addClassNames("identifier-row");
        this.name = new Label();
        name.addClassNames("identifier-name");
        this.image =  new Image();
        image.addClassNames("identifier-image");
        hasImage = Objects.requireNonNull(editable).getId() instanceof ImageIdentifier;
        setupView();
    }

    @Override
    public void undo() {
        if (!undo.isEmpty()) {
            System.out.println("undo id selector");
            undoing = true;
            Editable e = undo.pop();
            Editable previous = search.getValue();
            this.search.setValue(e);
            this.redo.push(previous);
        }
    }

    @Override
    public void redo() {
        if (!redo.isEmpty()){
            Editable e = redo.pop();
            Editable previous = search.getValue();
            redoing = true;
            this.search.setValue(e);
            this.undo.push(previous);
        }
    }

    @Override
    public void setUndoStackListener(UndoStack listener){
        this.undoListener = listener;
    }

    public void setupView(){
        search = new ComboBox<>();
        search.getElement().getStyle().set("--vaadin-combo-box-overlay-width","500px");
        ComboBox.ItemFilter<Editable> filter = (editable, filterString) -> String.valueOf(editable.getId().getId()).contains(filterString) || Database.getString(editable.getId().getName()).contains(filterString);
        List<Editable> list = new ArrayList<>(Objects.requireNonNull(Database.getList(this.type)));
        list.add(Database.getEditable(Database.NONE, 0));
        if (type.equals(Database.TECH)) list.add(Database.getEditable(Database.TECH, -1));
        search.setItems(filter, list);
        search.setItemLabelGenerator(editable -> String.valueOf(editable.getId().getId()));
        search.setRenderer(new ComponentRenderer<>(e -> e.getId().getSearchLabel()));
        search.setPlaceholder("Select " + this.type);
        search.addValueChangeListener(event -> {
            this.editable = event.getValue();
            this.name.setText(Database.getString(this.editable.getId().getName()));
            if (hasImage) this.image.setSrc(Database.getImage(((ImageIdentifier) this.editable.getId()).getImage()));
            if (this.valueListener != null) valueListener.onValueChange(event.getValue().getId().getId());
            if (!undoing && !redoing){
                if (undoListener != null) this.undoListener.stackUndo();
                if (event.getOldValue() != null) this.undo.push(event.getOldValue());
                this.redo.clear();
            }
            undoing = false;
            redoing = false;

        });
        search.setValue(editable);
        add(search, name);
        if (hasImage) add(image);
    }


    public interface ValueChanged{
        void onValueChange(int id);
    }

}
