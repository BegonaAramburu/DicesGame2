package com.exercice2.DicesGame2.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Domains.Player;

public interface GameRepository extends JpaRepository<Game, Long>{
	
	public List<Game> findByPlayerPlayerId(Long playerId);
	public void deleteByPlayerPlayerId(Long playerId);
	
}
