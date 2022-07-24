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

package de.kaiserpfalzedv.office.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Schema(
        title = "Medium",
        name = "Medium",
        description = "A single medium inside the library"
)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(callSuper = true)
@Data
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "mediums")
public class Medium extends OfficeBaseEntity {
    private static final long serialVersionUID = 1L;

    @Schema(
            name = "name",
            description = "Name of the medium",
            maxLength = 255,
            minLength = 1,
            example = "Torg Eternity - Core Rules",
            required = true
    )
    @Column(length = 255, nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    private String name;

    @Schema(
            name = "description",
            description = "A short excerpt a synapsis of the content of this medium",
            maxLength = 1000,
            minLength = 10,
            example = "This book contains all rules necessary for playing Torg Eternity.",
            required = false
    )
    @Column(length = 1000)
    @Size(min = 1, max = 1000)
    private String description;

    @Schema(
            name = "cover",
            description = "URI to the cover of this medium",
            maxLength = 500,
            minLength = 10,
            example = "https://d1vzi28wh99zvq.cloudfront.net/images/3444/216248-thumb140.jpg",
            required = false
    )
    @Column(length = 500)
    @Size(min = 1, max = 500)
    private String cover;

    @Schema(
            name = "mediumtype",
            required = false
    )
    @ToString.Exclude
    @ManyToOne(optional = false)
    private MediumType mediumtype;

    @Schema(
            name = "mediumlocation",
            required = false
    )
    @ToString.Exclude
    @ManyToOne(optional = false)
    private MediumLocation mediumlocation;
}
