/*
 * main.cpp
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <stdio.h>
#include <string.h>

#include "nvs_flash.h"
#include "esp_wifi.h"
#include "esp_system.h"
#include "esp_event.h"
#include "esp_event_loop.h"

#include "AnimationTask.h"
#include "CoapServerTask.h"
#include "HardwareConfig.h"
#include "HttpServerTask.h"
#include "I2C.h"
#include "Wifi.h"

#include "sdkconfig.h"

extern "C" void app_main()
{
    HardwareConfig hardwareConfig(HardwareConfig::DEV0);
    I2C i2c(&hardwareConfig);
    Wifi* wifi = new Wifi();
    wifi->start();
    AnimationTask* pAnimationTask = new AnimationTask("AnimationTask", &i2c);
    pAnimationTask->start(0);
    HttpServerTask* pHttpServerTask = new HttpServerTask("HttpServerTask");
    pHttpServerTask->start(0);
    CoapServerTask* pCoapServerTask = new CoapServerTask("CoapServerTask");
    pCoapServerTask->start(0);
}
