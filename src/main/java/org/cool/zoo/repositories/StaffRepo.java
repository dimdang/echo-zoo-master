package org.cool.zoo.repositories;

import org.cool.zoo.entities.users.Role;
import org.cool.zoo.entities.users.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Dang Dim
 * Date     : 17-Jan-18, 2:02 PM
 * Email    : d.dim@gl-f.com
 */

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {

    Staff findByUsername(@Param("username") String username);

    Staff findByEmail(@Param("email") String email);

    Page<Staff> findAllByAuthoritiesEquals(@Param("authorities") Role authority, Pageable pageable);

    boolean existsByPassword(@Param("password") String password);

    Staff findByLoginId(@Param("loginId") String loginId);

}
