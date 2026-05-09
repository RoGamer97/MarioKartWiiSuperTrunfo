package controle;

import java.util.Random;

import modelo.Carta;
import modelo.Mao;
import modelo.TipoJogador;
import modelo.TipoJogadorString;

public class ControleMao 
{
	Random random = new Random();
	
	private ControleBaralho controleBaralho;
	
	Mao maoHumano = new Mao();
	Mao maoMaquina = new Mao();
	
	public ControleMao(ControleBaralho controleBaralho)
	{
		this.controleBaralho = controleBaralho;
	}
	
	public Mao getMaoPorTipoJogador(TipoJogador tipoJogador)
	{
		return (tipoJogador == TipoJogador.HUMANO) ? maoHumano : maoMaquina;
	}
	
	public void distribuirCartasMao(TipoJogador tipoJogador)
	{
		int totalCartas = controleBaralho.getTotalCartas();
		for (int i = 0; i < totalCartas; i++)
		{	
				
			Carta cartaSorteada = sortearCartaBaralho(tipoJogador);
					
			getMaoPorTipoJogador(tipoJogador).adicionarCartaMao(cartaSorteada);
			controleBaralho.removerCarta(cartaSorteada);
			
			// DEBUG
			TipoJogadorString tjString = new TipoJogadorString();
			System.out.println("[ControleBaralho] " + i + ") Carta removida do baralho e adicionada a mão para " + tjString.getTipoJogadorString(tipoJogador) + " (" + cartaSorteada.getNome() + " | ID Array: " + cartaSorteada.getId() + ")");
		}
	}
	
	public void sortearCartaMao(TipoJogador tipoJogador)
	{
		int totalCartas = controleBaralho.getTotalCartas();
		int idCarta = random.nextInt(totalCartas);
		Carta cartaSorteada = getMaoPorTipoJogador(tipoJogador).getCartaPorId(idCarta);
		getMaoPorTipoJogador(tipoJogador).setCartaEscolhida(cartaSorteada);
	}
	

	public Carta sortearCartaBaralho(TipoJogador tipoJogador)
	{
		int totalCartas = controleBaralho.getNumCartasBaralho();
		int idCarta = random.nextInt(totalCartas);
		return controleBaralho.getCartaPorId(idCarta, tipoJogador);
	}
	
	public Carta getCartaEscolhida(TipoJogador tipoJogador)
	{
		return getMaoPorTipoJogador(tipoJogador).getCartaEscolhida();
	}

}
