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
		gameRepository.save(game);
		return game;
	}
		
	//me devuelve el game con el resultado de la suma de los dados by idgames
	//y lo guardo en el game
	public Game saveResultDicesInGame(Game game) {
		game.setResultDices(diceService.getResultadoDicesByGameId(game.getGameId()));
		gameRepository.save(game);
		return game;
	}
	
	//Miro si el juego esta ganado o perdido-----------------------------
	public Game gameWinner(Game game) {
		Boolean winnerGame=false;
		if(game.getNumDices()==2) {
			if(game.getResultDices()==7) {
				game.setWinnerGame(true);	
			}else {
				game.setWinnerGame(false);
			}
		}
		if(game.getNumDices()==6) {
			List<Dice> dices = new ArrayList<>();
			diceService.getAllDicesbyId(game.getGameId());
			int counter5 = 0;
			int counter6 = 0;
			
			for(int i=0; i<dices.size(); i++) {
				Dice dice=dices.get(i);
				if(dice.getDiceFace()==5){
					counter5++;
				}
				if(dice.getDiceFace()==6) {
					counter6++;
				}
			}
			if(counter5==6 || counter6==6) {
				game.setWinnerGame(true);
			}else {
				game.setWinnerGame(false);
			}
		}
		game.getWinnerGame();
			System.out.println(game.getWinnerGame());
		return game;
	}
		
	//salvo a game: PUT----------------------------------------------
	public Game putGame(Game game) {
		return gameRepository.save(game);
	}
	
	//Creo el succesRate con todos los games x playerId
	public Double createSuccesRate(Long playerId) {
		List<Game> games = new ArrayList<>();
		games = gameRepository.findByPlayerPlayerId(playerId);//me devuelve una lista de games
		int contador=0;
		double succesRate = 0.0;
		for(int i=0; i<games.size(); i++) {
			if(games.get(i).getWinnerGame()==true) {
				contador++;
			}else {contador=0;}
		}
		succesRate = (contador*100)/games.size();
		return succesRate;
	}
	

	//Delete all games by playerId: GET------------------------------------
	public void deleteGamesByPlayerId(Long playerId){
		try{
			Player player = playerService.getPlayerById(playerId);
			List<Game> games = player.getGames();
			player.setGames(games);
			for (int i = 0; i < games.size(); i++) {
				Game g = games.get(i);
				Long gameId = g.getGameId();
				diceService.deleteDices(gameId);
				gameRepository.deleteById(gameId);
			}
		}catch (Exception e) {
		    System.out.println ("El error es: " + e.getMessage());
		    e.printStackTrace();
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
	
	//Ask all games: GET---------------------private-------------------------
	public List<Game> getAllGames(){
		return (List<Game>) gameRepository.findAll();	
	}
	
}
