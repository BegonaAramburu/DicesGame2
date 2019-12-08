package com.exercice2.DicesGame2.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Domains.Player;
import com.exercice2.DicesGame2.Repositories.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private GameService gameService;
	
	//Create a playerId with a playerName:POST------------------------------
	public Long postPlayerId(String playerName) {
		Player player = null;
		if(playerName=="") {
			player = new Player("anonymous");
			playerRepository.save(player);	
		}else {
			List<Player> players = new ArrayList<>();
			player = new Player(playerName);
			players = playerRepository.findByPlayerName(playerName);
			if(players.size() >= 1) {
				System.out.println("This name already exists");
			}else {
				playerRepository.save(player);
			}
		}
		return player.getPlayerId();
	}
	
	//Change namePlayer-------------------------------------------------------
	public Player putPlayer(Player player) {
		
		if(player.getPlayerName()=="") {
			player.setPlayerName("anonymous");
			playerRepository.save(player);
		}else {
			List<Player> players = new ArrayList<>();
			players = playerRepository.findByPlayerName(player.getPlayerName());
			if(players.size() == 1) {
				System.out.println("This name already exists");
			}else {
				playerRepository.save(player);
			}	
		}
		return player;
	}
	
	//Get all Players---------------------------------------------------------
	public List<Player> getAllPlayers(){
		return (List<Player>) playerRepository.findAll();
	}
		
	//Get Player by playerId--------------------------------------------------
	public Player getPlayerById(Long playerId) {
		Player player = null;
		Optional<Player> playerOptional = playerRepository.findById(playerId);
		if(playerOptional.isPresent()) {
			player = playerOptional.get();
		}else {
			System.out.println("Player not found");
		}
		return player;
	}
	
	//Get succesRate of games by playerId-------------------------------------
	public Player createSuccesRate(Long playerId) {
		Player player = getPlayerById(playerId);
		List<Game> games = player.getGames();
		int contador=0;
		double succesRate = 0.0;
		for(int i=0; i<games.size(); i++) {
			if(games.get(i).getWinnerGame()==true) {
				contador++;
			}
		}
		succesRate = (contador*100)/games.size();
		player.setSuccesRate(succesRate);
		playerRepository.save(player);
		return player;
	}
	
	//Delete player by Id-----------------------------------------------------
	public void deletePlayerById(Long playerId) {
		Player player = getPlayerById(playerId);
		playerRepository.delete(player);
	}
	
	//Average suuces rate players---------------------------------------------
	public double getRankingSucces() {
		List<Player> players = getAllPlayers();
		int counter = 0;
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			counter += p.getSuccesRate(); 
		}
		double averagesucces = counter/players.size();
		return averagesucces;
	}
	
	//winner player-----------------------------------------------------------
	public Player getWinnerPlayer() {
		List<Player> players = getAllPlayers();
		Player winnerPlayer = players.get(0);
		double winnerSucces = winnerPlayer.getSuccesRate();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			double succesRate = player.getSuccesRate();
			if(succesRate > winnerSucces) {
				winnerPlayer = player;
			}
		}
		return winnerPlayer;
	}
	
	//loser player-----------------------------------------------------------
	public Player getLoserPlayer() {
		List<Player> players = getAllPlayers();
		Player loserPlayer = players.get(0);
		double loserSucces = loserPlayer.getSuccesRate();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			double succesRate = player.getSuccesRate();
			if(succesRate < loserSucces) {
				loserPlayer = player;
			}
		}
		return loserPlayer;
	}
	
}
