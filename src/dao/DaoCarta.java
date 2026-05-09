package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import modelo.Carta;

public class DaoCarta
{
    private static final Properties properties = new Properties();

    static
    {
        try
        {
        	FileInputStream arquivo = new FileInputStream("db.properties");
            properties.load(arquivo);

            Class.forName(properties.getProperty("db.driver"));
        }
        
        catch (Exception e)
        {
            System.out.println("Exceção: " + e.getMessage());
        }
    }

    public void sortearCarta(Carta carta, int id)
    {
        String query = "SELECT * FROM veiculos WHERE id = ?";

        try
        {
            Connection conexao = DriverManager.getConnection(properties.getProperty("db.url"));

            PreparedStatement operacao = conexao.prepareStatement(query);

            operacao.setInt(1, id);

            ResultSet resultado = operacao.executeQuery();

            if (resultado.next())
            {
                carta.setAtributos
                (
                        resultado.getFloat("speed"),
                        resultado.getFloat("weight"),
                        resultado.getFloat("acceleration"),
                        resultado.getFloat("handling"),
                        resultado.getFloat("drift"),
                        resultado.getFloat("offroad"),
                        resultado.getFloat("miniturbo")
                );

                carta.setNome(resultado.getString("nome"));
                carta.setId(resultado.getInt("id"));
            }

            resultado.close();
            operacao.close();
            conexao.close();
        }
        
        catch (Exception e)
        {
            System.out.println("Exceção: " + e.getMessage());
        }
    }
}