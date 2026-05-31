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
                
                // NOVA ADIÇÃO: Obtém os bytes da imagem salvos no SQLite
                carta.setImagem(resultado.getBytes("imagem"));
                
                baralho.adicionarCarta(carta);
                
                if (!Debug.DEBUG_PRINTS_ENABLED)
                {
                    continue;
                }
                
                System.out.println("[DaoCarta] Carta adicionada ao baralho (" + resultado.getString("nome") + " | Index BD: " + resultado.getInt("id") + " | Index Array: " + idx + ")");
                
                idx++;
            }

            resultado.close();
            operacao.close();
            conexao.close();
            
            if (!Debug.DEBUG_PRINTS_ENABLED)
            {
                return;
            }
            
            System.out.println("[DaoCarta] Todas as cartas foram sorteadas\n");
        }
        catch (Exception e)
        {
            throw new RuntimeException("[DaoCarta] Erro ao obter cartas do Banco de Dados! " + e);
        }
    }
}