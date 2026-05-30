package controle;

import java.util.List;
import java.util.Random;

import modelo.Carta;
import modelo.Debug;
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
			
			if (!Debug.DEBUG_PRINTS_ENABLED)
			{
				continue;
			}
			
			TipoJogadorString tjString = new TipoJogadorString();
			System.out.println("[ControleBaralho] " + (i + 1) + ") Carta removida do baralho e adicionada a mão para " + tjString.getTipoJogadorString(tipoJogador) + " (" + cartaSorteada.getNome() + " | ID Array: " + cartaSorteada.getId() + ")");
		}
	}
	
	public void distribuirCartasDesempate()
	{
		Carta cartaSorteada = sortearCartaBaralho(TipoJogador.HUMANO);
		maoHumano.adicionarCartaMao(cartaSorteada);
		controleBaralho.removerCarta(cartaSorteada);
		
		cartaSorteada = sortearCartaBaralho(TipoJogador.MAQUINA);
		maoMaquina.adicionarCartaMao(cartaSorteada);
		controleBaralho.removerCarta(cartaSorteada);
		
		
		if (!Debug.DEBUG_PRINTS_ENABLED)
		{
			return;
		}
		
		System.out.println("[ControleBaralho] Cartas de desempate distribuidas");
	}
	
	public void escolherCartaMaquina()
	{
		int totalCartas = maoMaquina.getNumCartasMao();
		int idCarta = random.nextInt(totalCartas);
		Carta cartaSorteada = maoMaquina.getCartaPorId(idCarta);
		maoMaquina.setCartaEscolhida(cartaSorteada);
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
	
	public List<Carta> getCartasMao(TipoJogador tipoJogador)
	{
		return getMaoPorTipoJogador(tipoJogador).getCartasMao();
	}
	
	public void removerCartaJogadaMao()
	{	
		maoHumano.removerCartaMao(maoHumano.getCartaEscolhida());
		maoMaquina.removerCartaMao(maoMaquina.getCartaEscolhida());
		
		if (!Debug.DEBUG_PRINTS_ENABLED)
		{
			return;
		}
		
		System.out.println("[ControleMao] removerCartaJogadaMao executado");
	}

}
