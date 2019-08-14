package org.cool.zoo.repositories;

import org.cool.zoo.entities.token.OauthClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailRepo extends JpaRepository<OauthClientDetail, Long> {

    OauthClientDetail getById(Long id);
}
