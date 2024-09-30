package com.aoedb.editor.views;


import com.aoedb.editor.data.simple.Editable;
import com.aoedb.editor.database.Database;
import com.aoedb.editor.views.pages.EditEditableView;
import com.aoedb.editor.views.pages.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@CssImport(value = "./themes/aoe2databaseeditor/components/general.css")
public class MainLayout extends AppLayout {

    ComboBox<Editable> editableSearch;
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassNames("menu-item-link");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            text.addClassNames("menu-item-text");

            link.add(new Span(text));
            add(link);
        }

        public Class<?> getView() {
            return view;
        }


    }

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");
        editableSearch = new ComboBox<>();
        setupEditableSearch();
        editableSearch.addClassNames("main-search-bar");
        editableSearch.setItemLabelGenerator(e -> Database.getString(e.getName()));
        editableSearch.setRenderer(new ComponentRenderer<>(e -> e.getEditableView().getSearchLabel()));
        Icon prefixIcon = VaadinIcon.SEARCH.create();
        prefixIcon.getElement().setAttribute("slot", "prefix");
        editableSearch.getElement().appendChild(prefixIcon.getElement());
        editableSearch.addValueChangeListener(event -> {
            Editable e = event.getValue();
            if (e != null) {
                Map<String, String> params = new HashMap<>();
                params.put("type", e.getType());
                params.put("id", String.valueOf(e.getId()));
                getUI().ifPresent(ui -> ui.navigate(EditEditableView.class, new RouteParameters(params)));
            }
        });
        Header header = new Header(toggle, editableSearch);
        header.addClassNames("view-header");
        return header;
    }

    public void setupEditableSearch(){


        ComboBox.ItemFilter<Editable> filter = (editable, filterString) -> editable.getType().toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT)) || Database.getString(editable.getName()).toLowerCase(Locale.ROOT).contains(filterString.toLowerCase(Locale.ROOT));
        editableSearch.setItems(filter, Database.getAllEditables());
        editableSearch.setValue(null);


    }

    private Component createDrawerContent() {
        H2 appName = new H2("AoE2DatabaseEditor");
        appName.addClassNames("app-name");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation());
        section.addClassNames("drawer-section");
        return section;
    }




    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("menu-item-container");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("navigation-list");
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }
        return nav;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("Main", MainView.class), //

        };
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        if (editableSearch != null) setupEditableSearch();

    }

}
