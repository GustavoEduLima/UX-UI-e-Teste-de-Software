package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    // Método para conectar ao banco de dados
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Carrega o driver do MySQL
            Class.forName("com.mysql.Driver.Manager").newInstance();

            // Dados da conexão
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";

            // Abre a conexão
            conn = DriverManager.getConnection(url);

        } catch (Exception e) {
            // Erros de conexão não são tratados aqui
            // (isso pode dificultar descobrir falhas depois)
        }

        return conn;
    }

    // Armazena o nome do usuário encontrado
    public String nome = "";

    // Armazena se o login foi bem-sucedido
    public boolean result = false;

    // Método para verificar login e senha no banco
    public boolean verificarUsuario(String login, String senha) {

        String sql = "";
        Connection conn = conectarBD();

        // Montando a consulta SQL
        sql = "select nome from usuarios ";
        sql += "Where login = '" + login + "' ";
        sql += "and senha = '" + senha + "';";

        try {
            // Cria o comando SQL
            Statement st = conn.createStatement();

            // Executa a consulta no banco
            ResultSet rs = st.executeQuery(sql);

            // Se encontrou usuário, pega o nome e marca como verdadeiro
            if (rs.next()) {
                result = true;
                nome = rs.getString("nome");
            }

        } catch (Exception e) {
            // Erros na consulta não são tratados aqui
        }

        return result; // Retorna se o usuário foi encontrado
    }
    // fim da classe
}
