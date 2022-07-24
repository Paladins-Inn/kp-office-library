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

package de.kaiserpfalzedv.office.library.location;

import de.kaiserpfalzedv.office.library.model.MediumLocation;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.MethodProperties;
import io.quarkus.rest.data.panache.ResourceProperties;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.annotation.security.DenyAll;
import java.util.UUID;

/**
 * MediumQueryResource --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 2.0.0  2022-07-24
 */
@DenyAll
@Schema(
        title = "Location Query Resource",
        name = "Location Query Resource",
        description = "Query API to MediumLocations"
)
@ResourceProperties(
        path = "/api/location",
        hal = true
)
@Counted
@Timed
public interface LocationQueryResource extends PanacheRepositoryResource<LocationRepository, MediumLocation, UUID> {
    @MethodProperties(exposed = false)
    boolean delete(UUID id);

    @MethodProperties(exposed = false)
    MediumLocation add(MediumLocation location);

    @MethodProperties(exposed = false)
    MediumLocation update(UUID id, MediumLocation location);
}
