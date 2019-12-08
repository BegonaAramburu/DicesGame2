package com.exercice2.DicesGame2.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercice2.DicesGame2.Domains.Dice;
import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Repositories.DicesRepository;

@Service
public class DiceService {
	
	@Autowired
	DicesRepository dicesRepository;
	
	@Autowired
	GameService gameService;
	
	//Post dices by gameId-------------------------------------------
	public ArrayList<Dice> postDice(Long gameId) { 
		Game game = gameService.getGameById(gameId);
		ArrayList<Dice> dices = new ArrayList<>();
		for (int i = 0; i < game.getNumDices(); i++) {
			Dice dice = new Dice(game);
			dices.add(dice); 
			dicesRepository.save(dice);
		}
		return dices;			
	}
		
	//Get result dices sum-------------------------------------------
	public Integer getResultDicesByGameId(Long gameId){
		Game game = gameService.getGameById(gameId);
		List<Dice> dices = game.getDices();
		int counter = 0;
		for (int i = 0; i < dices.size(); i++) {
			Dice dice=dices.get(i);
			counter += dice.getDiceFace();
		}
		game.setResultDices(counter);
		return game.getResultDices();
	}
	
	//Return winner (true) or loser(false) by gameId-----------------
	public Boolean gameWinner(Long gameId) {
		Game game = gameService.getGameById(gameId);
		Boolean winnerGame = game.getWinnerGame();
		if(game.getNumDices()==2) {
			if(game.getResultDices()==7) {
				game.setWinnerGame(true);	
			}else {
				game.setWinnerGame(false);
			}
		}
		if(game.getNumDices()==6) {
			List<Dice> dices = game.getDices();
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
		return game.getWinnerGame();
	}

}
