package com.jygis.geocoding.GeoModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Setter
@Getter
public class JusoInfo implements Serializable {

    private String address;
    private double x;
    private double y;

}
