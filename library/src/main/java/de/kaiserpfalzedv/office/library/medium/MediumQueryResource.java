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

import io.quarkus.panache.common.Sort;
import io.quarkus.rest.data.panache.ResourceProperties;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.annotation.security.DenyAll;
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
@ResourceProperties(
        path = "/api/medium",
        hal = true
)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Counted
@Timed
public class MediumQueryResource {

    private final MediumRepository repository;
    private final MediumMapper mapper;

    @GET
    @Path("{id}")
    public de.kaiserpfalzedv.office.library.model.Medium get(final @PathParam("id") UUID id) {
        return mapper.toResource(repository.findById(id));
    }

    @GET
    public List<de.kaiserpfalzedv.office.library.model.Medium> list(
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
