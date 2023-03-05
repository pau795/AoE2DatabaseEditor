package com.aoedb.editor.views;


import com.aoedb.editor.database.UndoRedoUtility;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyModifier;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.RouterLink;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    Button undo, redo;
    Icon undoEnable, undoDisable, redoEnable, redoDisable;
    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassNames("menu-item-link");
            link.setRoute(view);

            Span text = new Span(menuTitle);
            text.addClassNames("menu-item-text");

            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                addClassNames("menu-item-icon");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }

    }


    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        setupUndoRedoIcons();
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("view-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");


        undo = new Button();
        undo.addClassNames("undo-redo-button");
        undo.getElement().setAttribute("title", "Undo (CTRL+Z)");
        redo = new Button(new Icon(VaadinIcon.ARROW_FORWARD));
        redo.addClassNames("undo-redo-button");
        redo.getElement().setAttribute("title", "Redo (CTRL+SHIFT+Z)");

        enableUndoButton(false);
        enableRedoButton(false);

        Header header = new Header(toggle, undo, redo);
        header.addClassNames("view-header");
        return header;
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

    private void enableUndoButton(boolean enabled){
        undo.setEnabled(true);
        if (enabled) undo.setIcon(undoEnable);
        else undo.setIcon(undoDisable);
    }

    private void enableRedoButton(boolean enabled){
        redo.setEnabled(true);
        if (enabled) redo.setIcon(redoEnable);
        else redo.setIcon(redoDisable);
    }

    private Component createDrawerContent() {
        H2 appName = new H2("AoE2DatabaseEditor");
        appName.addClassNames("app-name");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
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
                new MenuItemInfo("Main", "la la-file", MainView.class), //

        };
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("footer");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        setupUndoRedo();
    }

    private void setupUndoRedo(){
        undo.addClickListener(event -> {
            UndoRedoUtility.performUndo();
            enableUndoButton(UndoRedoUtility.undoSize() > 0);
            enableRedoButton(UndoRedoUtility.redoSize() > 0);
        });
        undo.addClickShortcut(Key.KEY_Z, KeyModifier.CONTROL);
        redo.addClickListener(event -> {
            UndoRedoUtility.performRedo();
            enableUndoButton(UndoRedoUtility.undoSize() > 0);
            enableRedoButton(UndoRedoUtility.redoSize() > 0);
        });
        redo.addClickShortcut(Key.KEY_Z, KeyModifier.CONTROL, KeyModifier.SHIFT);
    }


}
