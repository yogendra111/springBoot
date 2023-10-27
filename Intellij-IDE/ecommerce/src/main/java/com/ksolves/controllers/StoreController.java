package com.ksolves.controllers;

import com.ksolves.models.StoreModel;
import com.ksolves.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping("/store")
    public ResponseEntity<?> saveStore(@Valid @RequestBody StoreModel storeModel){
        storeService.saveStore(storeModel);
        return new ResponseEntity<>("Store added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/store/{sid}")
    public ResponseEntity<?> getStore(@PathVariable("sid") Long storeId) throws RuntimeException {
        return ResponseEntity.ok().body(storeService.getStoreById(storeId));
    }

    @GetMapping("/store")
    public ResponseEntity<?> getAllStore(){
        return ResponseEntity.ok().body(storeService.getAllStore());
    }

    @DeleteMapping("/store/{sid}")
    public ResponseEntity<?> deleteStoreById(@PathVariable("sid") Long storeId) throws RuntimeException {
        storeService.deleteStoreById(storeId);
        return ResponseEntity.ok().body("Store deleted Successfully");
    }

    @PatchMapping("/store/{sid}")
    public ResponseEntity<?> updateStoreById(@PathVariable("sid") Long storeId, @RequestBody StoreModel storeModel) throws RuntimeException {
        return ResponseEntity.accepted().body(storeService.updateStore(storeId, storeModel));
    }

}
