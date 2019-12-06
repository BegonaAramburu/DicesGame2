
package com.exercice2.DicesGame2.Domains;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.crypto.Data;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Player {
	
	//--------------------------Properties--------------------------------------------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long playerId;
	private String playerName;
	private Date altaRegistro;
	private Double succesRate;
	
	@OneToMany(mappedBy = "player")//, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Game> games = new ArrayList<>();
	
	//--------------------------Constructors--------------------------------------------------------------

	public Player() {
		altaRegistro = new Date();
	}
	
	public Player(String playerName) {	
		altaRegistro = new Date();
		this.playerName = playerName;
	}
	
	//--------------------------Setters/Getters--------------------------------------------------------------------

	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Date getAltaRegistro() {
		return altaRegistro;
	}

	public void setAltaRegistro(Date altaRegistro) {
		this.altaRegistro = altaRegistro;
	}
	
	public Double getSuccesRate() {
		return succesRate;
	}

	public void setSuccesRate(Double succesRate) {
		this.succesRate = succesRate;
	}
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}
	
}
