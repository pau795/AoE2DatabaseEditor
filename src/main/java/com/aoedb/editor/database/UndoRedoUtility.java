package com.aoedb.editor.database;


import com.aoedb.editor.views.component.UndoRedoPanel;

import java.util.Stack;

public class UndoRedoUtility {


    private static UndoRedoUtility instance;

    public static UndoRedoUtility getInstance() {
        if (instance == null) instance = new UndoRedoUtility();
        return instance;
    }

    private final Stack<CustomFunction> undoStack;

    private final Stack<CustomFunction> redoStack;

    private final UndoRedoPanel buttonPanel;

    private boolean actionInProgress;

    private UndoRedoUtility(){
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        actionInProgress = false;
        buttonPanel = new UndoRedoPanel();
        buttonPanel.setUndoRedoListeners(new UndoRedoPanel.UndoRedoButtons() {
            @Override
            public void onUndoClick() {
                performUndo();
            }

            @Override
            public void onRedoClick() {
                performRedo();
            }
        });
    }

    public void pushUndo(CustomFunction function){
        undoStack.push(function);
        redoStack.clear();
        updateButtons();
    }

    private void pushUndoFromRedo(CustomFunction function){
        undoStack.push(function);
        updateButtons();
    }

    private void pushRedo(CustomFunction function){
        redoStack.push(function);
        updateButtons();
    }

    public void performUndo(){
        if (!undoStack.isEmpty()) {
            CustomFunction p = undoStack.pop();
            actionInProgress = true;
            p.function();
            pushRedo(p);
            actionInProgress = false;
            updateButtons();
        }
    }

    public void performRedo(){
        if (!redoStack.isEmpty()) {
            CustomFunction p = redoStack.pop();
            actionInProgress = true;
            p.function();
            pushUndoFromRedo(p);
            actionInProgress = false;
            updateButtons();
        }
    }

    private void updateButtons(){
        buttonPanel.enableUndoButton(undoStack.size() > 0);
        buttonPanel.enableRedoButton(redoStack.size() > 0);
    }

    public UndoRedoPanel getButtonPanel(){
        return buttonPanel;
    }

    public boolean isActionInProgress(){
        return actionInProgress;
    }


    public interface CustomFunction {
        void function();
    }

}
