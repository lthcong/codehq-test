package com.congla.codehqtest.testproject.data.repository;

import com.congla.codehqtest.testproject.data.model.DeviceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface DeviceDetailRepository extends JpaRepository<DeviceDetail, Integer> {

    public List<DeviceDetail> findByDeviceId(String deviceId);

    public List<DeviceDetail> findByDeviceIdAndTimestamp(String deviceId, String timestamp);

    public DeviceDetail save(DeviceDetail deviceDetail);
}
