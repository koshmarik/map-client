package org.koshenkova.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by asus-pc on 29.11.2015.
 */
@Entity
@Data
public class MapPoint implements Serializable{

    private static final long serialVersionUID = 2875104633140786713L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Double latitude;
    @Column
    private Double longitude;

    @Column
    private String link;

}
