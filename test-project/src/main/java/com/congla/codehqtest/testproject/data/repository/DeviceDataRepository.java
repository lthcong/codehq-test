package com.congla.codehqtest.testproject.data.repository;

import com.congla.codehqtest.testproject.data.model.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDataRepository extends JpaRepository<DeviceData, String> {

    public DeviceData findByDeviceId(String deviceId);

    public DeviceData save(DeviceData deviceData);
}
