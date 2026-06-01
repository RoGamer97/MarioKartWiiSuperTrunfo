package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.DaoCarta;

public class Baralho 
{
    private DaoCarta daoCarta = new DaoCarta();
	private List<Carta> cartas = new ArrayList<>();
	
	private int totalCartas;
	private int numCartasBaralho;
	
	public void removerTodasCartas()
	{
		cartas.clear();
	}
	
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
    }
    
    public void prepararBaralho()
    {
        List<Carta> cartas = daoCarta.getTodasCartas();

        for (Carta carta : cartas)
        {
            adicionarCarta(carta);
        }

        embaralharCartas();
    }
}
