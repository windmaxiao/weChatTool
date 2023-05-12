package com.wechattool.wechattool.controller;

import com.wechattool.wechattool.model.ServiceAddress;
import com.wechattool.wechattool.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxiao
 */
@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    @Autowired
    AddressService addressService;



    @GetMapping("/")
    public ResponseEntity<ServiceAddress> showAddress(@RequestParam("address") String address) {
        return ResponseEntity.ok(addressService.getServiceAddress());
    }
}
