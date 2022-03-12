package com.congla.codehqtest.testproject.service;

import com.congla.codehqtest.testproject.data.model.DeviceData;
import com.congla.codehqtest.testproject.data.model.DeviceDetail;
import com.congla.codehqtest.testproject.data.model.DeviceInfo;
import com.congla.codehqtest.testproject.data.repository.DeviceDataRepository;
import com.congla.codehqtest.testproject.data.repository.DeviceDetailRepository;
import com.congla.codehqtest.testproject.data.util.DateUtility;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceDataService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    private DeviceDetailRepository deviceDetailRepository;

    @Cacheable("devices")
    public LinkedHashMap getDeviceData(String deviceId, String timestamp) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        DeviceData deviceData = this.deviceDataRepository.findByDeviceId(deviceId);
        if (deviceData == null) {
            result.put("Device info", String.format("Cannot find any device with ID %1$s", deviceId));
        }
        else {
            DeviceInfo<java.util.Map<String, Object>> deviceInfo = new DeviceInfo<>();
            deviceInfo.setDeviceId(deviceId);
            deviceInfo.setLatitude(deviceData.getLatitude());
            deviceInfo.setLongitude(deviceData.getLongitude());
            deviceInfo.setData(new LinkedHashMap<>());
            result.put("device info", deviceInfo);

            List<DeviceDetail> details;
            if (timestamp == null) {
                details = this.deviceDetailRepository.findByDeviceId(deviceId);
            }
            else {
                details = this.deviceDetailRepository.findByDeviceIdAndTimestamp(deviceId, timestamp);
            }
            result.put("device details", details);
        }

        return result;
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
        }
        else {
            result.put("device", "Device is already exist.");
        }

        DeviceDetail deviceDetail = DeviceDetail.builder()
                .deviceId(deviceId)
                .detail(deviceInfo.getData().toString())
                .timestamp(DateUtility.getCurrentDateTime())
                .build();
        result.put("detail", this.deviceDetailRepository.save(deviceDetail));

        return result;
    }

}
