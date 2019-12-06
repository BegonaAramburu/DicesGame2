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
	
	//Crea los dados, los añade a un arrayList de dados y los guarda en el repository
		/*public ArrayList<Dice> postDice(Game game) { 
			
			ArrayList<Dice> dices = new ArrayList<>();
			for (int i = 0; i < game.getNumDices(); i++) {
				Dice dice = new Dice(game.getGameId()); 
				dices.add(dice); 
				dicesRepository.save(dice);
			}
			//game.setDices(dices);
			
			return dices;			
		}
		*/
		
		
	//Crea los dados, los añade a un arrayList de dados y los guarda en el repository
	public ArrayList<Dice> postDice(Game game) { 
		
		ArrayList<Dice> dices = new ArrayList<>();
		for (int i = 0; i < game.getNumDices(); i++) {
			Dice dice = new Dice(game);
			dices.add(dice); 
			dicesRepository.save(dice);
		}
		game.setDices(dices);
		
		return dices;			
	}
	

	

	//devuelveme el resultado de la suma de los dados por idgame: GET----------------------------------------------
	public Integer getResultadoDicesByGameId(Long gameId){
		List<Dice> dices = new ArrayList<>();
		dices = dicesRepository.findByGameGameId(gameId);
		Integer resultDices = 0;
		for (int i = 0; i < dices.size(); i++) {
			Dice dice=dices.get(i);
			resultDices += dice.getDiceFace();
		}
		System.out.println(resultDices);
		return resultDices;
	}

	//lo necesito para el gameWinner------------------------------
	public List<Dice> getAllDicesbyId(Long gameId) {
		return dicesRepository.findByGameGameId(gameId);
	}
	
	//delete dices by gameId
	public void deleteDices(Long gameId) {
		Game game = gameService.getGameById(gameId);
		List <Dice> dices = game.getDices();
		for (int i = 0; i < dices.size(); i++) {
			Dice d = dices.get(i);
			dicesRepository.delete(d);
		}
	}
	

}
