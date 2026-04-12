package modelo;

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
	
	public int getSomaTodosAtributos()
	{
		return this.speed +  this.weight +  this.handling +  this.acceleration +  this.drift +  this.offroad +  this.miniturbo;
	}
	
	public int getSpeed() 
	{
		return speed;
	}

	public void setSpeed(int speed) 
	{
		this.speed = speed;
	}

	public int getWeight() 
	{
		return weight;
	}

	public void setWeight(int weight) 
	{
		this.weight = weight;
	}

	public int getAcceleration() 
	{
		return acceleration;
	}

	public void setAcceleration(int acceleration) 
	{
		this.acceleration = acceleration;
	}

	public int getHandling() 
	{
		return handling;
	}

	public void setHandling(int handling) 
	{
		this.handling = handling;
	}

	public int getDrift() {
		return drift;
	}

	public void setDrift(int drift) 
	{
		this.drift = drift;
	}

	public int getOffroad() 
	{
		return offroad;
	}

	public void setOffroad(int offroad) 
	{
		this.offroad = offroad;
	}

	public int getMiniturbo() 
	{
		return miniturbo;
	}

	public void setMiniturbo(int miniturbo) 
	{
		this.miniturbo = miniturbo;
	}

	public Jogador getJogador() 
	{
		return jogador;
	}

	public void setJogador(Jogador jogador) 
	{
		this.jogador = jogador;
	}

	public void setTodosAtributos(int speed, int weight, int handling, int acceleration, int drift, int offroad, int miniturbo)
	{
		this.speed = speed;
		this.weight = weight;
		this.handling = handling;
		this.acceleration = acceleration;
		this.drift = drift;
		this.offroad = offroad;
		this.miniturbo = miniturbo;
	}
	
	public int getValorAtributoPorTipo(TipoAtributoCarta tipoAtribCarta)
	{
		switch (tipoAtribCarta)
		{
			case SPEED: return speed;
			case WEIGHT: return weight;
			case HANDLING: return handling;
			case ACCEL: return acceleration;
			case DRIFT: return drift;
			case OFFROAD: return offroad;
			case MINITURBO: return miniturbo;
			default: return 0;
		}
		
	}
}