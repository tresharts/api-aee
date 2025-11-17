package com.aee.sistema_aee.repository;

import com.aee.sistema_aee.entity.Aluno;
import com.aee.sistema_aee.entity.PDI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PDIRepository extends JpaRepository<PDI, Long>
{
    Optional<PDI> findByAlunoId(Long alunoId);
}
