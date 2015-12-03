package org.koshenkova.web;

import org.koshenkova.domain.MapPoint;
import org.koshenkova.service.MapPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by asus-pc on 29.11.2015.
 */
@Controller
@RequestMapping("/map_point")
public class SampleController {

    @Autowired
    private MapPointService mapPointService;

    @RequestMapping(value = "/ping",method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "pong";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String findById(@PathVariable("id") Long id) {
        return mapPointService.findMapPointById(id).getName();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<MapPoint> findAll(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        return mapPointService.findAllMapPoints(new PageRequest(pageNumber, size));
    }

}