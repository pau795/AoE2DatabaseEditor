package com.aoedb.editor.views.editors;

import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class StringEditor extends VerticalLayout {

    public interface StringChangedListener{
        void onChange(String s);
    }

    StringChangedListener listener;

    public StringEditor(String stringKey){
        TextField englishField = new TextField();
        englishField.setWidthFull();
        englishField.setValue(Database.getString(stringKey, "en"));
        englishField.addValueChangeListener(e -> {
            Database.setString(stringKey, e.getValue(), "en");
            if (listener != null) listener.onChange(e.getValue());
        });
        englishField.setPrefixComponent(new Label("\uD83C\uDDEC\uD83C\uDDE7"));
        TextField spanishField = new TextField();
        spanishField.setWidthFull();
        spanishField.setValue(Database.getString(stringKey, "es"));
        spanishField.addValueChangeListener(e -> {
            Database.setString(stringKey, e.getValue(), "es");
            if (listener != null) listener.onChange(e.getValue());
        });
        spanishField.setPrefixComponent(new Label("\uD83C\uDDEA\uD83C\uDDF8"));
        setWidthFull();
        VerticalLayout langLayout = new VerticalLayout(englishField, spanishField);
        langLayout.setPadding(false);
        Label title = new Label(stringKey);
        title.getElement().getStyle().set("font-size", "16px");
        title.getElement().getStyle().set("font-weight", "bold");
        setPadding(false);
        add(title, langLayout);
    }

    public void setStringChangedListener(StringChangedListener listener){
        this.listener = listener;
    }
}
