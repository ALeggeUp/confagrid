/*
 * HardwareConfig.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_HARDWARECONFIG_H_
#define MAIN_HARDWARECONFIG_H_

class HardwareConfig {

    public:
        enum Version {
            TEST, DEV0, ALPHA
        };

        HardwareConfig(Version version);
        virtual ~HardwareConfig();

    private:
        Version _version;
};

#endif /* MAIN_HARDWARECONFIG_H_ */
