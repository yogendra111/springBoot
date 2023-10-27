package com.ksolves.services;

import com.ksolves.models.StoreModel;

import java.util.List;

public interface StoreService {
    void saveStore(StoreModel store);
    StoreModel getStoreById(Long storeId) throws RuntimeException;
    void deleteStoreById(Long storeId) throws RuntimeException;
    List<StoreModel> getAllStore();
    StoreModel updateStore(Long storeId, StoreModel storeModel) throws RuntimeException;

}
