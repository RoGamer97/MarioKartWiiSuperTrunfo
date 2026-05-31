package controle;

import modelo.Carta;
import modelo.ResultadoComparacao;
import modelo.TipoAtributoCarta;
import modelo.TipoJogador;

public class ControleCarta 
{
	private ControleMao controleMao; 
	
	public ControleCarta(ControleMao controleMao)
	{
		this.controleMao = controleMao;
	}
	
	public Carta getCartaEscolhidaPorTipoJogador(TipoJogador tipoJogador)
	{
		return controleMao.getCartaEscolhida(tipoJogador);
	}

	public float getSpeed(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getSpeed();
	}
	
	public float getAcceleration(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getAcceleration();
	}
	
	public float getDrift(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getDrift();
	}
	
	public float getMiniturbo(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getMiniturbo();
	}
	
	public float getWeight(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getWeight();
	}
	
	public float getOffroad(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getOffroad();
	}
	
	public float getHandling(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getHandling();
	}
	
	public String getNome(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getNome();
	}
	
	public float getSomaTodosAtributos(TipoJogador tipoJogador)
	{
		return getCartaEscolhidaPorTipoJogador(tipoJogador).getSomaTodosAtributos();
	}
	
	public int getTotalAtributosMaiores(TipoJogador tipoJogador)
	{
		int totalAtribMaiores = 0;
		
		Carta cartaPrincipal = getCartaEscolhidaPorTipoJogador(tipoJogador);
		
		TipoJogador tipoJogadorOponente = (tipoJogador == tipoJogador.HUMANO) ? TipoJogador.MAQUINA : TipoJogador.HUMANO;
		
		Carta cartaOponente = getCartaEscolhidaPorTipoJogador(tipoJogadorOponente);
		
		for (TipoAtributoCarta tipoAtribCarta : TipoAtributoCarta.values())
		{
			if (cartaPrincipal.getValorAtributoPorTipo(tipoAtribCarta) > cartaOponente.getValorAtributoPorTipo(tipoAtribCarta))
			{
				totalAtribMaiores++;
			}
		}
		
		return totalAtribMaiores;
	}
	
	public ResultadoComparacao getResultadoComparacaoAtrib(TipoAtributoCarta tipoAtrib)
	{
		float valorAtribHumano = getCartaEscolhidaPorTipoJogador(TipoJogador.HUMANO).getValorAtributoPorTipo(tipoAtrib);
		float valorAtribMaquina = getCartaEscolhidaPorTipoJogador(TipoJogador.MAQUINA).getValorAtributoPorTipo(tipoAtrib);
		
		if (valorAtribHumano == valorAtribMaquina)
		{
			return ResultadoComparacao.EMPATE;
		}
		
		return valorAtribHumano > valorAtribMaquina ? ResultadoComparacao.MAIOR : ResultadoComparacao.MENOR;
	}
	
	public TipoJogador getJogadorVencedorAtributos()
	{
		int totalAtribMaioresHumano = getTotalAtributosMaiores(TipoJogador.HUMANO);
		int totalAtribMaioresMaquina = getTotalAtributosMaiores(TipoJogador.MAQUINA);
		
		return (totalAtribMaioresHumano > totalAtribMaioresMaquina) ? TipoJogador.HUMANO : TipoJogador.MAQUINA;
	}
	
	public byte[] getImagem(TipoJogador tipoJogador)
	{
	  return controleMao.getMaoPorTipoJogador(tipoJogador).getCartaEscolhida().getImagem();
	}
}
