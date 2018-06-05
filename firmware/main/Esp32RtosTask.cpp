/*
 * Esp32RtosTask.cpp
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <freertos/FreeRTOS.h>
#include <freertos/task.h>

#include <esp_log.h>

#include "sdkconfig.h"
#include "Esp32RtosTask.h"

const static char tag[] = "Esp32RtosTask";

Esp32RtosTask::Esp32RtosTask(const char* taskName)
        : m_taskData(nullptr),
          m_taskHandle(nullptr)
{
    ESP_LOGW(tag, "constructor");
}

Esp32RtosTask::~Esp32RtosTask()
{
}

void Esp32RtosTask::start(void* data) {
    ESP_LOGW(tag, "start");
    m_taskData = data;
    ::xTaskCreate(&runTask, "esp32_task_0", 2 * 1024, this, 10, &m_taskHandle);
}

void Esp32RtosTask::stop() {
    if (m_taskHandle == nullptr) {
        return;
    }
    xTaskHandle handle = m_taskHandle;
    ::vTaskDelete(handle);
    m_taskHandle = nullptr;
}

void Esp32RtosTask::runTask(void* const pvParameters) {
    Esp32RtosTask* pTask = (Esp32RtosTask*) pvParameters;
    pTask->run(pTask->m_taskData);
    pTask->stop();
}

void Esp32RtosTask::delay(const int ms) const {
    ::vTaskDelay(ms / portTICK_PERIOD_MS);
}
