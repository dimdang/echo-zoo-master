package org.cool.zoo.repositories;

import org.cool.zoo.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepo extends JpaRepository<Audit, Long> {
}
