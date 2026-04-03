package modelo;

public class TipoJogadorString 
{
	public TipoJogador tipoJogador;
	
	
	// Talvez mudar? Usa o index diretamente, ent se mudar a ordem do TipoJogador (oq n será feito, mas msm assim), fica desalinhado 
	public String getTipoJogadorString(TipoJogador tipoJogador)
	{
		String[] tipoJogadoresString =
		{
			"Você", 
			"Máquina"
		};
		
		return tipoJogadoresString[tipoJogador.ordinal()];
	}
}
