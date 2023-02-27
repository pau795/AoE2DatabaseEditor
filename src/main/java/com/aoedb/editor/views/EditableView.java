package com.aoedb.editor.views;

import com.aoedb.editor.data.items.Editable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.components.EditableComponent;
import com.aoedb.editor.views.components.UndoRedo;
import com.aoedb.editor.views.components.UndoStack;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.Stack;

@RoutePrefix("editable")
@Route(value = ":type?/:ID?", layout = MainLayout.class)
@CssImport(value = "./themes/aoe2databaseeditor/components/editable-component.css")
public class EditableView extends VerticalLayout implements BeforeEnterObserver, UndoRedo {

    private final Stack<UndoRedo> undo;
    private final Stack<UndoRedo> redo;

    protected RouteParameters parameters;
    String type;
    int editableID;

    UndoStack listener;

    Editable editable;

    EditableComponent component;

    public EditableView(){
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }

    public void setupView(){
        this.component = this.editable.getEditableComponent();
        this.component.setUndoStackListener(() -> {
            undo.push(component);
            redo.clear();
            if (this.listener != null) this.listener.stackUndo();
        });
        add(component);
        setPadding(false);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        parameters = beforeEnterEvent.getRouteParameters();
        this.type = parameters.get("type").orElse("");
        this.editableID = Integer.parseInt(parameters.get("ID").orElse("-1"));
        this.editable = Database.getEditable(type, editableID);
        setupView();
    }

    @Override
    public void undo() {
        if (!undo.isEmpty()) {
            UndoRedo u = undo.pop();
            u.undo();
            this.redo.push(u);
        }
    }

    @Override
    public void redo() {
        if (!redo.isEmpty()){
            UndoRedo u = redo.pop();
            u.redo();
            this.undo.push(u);
        }
    }

    @Override
    public void setUndoStackListener(UndoStack listener) {
        this.listener = listener;
    }

    public int undoSize(){
        return undo.size();
    }

    public int redoSize(){
        return redo.size();
    }
}
