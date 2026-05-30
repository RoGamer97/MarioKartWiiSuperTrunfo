package modelo;

import java.util.List;

public class Jogo 
{
	private Jogador humano;
	private Jogador maquina;

	private int rodadaAtual;
	private int totalRodadas;
	
	private EstadoJogo estadoJogo;
	
	private boolean mostrarCartaMaquina;
	
	public Jogo(Jogador humano, Jogador maquina)
	{
		this.humano = humano;
		this.maquina = maquina;
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
	
	public void incrementarRodadaAtual()
	{		
		rodadaAtual++;
	}
	
	public TipoJogador getVencedorRodada()
	{
		return (humano.getPontosRodada() > maquina.getPontosRodada()) ? TipoJogador.HUMANO : TipoJogador.MAQUINA;
	}
	
	public TipoJogador getVencedorPartida()
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
	
	public EstadoJogo getEstadoJogo()
	{
		return estadoJogo;
	}
	
	public void setEstadoJogo(EstadoJogo estado)
	{
		estadoJogo = estado;
	}
}



