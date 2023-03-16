package com.camelWithSpringDemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQCamelRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:target/webpage?delete=true").routeId("FileToMQ")
                .log("File on target found...")
                .to("activemq:queue:HELLO.WORLD");

        from("activemq:queue:HELLO.WORLD").routeId("MQToFile")
                .log("Sending from Queue to target back...")
                .to("file:target/mq");
    }
}
