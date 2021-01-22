package com.ordemservico.osworks.domain.repositoy;

import com.ordemservico.osworks.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findAllByEmail(String email);

}
