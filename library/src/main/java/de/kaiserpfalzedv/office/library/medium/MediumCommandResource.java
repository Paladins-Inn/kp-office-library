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

import de.kaiserpfalzedv.office.library.location.LocationRepository;
import de.kaiserpfalzedv.office.library.model.Medium;
import de.kaiserpfalzedv.office.library.model.MediumLocation;
import de.kaiserpfalzedv.office.library.security.ApplicationRoles;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.UUID;

/**
 * LocationCommandResource -- Create, Change and delete Locations
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 1.0.0  2022-07-24
 */
@ApplicationScoped
@RequiredArgsConstructor
@RolesAllowed({ApplicationRoles.ADMIN, ApplicationRoles.LIBRARIAN})
@Path("/api/medium/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@Schema(
        title = "Medium Command Resource",
        name = "Medium Command Resource",
        description = "Write API to Medium"
)
@Counted
@Timed
public class MediumCommandResource {
    final MediumRepository repository;

    @Transactional
    @POST
    public Medium create(Medium medium) {
        repository.persist(medium);

        Optional<Medium> result = repository.findByIdOptional(medium.getId());

        return result.orElse(null);
    }

    @Transactional
    @PUT
    @Path("{id}")
    public Medium changeName(
            @PathParam("id")
            @Schema(
                    name = "id",
                    description = "ID of the location to change the name for",
                    example = "1b79bd7c-c9e9-4cea-96c3-b96bcf47d270",
                    required = true
            )
            final UUID id,

            @NotNull final Medium medium) {
        Medium result = repository
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        result.setVersion(medium.getVersion());
        result.setName(medium.getName());
        result.setDescription(medium.getDescription());
        result.setCover(medium.getCover());

        result.persistAndFlush();

        return result;
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") final UUID id) {
        repository.deleteById(id);
    }
}
