package com.codermy.myspringsecurityplus.car.repository;

import com.codermy.myspringsecurityplus.car.entity.Gear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearRepository extends JpaRepository<Gear, Integer> {


}
