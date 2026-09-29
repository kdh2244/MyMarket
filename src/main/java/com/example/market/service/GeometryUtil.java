package com.example.market.service;

import org.locationtech.jts.geom.*;
import org.springframework.stereotype.Service;

@Service
public class GeometryUtil {

    private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),4326);

    public static Point createPoint(Double lat, Double lng){
        return geometryFactory.createPoint(new Coordinate(lng,lat));
    }

}
