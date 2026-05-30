package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mao 
{
	private List<Carta> mao = new ArrayList<>();
	
	Carta cartaEscolhida;

	public void adicionarCartaMao(Carta carta)
	{
		mao.add(carta);
	}
	
	public Carta getCartaPorId(int id)
	{
		return mao.get(id);
	}
	
	public Carta getCartaEscolhida()
	{
		return cartaEscolhida;
	}
	
	public void setCartaEscolhida(Carta carta)
	{
		cartaEscolhida = carta;
	}
	
	public List<Carta> getCartasMao()
	{
		return mao;
	}
	
	public void removerCartaMao(Carta carta)
	{
		mao.remove(carta);
	}
	
	public int getNumCartasMao()
	{
		return mao.size();
	}
}
