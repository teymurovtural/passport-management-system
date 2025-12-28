package com.human.passport.repositories;

import com.human.passport.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long>, JpaSpecificationExecutor<Passport> {
}
