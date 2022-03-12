package com.congla.codehqtest.testproject.controller;

import com.congla.codehqtest.testproject.data.model.DeviceData;
import com.congla.codehqtest.testproject.data.model.DeviceInfo;
import com.congla.codehqtest.testproject.service.DeviceDataService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping(path = "/api/devices")
public class DeviceController {

    @Autowired
    private DeviceDataService deviceDataService;

    @PostMapping()
    private LinkedHashMap save(@RequestBody DeviceInfo deviceInfo) {
        return this.deviceDataService.saveDeviceData(deviceInfo);
    }

    @GetMapping(path = "/{deviceId}")
    private LinkedHashMap get(@PathVariable String deviceId, @RequestParam(required = false) String timestamp) {
        return this.deviceDataService.getDeviceData(deviceId, timestamp);
    }


}
