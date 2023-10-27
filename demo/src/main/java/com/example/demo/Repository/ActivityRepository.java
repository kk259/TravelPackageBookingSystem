package com.example.demo.Repository;
import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    @Query("select a from Activity a where a.name = ?1")
    public Activity findActivityByActivityName(String name);
    @Query("select a from Activity a where a.destination = ?1")
    public Set<Activity> findActivitiesByItsDestination(Destination destination);
}
