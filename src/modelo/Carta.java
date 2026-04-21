package modelo;

public class Carta 
{
	private float speed;
	private float weight;
	private float acceleration;
	private float handling;
	private float drift;
	private float offroad;
	private float miniturbo;
	
	private String nome;
	private int id;

	private Jogador jogador;
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public float getSomaTodosAtributos()
	{
		return speed +  weight + handling + acceleration + drift + offroad + miniturbo;
	}
	
	public float getSpeed() 
	{
		return speed;
	}

	public void setSpeed(float speed) 
	{
		this.speed = speed;
	}

	public float getWeight() 
	{
		return weight;
	}

	public void setWeight(float weight) 
	{
		this.weight = weight;
	}

	public float getAcceleration() 
	{
		return acceleration;
	}

	public void setAcceleration(float acceleration) 
	{
		this.acceleration = acceleration;
	}

	public float getHandling() 
	{
		return handling;
	}

	public void setHandling(float handling) 
	{
		this.handling = handling;
	}

	public float getDrift() 
	{
		return drift;
	}

	public void setDrift(float drift) 
	{
		this.drift = drift;
	}

	public float getOffroad() 
	{
		return offroad;
	}

	public void setOffroad(float offroad) 
	{
		this.offroad = offroad;
	}

	public float getMiniturbo() 
	{
		return miniturbo;
	}

	public void setMiniturbo(float miniturbo) 
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

	public void setTodosAtributos(float speed, float weight, float acceleration, float handling, float drift, float offroad, float miniturbo)
	{
		this.speed = speed;
		this.weight = weight;
		this.acceleration = acceleration;
		this.handling = handling;
		this.drift = drift;
		this.offroad = offroad;
		this.miniturbo = miniturbo;
	}
	
	public float getValorAtributoPorTipo(TipoAtributoCarta tipoAtribCarta)
	{
		switch (tipoAtribCarta)
		{
			case SPEED: return speed;
			case WEIGHT: return weight;
			case ACCEL: return acceleration;
			case HANDLING: return handling;
			case DRIFT: return drift;
			case OFFROAD: return offroad;
			case MINITURBO: return miniturbo;
			default: return 0;
		}
		
	}
}