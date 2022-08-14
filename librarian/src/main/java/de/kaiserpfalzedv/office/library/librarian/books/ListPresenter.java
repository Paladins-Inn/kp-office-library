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

package de.kaiserpfalzedv.office.library.librarian.books;

import de.kaiserpfalzedv.office.library.model.Medium;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * ListPresenter --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 2.0.0  2022-08-14
 */
@Slf4j
@Dependent
public class ListPresenter {

    private MediumReaderService readerService;

    private List<Medium> data;
    private Medium medium;

    @Inject
    public ListPresenter(MediumReaderService readerService) {
        this.readerService = readerService;
    }

    List<Medium> data() {
        if (data == null) {
            data = readerService.list();
        }
        return data;
    }

    Medium get(@NotNull UUID id) {
        if (medium == null) {
            medium = readerService.get(id);
        }

        return medium;
    }
}
