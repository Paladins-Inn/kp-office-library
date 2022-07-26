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

package de.kaiserpfalzedv.office.library.medium;

import de.kaiserpfalzedv.office.library.QuarkusMappingConfig;
import de.kaiserpfalzedv.office.library.location.MediumLocationMapper;
import de.kaiserpfalzedv.office.library.mediumtype.MediumTypeMapper;
import de.kaiserpfalzedv.office.library.model.Medium;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MediumMapper --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 1.0.0  2022-08-14
 */
@Mapper(
        config = QuarkusMappingConfig.class,
        uses = {MediumTypeMapper.class, MediumLocationMapper.class}
)
public interface MediumMapper {
    Medium toResource(de.kaiserpfalzedv.office.library.medium.Medium medium);

    de.kaiserpfalzedv.office.library.medium.Medium toEntity(Medium medium);
}
