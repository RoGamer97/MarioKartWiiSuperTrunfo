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
	
	public void adicionarPontoRodada(int pontos)
	{
		this.pontosRodada += pontos;
	}
	
	public void resetPontoRodada()
	{
		this.pontosRodada = 0;
	}
}
