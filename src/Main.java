import controle.ControleBaralho;
import controle.ControleCarta;
import controle.ControleJogo;
import controle.ControleMao;
import modelo.TipoJogador;
import view.ViewJogo;
import view.ViewJogoMenuCarta;
import view.ViewMenuPrincipal;

public class Main {

	public static void main(String[] args) 
	{
		ControleBaralho controleBaralho = new ControleBaralho();
		
        ControleMao controleMao = new ControleMao(controleBaralho);
        
        ControleCarta controleCarta = new ControleCarta(controleMao);
        
        ControleJogo controleJogo = new ControleJogo(controleBaralho, controleCarta, controleMao);
    
        ViewMenuPrincipal viewMenuPrincipal = new ViewMenuPrincipal(controleJogo);
      
        ViewJogo viewJogo = new ViewJogo(viewMenuPrincipal, controleMao, controleCarta, controleBaralho, controleJogo);
		ViewJogoMenuCarta viewJogoMenuCarta = new ViewJogoMenuCarta(viewJogo, controleJogo, controleMao, controleCarta);
		
		viewJogo.setViewJogoMenuCarta(viewJogoMenuCarta);
        
        controleJogo.setViewJogo(viewJogo);
        
        viewMenuPrincipal.setVisible(true);
	}

}
