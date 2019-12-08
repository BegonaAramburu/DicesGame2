package com.exercice2.DicesGame2.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exercice2.DicesGame2.Domains.Dice;
import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Domains.Player;
import com.exercice2.DicesGame2.Services.DiceService;
import com.exercice2.DicesGame2.Services.GameService;
import com.exercice2.DicesGame2.Services.PlayerService;

@RestController
public class GameController {
	
	@Autowired
	GameService gameService;
		
	//--------------------------------------Calls--------------------------------------------------------
	
	//Call for to create a game------------------------------------
	@PostMapping("/players/{playerId}/games/{numDices}")
	public Game createGame(@PathVariable Integer numDices, @PathVariable Long playerId) {
		return gameService.postGame(numDices, playerId);	
	}
		
	//Call for play. ----------------------------------------------
	@PutMapping("/players/games/{gameId}")
	public Game playGame(@PathVariable Long gameId) {
		return gameService.dicesPlay(gameId);
	}

}



