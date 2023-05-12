package com.wechattool.wechattool.controller;

import com.wechattool.wechattool.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author maxiao
 */
@RestController
@RequestMapping("/")
public class AddressController {

    @Autowired
    AddressService addressService;



    @GetMapping("/address")
    public ResponseEntity showAddress() {
        return ResponseEntity.ok(addressService.getServiceAddress());
    }
}
