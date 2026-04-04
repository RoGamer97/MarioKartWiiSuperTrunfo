package modelo;

public class Jogador 
{
	private Carta carta;
	private int pontosPartida;
	private int pontosRodada;
	
	public int getPontosPartida()
	{
		return pontosPartida;
	}
	
	public int getPontosRodada()
	{
		return pontosRodada;
	}
	
	public void adicionarPontosPartida()
	{
		this.pontosPartida += this.pontosRodada;
	}
	
	public void adicionarPontosRodada(int pontos)
	{
		this.pontosRodada += pontos;
	}
	
	public void resetPontosRodada()
	{
		this.pontosRodada = 0;
	}
}
