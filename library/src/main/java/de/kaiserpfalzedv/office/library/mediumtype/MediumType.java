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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.kaiserpfalzedv.office.library.OfficeBaseEntity;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(
        title = "MediumType",
        name = "MediumType",
        description = "Type of the medium"
)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@ToString(callSuper = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "mediumtypes")
public class MediumType extends OfficeBaseEntity {
    private static final long serialVersionUID = 1L;

    @Schema(
            name = "name",
            description = "Name of the medium type",
            maxLength = 255,
            minLength = 1,
            required = true
    )
    @Size(min = 1, max = 255)
    @NotNull
    @Column(length = 255, nullable = false)
    private String name;
}
