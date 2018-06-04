/*
 * I2C.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_I2C_H_
#define MAIN_I2C_H_

#include <driver/gpio.h>

#include "HardwareConfig.h"

class I2C {

    public:
        I2C(HardwareConfig* config);
        virtual ~I2C();

        esp_err_t init(i2c_port_t port_num, i2c_mode_t mode, gpio_num_t sda_io_num, gpio_num_t scl_io_num, gpio_pullup_t sda_pullup_en, gpio_pullup_t scl_pullup_en, uint32_t clk_speed);

    private:
        HardwareConfig* _config;
};

#endif /* MAIN_I2C_H_ */
