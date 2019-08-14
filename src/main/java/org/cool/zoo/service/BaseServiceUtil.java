package org.cool.zoo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Dang Dim
 * Date     : 18-Jan-18, 12:48 PM
 * Email    : d.dim@gl-f.com
 */

@Service
public interface BaseServiceUtil<T> {

    Page<T> findAll(Pageable pageable);

    T findById(Long id);

    T saveOrUpdate(T entity);

    void delete(Long id);

}

