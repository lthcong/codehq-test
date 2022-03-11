package com.congla.codehqtest.testproject.controller;

import com.congla.codehqtest.testproject.data.model.DeviceData;
import com.congla.codehqtest.testproject.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/devices")
public class DeviceController {

    @Autowired
    private DeviceDataService deviceDataService;

    @PostMapping()
    private DeviceData save(@RequestBody DeviceData deviceData) {
        return this.deviceDataService.saveDeviceData(deviceData);
    }

    @GetMapping(path = "/{deviceId}")
    private DeviceData get(@PathVariable String deviceId) {
        return this.deviceDataService.getDeviceData(deviceId);
    }


}
