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

import de.kaiserpfalzedv.office.library.location.LocationRepository;
import de.kaiserpfalzedv.office.library.model.MediumLocation;
import de.kaiserpfalzedv.office.library.model.MediumType;
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
 * MediumTypeCommandResource --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 2.0.0  2022-07-24
 */
@ApplicationScoped
@RequiredArgsConstructor
@RolesAllowed({ApplicationRoles.ADMIN})
@Path("/api/mediumtype/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@Schema(
        title = "MediumType Command Resource",
        name = "MediumType Command Resource",
        description = "Write API to MediumTypes"
)
@Counted
@Timed
public class MediumTypeCommandResource {
    final MediumTypeRepository repository;

    @Transactional
    @POST
    @Path("{name}")
    public MediumType create(
            @PathParam("name")
            @Schema(
                    name = "name",
                    minLength = 1,
                    maxLength = 255,
                    description = "Neuer Name",
                    example = "Bibliotheksname",
                    required = true)
            @NotNull
            @Size(min = 1, max = 255)
            final String name
    ) {
        repository.persist(
                MediumType.builder()
                        .name(name)
                        .build()
        );

        Optional<MediumType> result = repository.findByName(name);

        return result.orElse(null);
    }

    @Transactional
    @PUT
    @Path("{id}/{name}")
    public MediumType changeName(
            @PathParam("id")
            @Schema(
                    name = "id",
                    description = "ID of the media type to change the name for",
                    example = "1b79bd7c-c9e9-4cea-96c3-b96bcf47d270",
                    required = true
            )
            final UUID id,

            @PathParam("name")
            @Schema(
                    name = "name",
                    minLength = 1,
                    maxLength = 255,
                    description = "New Name of the type",
                    example = "Buch",
                    required = true)
            @NotNull
            @Size(min = 1, max = 255)
            final String name) {
        MediumType result = repository
                .findByIdOptional(id)
                .orElseThrow(NotFoundException::new);

        result.setName(name);
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
