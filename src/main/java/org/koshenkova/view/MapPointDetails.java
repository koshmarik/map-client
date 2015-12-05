package org.koshenkova.view;

import lombok.Data;

/**
 * Created by asus-pc on 04.12.2015.
 */
@Data
public class MapPointDetails {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String shortName;
    private String description;

}
