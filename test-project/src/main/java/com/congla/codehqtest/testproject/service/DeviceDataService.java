package com.congla.codehqtest.testproject.service;

import com.congla.codehqtest.testproject.data.model.DeviceData;
import com.congla.codehqtest.testproject.data.model.DeviceDetail;
import com.congla.codehqtest.testproject.data.model.DeviceInfo;
import com.congla.codehqtest.testproject.data.repository.DeviceDataRepository;
import com.congla.codehqtest.testproject.data.repository.DeviceDetailRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;

@Service
public class DeviceDataService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private DeviceDetailRepository deviceDetailRepository;

    @Cacheable("devices")
    public DeviceInfo<java.util.Map<String, Object>> getDeviceData(String deviceId) {
        DeviceInfo<java.util.Map<String, Object>> deviceInfo = new DeviceInfo<>();

        DeviceData deviceData = this.deviceDataRepository.findByDeviceId(deviceId);
        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setLatitude(deviceData.getLatitude());
        deviceInfo.setLongitude(deviceData.getLongitude());

        DeviceDetail deviceDetail = this.deviceDetailRepository.findByDeviceId(deviceId);
        String detailString = deviceDetail.getDetail().replace("=", ":");
        JSONObject detailJson = new JSONObject(detailString);
        deviceInfo.setData(detailJson.toMap());

        return deviceInfo;
    }

    @Transactional
    public LinkedHashMap<String, Object> saveDeviceData(DeviceInfo deviceInfo) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        String deviceId = deviceInfo.getDeviceId();

        if (this.deviceDataRepository.findByDeviceId(deviceId) == null) {
            DeviceData deviceData = DeviceData.builder()
                    .deviceId(deviceId)
                    .latitude(deviceInfo.getLatitude())
                    .longitude(deviceInfo.getLongitude())
                    .build();
            result.put("device", this.deviceDataRepository.save(deviceData));

            DeviceDetail deviceDetail = DeviceDetail.builder()
                    .deviceId(deviceId)
                    .detail(deviceInfo.getData().toString())
                    .build();
            result.put("detail", this.deviceDetailRepository.save(deviceDetail));
        }
        else {
            result.put("Error:", String.format("A device with the same ID (%1$s) was added. Please check your data.", deviceId));
        }

        return result;
    }

}
