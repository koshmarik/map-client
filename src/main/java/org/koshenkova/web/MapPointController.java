package org.koshenkova.web;

import org.koshenkova.converter.MapPointConverter;
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
 * Controller for publishing REST services
 *
 * Created by asus-pc on 29.11.2015.
 */
@Controller
@RequestMapping("/map_point")
public class MapPointController {

    @Autowired
    private MapPointService mapPointService;
    @Autowired
    private MapPointConverter mapPointConverter;

    /** find map pint by id */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public MapPointView findPointById(@PathVariable("id") Long id) {
        MapPoint mapPoint = mapPointService.findMapPointById(id);
        return mapPointConverter.toMapPointView(mapPoint);
    }

    /** find link to the page for map point with id */
    @RequestMapping(value = "/{id}/link",method = RequestMethod.GET)
    @ResponseBody
    public String findLinkMapPointById(@PathVariable("id") Long id) {
        return mapPointService.findMapPointLinkById(id);
    }

    /** find map point by page with pageNumber and size*/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<MapPointView> findAllPoints(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        Page<MapPoint> mapPoints = mapPointService.findAllMapPoints(new PageRequest(pageNumber, size));

        return PageConverter.convert(mapPoints).using(mapPoint -> mapPointConverter.toMapPointView(mapPoint));
    }

    /** add new map point */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MapPointView addPoint(MapPointView mapPointView) {
        MapPoint mapPoint = mapPointConverter.toMapPoint(mapPointView);
        mapPoint = mapPointService.saveNewMapPoint(mapPoint);
        return mapPointConverter.toMapPointView(mapPoint);
    }

}