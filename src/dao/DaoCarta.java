package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import modelo.Baralho;
import modelo.Carta;
import modelo.Debug;

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
            throw new RuntimeException("[DaoCarta] Erro ao iniciar Banco de Dados! " + e);
        }
    }

    public void adicionarCartasBaralho(Baralho baralho)
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
                
                carta.setImagem(resultado.getBytes("imagem"));
                
                baralho.adicionarCarta(carta);
            }

            resultado.close();
            operacao.close();
            conexao.close();
        }
        catch (Exception e)
        {
            throw new RuntimeException("[DaoCarta] Erro ao obter cartas do Banco de Dados! " + e);
        }
    }
}