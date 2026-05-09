package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho 
{
	private ArrayList<Carta> cartas = new ArrayList<>();
	
	private Carta cartaEscolhida;
	
	private TipoJogador tipoJogador;
	
	public Baralho(TipoJogador tipoJogador)
	{
		this.tipoJogador = tipoJogador;
	}
	
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
    	
    	// DEBUG
    	
    	int idx = 0;
    	
    	for (Carta carta : cartas)
    	{
            System.out.println("[Baralho] Carta embaralhada (" + carta.getNome() + " | Index Array Velho: " + (carta.getId() - 1) + " | Index Array Novo: " + idx + ")");
            
            idx++;
    	}
    	
    	
    }
}
