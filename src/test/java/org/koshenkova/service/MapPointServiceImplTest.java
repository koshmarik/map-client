package org.koshenkova.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.koshenkova.MapClientApplication;
import org.koshenkova.domain.MapPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by asus-pc on 06.12.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MapClientApplication.class)
public class MapPointServiceImplTest {

    @Autowired
    private MapPointService mapPointService;

    @Test
    public void testFindAllMapPoints() throws Exception {
        Page<MapPoint> mapPoints = mapPointService.findAllMapPoints(new PageRequest(1,5));
        assertNotNull(mapPoints);
        assertFalse(mapPoints.getSize() == 0);
    }

    @Test
    public void testFindMapPointById() throws Exception {
        MapPoint mapPoint = mapPointService.findMapPointById(1L);
        assertNotNull(mapPoint);
        assertEquals("Google", mapPoint.getName());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testSaveNewMapPoint() throws Exception {
        mapPointService.saveNewMapPoint(new MapPoint());
    }

    @Test
    public void testFindMapPointLinkById() throws Exception {
        String mapPointLink = mapPointService.findMapPointLinkById(1L);
        assertNotNull(mapPointLink);
        assertEquals("http://google.com", mapPointLink);
    }
}