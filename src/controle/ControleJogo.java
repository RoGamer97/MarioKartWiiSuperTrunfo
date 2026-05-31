package controle;

import modelo.Carta;
import modelo.Debug;
import modelo.EstadoJogo;
import modelo.Jogador;
import modelo.Jogo;
import modelo.ResultadoComparacao;
import modelo.TipoAtributoCarta;
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
		compararKartsParaEasterEgg();
		setImagemsComparacaoAtributos();
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
		viewJogo.setIsBtnTrocarCartaEnabled(false);
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
		
		EstadoJogo estadoJogo;
		
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
		
		setEstadoJogo(estadoJogo);
		
		viewJogo.setIsBtnTrocarCartaEnabled(false);
		viewJogo.setIsBtnJogarEnabled(true);
		
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
	
	public void proximaRodada()
	{
		viewJogo.limparElementosRodada();
		viewJogo.setTextoBotaoNenhumaCarta();
		viewJogo.setIsBtnTrocarCartaEnabled(false);
		viewJogo.setIsBtnJogarEnabled(false);
		viewJogo.abrirMenuSelecaoCarta();
	}
	
	public void finalizarPartida()
	{
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
	}
	
	public void selecionarCarta(Carta carta)
	{
		controleMao.getMaoPorTipoJogador(TipoJogador.HUMANO).setCartaEscolhida(carta);
		
		setEstadoJogo(EstadoJogo.CARTA_ESCOLHIDA);
		viewJogo.setTextoBotaoJogar();
		viewJogo.setIsBtnJogarEnabled(true);

		viewJogo.atualizarTextoCartaHumano();
		viewJogo.atualizarTextoAtributosHumano();
		
		viewJogo.atualizarImagemCartaHumano(controleCarta.getImagem(TipoJogador.HUMANO));
		
		viewJogo.setIsBtnTrocarCartaEnabled(true);
	}
	
	public void processarJogo()
	{
		EstadoJogo estadoJogo = getEstadoJogo();
		
		switch (estadoJogo)
		{
			case CARTA_ESCOLHIDA:
				processarRodada();
				break;
			case RODADA_FINALIZADA:
				proximaRodada();
				break;
			case PARTIDA_FINALIZADA:
				finalizarPartida();
			default:	
		}
	}

	public void compararKartsParaEasterEgg()
	{
		String nomeCartaHumano = controleCarta.getNome(TipoJogador.HUMANO);
		String nomeCartaMaquina = controleCarta.getNome(TipoJogador.MAQUINA);
		
		String quacker = "Quacker";
		String piranhaPlowler = "Piranha Prowler";
		
		if ((nomeCartaHumano.equals(quacker) && nomeCartaMaquina.equals(piranhaPlowler)) || (nomeCartaHumano.equals(piranhaPlowler) && nomeCartaMaquina.equals(quacker)))
		{
			viewJogo.mostrarTextoEasterEggBumpfest();
		}
	}
	
	public void setImagemsComparacaoAtributos() 
	{
		compararSetarImagemComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.SPEED), TipoAtributoCarta.SPEED);
		compararSetarImagemComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.WEIGHT), TipoAtributoCarta.WEIGHT);
		compararSetarImagemComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.ACCEL), TipoAtributoCarta.ACCEL);
		compararSetarImagemComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.HANDLING), TipoAtributoCarta.HANDLING);
	    compararSetarImagemComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.DRIFT), TipoAtributoCarta.DRIFT);
	    compararSetarImagemComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.OFFROAD), TipoAtributoCarta.OFFROAD);
	    compararSetarImagemComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.MINITURBO), TipoAtributoCarta.MINITURBO);
	}

	private void compararSetarImagemComparacaoAtributos(ResultadoComparacao resultado, TipoAtributoCarta tipoAtrib) 
	{
	    String maior = "/imagens/maior.png";
	    String menor = "/imagens/menor.png";
	    String empate = "/imagens/empate.png";

	    String pathHumano = empate;
	    String pathMaquina = empate;

	    switch (resultado)
		{
	    	case MAIOR:
	    		pathHumano = maior;
	    		pathMaquina = menor;
	    		break;
	    	case MENOR:
	    		pathHumano = menor;
	    		pathMaquina = maior;
	    		break;
	    	default:
	    		pathHumano = empate;
	    		pathMaquina = empate;
		}

	    switch (tipoAtrib) 
	    {
	        case SPEED:    
	        	viewJogo.setImagemComparacaoSpeed(pathHumano, pathMaquina); 
	        	break;
	        case WEIGHT:     
	        	viewJogo.setImagemComparacaoWeight(pathHumano, pathMaquina); 
	        	break;
	        case ACCEL:      
	        	viewJogo.setImagemComparacaoAccel(pathHumano, pathMaquina); 
	        	break;
	        case HANDLING:   
	        	viewJogo.setImagemComparacaoHandling(pathHumano, pathMaquina); 
	        	break;
	        case DRIFT:      
	        	viewJogo.setImagemComparacaoDrift(pathHumano, pathMaquina); 
	        	break;
	        case OFFROAD:    
	        	viewJogo.setImagemComparacaoOffroad(pathHumano, pathMaquina); 
	        	break;
	        case MINITURBO:  
	        	viewJogo.setImagemComparacaoMT(pathHumano, pathMaquina); 
	    }
	}
	
	// USADAS NO DEBUG MENU!
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