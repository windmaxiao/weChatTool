package com.wechattool.wechattool.service.impl;

import com.wechattool.wechattool.model.ServiceAddress;
import com.wechattool.wechattool.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author maxiao
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    HttpServletRequest request;

    @Override
    public ServiceAddress getServiceAddress() {

        ServiceAddress address = new ServiceAddress();
        address.setRequestURI(request.getRequestURI());
        address.setRequestURL(request.getRequestURL());
        address.setContextPath(request.getContextPath());
        address.setLocalAddr(request.getLocalAddr());
        address.setLocalPort(request.getLocalPort());
        address.setLocalName(request.getLocalName());
        address.setRemoteAddr(request.getRemoteAddr());
        address.setRemoteUser(request.getRemoteUser());
        address.setRemotePort(request.getRemotePort());
        address.setRemoteHost(request.getRemoteHost());
        address.setServerName(request.getServerName());
        address.setServerPort(request.getServerPort());

        address.setRealIp(request.getHeader("X-Real-IP"));
        address.setHost(request.getHeader("Host"));
        address.setForwardedFor(request.getHeader("X-Forwarded-For"));
        address.setIsNginx(request.getHeader("X-NginX-Proxy"));

        return address;
    }

}
