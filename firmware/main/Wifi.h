/*
 * Wifi.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_WIFI_H_
#define MAIN_WIFI_H_

#include "esp_err.h"
#include "esp_wifi.h"

class Wifi {

    public:
        Wifi();
        virtual ~Wifi();
        void start();

        static esp_err_t eventHandler(void *ctx, system_event_t *event);
};

#endif /* MAIN_WIFI_H_ */
