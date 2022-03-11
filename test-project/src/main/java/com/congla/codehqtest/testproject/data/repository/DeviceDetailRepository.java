package com.congla.codehqtest.testproject.data.repository;

import com.congla.codehqtest.testproject.data.model.DeviceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetail, Integer> {

    public ArrayList<DeviceDetail> findByDeviceId(String deviceId);

    public DeviceDetail save(DeviceDetail deviceDetail);
}
