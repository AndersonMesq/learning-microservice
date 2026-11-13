package com.amv.book_service.environment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class InstanceInformationService implements ApplicationListener<WebServerInitializedEvent> {
    private String port;
    @Value("${HOSTNAME:LOCALHOST}")
    private String hostname;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.port = String.valueOf(event.getWebServer().getPort());
    }

    public String retrieveServerPort(){
        return port;
    }

    public String retrieveInstanceInfo() {
        return hostname.substring(hostname.length()-5);
    }
}
