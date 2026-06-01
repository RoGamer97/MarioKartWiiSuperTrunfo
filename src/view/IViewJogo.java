package view;

import java.util.List;
import modelo.Carta;

public interface IViewJogo 
{
	void resetElementos();
	void atualizarTextoTotalRodadas();
	void abrirMenuSelecaoCarta();
	void limparElementosRodada();
	void setTextoBotaoNenhumaCarta();
	void setIsBtnTrocarCartaEnabled(boolean isEnabled);
	void setIsBtnJogarEnabled(boolean isEnabled);
	void atualizarTextoPontos();
	void atualizarTextoRodada();
	void mostrarAvisoDesempate();
	void setTextoTotalRodadasDesempate();
	void setTextoBotaoFinalizarPartida();
	void atualizarElementosRodada();
	void mostrarEsconderElementosFimPartida();
	void setTextoBotaoJogar();
	void atualizarTextoAtributosHumano();
	void atualizarTextoCartaHumano();
	void atualizarImagemCartaHumano(byte[] imagem);
	void voltarMenuTitulo();
	void mostrarTextoEasterEggBumpfest();
	void mostrarTextoEasterEggMeta();
	void setImagemComparacaoSpeed(String pathHumano, String pathMaquina);
	void setImagemComparacaoWeight(String pathHumano, String pathMaquina);
	void setImagemComparacaoAccel(String pathHumano, String pathMaquina);
	void setImagemComparacaoDrift(String pathHumano, String pathMaquina);
	void setImagemComparacaoOffroad(String pathHumano, String pathMaquina);
	void setImagemComparacaoMT(String pathHumano, String pathMaquina);
	void setImagemComparacaoHandling(String pathHumano, String pathMaquina);
	void setCoroaHumanoIsVisible(boolean isVisible);
	void setCoroaMaquinaIsVisible(boolean isVisible);
	void mostrarIncrementoPontoHumano();
	void mostrarIncrementoPontoMaquina();
}