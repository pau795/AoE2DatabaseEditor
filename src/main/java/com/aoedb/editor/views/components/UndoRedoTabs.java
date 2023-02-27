package com.aoedb.editor.views.components;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.Stack;

public class UndoRedoTabs extends Tabs implements UndoRedo{

    private final Stack<Tab> undo;
    private final Stack<Tab> redo;

    boolean undoing, redoing;
    private UndoStack listener;

    public UndoRedoTabs(){
        super();
        this.undo = new Stack<>();
        this.redo = new Stack<>();
        undoing = false;
        redoing = false;
        this.addSelectedChangeListener(event ->{
            if (!undoing && ! redoing) {
                if (event.getPreviousTab() != null) undo.push(event.getPreviousTab());
                redo.clear();
                if (listener != null) listener.stackUndo();
            }
            undoing = false;
            redoing = false;
        });
    }

    @Override
    public void undo() {
        if (!undo.isEmpty()) {
            Tab t = undo.pop();
            this.setSelectedTab(t);
            this.redo.push(t);
        }
    }

    @Override
    public void redo() {
        if (!redo.isEmpty()){
            Tab t = redo.pop();
            this.setSelectedTab(t);
            this.undo.push(t);
        }
    }

    @Override
    public void setUndoStackListener(UndoStack listener){
        this.listener = listener;
    }
}
