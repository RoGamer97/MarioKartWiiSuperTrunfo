package modelo;

public class Jogador 
{
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
	
	public void adicionarPontoPartida()
	{
		pontosPartida++;
	}
	
	public void adicionarPontosRodada(int pontos)
	{
		pontosRodada += pontos;
	}
	
	public void resetPontosRodada()
	{
		pontosRodada = 0;
	}
	
	public void resetPontos()
	{
		pontosPartida = 0;
		pontosRodada = 0;
	}
}
