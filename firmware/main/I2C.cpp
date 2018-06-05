/*
 * I2C.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include "driver/i2c.h"

#include "I2C.h"

I2C::I2C(HardwareConfig *config)
        : _config(config) {
}

I2C::~I2C() {
}

esp_err_t I2C::init(i2c_port_t port_num, i2c_mode_t mode, gpio_num_t sda_io_num, gpio_num_t scl_io_num,
        gpio_pullup_t sda_pullup_en, gpio_pullup_t scl_pullup_en, uint32_t clk_speed) {

    i2c_config_t conf;
    conf.mode = mode;
    conf.sda_io_num = sda_io_num;
    conf.scl_io_num = scl_io_num;
    conf.sda_pullup_en = sda_pullup_en;
    conf.scl_pullup_en = scl_pullup_en;
    conf.master.clk_speed = clk_speed;

    esp_err_t err = ::i2c_param_config(port_num, &conf);
    if (err != ESP_OK) {
        return err;
    }

    return ::i2c_driver_install(port_num, conf.mode, 0, 0, 0);
}
