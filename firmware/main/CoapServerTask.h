/*
 * CoapServerTask.h
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#ifndef MAIN_COAPSERVERTASK_H_
#define MAIN_COAPSERVERTASK_H_

#include "coap.h"

#include "Esp32RtosTask.h"

class CoapServerTask: public Esp32RtosTask {

    public:
        CoapServerTask(const char* taskName);
        virtual ~CoapServerTask();

        void run(void* data) override;

        static void send_async_response(coap_context_t* ctx, const coap_endpoint_t* local_if);
        static void async_handler(coap_context_t* ctx, struct coap_resource_t* resource,
                const coap_endpoint_t* local_interface, coap_address_t* peer, coap_pdu_t* request, str* token,
                coap_pdu_t* response);
};

#endif /* MAIN_COAPSERVERTASK_H_ */
