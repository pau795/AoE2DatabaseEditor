package com.aoedb.editor.views;

import com.aoedb.editor.data.items.Editable;
import com.aoedb.editor.database.Database;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;

import java.util.Locale;

@PageTitle("Main")
@Route(value = "main", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

@CssImport(value = "./themes/aoe2databaseeditor/components/general.css")
public class MainView extends VerticalLayout {

    ComboBox<Editable> editableSearch;

    public MainView() {
        editableSearch = new ComboBox<>();
        editableSearch.addClassNames("main-search-bar");
        ComboBox.ItemFilter<Editable> filter = (editable, filterString) -> editable.getId().getType().toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT)) || Database.getString(editable.getId().getName()).toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT));
        editableSearch.setItems(filter, Database.getAllEditables());
        editableSearch.setItemLabelGenerator(e -> Database.getString(e.getId().getName()));
        editableSearch.setRenderer(new ComponentRenderer<>(e -> e.getId().getSearchLabel()));
        Icon prefixIcon = VaadinIcon.SEARCH.create();
        prefixIcon.getElement().setAttribute("slot", "prefix");
        editableSearch.getElement().appendChild(prefixIcon.getElement());
        editableSearch.addValueChangeListener(event -> {
            Editable e = event.getValue();
            RouteParameters parameters = new RouteParameters(new RouteParam("type", e.getId().getType()), new RouteParam("ID", String.valueOf(e.getId().getId())));
            getUI().ifPresent(ui -> ui.navigate(EditableView.class, parameters));
        });
        add(editableSearch);
    }

}
