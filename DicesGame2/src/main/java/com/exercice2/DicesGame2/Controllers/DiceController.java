/*package com.exercice2.DicesGame2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exercice2.DicesGame2.Domains.Dice;
import com.exercice2.DicesGame2.Domains.Game;
import com.exercice2.DicesGame2.Services.DiceService;

@RestController
public class DiceController {
	
	@Autowired
	DiceService diceService;
	
	//--------------------------------------Calls--------------------------------------------------------
	
	//Call for to create a dice------------------------------------
	@PostMapping("/games/{gameId}/dices")
	public List<Dice> CreateDice(@RequestBody Game game) {
		return diceService.postDice(game);
		//me devuelve la suma de los dados
	}
	
	//Ask all dices by gameId: GET----------------------------------------------
	@GetMapping("/games/{gameId}/dices")
	public List<Dice> getDicesByGameId(@PathVariable Long gameId){
		System.out.println(gameId);
		return diceService.getAllDicesbyId(gameId);
		

	}

}
*/