package com.congla.codehqtest.testproject.service;

import com.congla.codehqtest.testproject.data.model.DeviceData;
import com.congla.codehqtest.testproject.data.model.DeviceDetail;
import com.congla.codehqtest.testproject.data.model.DeviceTemperature;
import com.congla.codehqtest.testproject.data.repository.DeviceDataRepository;
import com.congla.codehqtest.testproject.data.repository.DeviceDetailRepository;
import com.congla.codehqtest.testproject.data.repository.DeviceTemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class DeviceDataService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private DeviceDetailRepository deviceDetailRepository;

    @Autowired
    private DeviceTemperatureRepository deviceTemperatureRepository;

    public DeviceData getDeviceDataV2(String deviceId) {
        Set<DeviceDetail> details = this.deviceDetailRepository.findByDeviceId(deviceId);
        for (DeviceDetail detail : details) {
            detail.setTemperature(this.deviceTemperatureRepository.findByDetailId(detail.getDetailId()));
        }

        DeviceData deviceData = this.deviceDataRepository.findByDeviceId(deviceId);
        deviceData.setData(details);

        return deviceData;
    }

    @Cacheable("devices")
    public DeviceData getDeviceData(String deviceId) {
        return this.deviceDataRepository.findByDeviceId(deviceId);
    }

    @Transactional
    public DeviceData saveDeviceData(DeviceData deviceData) {
        this.saveDeviceDetail(deviceData.getDeviceId(), deviceData.getData());
        return this.deviceDataRepository.save(deviceData);
    }

    @Transactional
    public void saveDeviceDetail(String deviceId, Set<DeviceDetail> details) {
        for (DeviceDetail detail : details) {
            detail.setDeviceId(deviceId);
            DeviceDetail newDetail = this.deviceDetailRepository.save(detail);
            this.saveDeviceTemperature(newDetail.getDetailId(), detail.getTemperature());
        }
    }

    @Transactional
    public void saveDeviceTemperature(int detailId, DeviceTemperature deviceTemperature) {
        deviceTemperature.setDetailId(detailId);
        this.deviceTemperatureRepository.save(deviceTemperature);
    }

}
