package com.example.demo.Repository;

import com.example.demo.Entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Integer> {
    @Query("select t from TravelPackage t where t.travelPackageName = ?1")
    TravelPackage findTravelPackageByTravelPackageName(String travelPackageName);
}
