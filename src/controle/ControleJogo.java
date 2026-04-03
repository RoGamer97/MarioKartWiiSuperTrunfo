package controle;

import modelo.Jogador;
import modelo.Jogo;
import modelo.TipoJogador;
import modelo.TipoJogadorString;
import view.ViewJogo;

public class ControleJogo 
{
	Jogador humano = new Jogador();
	Jogador maquina = new Jogador();
	
	private Jogo jogo = new Jogo(humano, maquina);
	private TipoJogadorString tjString = new TipoJogadorString();
	
	private ViewJogo viewJogo;
	
	public ControleJogo(ViewJogo viewJogo)
	{
		this.viewJogo = viewJogo;
	}
	
	public void proximaRodada()
	{	
		if (jogo.isUltimaRodada())
		{
			return;
		}
		
		jogo.proximaRodada();
		viewJogo.atualizarTextoRodada(jogo.getRodadaAtual());
	}
	
	public void finalizarPartidaSeNecessario()
	{
		if (!jogo.isUltimaRodada())
		{
			return;
		}
		
		viewJogo.mostrarElementosFimPartida();
	}
	
	public int getRodadaAtual()
	{
		return jogo.getRodadaAtual();
	}
	
	public String getStringVencedor()
	{	
		return tjString.getTipoJogadorString(jogo.getTipoJogadorVencedor());

	}
	
	public void setTotalRodadas(int quantidade)
	{
		jogo.setTotalRodadas(quantidade);
	}
	
	public boolean isMostrarCartaMaquina()
	{
		return jogo.isMostrarCartaMaquina();
	}
	
	public void setMostrarCartaMaquina(boolean deveMostrar)
	{
		jogo.setMostrarCartaMaquina(deveMostrar);
	}
	
	public boolean isPartidaFinalizada()
	{
		return jogo.isUltimaRodada();
	}
}
