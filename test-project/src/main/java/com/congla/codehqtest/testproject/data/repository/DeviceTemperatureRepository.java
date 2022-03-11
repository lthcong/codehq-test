package com.congla.codehqtest.testproject.data.repository;

import com.congla.codehqtest.testproject.data.model.DeviceTemperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTemperatureRepository extends JpaRepository<DeviceTemperature, Integer>{

    public DeviceTemperature findByDetailId(int detailId);

    public DeviceTemperature save(DeviceTemperature deviceTemperature);
}
