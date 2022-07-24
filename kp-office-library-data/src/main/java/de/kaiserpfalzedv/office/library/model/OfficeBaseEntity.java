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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * OfficeBaseEntity -- The base entity with all meta data needed.
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 1.0.0  2022-07-24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public abstract class OfficeBaseEntity extends PanacheEntityBase implements Serializable {
    public static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";


    @Schema(
            name = "id",
            description = "The technical ID of this entity (UUID4)",
            pattern = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}",
            example = "058a0805-3309-4680-9554-34b53dff5962",
            required = true
    )
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", nullable = false, updatable = false, unique = true)
    private UUID id;

    @Schema(
            name = "version",
            description = "A counter of changes to the entity in the data store. Used for optimistic locking",
            minimum = "0",
            maximum = "2147483647",
            example = "5",
            required = true
    )
    @Version
    @Column(nullable = false)
    private Integer version = 0;

    @Schema(
            name = "created",
            description = "Timestamp of the creation of this entity",
            pattern = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{6}",
            example = "2022-01-01T00:00:00.000000",
            required = true
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = ISO8601_PATTERN
    )
    @ToString.Exclude
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Schema(
            name = "changed",
            description = "Timestamp of the last change to this entity",
            pattern = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{6}",
            example = "2022-01-01T00:00:00.000000",
            required = true
    )
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = ISO8601_PATTERN
    )
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime changed = LocalDateTime.now();
}
