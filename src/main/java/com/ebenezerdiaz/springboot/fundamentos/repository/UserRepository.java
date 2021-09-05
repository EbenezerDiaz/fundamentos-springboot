package com.ebenezerdiaz.springboot.fundamentos.repository;

import com.ebenezerdiaz.springboot.fundamentos.entity.UserApp;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {

    @Query("SELECT u FROM UserApp u WHERE u.email = ?1")
    public Optional<UserApp> findByUserEmail(String email);

    @Query("SELECT u FROM UserApp u WHERE u.name like ?1% ")
    List<UserApp> findAndSort(String name, Sort sort);

    List<UserApp> findByName(String name);

    List<UserApp> findByNameLike(String name);

    Optional<UserApp> findByNameAndEmail(String name, String email);
}
