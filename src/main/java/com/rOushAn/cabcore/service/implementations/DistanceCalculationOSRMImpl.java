package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.service.DistanceCalculationService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculationOSRMImpl implements DistanceCalculationService {

    @Override
    public double calculateDistance(Point src, Point des) {
        return 0.0;
    }
}