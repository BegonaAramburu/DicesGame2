package com.exercice2.DicesGame2.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.exercice2.DicesGame2.Domains.Dice;
import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Domains.Player;
import com.exercice2.DicesGame2.Repositories.GameRepository;
import com.exercice2.DicesGame2.Repositories.PlayerRepository;

@Service
public class GameService {
	
	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	DiceService diceService;
	
	//Create a game: POST----------------------------------------------
	public Game postGame(Integer numDices, Long playerId) {
		Player player = playerService.getPlayerById(playerId);
		Game game = new Game(numDices, player);
		return gameRepository.save(game);
	}
		
	//Dices play
	public Game dicesPlay(Long gameId) {
		Game game = getGameById(gameId);
		ArrayList<Dice> dices = diceService.postDice(gameId);
		Integer resultDices = diceService.getResultDicesByGameId(gameId);
		Boolean winnerGame = diceService.gameWinner(gameId);
		return gameRepository.save(game);
	}

	//Delete all games by playerId: GET------------------------------------
	public void deleteGamesByPlayerId(Long playerId){
		Player player = playerService.getPlayerById(playerId);
		List<Game> games = player.getGames();
		for (int i = 0; i < games.size(); i++) {
			Game g = games.get(i);
			gameRepository.delete(g);
		}
	}

	//Ask a game by Id: GET----------------private------------------------------
	public Game getGameById(Long gameId) {
		Game game = null;
		Optional<Game> gameOptional = gameRepository.findById(gameId);
		if(gameOptional.isPresent()){
			game = gameOptional.get();
		}else {
			System.out.println("Game not found");
		}
		return game;
	}
	
}
