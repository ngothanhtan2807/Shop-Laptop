package com.devpro.repositories;

import com.devpro.entities.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImagesRepo extends JpaRepository<ProductImages, Integer> {

}
