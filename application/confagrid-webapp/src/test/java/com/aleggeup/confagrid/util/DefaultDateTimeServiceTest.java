/*
 * DefaultDateTimeServiceTest.java
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid.util;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.aleggeup.confagrid.time.DefaultDateTimeService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultDateTimeServiceTest {

    private DefaultDateTimeService defaultDateTimeService;

    @Before
    public void setUp() {
        defaultDateTimeService = new DefaultDateTimeService();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testNow() {
        final DateTime dateTime = defaultDateTimeService.now();
        Assert.assertNotNull(dateTime);
    }

    @Test
    public void testNowAsDate() {
        final Date date = defaultDateTimeService.nowAsDate();
        Assert.assertNotNull(date);
    }

    @Test
    public void testPlusDay() {
        final DateTime now = DateTime.now();
        final DateTime expiry = now.plusDays(1);

        Assert.assertEquals(expiry, defaultDateTimeService.plusDays(now, 1));
    }

    @Test
    public void testPlusDayAsDate() {
        final DateTime now = DateTime.now();
        final Date expiry = now.plusDays(1).toDate();

        Assert.assertEquals(expiry, defaultDateTimeService.plusDaysAsDate(now, 1));
    }
}
