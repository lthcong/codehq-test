package com.congla.codehqtest.testproject.data.model;

import lombok.*;

@NoArgsConstructor
@ToString
@Getter
@Setter
public class DeviceInfo<T> {

    private String deviceId;
    private float latitude;
    private float longitude;
    private T data;

}
