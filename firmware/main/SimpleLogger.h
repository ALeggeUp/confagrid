/*
 * SimpleLogger.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_SIMPLELOGGER_H_
#define MAIN_SIMPLELOGGER_H_

class SimpleLogger {

    public:
        SimpleLogger();
        virtual ~SimpleLogger();

        virtual void debug(const char* text) = 0;
};

#endif /* MAIN_SIMPLELOGGER_H_ */
