package com.ahmetakkoyun.eurekaclient2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String getServiceInfo() {
        List<ServiceInstance> instances = discoveryClient.getInstances("Client_2");

        StringBuilder response = new StringBuilder("<html><body><h2>Service Instances</h2><ul>");

        for (ServiceInstance instance : instances) {
            response.append("<li><strong>URI:</strong> ").append(instance.getUri()).append("</li>");
            response.append("<li><strong>Port:</strong> ").append(instance.getPort()).append("</li>");
            response.append("<li><strong>Service ID:</strong> ").append(instance.getServiceId()).append("</li>");
            response.append("<li><strong>Host:</strong> ").append(instance.getHost()).append("</li>");
        }

        response.append("</ul></body></html>");

        return response.toString();
    }
}
