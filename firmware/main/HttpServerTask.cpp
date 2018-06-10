/*
 * HttpServerTask.cpp
 *
 * Copyright (C) 2018 [ A Legge Up ]
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

#include <lwip/sockets.h>
#include <esp_log.h>
#include <string.h>

#include "HttpServerTask.h"
#include "sdkconfig.h"

static char tag[] = "HttpServerTask";

HttpServerTask::HttpServerTask(const char* taskName)
        : Esp32RtosTask(taskName)
{
}

HttpServerTask::~HttpServerTask()
{
}

void HttpServerTask::run(void* data) {
    char buffer[1024] = { };

    while (1) {
        ESP_LOGW(tag, "HttpServerTask::run");
        delay(1000);

        struct sockaddr_in server_addr, client_addr;
        int server_sock;
        socklen_t sin_size = sizeof(client_addr);
        bzero(&server_addr, sizeof(struct sockaddr_in));
        server_addr.sin_family = AF_INET;
        server_addr.sin_addr.s_addr = htonl(INADDR_ANY);
        server_addr.sin_port = htons(80);

        server_sock = socket(AF_INET, SOCK_STREAM, 0);
        bind(server_sock, (struct sockaddr *) (&server_addr), sizeof(struct sockaddr));
        listen(server_sock, 5);
        for (;;) {
            int client_sock = accept(server_sock, (struct sockaddr *) &client_addr, &sin_size);
            if (client_sock > 0) {
                ESP_LOGW(tag, "ACCEPT");
                int res = recv(client_sock, (void*) buffer, sizeof(buffer) - 1, 0);
                if (res >= 3) {
                    ESP_LOG_BUFFER_HEXDUMP(tag, buffer, res, ESP_LOG_INFO);
                    const char get[] = "GET /";
                    char * pch;
                    pch = strtok(buffer, "\r\n");
                    while (pch != NULL) {
                        ESP_LOGW(tag, "%s", pch);
                        pch = strtok(NULL, "\r\n");
                    }
                    char* r = strstr(buffer, get);
                    if (r != NULL) {
                        ESP_LOGW(tag, "NOT NULL");
                    }
                    for (int i = 0; i < strlen(get); ++i) {
                        if (buffer[i] == get[i]) {
                            ESP_LOGW(tag, "MATCH %d", i);
                        } else {
                            ESP_LOGW(tag, "NO MATCH %d", i);
                        }
                    }
                } else {
                    ESP_LOGW(tag, "ERR");
                }
                write(client_sock, "HTTP/1.1 200 OK\n", 16);
                write(client_sock, "Content-length: 46\n", 19);
                write(client_sock, "Content-Type: text/html\n\n", 25);
                write(client_sock, "<html><body><H1>Hello world</H1></body></html>", 46);
                close(client_sock);
            }
        }
    }
}
