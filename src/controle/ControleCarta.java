package controle;

import java.util.Random;

import dao.DaoCarta;
import modelo.Carta;
import modelo.TipoAtributoCarta;
import modelo.TipoJogador;

public class ControleCarta 
{
	Carta cartaHumano = new Carta();
	Carta cartaMaquina = new Carta();
	
	DaoCarta daoCarta = new DaoCarta();
	
	public Carta getCartaPorTipoJogador(TipoJogador tipoJogador)
	{
		return (tipoJogador == TipoJogador.HUMANO) ? cartaHumano : cartaMaquina;
	}

	public float getSpeed(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getSpeed();
	}
	
	public float getAcceleration(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getAcceleration();
	}
	
	public float getDrift(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getDrift();
	}
	
	public float getMiniturbo(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getMiniturbo();
	}
	
	public float getWeight(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getWeight();
	}
	
	public float getOffroad(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getOffroad();
	}
	
	public float getHandling(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getHandling();
	}
	
	public String getNome(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getNome();
	}
	
	public float getSomaTodosAtributos(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getSomaTodosAtributos();
	}
	
	public void debugSetAtributosAleatorios()
	{
		
		final int QUANTIDADE_CARTAS = 36;
		
		int idCartaHumano;
		int idCartaMaquina;
		
		Random random = new Random();
		
		do
		{
			idCartaHumano = random.nextInt(QUANTIDADE_CARTAS - 1) + 1;
			idCartaMaquina = random.nextInt(QUANTIDADE_CARTAS - 1) + 1;
		}
		while (idCartaHumano == idCartaMaquina);
		
		daoCarta.sortearCarta(cartaHumano, idCartaHumano);
		daoCarta.sortearCarta(cartaMaquina, idCartaMaquina);
		
		
	}
	
	public int getTotalAtributosMaiores(TipoJogador tipoJogador)
	{
		int totalAtribMaiores = 0;
		
		Carta cartaPrincipal = getCartaPorTipoJogador(tipoJogador);
		Carta cartaOponente = (tipoJogador == tipoJogador.HUMANO) ? cartaMaquina : cartaHumano;
		
		for (TipoAtributoCarta tipoAtribCarta : TipoAtributoCarta.values())
		{
			if (cartaPrincipal.getValorAtributoPorTipo(tipoAtribCarta) > cartaOponente.getValorAtributoPorTipo(tipoAtribCarta))
			{
				totalAtribMaiores++;
			}
		}
		
		return totalAtribMaiores;
	}
	
	public TipoJogador getJogadorVencedorAtributos()
	{
		int totalAtribMaioresHumano = getTotalAtributosMaiores(TipoJogador.HUMANO);
		int totalAtribMaioresMaquina = getTotalAtributosMaiores(TipoJogador.MAQUINA);
		
		return (totalAtribMaioresHumano > totalAtribMaioresMaquina) ? TipoJogador.HUMANO : TipoJogador.MAQUINA;
	}
}
