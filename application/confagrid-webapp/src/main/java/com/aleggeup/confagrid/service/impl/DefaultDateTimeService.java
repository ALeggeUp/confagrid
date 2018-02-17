/*
 * DefaultDateTimeService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.service.impl;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import com.aleggeup.confagrid.service.DateTimeService;

@Service
public class DefaultDateTimeService implements DateTimeService {

    @Override
    public DateTime now() {
        return DateTime.now().withZone(DateTimeZone.UTC);
    }

    @Override
    public Date nowAsDate() {
        return DateTime.now().withZone(DateTimeZone.UTC).toDate();
    }

    @Override
    public DateTime plusDays(final DateTime dateTime, final int numberOfDays) {
        return dateTime.plusDays(numberOfDays);
    }

    @Override
    public Date plusDaysAsDate(final DateTime dateTime, final int numberOfDays) {
        return dateTime.plusDays(numberOfDays).toDate();
    }
}
