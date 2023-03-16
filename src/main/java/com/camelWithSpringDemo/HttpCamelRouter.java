package com.camelWithSpringDemo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.FileComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpCamelRouter extends RouteBuilder {

    @Autowired
    MyBean myBean;

    @Override
    public void configure() throws Exception {
        from("timer://foo?period={{myPeriod}}").routeId("httpRoute")
                .to("http://httpforever.com?httpMethod=GET")
                .bean(myBean, "saySomething")
                .to("file:target/webpage");

        from("direct:start").routeId("springEndpointBridge")
                .to("http://localhost:8080/api/v1/reply?httpMethod=GET");
    }
}
