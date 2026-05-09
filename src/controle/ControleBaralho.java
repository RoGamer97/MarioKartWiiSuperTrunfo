package controle;

import java.util.Random;

import dao.DaoCarta;
import modelo.Baralho;
import modelo.Carta;
import modelo.TipoJogador;
import modelo.TipoJogadorString;

public class ControleBaralho 
{
	DaoCarta daoCarta = new DaoCarta();
	
	Baralho baralhoHumano = new Baralho();
	Baralho baralhoMaquina = new Baralho();
	
	public void setTotalCartas(int total)
	{
		baralhoHumano.setTotalCartas(total);
		baralhoMaquina.setTotalCartas(total);
	}
	
	public Baralho getBaralhoPorTipoJogador(TipoJogador tipoJogador)
	{
		return (tipoJogador == TipoJogador.HUMANO) ? baralhoHumano : baralhoMaquina;
	}
	
	public void sortearCartasPartida()
	{
		daoCarta.sortearCartasPartida(baralhoHumano);
		daoCarta.sortearCartasPartida(baralhoMaquina);
	}
	
	public void sortearCartaRodada(TipoJogador tipoJogador)
	{
		// DEBUG
		TipoJogadorString tjString = new TipoJogadorString();
		
		Random random = new Random();
		
		int idCarta = random.nextInt(baralhoHumano.getTotalCartas() + 1);
		
		Baralho baralho = getBaralhoPorTipoJogador(tipoJogador);
		
		Carta cartaEscolhida = baralho.getCartaPorId(idCarta);
		
		baralho.setCartaEscolhida(cartaEscolhida);
		
		// DEBUG
		System.out.println("[ControleBaralho] Carta Rodada sorteada para " + tjString.getTipoJogadorString(tipoJogador) + " (" + cartaEscolhida.getNome() + " | ID Array: " + cartaEscolhida.getId() + ")");
	}
	
	public void embaralharCartas()
	{
		baralhoHumano.embaralharCartas();
		baralhoMaquina.embaralharCartas();
	}
	
	public Carta getCartaPorId(int id, TipoJogador tipoJogador)
	{
		return getBaralhoPorTipoJogador(tipoJogador).getCartaPorId(id);
	}
	
	public Carta getCartaEscolhida(TipoJogador tipoJogador)
	{
		return getBaralhoPorTipoJogador(tipoJogador).getCartaEscolhida();
	}
}
