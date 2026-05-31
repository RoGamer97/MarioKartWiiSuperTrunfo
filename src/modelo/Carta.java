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
    
	private byte[] imagem;
    
    public Carta(String nome, int id, float speed, float weight, float acceleration, float handling, float drift, float offroad, float miniturbo)
    {
    	this.nome = nome;
    	this.id = id;
		this.speed = speed;
		this.weight = weight;
		this.acceleration = acceleration;
		this.handling = handling;
		this.drift = drift;
		this.offroad = offroad;
		this.miniturbo = miniturbo;
    }
    
    public void setId(int id)
    {
    	this.id = id;
    }
    
    public int getId()
    {
    	return id;
    }
    
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

	public float getWeight() 
	{
		return weight;
	}

	public float getAcceleration() 
	{
		return acceleration;
	}

	public float getHandling() 
	{
		return handling;
	}

	public float getDrift() 
	{
		return drift;
	}

	public float getOffroad() 
	{
		return offroad;
	}

	public float getMiniturbo() 
	{
		return miniturbo;
	}

	public void setAtributos(float speed, float weight, float acceleration, float handling, float drift, float offroad, float miniturbo)
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
	
    public byte[] getImagem() 
    {
        return imagem;
    }

    public void setImagem(byte[] imagem) 
    {
        this.imagem = imagem;
    }
}