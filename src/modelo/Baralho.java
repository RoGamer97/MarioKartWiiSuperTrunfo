package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho 
{
	private List<Carta> cartas = new ArrayList<>();
	
	private int totalCartas;
	private int numCartasBaralho;
	
	public int getNumCartasBaralho()
	{
		return cartas.size();
	}
	
	public int getTotalCartas()
	{
		return totalCartas;
	}
	
	public void setTotalCartas(int qtdCartas)
	{
		totalCartas = qtdCartas;
	}
	
    public void adicionarCarta(Carta carta)
    {
        cartas.add(carta);
    }
    
    public void removerCarta(Carta carta)
    {
    	cartas.remove(carta);
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
