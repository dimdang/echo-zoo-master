package org.cool.zoo.repositories;

import org.cool.zoo.entities.users.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepo extends JpaRepository<Dealer, Long> {

    @Query(value = "select deal from  Dealer  deal where deal.dealerCode=:dealerCode")
    Dealer findByDealerCode(@Param("dealerCode") String dealerCode);

    @Query(value = "select deal from  Dealer  deal where deal.dealerId=:dealerId")
    Dealer findByDealerId(@Param("dealerId") Long dealerId);

    Dealer findByEmail(@Param("email") String email);

    @Query(value = "select deal from  Dealer  deal where deal.dealerCode=:dealerCode")
    List<Dealer> getByDealerCode(@Param("dealerCode") String dealerCode);

    @Query("select deal from Dealer  deal WHERE deal.email=:email and deal.dealerCode=:dealerCode and deal.mobile=:mobile")
    Dealer findByEmailAndMobileAndDealerCode(@Param("email") String email, @Param("dealerCode") String dealerCode, @Param("mobile") String mobile);


}
