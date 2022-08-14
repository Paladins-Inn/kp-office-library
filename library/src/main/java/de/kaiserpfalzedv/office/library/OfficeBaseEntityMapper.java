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

package de.kaiserpfalzedv.office.library;

import de.kaiserpfalzedv.office.library.model.OfficeBaseEntity;
import org.mapstruct.Mapper;

/**
 * OfficeBaseEntityMapper --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 2.0.0  2022-08-14
 */
@Mapper(config = QuarkusMappingConfig.class)
public interface OfficeBaseEntityMapper {
    OfficeBaseEntity toResource(de.kaiserpfalzedv.office.library.OfficeBaseEntity base);

    de.kaiserpfalzedv.office.library.OfficeBaseEntity toEntity(OfficeBaseEntity base);
}
