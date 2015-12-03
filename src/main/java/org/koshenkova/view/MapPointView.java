package org.koshenkova.view;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

/**
 * Created by asus-pc on 03.12.2015.
 */
public class MapPointView implements Serializable {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String shortName;
    private String description;
}
