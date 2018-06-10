/*
 * AnimationTask.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_ANIMATIONTASK_H_
#define MAIN_ANIMATIONTASK_H_

#include "I2C.h"

#include "Esp32RtosTask.h"

class AnimationTask: public Esp32RtosTask {

    public:
        AnimationTask(const char* taskName, I2C* i2c);
        virtual ~AnimationTask();
        void run(void *data) override;

    private:
        I2C* m_i2c;
};

#endif /* MAIN_ANIMATIONTASK_H_ */
