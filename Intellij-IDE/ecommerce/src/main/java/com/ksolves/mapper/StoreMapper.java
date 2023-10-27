package com.ksolves.mapper;

import com.ksolves.entities.Store;
import com.ksolves.models.StoreModel;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public static Store convertStoreModelToStore(StoreModel storeModel){
        Store store = new Store();
        store.setId(storeModel.getId());
        store.setName(storeModel.getName());
        store.setLocation(LocationMapper.convertLocationModelToLocation(storeModel.getLocation()));
        return store;
    }

    public static StoreModel convertStoreToStoreModel(Store store){
        StoreModel storeModel = new StoreModel();
        storeModel.setId(store.getId());
        storeModel.setName(store.getName());
        storeModel.setLocation(LocationMapper.convertLocationToLocationModel(store.getLocation()));
        return storeModel;
    }

}
