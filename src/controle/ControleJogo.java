package controle;

import modelo.Carta;
import modelo.Jogador;
import modelo.Jogo;
import modelo.TipoJogador;
import modelo.TipoJogadorString;
import view.ViewJogo;

public class ControleJogo 
{
	Jogador humano = new Jogador();
	Jogador maquina = new Jogador();
	
	private Carta cartaHumano = new Carta(humano);
	private Carta cartaMaquina = new Carta(maquina);
	
	private Jogo jogo = new Jogo(humano, maquina);
	private TipoJogadorString tjString = new TipoJogadorString();
	
	private ViewJogo viewJogo;
	
	public ControleJogo(ViewJogo viewJogo)
	{
		this.viewJogo = viewJogo;
	}
	
	public void proximaRodada()
	{	
		jogo.proximaRodada();
		viewJogo.atualizarTextoRodada(jogo.getRodadaAtual());
	}
	
	public void finalizarPartidaSeNecessario()
	{
		if (!isPartidaFinalizada())
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
	
	public int getPontosRodadaPorTipoJogador(TipoJogador tipoJogador)
	{
		Jogador jogador = tipoJogador == TipoJogador.HUMANO ? humano : maquina;
		
		return jogador.getPontosRodada();
	}
	
	public int getPontosPartidaPorTipoJogador(TipoJogador tipoJogador)
	{
		Jogador jogador = tipoJogador == TipoJogador.HUMANO ? humano : maquina;
		
		return jogador.getPontosPartida();
	}
	
	// na ControleJogo temporariamente
	public void setAtributosCartasHumano(int speed, int weight, int accel, int handling, int drift, int offroad, int mt)
	{
		cartaHumano.setAtributos(speed, weight, accel, handling, drift, offroad, mt);
	}
	
	// na ControleJogo temporariamente
	public void setAtributosCartasMaquina(int speed, int weight, int accel, int handling, int drift, int offroad, int mt)
	{
		cartaMaquina.setAtributos(speed, weight, accel, handling, drift, offroad, mt);
	}
	
	public void atualizarPontos()
	{
		humano.adicionarPontosRodada(cartaHumano.getSomaAtributos());
		maquina.adicionarPontosRodada(cartaMaquina.getSomaAtributos());
		
		humano.adicionarPontosPartida();
		maquina.adicionarPontosPartida();
		
		viewJogo.atualizarTextoPontos();
	}
	
}
