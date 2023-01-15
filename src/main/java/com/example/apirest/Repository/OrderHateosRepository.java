package com.example.apirest.Repository;

import com.example.apirest.Entity.OrderHateoas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHateosRepository extends JpaRepository<OrderHateoas, Long> {

}

