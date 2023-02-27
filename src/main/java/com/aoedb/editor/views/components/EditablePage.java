package com.aoedb.editor.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Stack;

public class EditablePage extends VerticalLayout implements UndoRedo {

    private final Stack<UndoRedo> undo;
    private final Stack<UndoRedo> redo;

    private UndoStack listener;

    public EditablePage(){
        this.undo = new Stack<>();
        this.redo = new Stack<>();
    }
    public void addContent(Component... components){
        for (Component c : components){
            if (this.getChildren().findAny().isEmpty()) add(c);
            else add(new Hr(), c);
            recurseComponent(c);
        }
    }

    private void recurseComponent(Component c){
        if (c instanceof UndoRedo){
            UndoRedo u = (UndoRedo) c;
            u.setUndoStackListener(() -> {
                undo.push(u);
                redo.clear();
                if (listener != null) listener.stackUndo();
            });
        }
        else c.getChildren().forEach(this::recurseComponent);
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
}
