/*
 * AnimationTask.cpp
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <esp_log.h>

#include "nvs_flash.h"
#include "esp_wifi.h"
#include "esp_system.h"
#include "esp_event.h"
#include "esp_event_loop.h"

#include "AnimationTask.h"
#include "sdkconfig.h"

static char tag[] = "AnimationTask";

AnimationTask::AnimationTask(const char* taskName)
        : Esp32RtosTask(taskName) {
}

AnimationTask::~AnimationTask() {
}

void AnimationTask::run(void* data) {
    int i = 0;
    while (1) {
        ESP_LOGW(tag, "AnimationTask::run");
        delay(1000);
        if (i++ == 10) {
            esp_wifi_start();
        }
    }
}
