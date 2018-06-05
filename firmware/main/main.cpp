/*
 * main.cpp
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <stdio.h>

#include "HardwareConfig.h"
#include "AnimationTask.h"

extern "C" void app_main()
{
    HardwareConfig hardwareConfig(HardwareConfig::DEV0);
    AnimationTask *pAnimationTask = new AnimationTask("AnimationTask");
    pAnimationTask->start(0);
}
