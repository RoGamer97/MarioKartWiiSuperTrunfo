package controle;

import modelo.Carta;
import modelo.Debug;
import modelo.EstadoJogo;
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
	private ControleMao controleMao;
	
	boolean mostrouMensagemVencedor = false;
	
	public ControleJogo(ViewJogo viewJogo, ControleBaralho controleBaralho, ControleCarta controleCarta, ControleMao controleMao)
	{
		this.viewJogo = viewJogo;
		this.controleBaralho = controleBaralho;
		this.controleCarta = controleCarta;
		this.controleMao = controleMao;
	}
	
	public Jogador getJogadorPorTipo(TipoJogador tipoJogador)
	{
		return (tipoJogador == TipoJogador.HUMANO) ? humano : maquina;
	}
	
	public void processarRodada()
	{
		iniciarRodada();
		escolherCartaMaquina();
		removerCartaJogadaMao();
		finalizarRodada();
	}
	
	public void escolherCartaMaquina()
	{
		controleMao.escolherCartaMaquina();
	}
	
	public void iniciarRodada()
	{	
		humano.resetPontosRodada();
		maquina.resetPontosRodada();
		jogo.incrementarRodadaAtual();
		viewJogo.atualizarTextoRodada();
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
	
	public void removerCartaJogadaMao()
	{
		controleMao.removerCartaJogadaMao();
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
	
	public int getTotalRodadas()
	{
		return jogo.getTotalRodadas();
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
	
	public boolean isRodadaDesempate()
	{
		return jogo.isRodadaDesempate();
	}
	
	public void setIsRodadaDesempate(boolean isDesempate)
	{
		jogo.setIsRodadaDesempate(isDesempate);
	}
	
	public boolean isPartidaEmpatada()
	{
		return humano.getPontosPartida() == maquina.getPontosPartida();
	}
	
	public EstadoJogo getEstadoJogo()
	{
		return jogo.getEstadoJogo();
	}
	public void setEstadoJogo(EstadoJogo estado)
	{
		if (getEstadoJogo() == estado)
		{
			return;
		}
		
		jogo.setEstadoJogo(estado);
	}
	
	
	
	public void processarJogo()
	{
		EstadoJogo estadoJogo = getEstadoJogo();
		
		switch (estadoJogo)
		{
			case CARTA_ESCOLHIDA:		
				processarRodada();
								
				if (isPartidaFinalizada() && !isPartidaEmpatada())
				{
					viewJogo.setTextoBotaoFinalizarPartida();
					estadoJogo = EstadoJogo.PARTIDA_FINALIZADA;
				}
				else
				{
				    viewJogo.setTextoBotaoProximaRodada();
				    estadoJogo = EstadoJogo.RODADA_FINALIZADA;
				}
				
				viewJogo.setIsBtnMudarCartaEnabled(false);
				viewJogo.setIsBtnJogarEnabled(true);

				setEstadoJogo(estadoJogo);
				break;
				
			case RODADA_FINALIZADA:
				viewJogo.limparElementosRodada();
				viewJogo.setTextoBotaoNenhumaCarta();
				viewJogo.setIsBtnMudarCartaEnabled(true);
				viewJogo.setIsBtnJogarEnabled(false);
				viewJogo.abrirMenuSelecaoCarta();
				break;
				
			case PARTIDA_FINALIZADA:
				if (mostrouMensagemVencedor)
				{
					viewJogo.voltarMenuTitulo();
					return;
				}
				
				if (isPartidaEmpatada())
				{
					viewJogo.mostrarAvisoDesempate();
					setEstadoJogo(EstadoJogo.RODADA_FINALIZADA);
					
					setTotalRodadas(getTotalRodadas() + 1);
					viewJogo.setTextoTotalRodadasDesempate();
					viewJogo.limparElementosRodada();
					controleMao.distribuirCartasDesempate();
					viewJogo.abrirMenuSelecaoCarta();
					
					return;
				}
				viewJogo.mostrarElementosFimPartida();
				mostrouMensagemVencedor = true;
			
			default:	
				break;
		}
	}
	
	
	public void debugMudarPontosPartida(TipoJogador tipoJogador, boolean aumentar)
	{
		if (!Debug.DEBUG_MENU_ENABLED)
		{
			return;
		}
		
		if (getPontosPartida(tipoJogador) <= 0 && !aumentar)
		{
			return;
		}
		
		int ponto = aumentar ? 1 : -1;
		
		getJogadorPorTipo(tipoJogador).debugAdicionarPontoPartida(ponto);
		viewJogo.atualizarTextoPontos();
		
		System.out.println("DEBUG: " + ponto + " ponto para " + tipoJogador);
		viewJogo.displayDebugMark();
	}
	
	public void debugAumentarRodada()
	{
		if (!Debug.DEBUG_MENU_ENABLED)
		{
			return;
		}
		
		if (getRodadaAtual() < getTotalRodadas() - 1)
		{
			jogo.incrementarRodadaAtual();
			viewJogo.atualizarTextoRodada();
			
			System.out.println("DEBUG: Rodada atual incrementada");
			viewJogo.displayDebugMark();
		}
	}
}