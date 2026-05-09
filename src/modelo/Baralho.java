package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho 
{
	private ArrayList<Carta> cartas = new ArrayList<>();
	
	private Carta cartaEscolhida;
	
	private int totalCartas;
	
	public int getTotalCartas()
	{
		return totalCartas;
	}
	
	public void setTotalCartas(int qtdCartas)
	{
		totalCartas = qtdCartas;
	}
	
	public Carta getCartaEscolhida()
	{
		return cartaEscolhida;
	}
	
	public void setCartaEscolhida(Carta carta)
	{
		cartaEscolhida = carta;
	}
	
    public void adicionarCarta(Carta carta)
    {
        cartas.add(carta);
    }
    
    public Carta getCartaPorId(int id)
    {
        return cartas.get(id);
    }
    
    public void embaralharCartas()
    {
    	Collections.shuffle(cartas);
    }
}
