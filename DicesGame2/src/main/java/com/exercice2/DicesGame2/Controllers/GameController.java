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
	
	@Autowired
	DiceService diceService;
	
	@Autowired
	PlayerService playerService;
	
	//--------------------------------------Calls--------------------------------------------------------
	
	//Call for to create a game------------------------------------
	@PostMapping("/players/{playerId}/games/{numDices}")
	public Game createGame(@PathVariable Integer numDices, @PathVariable Long playerId) {
		return gameService.postGame(numDices, playerId);	
	}
		
	//Call for play. ----------------------------------------------
	@PutMapping("/players/{playerId}/games")
	public Game playGame(@RequestBody Game game, @PathVariable Long playerId) {
		Player player = playerService.getPlayerById(playerId);
		game.setPlayer(player);
		diceService.postDice(game);//return a List of dices
		gameService.saveResultDicesInGame(game);//save result of dices sum
		gameService.gameWinner(game);//save winner o lost game
		gameService.putGame(game);//save game in the repository
		return game;//return a complet game
	}
	
	//Call for to ask a game by Id-----------Private-------------------------
	@GetMapping("/games/{gameId}")
	public Game getGameById(@PathVariable Long gameId) {
		return gameService.getGameById(gameId);
	}
	
	//Call for to ask all games-----------PARA NADA-------------------------
	@GetMapping("/games")
	public List<Game> getAllGames(){
		return gameService.getAllGames();
	}
	
}



