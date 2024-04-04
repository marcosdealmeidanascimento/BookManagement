package com.example.bookmanagement.priceGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceGroupRepository extends JpaRepository<PriceGroup, Long> {
}
