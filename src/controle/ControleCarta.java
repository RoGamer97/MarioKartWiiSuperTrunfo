package controle;

import java.util.Random;

import modelo.Carta;
import modelo.TipoAtributoCarta;
import modelo.TipoJogador;

public class ControleCarta 
{
	Carta cartaHumano = new Carta();
	Carta cartaMaquina = new Carta();
	
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
	
	public float getSomaTodosAtributos(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getSomaTodosAtributos();
	}
	
	public void setTodosAtributos(TipoJogador tipoJogador, float speed, float weight, float handling, float accel, float drift, float offroad, float mt)
	{
		getCartaPorTipoJogador(tipoJogador).setTodosAtributos(speed, weight, handling, accel, drift, offroad, mt);
	}
	
	public void debugSetAtributosAleatorios()
	{
		Random random = new Random();
		
		cartaHumano.setTodosAtributos(random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10);
		cartaMaquina.setTodosAtributos(random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10, random.nextFloat() * 10);
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
