package org.koshenkova.converter;

import org.koshenkova.domain.MapPoint;
import org.koshenkova.view.MapPointView;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Converting entity to view and view to entity
 *
 * Created by asus-pc on 04.12.2015.
 */
@Component
public class MapPointConverter {

    public MapPointView toMapPointView(MapPoint obj) {
        MapPointView mapPointView = new MapPointView();
        mapPointView.setId(obj.getId());
        mapPointView.setShortName(obj.getName());
        mapPointView.setLatitude(obj.getLatitude());
        mapPointView.setLongitude(obj.getLongitude());
        return mapPointView;
    }

    public MapPoint toMapPoint(MapPointView obj) {
        MapPoint mapPoint = new MapPoint();
        mapPoint.setId(obj.getId());
        mapPoint.setName(obj.getShortName());
        mapPoint.setLatitude(obj.getLatitude());
        mapPoint.setLongitude(obj.getLongitude());
        mapPoint.setLink(obj.getLink());
        return mapPoint;
    }
}
