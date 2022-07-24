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

package de.kaiserpfalzedv.office.library.mediumtype;

import de.kaiserpfalzedv.office.library.mediumtype.MediumTypeRepository;
import de.kaiserpfalzedv.office.library.model.*;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.MethodProperties;
import io.quarkus.rest.data.panache.ResourceProperties;

import javax.annotation.security.DenyAll;
import java.util.UUID;

/**
 * MediumTypeQueryResource --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 2.0.0  2022-07-24
 */
@DenyAll
@ResourceProperties(
        path = "/api/mediumtype",
        hal = true
)
public interface MediumTypeQueryResource extends PanacheRepositoryResource<MediumTypeRepository, MediumType, UUID> {
    @MethodProperties(exposed = false)
    boolean delete(UUID id);

    @MethodProperties(exposed = false)
    MediumType add(MediumType location);

    @MethodProperties(exposed = false)
    MediumType update(UUID id, MediumType location);
}