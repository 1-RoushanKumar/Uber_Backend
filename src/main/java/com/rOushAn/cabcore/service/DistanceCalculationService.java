package com.rOushAn.cabcore.service;

import org.locationtech.jts.geom.Point;

public interface DistanceCalculationService {

    double calculateDistance(Point src, Point des);
}