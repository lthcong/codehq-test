package com.congla.codehqtest.testproject.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "device_data")
public class DeviceData {

    @Id
    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;
}
