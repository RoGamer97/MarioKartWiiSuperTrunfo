package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.DaoCarta;
import modelo.Baralho;
import modelo.Carta;
import modelo.TipoJogador;
import modelo.TipoJogadorString;

public class ControleBaralho 
{
	DaoCarta daoCarta = new DaoCarta();
	
	Baralho baralho = new Baralho();
	
	Random random = new Random();
	
	public int getNumCartasBaralho()
	{
		return baralho.getNumCartasBaralho();
	}
	
	public int getTotalCartas()
	{
		return baralho.getTotalCartas();
	}
	
	public void setTotalCartas(int total)
	{
		baralho.setTotalCartas(total);
	}

	public void prepararBaralho()
	{
		daoCarta.adicionarCartasBaralho(baralho);
		baralho.embaralharCartas();
	}
	
	public void embaralharCartas()
	{
		baralho.embaralharCartas();
	}
	
	public Carta getCartaPorId(int id, TipoJogador tipoJogador)
	{
		return baralho.getCartaPorId(id);
	}
	
	public void removerCarta(Carta carta)
	{
		baralho.removerCarta(carta);
	}
}
