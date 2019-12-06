package com.exercice2.DicesGame2.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Domains.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

	public List<Player> findByPlayerName(String playerName);

	
	
	
	

}
