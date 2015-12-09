package org.koshenkova.service;

import org.koshenkova.dao.MapPointRepository;
import org.koshenkova.domain.MapPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Realization interface interaction with points on the map
 *
 * Created by asus-pc on 29.11.2015.
 */
@Service
public class MapPointServiceImpl implements MapPointService {

    @Autowired
    private MapPointRepository mapPointRepository;

    @Override
    public Page<MapPoint> findAllMapPoints(Pageable pageable) {
        return mapPointRepository.findAll(pageable);
    }

    @Override
    public MapPoint findMapPointById(Long id) {
        return mapPointRepository.findOne(id);
    }

    @Override
    public MapPoint saveNewMapPoint(MapPoint mapPoint) {
        return mapPointRepository.save(mapPoint);
    }

    @Override
    public String findMapPointLinkById(Long id) {
        return mapPointRepository.findMapPointLinkById(id);
    }
}
