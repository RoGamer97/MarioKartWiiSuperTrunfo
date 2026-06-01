package controle;

import modelo.Carta;
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
	
	public ControleJogo(ControleBaralho controleBaralho, ControleCarta controleCarta, ControleMao controleMao)
	{
		this.controleBaralho = controleBaralho;
		this.controleCarta = controleCarta;
		this.controleMao = controleMao;
	}
	
	public void setViewJogo(ViewJogo viewJogo)
	{
		this.viewJogo = viewJogo;
	}
	
	public Jogador getJogadorPorTipo(TipoJogador tipoJogador)
	{
		return (tipoJogador == TipoJogador.HUMANO) ? humano : maquina;
	}
	
	public void resetTudo()
	{
		controleBaralho.removerTodasCartas();
		controleMao.removerTodasCartas();
		jogo.resetRodadaAtual();
		humano.resetarPontos();
		maquina.resetarPontos();
	}
	
	public void iniciarPartida()
	{
		viewJogo.resetElementos();
		controleBaralho.setTotalCartas(getTotalRodadas());
		viewJogo.atualizarTextoTotalRodadas();
		controleBaralho.prepararBaralho();
		controleMao.distribuirCartasMao(TipoJogador.HUMANO);
		controleMao.distribuirCartasMao(TipoJogador.MAQUINA);
		viewJogo.abrirMenuSelecaoCarta();
	}
	
	public void jogarRodada()
	{
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
	
	public void proximaRodada()
	{
		humano.resetPontosRodada();
		maquina.resetPontosRodada();
		jogo.incrementarRodadaAtual();
		viewJogo.limparElementosRodada();
		viewJogo.setTextoBotaoNenhumaCarta();
		viewJogo.setIsBtnTrocarCartaEnabled(false);
		viewJogo.setIsBtnJogarEnabled(false);
		viewJogo.atualizarTextoPontos();
		viewJogo.atualizarTextoRodada();
		viewJogo.abrirMenuSelecaoCarta();
		
		setEstadoJogo(EstadoJogo.CARTA_NAO_ESCOLHIDA);
	}
	
	public void proximaRodadaDesempate()
	{
		viewJogo.mostrarAvisoDesempate();
		setTotalRodadas(getTotalRodadas() + 1);
		viewJogo.setTextoTotalRodadasDesempate();
		controleMao.distribuirCartasDesempate();
		proximaRodada();
	}
	

	public void finalizarRodada()
	{
		viewJogo.atualizarTextoRodada();
		viewJogo.setIsBtnTrocarCartaEnabled(false);
		
		int totalAtribMaiorHumano = controleCarta.getTotalAtributosMaiores(TipoJogador.HUMANO);
		int totalAtribMaiorMaquina = controleCarta.getTotalAtributosMaiores(TipoJogador.MAQUINA);
		
		Carta cartaHumano = controleCarta.getCartaEscolhidaPorTipoJogador(TipoJogador.HUMANO);
		Carta cartaMaquina = controleCarta.getCartaEscolhidaPorTipoJogador(TipoJogador.MAQUINA);
		
		humano.adicionarPontosRodada(totalAtribMaiorHumano);
		maquina.adicionarPontosRodada(totalAtribMaiorMaquina);
		
		getJogadorPorTipo(controleCarta.getJogadorVencedorAtributos()).adicionarPontoPartida();
		
		EstadoJogo estadoJogo = EstadoJogo.RODADA_FINALIZADA;
		
		if (deveFinalizarPartida())
		{
			viewJogo.setTextoBotaoFinalizarPartida();
			estadoJogo = isPartidaEmpatada() ? EstadoJogo.RODADA_DESEMPATE : EstadoJogo.PARTIDA_FINALIZADA;
		}
		
		setEstadoJogo(estadoJogo);
		
		viewJogo.setIsBtnTrocarCartaEnabled(false);
		viewJogo.setIsBtnJogarEnabled(true);
		
		viewJogo.atualizarElementosRodada();
		mostrarCoroaGanhando();
		mostrarIncrementoPonto();
	}
	
	public void removerCartaJogadaMao()
	{
		controleMao.removerCartaJogadaMao();
	}
	
	public boolean deveFinalizarPartida()
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
	
	public void finalizarPartida()
	{
		viewJogo.mostrarEsconderElementosFimPartida();
		setEstadoJogo(EstadoJogo.RESULTADO_PARTIDA);
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
				jogarRodada();
				break;
			case RODADA_DESEMPATE:
				proximaRodadaDesempate();
				break;
			case RODADA_FINALIZADA:
				proximaRodada();
				break;	
			case PARTIDA_FINALIZADA:
				finalizarPartida();
				break;
			case RESULTADO_PARTIDA:
				viewJogo.voltarMenuTitulo();
			default:	
		}
	}

	public void compararKartsParaEasterEgg()
	{
		String nomeCartaHumano = controleCarta.getNome(TipoJogador.HUMANO);
		String nomeCartaMaquina = controleCarta.getNome(TipoJogador.MAQUINA);
		
		String quacker = "Quacker";
		String piranhaPlowler = "Piranha Prowler";
		String flameRunner = "Flame Runner";
		String machBike = "Mach Bike";
		
		if ((nomeCartaHumano.equals(quacker) && nomeCartaMaquina.equals(piranhaPlowler)) || (nomeCartaHumano.equals(piranhaPlowler) && nomeCartaMaquina.equals(quacker)))
		{
			viewJogo.mostrarTextoEasterEggBumpfest();
		}
		else if ((nomeCartaHumano.equals(machBike) && nomeCartaMaquina.equals(flameRunner)) || (nomeCartaHumano.equals(flameRunner) && nomeCartaMaquina.equals(machBike)))
		{
			viewJogo.mostrarTextoEasterEggMeta();
		}
	}
	
	public void setImagemsComparacaoAtributos() 
	{
		setImagemBaseadoComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.SPEED), TipoAtributoCarta.SPEED);
		setImagemBaseadoComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.WEIGHT), TipoAtributoCarta.WEIGHT);
		setImagemBaseadoComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.ACCEL), TipoAtributoCarta.ACCEL);
		setImagemBaseadoComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.HANDLING), TipoAtributoCarta.HANDLING);
		setImagemBaseadoComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.DRIFT), TipoAtributoCarta.DRIFT);
		setImagemBaseadoComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.OFFROAD), TipoAtributoCarta.OFFROAD);
		setImagemBaseadoComparacaoAtributos(controleCarta.getResultadoComparacaoAtrib(TipoAtributoCarta.MINITURBO), TipoAtributoCarta.MINITURBO);
	}

	private void setImagemBaseadoComparacaoAtributos(ResultadoComparacao resultado, TipoAtributoCarta tipoAtrib) 
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
	
	public void mostrarCoroaGanhando()
	{
		int pontosPartidaHumano = humano.getPontosPartida();
		int pontosPartidaMaquina = maquina.getPontosPartida();
		
		if (pontosPartidaHumano > pontosPartidaMaquina)
		{
			viewJogo.setCoroaHumanoIsVisible(true);
			viewJogo.setCoroaMaquinaIsVisible(false);
		}
		else if (pontosPartidaHumano < pontosPartidaMaquina)
		{
			viewJogo.setCoroaHumanoIsVisible(false);
			viewJogo.setCoroaMaquinaIsVisible(true);
		}
		else
		{
			viewJogo.setCoroaHumanoIsVisible(false);
			viewJogo.setCoroaMaquinaIsVisible(false);
		}
	}
	
	public void mostrarIncrementoPonto()
	{
		int pontosRodadaHumano = humano.getPontosRodada();
		int pontosRodadaMaquina = maquina.getPontosRodada();
		
		if (pontosRodadaHumano > pontosRodadaMaquina)
		{
			viewJogo.mostrarIncrementoPontoHumano();
		}
		else if (pontosRodadaHumano < pontosRodadaMaquina)
		{
			viewJogo.mostrarIncrementoPontoMaquina();
		}
	}
}