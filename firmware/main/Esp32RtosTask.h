/*
 * Esp32RtosTask.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_ESP32RTOSTASK_H_
#define MAIN_ESP32RTOSTASK_H_

#include <freertos/FreeRTOS.h>
#include <freertos/task.h>

class Esp32RtosTask {

    public:
        Esp32RtosTask(const char* taskName);
        virtual ~Esp32RtosTask();

        void delay(const int ms) const;
        void start(void *data);
        void stop();

        virtual void run(void *data) = 0;

    private:
        void* m_taskData;
        TaskHandle_t m_taskHandle;
        static void runTask(void *data);
};

#endif /* MAIN_ESP32RTOSTASK_H_ */
