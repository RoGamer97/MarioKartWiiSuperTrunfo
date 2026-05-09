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
	
	private Jogo jogo = new Jogo(humano, maquina);
	private TipoJogadorString tjString = new TipoJogadorString();
	
	private ViewJogo viewJogo;
	private ControleCarta controleCarta;
	private ControleBaralho controleBaralho;
	
	public ControleJogo(ViewJogo viewJogo, ControleBaralho controleBaralho, ControleCarta controleCarta)
	{
		this.viewJogo = viewJogo;
		this.controleBaralho = controleBaralho;
		this.controleCarta = controleCarta;
	}
	
	public Jogador getJogadorPorTipo(TipoJogador tipoJogador)
	{
		return (tipoJogador == TipoJogador.HUMANO) ? humano : maquina;
	}
	
	public void jogar()
	{
		if (isPartidaFinalizada())
		{
			viewJogo.voltarMenuTitulo();
			return;
		}
		
		if (getRodadaAtual() == 0)
		{
			viewJogo.iniciarElementosPartida();
		}
		
		sortearCartaRodada();
		iniciarRodada();
		finalizarRodada();
		checarFinalizarPartida();
	}
	
	public void sortearCartaRodada()
	{
		controleBaralho.sortearCartaRodada(TipoJogador.HUMANO);
		controleBaralho.sortearCartaRodada(TipoJogador.MAQUINA);
	}
	
	public void iniciarRodada()
	{	
		humano.resetPontosRodada();
		maquina.resetPontosRodada();
		jogo.incrementarRodadaAtual();
	}
	
	public void checarFinalizarPartida()
	{
		if (!isPartidaFinalizada())
		{
			return;
		}
		
		viewJogo.mostrarElementosFimPartida();
	}
	
	
	public void finalizarRodada()
	{
		int totalAtribMaiorHumano = controleCarta.getTotalAtributosMaiores(TipoJogador.HUMANO);
		int totalAtribMaiorMaquina = controleCarta.getTotalAtributosMaiores(TipoJogador.MAQUINA);
		
		Carta cartaHumano = controleCarta.getCartaEscolhidaPorTipoJogador(TipoJogador.HUMANO);
		Carta cartaMaquina = controleCarta.getCartaEscolhidaPorTipoJogador(TipoJogador.MAQUINA);
		
		humano.adicionarPontosRodada(totalAtribMaiorHumano);
		maquina.adicionarPontosRodada(totalAtribMaiorMaquina);
		
		getJogadorPorTipo(controleCarta.getJogadorVencedorAtributos()).adicionarPontoPartida();
		
		viewJogo.atualizarElementosRodada();
	}
	
	public boolean isPartidaFinalizada()
	{
		return jogo.isUltimaRodada();
	}
	
	public int getRodadaAtual()
	{
		return jogo.getRodadaAtual();
	}
	
	public String getStringVencedor()
	{	
		return tjString.getTipoJogadorString(jogo.getVencedorPartida());
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
	
	public int getPontosRodada(TipoJogador tipoJogador)
	{	
		return getJogadorPorTipo(tipoJogador).getPontosRodada();
	}
	
	public int getPontosPartida(TipoJogador tipoJogador)
	{
		return getJogadorPorTipo(tipoJogador).getPontosPartida();
	}
	
}
