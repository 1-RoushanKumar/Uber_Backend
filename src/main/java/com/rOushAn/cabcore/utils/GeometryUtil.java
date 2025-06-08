package com.rOushAn.cabcore.utils;

import com.rOushAn.cabcore.dtos.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;


// Utility class for geometric operations related to creating geometry objects like Point.
public class GeometryUtil {

    /**
     * Converts a PointDto (Data Transfer Object) into a JTS Point object.
     *
     * @param pointDto The PointDto containing coordinates (longitude and latitude) as a double array.
     * @return A JTS Point object constructed using the provided coordinates and WGS84 (SRID 4326).
     */
    public static Point createPoint(PointDto pointDto) {
        // Create a GeometryFactory with a specified precision model and SRID (Spatial Reference System Identifier).
        // SRID 4326 refers to WGS 84, a commonly used geographic coordinate system based on latitude and longitude.
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

        // Extract the coordinates from the PointDto.
        // It is assumed that coordinates[0] is the X value (longitude) and coordinates[1] is the Y value (latitude).
        Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0], pointDto.getCoordinates()[1]);

        // Use the GeometryFactory to create and return a Point object from the given coordinate.
        return geometryFactory.createPoint(coordinate);
    }
}

