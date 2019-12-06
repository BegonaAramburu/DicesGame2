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
	
	//Create a Player with playerName: POST----------------------------------
	public Player postPlayer(String playerName) {
		if(playerName=="") {
			playerName="anonymous";
			Player player = new Player("anonymous");
			playerRepository.save(player);
			System.out.println("Player registrated as anonymous");
			return player;
		}else {
			Player player = new Player(playerName);
			playerRepository.save(player);
			List<Player> sameName = new ArrayList<>();
			sameName = playerRepository.findByPlayerName(playerName);
			
			if(sameName.size()>1) {
				playerRepository.delete(player);
				System.out.println("This name already exists");
			}else {playerRepository.save(player);
			}
			return player;
		}
	}

	//Change namePlayer-------------------------------------------------------
	public Player putPlayer(Player player) {
		playerRepository.save(player);
		return player;
	}
		
	//Get by Id---------------------------------------------------------------
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

	//Get all Players---------------------------------------------------------
	public List<Player> getAllPlayers(){
		return (List<Player>) playerRepository.findAll();
	}
	
	//Delete player by Id-----------------------------------------------------
	public void deletePlayerById(Long playerId) {
		playerRepository.deleteById(playerId);
	}

	//Average suuces rate players---------------------------------------------
	public double getRankingSucces() {
		List<Player> players = getAllPlayers();
		int succesAdd = 0;
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			succesAdd += p.getSuccesRate(); 
		}
		double averagesucces = succesAdd/players.size();
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
