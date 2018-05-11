/*
 * DateTimeService.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.time;

import java.util.Date;

import org.joda.time.DateTime;

public interface DateTimeService {

    DateTime now();

    Date nowAsDate();

    DateTime plusDays(DateTime dateTime, int numberOfDays);

    Date plusDaysAsDate(DateTime dateTime, int numberOfDays);
}
