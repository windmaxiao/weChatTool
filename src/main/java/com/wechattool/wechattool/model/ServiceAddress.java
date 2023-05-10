package com.wechattool.wechattool.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author maxiao
 */
@Data
@ToString
public class ServiceAddress {

    private String requestURI;

    private StringBuffer requestURL;

    private String contextPath;

    private String localAddr;

    private int localPort;

    private String localName;

    private String remoteAddr;

    private String remoteUser;

    private int remotePort;

    private String remoteHost;

    private String serverName;

    private int serverPort;

    private String realIp;

    private String host;

    private String forwardedFor;

    private String isNginx;


}
