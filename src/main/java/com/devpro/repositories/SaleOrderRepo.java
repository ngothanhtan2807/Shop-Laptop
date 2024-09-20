package com.devpro.repositories;

import com.devpro.entities.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOrderRepo extends JpaRepository<SaleOrder, Integer> {

}
