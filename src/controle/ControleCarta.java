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

	public int getSpeed(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getSpeed();
	}
	
	public int getAcceleration(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getAcceleration();
	}
	
	public int getDrift(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getDrift();
	}
	
	public int getMiniturbo(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getMiniturbo();
	}
	
	public int getWeight(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getWeight();
	}
	
	public int getOffroad(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getOffroad();
	}
	
	public int getHandling(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getHandling();
	}
	
	public int getSomaTodosAtributos(TipoJogador tipoJogador)
	{
		return getCartaPorTipoJogador(tipoJogador).getSomaTodosAtributos();
	}
	
	public void setTodosAtributos(TipoJogador tipoJogador, int speed, int weight, int handling, int accel, int drift, int offroad, int mt)
	{
		getCartaPorTipoJogador(tipoJogador).setTodosAtributos(speed, weight, handling, accel, drift, offroad, mt);
	}
	
	public void debugSetAtributosAleatorios()
	{
		Random random = new Random();
		
		cartaHumano.setTodosAtributos(random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11));
		cartaMaquina.setTodosAtributos(random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11), random.nextInt(11));
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
