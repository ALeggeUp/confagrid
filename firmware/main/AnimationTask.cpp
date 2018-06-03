/*
 * AnimationTask.cpp
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <esp_log.h>

#include "AnimationTask.h"
#include "sdkconfig.h"

static char tag[] = "AnimationTask";

AnimationTask::AnimationTask(std::string taskName)
        : Task(taskName) {
}

AnimationTask::~AnimationTask() {
}

void AnimationTask::run(void *data) {
    while (1) {
        ESP_LOGW(tag, "AnimationTask::run");
        delay(1000);
    }
}
