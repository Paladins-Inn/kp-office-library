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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.kaiserpfalzedv.office.library.OfficeBaseEntity;
import de.kaiserpfalzedv.office.library.location.MediumLocation;
import de.kaiserpfalzedv.office.library.mediumtype.MediumType;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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

    @Column(length = 255, nullable = false)
    @Size(min = 1, max = 255)
    @NotNull
    private String name;

    @Column(length = 1000)
    @Size(min = 1, max = 1000)
    private String description;

    @Column(length = 500)
    @Size(min = 1, max = 500)
    private String cover;

    @ToString.Exclude
    @ManyToOne(optional = false)
    private MediumType mediumtype;

    @ToString.Exclude
    @ManyToOne(optional = false)
    private MediumLocation mediumlocation;
}
