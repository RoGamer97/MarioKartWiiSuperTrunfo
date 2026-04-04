package modelo;

import java.util.List;

public class Carta 
{
	private int speed;
	private int weight;
	private int acceleration;
	private int handling;
	private int drift;
	private int offroad;
	private int miniturbo;

	private Jogador jogador;
	
	public Carta(Jogador jogador)
	{
		this.jogador = jogador;
	}
	
	public int getSomaAtributos()
	{
		return this.speed +  this.weight +  this.handling +  this.acceleration +  this.drift +  this.offroad +  this.miniturbo;
	}
	
	public void setAtributos(int speed, int weight, int handling, int acceleration, int drift, int offroad, int miniturbo)
	{
		this.speed = speed;
		this.weight = weight;
		this.handling = handling;
		this.acceleration = acceleration;
		this.drift = drift;
		this.offroad = offroad;
		this.miniturbo = miniturbo;
	}
}