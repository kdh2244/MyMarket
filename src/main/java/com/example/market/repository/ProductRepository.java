package com.example.market.repository;

import com.example.market.entity.Product;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM product p " +
            "WHERE ST_Distance_Sphere(p.location, :userLocation) <= :radiusInMeters"
                + " AND p.name LIKE CONCAT('%',:keyword,'%')"
                    + " ORDER BY p.id DESC", nativeQuery = true)
    List<Product> findProductsWithinRadius(
            Point userLocation ,
            double radiusInMeters,
            String keyword
    );
}
