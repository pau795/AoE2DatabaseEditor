package com.aoedb.editor.views.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;

import java.util.HashMap;
import java.util.Stack;

public class EditableComponent extends VerticalLayout implements UndoRedo {

    private final Stack<UndoRedo> undo;

    private final Stack<UndoRedo> redo;

    private UndoStack listener;


    UndoRedoTabs tabs;
    HashMap<Tab, EditablePage> tabMap;
    Div tabContent;

    public EditableComponent(){
        this.undo = new Stack<>();
        this.redo = new Stack<>();
        tabMap = new HashMap<>();
        tabs = new UndoRedoTabs();
        tabs.addClassNames("main-tabs");
        tabContent = new Div();
        tabContent.addClassNames("main-tab-content");
        tabs.setFlexGrowForEnclosedTabs(1);
        tabs.addSelectedChangeListener(event -> selectTab());
        tabs.setUndoStackListener(() -> {
            undo.push(tabs);
            redo.clear();
            if (listener != null) listener.stackUndo();
        });
        add(tabs, tabContent);
    }

    public void addTab(String title, EditablePage c){
        Tab tab = new Tab(title);
        tab.setClassName("main-tab");
        tabContent.add(c);
        tabMap.put(tab, c);
        tabs.add(tab);
        c.setUndoStackListener(() -> {
            undo.push(c);
            redo.clear();
            if (listener != null) listener.stackUndo();
        });
    }

    public EditablePage getTabComponent(int i){
        return (EditablePage) tabContent.getComponentAt(i);
    }

    public void selectTab(){
        tabMap.values().forEach(page -> page.setVisible(false));
        Component selectedPage = tabMap.get(tabs.getSelectedTab());
        selectedPage.setVisible(true);
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
