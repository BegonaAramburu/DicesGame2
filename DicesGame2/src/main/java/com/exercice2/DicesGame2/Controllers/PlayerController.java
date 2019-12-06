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
	//Create player only with name
	@PostMapping("/players/{playerName}")
	public Player postPlayer(@PathVariable String playerName) {
		return playerService.postPlayer(playerName);
	}

	//Call for change namePlayer------------------------------------
	@PutMapping("/players")
	public Player putPlayer(@RequestBody Player player) {
		playerService.putPlayer(player);
		return player;
	}
	
	//Call for to ask all games by player id------------------------------------
	@GetMapping("/players/{playerId}/games")
	public Player getAllGamesByPlayerId(@PathVariable Long playerId){
		Player player = playerService.getPlayerById(playerId);
		player.getGames();
		player.setSuccesRate(gameService.createSuccesRate(playerId));
		player.getSuccesRate();
		System.out.println();
		playerService.putPlayer(player);
		return player;
	}

	//Call for to delete all games by player id------------------------------------
	@DeleteMapping("/players/{playerId}/games")
	public String DeleteAllGamesByPlayerId(@PathVariable Long playerId) {
		gameService.deleteGamesByPlayerId(playerId);
		return "Games deleted by player " + playerId;
	}

	//Call for to get all players------------------------------------
	@GetMapping("/players")
	public List<Player> getAllPlayers(){
		return playerService.getAllPlayers();
	}
	
	//Call for average succes player ranking------------------------------
	@GetMapping("/players/ranking")
	public double getRankingSucces() {
		return playerService.getRankingSucces();
	}
	
	//Call the winner player
	@GetMapping("players/ranking/winner")
	public Player getWinnerPlayer() {
		return playerService.getWinnerPlayer();
	}
	
	//Call the loser player
		@GetMapping("players/ranking/loser")
		public Player getLoserPlayer() {
			return playerService.getLoserPlayer();
		}
		
	
	
	
	
	
	
	
	//Call for to get a player by playerId------------------------------------
	/*@GetMapping("/players/{playerId}")
	public Optional<Player> getPlayerById(@PathVariable Long playerId) {
		return playerService.getPlayerById(playerId);
	}*/
	
	

	
	//Call for delete player by Id------------------------------------
	@DeleteMapping("/players/{playerId}")
	public String DeletePlayerById(@PathVariable Long playerId) {
		playerService.deletePlayerById(playerId);
		return "Player deleted";
	}
	
	
	
	
}



