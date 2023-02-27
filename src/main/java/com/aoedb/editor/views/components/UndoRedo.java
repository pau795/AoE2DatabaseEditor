package com.aoedb.editor.views.components;

public interface UndoRedo {
    void undo();
    void redo();

    void setUndoStackListener(UndoStack listener);
}
