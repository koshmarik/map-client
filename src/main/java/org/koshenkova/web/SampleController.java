package org.koshenkova.web;

import org.koshenkova.converter.PageConverter;
import org.koshenkova.domain.MapPoint;
import org.koshenkova.service.MapPointService;
import org.koshenkova.view.MapPointView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

/**
 * Created by asus-pc on 29.11.2015.
 */
@Controller
@RequestMapping("/map_point")
public class SampleController {

    @Autowired
    private MapPointService mapPointService;
    @Autowired
    private ConversionService conversionService;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public MapPointView findPointById(@PathVariable("id") Long id) {
        MapPoint mapPoint = mapPointService.findMapPointById(id);
        return conversionService.convert(mapPoint, MapPointView.class);
    }

    @RequestMapping(value = "/{id}/link",method = RequestMethod.GET)
    @ResponseBody
    public String findLinkMapPointById(@PathVariable("id") Long id) {
        return mapPointService.findMapPointLinkById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<MapPointView> findAllPoints(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<MapPoint> mapPoints = mapPointService.findAllMapPoints(new PageRequest(pageNumber, size));

        return PageConverter.convert(mapPoints).using(new Function<MapPoint, MapPointView>() {
            @Override
            public MapPointView apply(MapPoint mapPoint) {
                return conversionService.convert(mapPoint, MapPointView.class);
            }
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MapPointView addPoint(MapPointView mapPointView) {
        MapPoint mapPoint = conversionService.convert(mapPointView, MapPoint.class);
        mapPoint = mapPointService.saveNewMapPoint(mapPoint);
        return conversionService.convert(mapPoint, MapPointView.class);
    }

}