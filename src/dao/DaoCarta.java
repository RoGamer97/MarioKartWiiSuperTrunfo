package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
            throw new RuntimeException("[DaoCarta] Erro ao iniciar Banco de Dados! " + e);
        }
    }

    public List<Carta> getTodasCartas()
    {
        String query = "SELECT * FROM veiculos";
        List<Carta> listaCartas = new ArrayList<>();

        try (Connection conexao = DriverManager.getConnection(properties.getProperty("db.url"));
             PreparedStatement operacao = conexao.prepareStatement(query);
             ResultSet resultado = operacao.executeQuery())
        {
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
                listaCartas.add(carta);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException("[DaoCarta] Erro ao obter cartas do banco!", e);
        }

        return listaCartas;
    }
}