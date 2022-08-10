package com.works.repositories;

import com.works.entities.ProJoinCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProJoinCatRepository extends JpaRepository<ProJoinCat, Integer> {

    @Query(value = "select p.pid, p.detail, p.price, p.title, c.title as ctitle, c.cid from PRODUCT as p\n" +
            "inner join PRODUCT_CATEGORIES PC on p.PID = PC.PRODUCTS_PID\n" +
            "inner join CATEGORY C on C.CID = PC.CATEGORIES_CID where c.cid = ?1", nativeQuery = true)
    List<ProJoinCat> joinList( int cid );

}