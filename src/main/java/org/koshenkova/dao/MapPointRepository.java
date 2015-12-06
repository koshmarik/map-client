package org.koshenkova.dao;

import org.koshenkova.domain.MapPoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Расширения CRUD для манипуляций с MapPoint
 *
 * Created by asus-pc on 29.11.2015.
 */
@Repository
public interface MapPointRepository extends CrudRepository<MapPoint, Long> {

    Page<MapPoint> findAll(Pageable pageable);

    @Query("select m.link from MapPoint m where m.id=?1")
    String findMapPointLinkById(Long id);

}
