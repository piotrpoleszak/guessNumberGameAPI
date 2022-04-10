package com.poleszak.GuessGame.repository;

import com.poleszak.GuessGame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
