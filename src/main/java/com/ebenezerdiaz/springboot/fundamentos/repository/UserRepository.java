package com.ebenezerdiaz.springboot.fundamentos.repository;

import com.ebenezerdiaz.springboot.fundamentos.dto.UserDto;
import com.ebenezerdiaz.springboot.fundamentos.entity.UserApp;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    List<UserApp> findByNameLikeOrderByIdDesc(String name);

    @Query("SELECT new com.ebenezerdiaz.springboot.fundamentos.dto.UserDto(u.id, u.email, u.birthDate) " +
            " FROM UserApp u " +
            " WHERE u.birthDate =:parametroFecha " +
            " AND u.email =:parametroEmail")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate birthdate,
                                                @Param("parametroEmail") String email);
}
