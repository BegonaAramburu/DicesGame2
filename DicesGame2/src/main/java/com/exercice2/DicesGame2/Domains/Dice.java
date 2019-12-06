package com.exercice2.DicesGame2.Domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Dice {
	
	//--------------------------Properties--------------------------------------------------------------

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long diceId;
	private Integer diceFace;
	
	//@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade=CascadeType.MERGE)

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name="game_id")
	private Game game;
	
	
	//--------------------------Constructors--------------------------------------------------------------
	public Dice() {
		
	}
	
	/*public Dice(Long gameId) {
		diceFace= (int) Math.floor(Math.random()*6+1);
		this.game = new Game(gameId);
	}*/
	
	public Dice(Game game) {
		diceFace= (int) Math.floor(Math.random()*6+1);
		getDiceFace();
		this.setGame(game);
		
	}
	
	//--------------------------Set/Get--------------------------------------------------------------------

	public Long getDiceId() {
		return diceId;
	}
	public void setDiceId(Long diceId) {
		this.diceId = diceId;
	}
	//-----------------------------------------------

	public Integer getDiceFace() {
		return diceFace;
	}
	public void setDiceFace(Integer diceFace) {
		this.diceFace = diceFace;
	}
	//-----------------------------------------------

	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	

	
	
	
	
}
