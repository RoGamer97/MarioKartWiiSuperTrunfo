package controle;

import java.util.List;
import java.util.Random;
import dao.DaoCarta;
import modelo.Baralho;
import modelo.Carta;
import modelo.TipoJogador;

public class ControleBaralho 
{
    private DaoCarta daoCarta = new DaoCarta();
    private Baralho baralho = new Baralho();
    private Random random = new Random();

    public void removerTodasCartas()
    {
    	baralho.removerTodasCartas();
    }
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
        List<Carta> cartas = daoCarta.getTodasCartas();

        for (Carta carta : cartas)
        {
            baralho.adicionarCarta(carta);
        }

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