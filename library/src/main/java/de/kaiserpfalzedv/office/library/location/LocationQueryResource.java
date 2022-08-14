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
import io.quarkus.panache.common.Sort;
import io.quarkus.rest.data.panache.ResourceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

/**
 * MediumQueryResource --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 2.0.0  2022-07-24
 */
@ApplicationScoped
@RequiredArgsConstructor
@DenyAll
@Path("/api/location/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@Schema(
        title = "Location Query Resource",
        name = "Location Query Resource",
        description = "Query API to MediumLocations"
)
@Counted
@Timed
public class LocationQueryResource {
    private final LocationRepository repository;
    private final MediumLocationMapper mapper;

    @GET
    @PermitAll
    @Path("{id}")
    public MediumLocation get(final @PathParam("id") UUID id) {
        return mapper.toResource(repository.findById(id));
    }

    @GET
    @PermitAll
    public List<MediumLocation> list(
            @QueryParam("sort") List<String> sortQuery,
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("20") int pageSize
    ) {
        Sort sort = Sort.by(sortQuery.toArray(new String[] {}));

        return repository.listAll(sort).stream()
                .skip(pageIndex).limit(pageSize)
                .map(mapper::toResource)
                .toList();
    }
}
