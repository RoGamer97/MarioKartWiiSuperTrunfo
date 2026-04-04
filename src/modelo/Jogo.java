package modelo;

import java.util.List;

public class Jogo 
{
	private Jogador humano;
	private Jogador maquina;
	private List<Jogador> jogadores;
	
	private int rodadaAtual;
	private int totalRodadas;
	
	private boolean mostrarCartaMaquina;
	
	public Jogo(Jogador humano, Jogador maquina)
	{
		this.humano = humano;
		this.maquina = maquina;
		jogadores = List.of(humano, maquina);
	}
	
	public int getRodadaAtual()
	{
		return rodadaAtual;
	}
	
	public int getTotalRodadas()
	{
		return totalRodadas;
	}
	
	public void setTotalRodadas(int quantidade)
	{
		this.totalRodadas = quantidade;
	}
	
	public void proximaRodada()
	{
		for (Jogador jogador : jogadores)
		{
			// jogador.adicionarPontosPartida();
			jogador.resetPontosRodada();
		}
		rodadaAtual++;
	}
	
	public TipoJogador getTipoJogadorVencedor()
	{
		return (humano.getPontosPartida() > maquina.getPontosPartida()) ? TipoJogador.HUMANO : TipoJogador.MAQUINA;
	}
	
	public boolean isUltimaRodada()
	{
		return rodadaAtual >= totalRodadas;
	}
	
	public boolean isMostrarCartaMaquina()
	{
		return mostrarCartaMaquina;
	}
	 
	public void setMostrarCartaMaquina(boolean deveMostrar)
	{
		this.mostrarCartaMaquina = deveMostrar;
	}
}



