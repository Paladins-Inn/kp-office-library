/*
 * Copyright (C)  2022. Roland T. Lichti, Kaiserpfalz EDV-Service.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not,
 * see <https://www.gnu.org/licenses/>.
 */

package de.kaiserpfalzedv.office.library.librarian;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import de.kaiserpfalzedv.office.library.librarian.books.ListView;
import de.kaiserpfalzedv.office.library.librarian.greeter.GreeterView;
import lombok.extern.slf4j.Slf4j;

/**
 * LibrarianLayout --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 2.0.0  2022-07-23
 */
@Slf4j
public class LibrarianLayout extends AppLayout {

    final HorizontalLayout header = new HorizontalLayout();
    final VerticalLayout menu = new VerticalLayout();

    public LibrarianLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Paladins Inn Library");
        logo.addClassNames("text-l", "m-m");

        header.add(new DrawerToggle(), logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        addToNavbar(header);
    }

    private void createDrawer() {
        menu.add(createLink("Greeter", GreeterView.class));
        menu.add(createLink("Books", ListView.class));

        addToDrawer(menu);
    }

    private RouterLink createLink(final String title, Class<? extends Component> view) {
        RouterLink result = new RouterLink(title, view);
        result.setHighlightCondition(HighlightConditions.sameLocation());

        return result;
    }
}
