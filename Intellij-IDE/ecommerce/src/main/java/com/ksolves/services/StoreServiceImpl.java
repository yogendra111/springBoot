package com.ksolves.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksolves.entities.Location;
import com.ksolves.entities.Store;
import com.ksolves.exceptionhandler.StoreException;
import com.ksolves.mapper.LocationMapper;
import com.ksolves.mapper.StoreMapper;
import com.ksolves.models.StoreModel;
import com.ksolves.redis.CustomRedisSerializer;
import com.ksolves.repositories.StoreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService{

    static final String KEY = "Stores";

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    ObjectMapper mapper;

    private HashOperations<String, Long, Store> ops;

    @PostConstruct
    public void initialization() {
        CustomRedisSerializer<Store> serializer = new CustomRedisSerializer<Store>(mapper,Store.class);
        redisTemplate.setHashValueSerializer(serializer);
        ops = redisTemplate.opsForHash();
    }

    @Override
    public void saveStore(StoreModel storeModel) {
//        Store store = storeRepository.save(StoreMapper.convertStoreModelToStore(storeModel));
//        ops.put(KEY, store.getId(), store);
        for (int i = 0; i<1000; i++){
            Store store = new Store();
            store.setName(storeModel.getName() + i);

            Location location = new Location();
            location.setStreet(storeModel.getLocation().getStreet() + i);
            location.setCity(storeModel.getLocation().getCity() + i);
            location.setState(storeModel.getLocation().getState() + i);
            location.setPincode(storeModel.getLocation().getPincode() + i);
            store.setLocation(location);

            Store store1 = storeRepository.save(store);
            ops.put(KEY, store1.getId(), store1);
        }
    }

    @Override
    public StoreModel getStoreById(Long storeId) {
        StoreModel storeModel = null;
        if(ops.hasKey(KEY, storeId)) {
            storeModel = StoreMapper.convertStoreToStoreModel((Store) ops.get(KEY, storeId));
        }else{
//            if (!storeRepository.existsById(storeId)) {
                throw new StoreException("No Store Found");
//            }
//            Store store = storeRepository.findById(storeId).get();
//            ops.put(KEY, store.getId(), store);
//            storeModel = StoreMapper.convertStoreToStoreModel(store);
        }
        return storeModel;
    }

    @Override
    public void deleteStoreById(Long storeId) {
        if(storeRepository.existsById(storeId)){
            storeRepository.deleteById(storeId);
            ops.delete(KEY, storeId);
        }else{
            throw new RuntimeException("Store Not found to delete");
        }
    }

    @Override
    public List<StoreModel> getAllStore() {
        List<StoreModel> stores = null;
//        if(storeRepository.count()>ops.size(KEY)){
//            stores = storeRepository.findAll().stream()
//                    .map(store -> {
//                        ops.putIfAbsent(KEY, store.getId(), store);
//                        return StoreMapper.convertStoreToStoreModel(store);
//                    })
//                    .collect(Collectors.toList());
//        }else{
            stores = ops.entries(KEY).values()
                    .stream().map(store -> StoreMapper.convertStoreToStoreModel(store)).toList();
//        }
        return stores;
    }

    @Override
    public StoreModel updateStore(Long storeId, StoreModel storeModel) {
        if(!storeRepository.existsById(storeId)) {
            throw new RuntimeException("Store not Found to update.");
        }
        Store store = storeRepository.findById(storeId).get();
        store.setName(storeModel.getName());
        store.setLocation(LocationMapper.convertLocationModelToLocation(storeModel.getLocation()));
        Store updatedStore = storeRepository.save(store);
        return StoreMapper.convertStoreToStoreModel(updatedStore);
    }

}
