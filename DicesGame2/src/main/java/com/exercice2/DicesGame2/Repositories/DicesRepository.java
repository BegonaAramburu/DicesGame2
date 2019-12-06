package com.exercice2.DicesGame2.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.exercice2.DicesGame2.Domains.Dice;
import com.exercice2.DicesGame2.Domains.Game;

public interface DicesRepository extends JpaRepository<Dice, Long>{
	
	public List<Dice> findByGameGameId(Long gameId);

}
