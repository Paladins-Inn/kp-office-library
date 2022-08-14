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
import io.quarkus.test.junit.QuarkusTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * MediumLocationMapperTest --
 *
 * @author klenkes74 {@literal <rlichti@kaiserpfalz-edv.de>}
 * @since 1.0.0  2022-08-14
 */
@QuarkusTest
@RequiredArgsConstructor
@Slf4j
public class MediumLocationMapperTest {
    private final MediumLocationMapper sut;

    @Test
    public void shouldMapTheNameWhenANonNullModellIsMapped() {
        MediumLocation data = MediumLocation.builder()
                .name("Paladins Inn")
                .build();

        de.kaiserpfalzedv.office.library.location.MediumLocation result = sut.toEntity(data);
        log.info("mapping model to entity: model={}, entity={}", data, result);

        Assertions.assertEquals(data.getName(), result.getName());
        Assertions.assertEquals(data.getCreated(), result.getCreated());
    }

    @Test
    public void shouldMapTheNameWhenANonNullEntityIsMapped() {
        de.kaiserpfalzedv.office.library.location.MediumLocation data = de.kaiserpfalzedv.office.library.location.MediumLocation.builder()
                .name("Paladins Inn")
                .build();

        MediumLocation result = sut.toResource(data);
        log.info("mapping entity to model: model={}, entity={}", result, data);

        Assertions.assertEquals(data.getName(), result.getName());
        Assertions.assertEquals(data.getCreated(), result.getCreated());
    }
}
