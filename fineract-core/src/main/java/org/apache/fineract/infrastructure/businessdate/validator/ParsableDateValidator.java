/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.infrastructure.businessdate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ParsableDateValidator implements ConstraintValidator<ParsableDate, Object> {

    private String dateFieldName;
    private String formatFieldName;
    private String localeFieldName;

    @Override
    public void initialize(ParsableDate annotation) {
        this.dateFieldName = annotation.dateField();
        this.formatFieldName = annotation.formatField();
        this.localeFieldName = annotation.localeField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field dateField = value.getClass().getDeclaredField(dateFieldName);
            Field formatField = value.getClass().getDeclaredField(formatFieldName);
            Field localeField = value.getClass().getDeclaredField(localeFieldName);

            dateField.setAccessible(true);
            formatField.setAccessible(true);
            localeField.setAccessible(true);

            String dateStr = (String) dateField.get(value);
            String dateFormat = (String) formatField.get(value);
            String localeStr = (String) localeField.get(value);

            if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(dateFormat) || StringUtils.isBlank(localeStr)) {
                return true;
            }

            Locale locale = Locale.forLanguageTag(localeStr);
            String strictFormat = dateFormat.replace("y", "u");

            DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern(strictFormat)
                    .toFormatter(locale).withResolverStyle(ResolverStyle.STRICT);

            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Invalid configuration for @ParsableDate", e);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
