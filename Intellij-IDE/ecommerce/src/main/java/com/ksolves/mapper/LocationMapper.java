package com.ksolves.mapper;

import com.ksolves.entities.Location;
import com.ksolves.entities.Location;
import com.ksolves.models.LocationModel;
import com.ksolves.models.LocationModel;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public static Location convertLocationModelToLocation(LocationModel locationModel){
        Location location = new Location();
        location.setId(location.getId());
        location.setStreet(locationModel.getStreet());
        location.setCity(locationModel.getCity());
        location.setState(locationModel.getState());
        location.setPincode(locationModel.getPincode());
        return location;
    }

    public static LocationModel convertLocationToLocationModel(Location location){
        LocationModel locationModel = new LocationModel();
        locationModel.setId(location.getId());
        locationModel.setStreet(location.getStreet());
        locationModel.setCity(location.getCity());
        locationModel.setState(location.getState());
        locationModel.setPincode(location.getPincode());
        return locationModel;
    }

}
