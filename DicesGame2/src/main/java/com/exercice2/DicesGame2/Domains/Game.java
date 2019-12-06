package com.exercice2.DicesGame2.Domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Game {
	
	//--------------------------Properties--------------------------------------------------------------

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long gameId;
	private Integer numDices;
	private Integer resultDices;
	private Boolean winnerGame=false;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="player_id")
	private Player player;
	
	@OneToMany(mappedBy = "game",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dice> dices = new ArrayList<>();
	
	//--------------------------Constructors--------------------------------------------------------------

	public Game() {
	}
	
	public Game(Integer numDices, Player player) {
		this.numDices = numDices;
		this.setDices(dices);
		this.setPlayer(player);
	}
	
	//--------------------------Setters/Getters--------------------------------------------------------------------
	
	public Long getGameId() {
		return gameId;
	}
	
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
	public Integer getNumDices() {
		return numDices;
	}
	
	public void setNumDices(Integer numDices) {
		this.numDices = numDices;
	}

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getResultDices() {
		return resultDices;
	}

	public void setResultDices(Integer resultDices) {
		this.resultDices = resultDices;
	}

	public Boolean getWinnerGame() {
		return winnerGame;
	}

	public void setWinnerGame(Boolean winnerGame) {
		this.winnerGame = winnerGame;
	}
	
	public List<Dice> getDices() {
		return dices;
	}

	public void setDices(List<Dice> dices) {
		this.dices = dices;
	}

}
