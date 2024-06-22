package com.example.sonar.multimodule.repository;

import com.example.sonar.multimodule.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProdutoRepository extends JpaRepository<Produto, Long> {
}
