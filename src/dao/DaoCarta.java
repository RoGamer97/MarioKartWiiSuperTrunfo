package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import modelo.Carta;
import modelo.TipoJogador;

public class DaoCarta 
{	
	public void sortearCarta(Carta carta, int id) 
	{
		String query = "SELECT * FROM veiculos WHERE id = " + id;
		
		try 
		{
			Properties properties = new Properties();
			FileInputStream arquivo = new FileInputStream("db.properties");
			properties.load(arquivo);
			
			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String usuario = properties.getProperty("db.usuario");
			String senha = properties.getProperty("db.senha");
			
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
			PreparedStatement operacao = conexao.prepareStatement(query);
			ResultSet resultado = operacao.executeQuery();
			
			if(resultado.next()) 
			{
				float speed = resultado.getFloat(3);
				float weight = resultado.getFloat(4);
				float acceleration = resultado.getFloat(5);
				float handling = resultado.getFloat(6);
				float drift = resultado.getFloat(7);
				float offroad = resultado.getFloat(8);
				float miniturbo = resultado.getFloat(9);
				
				carta.setAtributos(speed, weight, acceleration, handling, drift, offroad, miniturbo);
				
				String nome = resultado.getString(2);
				
				carta.setNome(nome);
			}
			
			conexao.close();
			operacao.close();
		}
		
		catch(SQLException e) 
		{
			System.out.println("Exception: " + e.getMessage());
		}
		
		catch(FileNotFoundException e) 
		{
			System.out.println("Exception: " + e.getMessage());
		}
		
		catch(IOException e) 
		{
			System.out.println("Exception: " + e.getMessage());
		}
	}
}
