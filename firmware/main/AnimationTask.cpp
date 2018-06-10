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

AnimationTask::AnimationTask(const char* taskName, I2C* i2c)
        : Esp32RtosTask(taskName),
          m_i2c(i2c) {
}

AnimationTask::~AnimationTask() {
}

void AnimationTask::run(void* data) {
    m_i2c->init(I2C_NUM_1, I2C_MODE_MASTER, GPIO_NUM_18, GPIO_NUM_19, GPIO_PULLUP_DISABLE, GPIO_PULLUP_DISABLE, 100000);
    m_i2c->i2c_is31_init(I2C_NUM_1);
    while (1) {
        ESP_LOGW(tag, "AnimationTask::run");
        m_i2c->i2c_is31_pwm(I2C_NUM_1);
        m_i2c->i2c_is31_en(I2C_NUM_1);
        delay(1000);
    }
}
