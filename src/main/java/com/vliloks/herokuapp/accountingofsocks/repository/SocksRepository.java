package com.vliloks.herokuapp.accountingofsocks.repository;

import com.vliloks.herokuapp.accountingofsocks.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Integer> {

    Optional<Socks> findByColorIgnoreCaseAndCottonPart(String color, int cottonPart);

    List<Socks> findAllByColorIgnoreCaseAndCottonPartIsGreaterThan(String color, int cottonPart);

    List<Socks> findAllByColorIgnoreCaseAndCottonPartIsLessThan(String color, int cottonPart);

    List<Socks> findAllByColorIgnoreCaseAndCottonPartEquals(String color, int cottonPart);

}