package com.aoedb.editor.views.component;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@CssImport(value = "./themes/aoe2databaseeditor/main-layout.css")
public class UndoRedoPanel extends HorizontalLayout {
    Button undo, redo;
    UndoRedoButtons listener;

    Icon undoEnable, undoDisable, redoEnable, redoDisable;
    public UndoRedoPanel(){
        listener = null;
        undo = new Button();
        undo.addClassNames("undo-redo-button");
        undo.getElement().setAttribute("title", "Undo (CTRL+Z)");
        redo = new Button(new Icon(VaadinIcon.ARROW_FORWARD));
        redo.addClassNames("undo-redo-button");
        redo.getElement().setAttribute("title", "Redo (CTRL+SHIFT+Z)");
        enableUndoButton(false);
        enableRedoButton(false);
        setupUndoRedoIcons();
        setupUndoRedo();
        this.add(undo, redo);
    }

    private void setupUndoRedoIcons(){
        undoEnable = new Icon(VaadinIcon.ARROW_BACKWARD);
        undoEnable.addClassNames("undo-redo-icon");
        undoEnable.setColor("blue");

        undoDisable = new Icon(VaadinIcon.ARROW_BACKWARD);
        undoDisable.addClassNames("undo-redo-icon");
        undoDisable.setColor("grey");

        redoEnable = new Icon(VaadinIcon.ARROW_FORWARD);
        redoEnable.addClassNames("undo-redo-icon");
        redoEnable.setColor("blue");

        redoDisable = new Icon(VaadinIcon.ARROW_FORWARD);
        redoDisable.addClassNames("undo-redo-icon");
        redoDisable.setColor("grey");
    }

    public void enableUndoButton(boolean enabled){
        undo.setEnabled(true);
        if (enabled) undo.setIcon(undoEnable);
        else undo.setIcon(undoDisable);
    }

    public void enableRedoButton(boolean enabled){
        redo.setEnabled(true);
        if (enabled) redo.setIcon(redoEnable);
        else redo.setIcon(redoDisable);
    }


    private void setupUndoRedo(){

        undo.addClickListener(event ->{
            if (listener != null) listener.onUndoClick();
        });
        undo.addClickShortcut(Key.KEY_Z, KeyModifier.CONTROL);

        redo.addClickListener(event ->{
            if (listener != null) listener.onRedoClick();
        });
        redo.addClickShortcut(Key.KEY_Z, KeyModifier.CONTROL, KeyModifier.SHIFT);
    }

    public void setUndoRedoListeners(UndoRedoButtons listener) {
        this.listener = listener;
    }

    public interface UndoRedoButtons{
        void onUndoClick();
        void onRedoClick();
    }
}
