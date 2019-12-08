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

import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Domains.Player;
import com.exercice2.DicesGame2.Services.GameService;
import com.exercice2.DicesGame2.Services.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private GameService gameService;
	
	//--------------------------------------Calls--------------------------------------------------------
	
	//Call for to have a playerId: POST--------------------------------
	@PostMapping("/players")
	public Long createPlayerId(@RequestBody Player player) {
		return playerService.postPlayerId(player.getPlayerName()); 
	}
	
	//Call for change namePlayer--------------------------------------
	@PutMapping("/players")
	public Player putPlayer(@RequestBody Player player) {
		return playerService.putPlayer(player);
	}
	
	//Call for to get all players--------------------------------------
	@GetMapping("/players")
	public List<Player> getAllPlayers(){
		return playerService.getAllPlayers();
	}

	//Call for to ask all games by player id---------------------------
	@GetMapping("/players/{playerId}/games")
	public Player getAllGamesByPlayerId(@PathVariable Long playerId){
		return playerService.createSuccesRate(playerId);
	}
		
	//Call for delete player by Id------------------------------------
	@DeleteMapping("/players/{playerId}")
	public String DeletePlayerById(@PathVariable Long playerId) {
		playerService.deletePlayerById(playerId);
		return "Player deleted";
	}

	//Call for to delete all games by player id------------------------
	@DeleteMapping("/players/{playerId}/games")
	public String DeleteAllGamesByPlayerId(@PathVariable Long playerId) {
		gameService.deleteGamesByPlayerId(playerId);
		return "Games deleted by player " + playerId;
	}
	
	//Call for average succes player ranking---------------------------
	@GetMapping("/players/ranking")
	public double getRankingSucces() {
		return playerService.getRankingSucces();
	}
	
	//Call the winner player-------------------------------------------
	@GetMapping("players/ranking/winner")
	public Player getWinnerPlayer() {
		return playerService.getWinnerPlayer();
	}
	
	//Call the loser player-------------------------------------------
	@GetMapping("players/ranking/loser")
	public Player getLoserPlayer() {
		return playerService.getLoserPlayer();
	}

}



