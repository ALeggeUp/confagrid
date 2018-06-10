/*
 * I2C.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include "I2C.h"

#define I2C_EXAMPLE_MASTER_NUM             I2C_NUM_1        /*!< I2C port number for master dev */
#define ESP_SLAVE_ADDR                     0x50             /*!< IS31FL3733 slave address */

#define IS31_REG_CR                        0xFD
#define IS31_REG_CRWL                      0xFE
#define IS31_REG_CRWL_EN                   0xC5

#define BH1750_SENSOR_ADDR                 0x23             /*!< slave address for BH1750 sensor */
#define BH1750_CMD_START                   0x23             /*!< Command to set measure mode */
#define WRITE_BIT                          I2C_MASTER_WRITE /*!< I2C master write */
#define READ_BIT                           I2C_MASTER_READ  /*!< I2C master read */
#define ACK_CHECK_EN                       0x1              /*!< I2C master will check ack from slave*/
#define ACK_CHECK_DIS                      0x0              /*!< I2C master will not check ack from slave */
#define ACK_VAL                            0x0              /*!< I2C ack value */
#define NACK_VAL                           0x1              /*!< I2C nack value */

I2C::I2C(HardwareConfig* config)
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

esp_err_t I2C::i2c_is31_init(i2c_port_t i2c_num) {
    esp_err_t ret = i2c_is31_change_page(i2c_num, 3);
    if (ret != ESP_OK) {
        return ret;
    }

    i2c_cmd_handle_t cmd = i2c_cmd_link_create();
    i2c_master_start(cmd);
    i2c_master_write_byte(cmd, (ESP_SLAVE_ADDR << 1) | WRITE_BIT, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, 0x00, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, 0x05, ACK_CHECK_EN);
    i2c_master_stop(cmd);
    ret = i2c_master_cmd_begin(i2c_num, cmd, 10000 / portTICK_RATE_MS);
    i2c_cmd_link_delete(cmd);

    if (ret != ESP_OK) {
        return ret;
    }
    vTaskDelay(300 / portTICK_RATE_MS);

    cmd = i2c_cmd_link_create();
    i2c_master_start(cmd);
    i2c_master_write_byte(cmd, (ESP_SLAVE_ADDR << 1) | WRITE_BIT, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, 0x01, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, 0xFF, ACK_CHECK_EN);
    i2c_master_stop(cmd);
    ret = i2c_master_cmd_begin(i2c_num, cmd, 10000 / portTICK_RATE_MS);
    i2c_cmd_link_delete(cmd);

    return ret;
}

esp_err_t I2C::i2c_is31_pwm(i2c_port_t i2c_num) {
    uint8_t* data = (uint8_t*) malloc(24);
    for (uint8_t i = 0; i < 24; ++i) {
        data[i] = 0xFF;
    }

    i2c_is31_change_page(I2C_EXAMPLE_MASTER_NUM, 1);
    vTaskDelay(300 / portTICK_RATE_MS);

    i2c_cmd_handle_t cmd = i2c_cmd_link_create();
    i2c_master_start(cmd);
    i2c_master_write_byte(cmd, ESP_SLAVE_ADDR << 1 | WRITE_BIT, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, 0x00, ACK_CHECK_EN);
    i2c_master_write(cmd, data, 24, ACK_CHECK_EN);
    i2c_master_stop(cmd);
    esp_err_t ret = i2c_master_cmd_begin(i2c_num, cmd, 1000 / portTICK_RATE_MS);
    i2c_cmd_link_delete(cmd);

    return ret;
}

esp_err_t I2C::i2c_is31_en(i2c_port_t i2c_num) {
    uint8_t* data = (uint8_t*) malloc(24);
    for (uint8_t i = 0; i < 24; ++i) {
        data[i] = 0xFF;
    }

    i2c_is31_change_page(I2C_EXAMPLE_MASTER_NUM, 0);
    vTaskDelay(300 / portTICK_RATE_MS);

    i2c_cmd_handle_t cmd = i2c_cmd_link_create();
    i2c_master_start(cmd);
    i2c_master_write_byte(cmd, ESP_SLAVE_ADDR << 1 | WRITE_BIT, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, 0x00, ACK_CHECK_EN);
    i2c_master_write(cmd, data, 24, ACK_CHECK_EN);
    i2c_master_stop(cmd);
    esp_err_t ret = i2c_master_cmd_begin(i2c_num, cmd, 1000 / portTICK_RATE_MS);
    i2c_cmd_link_delete(cmd);

    return ret;
}

esp_err_t I2C::i2c_is31_change_page(i2c_port_t i2c_num, uint8_t page) {
    i2c_cmd_handle_t cmd = i2c_cmd_link_create();
    i2c_master_start(cmd);
    i2c_master_write_byte(cmd, (ESP_SLAVE_ADDR << 1) | WRITE_BIT, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, IS31_REG_CRWL, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, IS31_REG_CRWL_EN, ACK_CHECK_EN);
    i2c_master_stop(cmd);
    esp_err_t ret = i2c_master_cmd_begin(i2c_num, cmd, 10000 / portTICK_RATE_MS);
    i2c_cmd_link_delete(cmd);

    if (ret != ESP_OK) {
        return ret;
    }
    vTaskDelay(300 / portTICK_RATE_MS);

    cmd = i2c_cmd_link_create();
    i2c_master_start(cmd);
    i2c_master_write_byte(cmd, (ESP_SLAVE_ADDR << 1) | WRITE_BIT, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, IS31_REG_CR, ACK_CHECK_EN);
    i2c_master_write_byte(cmd, page, ACK_CHECK_EN);
    i2c_master_stop(cmd);
    ret = i2c_master_cmd_begin(i2c_num, cmd, 10000 / portTICK_RATE_MS);

    i2c_cmd_link_delete(cmd);
    return ret;
}
