/*
 * HttpServerTask.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_HTTPSERVERTASK_H_
#define MAIN_HTTPSERVERTASK_H_

#include "Esp32RtosTask.h"

class HttpServerTask: public Esp32RtosTask {

    public:
        HttpServerTask(const char* taskName);
        virtual ~HttpServerTask();

        void run(void *data) override;
};

#endif /* MAIN_HTTPSERVERTASK_H_ */
