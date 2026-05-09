package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import modelo.Baralho;
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

    public void sortearCartasPartida(Baralho baralho)
    {
        String query = "SELECT * FROM veiculos";

        try
        {
            Connection conexao = DriverManager.getConnection(properties.getProperty("db.url"));
            PreparedStatement operacao = conexao.prepareStatement(query);
            ResultSet resultado = operacao.executeQuery();

            int idx = 0;
            
            while (resultado.next())
            {
                Carta carta = new Carta(
                	resultado.getString("nome"),
                    resultado.getInt("id"),
                    resultado.getFloat("speed"),
                    resultado.getFloat("weight"),
                    resultado.getFloat("acceleration"),
                    resultado.getFloat("handling"),
                    resultado.getFloat("drift"),
                    resultado.getFloat("offroad"),
                    resultado.getFloat("miniturbo")
                );
                
                baralho.adicionarCarta(carta);
                
                // DEBUG
                System.out.println("[DaoCarta] Carta adicionada ao baralho (" + resultado.getString("nome") + " | Index BD: " + resultado.getInt("id") + " | Index Array: " + idx + ")");
                
                idx++;
            }

            resultado.close();
            operacao.close();
            conexao.close();
            System.out.println("DEBUG: Todas as cartas foram sorteadas\n");
        }
        catch (SQLException e)
        {
            System.out.println("Exceção: " + e.getMessage());
        }
    }
}