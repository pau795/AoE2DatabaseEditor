package com.aoedb.editor.views.component;

import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Collection;


public class FilterableVerticalList<T extends Editable> extends VerticalLayout {

    Collection<T> items;
    ChildClick listener;

    public FilterableVerticalList(){
        this.setPadding(false);
        this.setMargin(false);
        this.setSpacing(false);
        this.setAlignItems(Alignment.STRETCH);
    }

    public void setItems(Collection<T> items){
        this.items = items;
    }

    public void performFilter(String filter){
        if (items != null){
            this.removeAll();
            items.stream()
                .filter(e -> Database.getString(e.getName()).toLowerCase().contains(filter.toLowerCase()))
                .forEach(e ->{
                    Div d = (Div) e.getEditableLabel();
                    d.addClickListener(event ->{
                        if (this.listener != null) listener.onChildClick(e);
                    });
                    this.add(d);
                });
        }
    }

    public int getSize(){
        return (int) this.getChildren().count();
    }

    public void addChildClickListener(ChildClick listener){
        this.listener = listener;
    }
    public interface ChildClick{
        void onChildClick(Editable e);
    }


}
