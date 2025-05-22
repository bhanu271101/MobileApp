package com.example.memo.Service;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service

public class InfoService {

    @Value("${spring.application.name}")
    String applicationName;

    @Value("${server.port}")
    String portNumber;

    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public String getServer()
    {
        try {
            return InetAddress.getLocalHost().getHostAddress();
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to fetchh Ip";
        }
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }





}
