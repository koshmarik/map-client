package org.koshenkova.converter;

import org.koshenkova.domain.MapPoint;
import org.koshenkova.view.MapPointView;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asus-pc on 04.12.2015.
 */
@Component
public class MapPointConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> convertiblePairs = new HashSet<>();
        convertiblePairs.add(new ConvertiblePair(MapPoint.class, MapPointView.class));
        convertiblePairs.add(new ConvertiblePair(MapPointView.class, MapPoint.class));
        return convertiblePairs;
    }

    @Override
    public Object convert(Object obj, TypeDescriptor sourceTypeDescriptor, TypeDescriptor targetTypeDescriptor) {
        if(MapPoint.class.equals(sourceTypeDescriptor.getType()) && MapPointView.class.equals(targetTypeDescriptor.getType()))
           return toMapPointView((MapPoint) obj);
        if(MapPointView.class.equals(sourceTypeDescriptor.getType()) && MapPoint.class.equals(targetTypeDescriptor.getType()))
            return toMapPoint((MapPointView) obj);
        return null;
    }

    private MapPointView toMapPointView(MapPoint obj) {
        MapPointView mapPointView = new MapPointView();
        mapPointView.setId(obj.getId());
        mapPointView.setShortName(obj.getName());
        mapPointView.setLatitude(obj.getLatitude());
        mapPointView.setLongitude(obj.getLongitude());
        mapPointView.setLink(obj.getLink());
        return mapPointView;
    }

    private MapPoint toMapPoint(MapPointView obj) {
        MapPoint mapPoint = new MapPoint();
        mapPoint.setId(obj.getId());
        mapPoint.setName(obj.getShortName());
        mapPoint.setLatitude(obj.getLatitude());
        mapPoint.setLongitude(obj.getLongitude());
        mapPoint.setLink(obj.getLink());
        return mapPoint;
    }
}
