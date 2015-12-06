package org.koshenkova.service;

import org.koshenkova.domain.MapPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by asus-pc on 29.11.2015.
 */
public interface MapPointService {

    Page<MapPoint> findAllMapPoints(Pageable pageable);

    MapPoint findMapPointById(Long id);

    MapPoint saveNewMapPoint(MapPoint mapPoint);

    String findMapPointLinkById(Long id);
}
