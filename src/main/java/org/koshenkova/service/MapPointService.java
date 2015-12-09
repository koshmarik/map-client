package org.koshenkova.service;

import org.koshenkova.domain.MapPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface to describe manipulation of the points on the map
 *
 * Created by asus-pc on 29.11.2015.
 */
public interface MapPointService {
    /** find app map points by page */
    Page<MapPoint> findAllMapPoints(Pageable pageable);
    /** find point by id */
    MapPoint findMapPointById(Long id);
    /** save new map point */
    MapPoint saveNewMapPoint(MapPoint mapPoint);
    /** find map point link by map pint id */
    String findMapPointLinkById(Long id);
}
