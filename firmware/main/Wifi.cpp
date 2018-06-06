/*
 * Wifi.cpp
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include "Wifi.h"

#include <string.h>

#include "nvs_flash.h"
#include "esp_wifi.h"
#include "esp_system.h"
#include "esp_event.h"
#include "esp_event_loop.h"

#include "sdkconfig.h"

Wifi::Wifi()
{
    nvs_flash_init();
    tcpip_adapter_init();
    ESP_ERROR_CHECK(esp_event_loop_init(Wifi::eventHandler, this));

    wifi_init_config_t cfg = WIFI_INIT_CONFIG_DEFAULT();
    ESP_ERROR_CHECK(esp_wifi_init(&cfg));
    ESP_ERROR_CHECK(esp_wifi_set_storage(WIFI_STORAGE_RAM));
    ESP_ERROR_CHECK(esp_wifi_set_mode(WIFI_MODE_AP));

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

    ESP_ERROR_CHECK(esp_wifi_set_config(WIFI_IF_AP, &ap_config));
}

Wifi::~Wifi()
{
}

esp_err_t Wifi::eventHandler(void *ctx, system_event_t *event) {
    return ESP_OK;
}

void Wifi::start() {
    esp_wifi_start();
}
