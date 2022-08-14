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
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

/**
 * MediumReaderClient --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 1.0.0  2022-08-14
 */
@ApplicationScoped
@Path("/medium")
@RegisterRestClient(configKey = "library")
@Produces(MediaType.APPLICATION_JSON)
public interface MediumReaderClient {
    /**
     * @return A response with an entities JSON array.
     */
    @GET
    List<Medium> list(
            @QueryParam("page") int page,
            @QueryParam("size") int size
    );

    /**
     * @param id Entity identifier.
     * @return A response with a JSON object representing an entity.
     */
    @GET
    Medium get(UUID id);
}
