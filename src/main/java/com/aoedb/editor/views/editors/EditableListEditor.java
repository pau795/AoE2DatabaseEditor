package com.aoedb.editor.views.editors;

import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.html.Div;

import java.util.List;

public class EditableListEditor extends Div {

    private List<Integer> idList;
    String editableType;

    public EditableListEditor(List<Integer> idList, String editableType){
        this.addClassNames("entities-layout-grid", "standard-horizontal-padding", "black-border-bottom");
        this.setList(idList, editableType);
    }

    public void setList(List<Integer> idList, String editableType){
        this.idList = idList;
        this.editableType = editableType;
        this.populateGrid();
    }

    public void addItem(Integer id){
        this.idList.add(id);
        this.addItemToGrid(id);
    }

    private void populateGrid(){
        this.removeAll();
        idList.forEach(this::addItemToGrid);
    }

    private void addItemToGrid(Integer id){
        EditableSelector selector = new EditableSelector(Database.getEditable(editableType, id));
        selector.setDeleteButton(true);
        selector.setEditableChangedListener(editable ->{
            this.idList.remove(Integer.valueOf(selector.getEditable().getId()));
            this.idList.add(editable.getId());
        });
        selector.setRemoveEditableListener(event1 -> {
            this.idList.remove(Integer.valueOf(selector.getEditable().getId()));
            this.remove(selector);
        });
        this.add(selector);
    }



}
