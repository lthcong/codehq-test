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
@Table(name = "device_temperature")
public class DeviceTemperature {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @JsonIgnore
    @Column(name = "detailId")
    private int detailId;

    @Column(name = "unit")
    private String unit;

    @Column(name = "value")
    private String value;
}
