package com.target.system.repository;

import java.util.List;

import com.target.system.entity.TargetModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends CrudRepository<TargetModel, Long> {
    
//    List<TargetModel> findByName(String userID);
    
}
