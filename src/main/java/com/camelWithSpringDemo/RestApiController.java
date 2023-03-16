package com.camelWithSpringDemo;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1")
public class RestApiController{

    final static String CAMEL_START_URI ="direct:start";

    @Autowired
    CamelContext camelContext;

    @Autowired
    @EndpointInject(uri=CAMEL_START_URI)
    ProducerTemplate producer;

    @GetMapping(value = "/request")
    public String requestMapping() {

        Exchange sendExchange = ExchangeBuilder.anExchange(camelContext).withBody("sampleText-Request").build();
        Exchange outExchange = producer.send(sendExchange);
        String outString = outExchange.getMessage().getBody(String.class);
        return outString;
    }

    @GetMapping(value = "/reply")
    public String replyMapping() {
        System.out.println("Replying.............");
        return "sampleText-reply";
    }

}
