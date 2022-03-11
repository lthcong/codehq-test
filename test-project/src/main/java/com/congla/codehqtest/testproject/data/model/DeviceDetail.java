package com.congla.codehqtest.testproject.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "device_detail")
public class DeviceDetail {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "detailId")
    private int detailId;

    @JsonIgnore
    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "humidity")
    private int humidity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detailId", referencedColumnName = "detailId", insertable = false, updatable = false)
    private DeviceTemperature temperature;
}
