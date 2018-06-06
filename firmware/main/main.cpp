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

#include "HardwareConfig.h"
#include "AnimationTask.h"

#include "sdkconfig.h"

extern "C" esp_err_t event_handler(void *ctx, system_event_t *event)
{
    return ESP_OK;
}

extern "C" void app_main()
{
    nvs_flash_init();
    tcpip_adapter_init();
    ESP_ERROR_CHECK(esp_event_loop_init(event_handler, NULL));

    wifi_init_config_t cfg = WIFI_INIT_CONFIG_DEFAULT();
    ESP_ERROR_CHECK( esp_wifi_init(&cfg) );
    ESP_ERROR_CHECK( esp_wifi_set_storage(WIFI_STORAGE_RAM) );
    ESP_ERROR_CHECK( esp_wifi_set_mode(WIFI_MODE_AP) );

    wifi_config_t ap_config;
    const char* ssid = "esp32-ssid";
    strcpy(reinterpret_cast<char*>(ap_config.ap.ssid), ssid);
    ap_config.ap.channel = 0;
    ap_config.ap.ssid_len = strlen(ssid);
    ap_config.ap.ssid_hidden = 0;
    ap_config.ap.max_connection = 4;
    ap_config.ap.beacon_interval = 100;

    ap_config.ap.authmode = WIFI_AUTH_OPEN;
    *ap_config.ap.password = 0;

    ESP_ERROR_CHECK( esp_wifi_set_config(WIFI_IF_AP, &ap_config) );
    // ESP_ERROR_CHECK( esp_wifi_start() );
    // ESP_ERROR_CHECK( esp_wifi_connect() );

    HardwareConfig hardwareConfig(HardwareConfig::DEV0);
    AnimationTask *pAnimationTask = new AnimationTask("AnimationTask");
    pAnimationTask->start(0);
}
