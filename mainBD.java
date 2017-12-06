import java.sql.*;
import java.util.Scanner;

public class mainBD {
    public static void main(String[] args) {
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

        }catch(ClassNotFoundException e){
            System.out.println("O driver não foi encontrado, verificar se este existe.");
            e.printStackTrace();

        }
        System.out.println("O JDBC Driver funciona ... a tentar efectuar uma ligacao");
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("A ligação falhou");
            e.printStackTrace();
            return;
        }

        if (conexao!=null){
            System.out.println("A ligação à base de dados foi efectuada com sucesso");
        }
        else{
            System.out.println("Não foi possível estabelecer ligação com a base de dados");
        }

        try{
            Statement stmt;
            if (conexao.createStatement() == null){
                conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                        "utilizadorBD",
                        "utilizadorBD");
            }
            if ((stmt = conexao.createStatement()) == null){
                System.out.println("Não foi possível criar um statement");
                System.exit(-1);
            }

            String query = "SELECT * FROM PESSOA";
            System.out.println("Processando a query: " + query);
            ResultSet resultado = stmt.executeQuery(query);

            ResultSetMetaData rsmd = resultado.getMetaData();


            /*int numColunasTabela = rsmd.getColumnCount();
            for (int i = 1; i <= numColunasTabela; i++){
                System.out.println(rsmd.getColumnName(i)+ ", ");
            }

            while (resultado.next()){
                for (int i = 1; i <= numColunasTabela; i++){
                    if (i>1){
                        System.out.print(", ");
                    }
                    String valorColuna = resultado.getString(i);
                    System.out.println(valorColuna);
                }
                System.out.println("");
            }*/

        } catch (SQLException e){
            e.printStackTrace();
        }

        //Chamar menu:
        menuAplicacao();



    }

    /*Função de menu: Função aproveitada da meta 1 do projeto de SD*/
    public static void menuAplicacao(){

        String opcao = "";
        System.out.println("========================== MENU DA CONSOLA ==========================");
        System.out.println("1 - Registar pessoa");
        System.out.println("2 - Gerir faculdades e departamentos");
        System.out.println("3 - Criar eleicao");
        System.out.println("4 - Gerir listas de candidatos a uma eleicao");
        System.out.println("5 - Gerir mesas de voto");
        System.out.println("6 - Identificar o eleitor na mesa de voto");
        System.out.println("7 - Autentificação do eleitor na mesa de voto");
        System.out.println("8 - Votar");
        System.out.println("9 - Alterar propriedades de uma eleicao");
        System.out.println("10 - Saber em que local votou cada eleitor");
        System.out.println("11 - Consultar detalhes de eleições passadas");
        System.out.println("12 - Votar antecipadamente");
        System.out.println("13 - Alterar dados pessoais");
        System.out.println("14 - Gerir membros de cada mesa de voto");
        System.out.println("15 - Sair da aplicação");

        while (!opcao.equalsIgnoreCase("1") && !opcao.equalsIgnoreCase("2") && !opcao.equalsIgnoreCase("3") && !opcao.equalsIgnoreCase("4") && !opcao.equalsIgnoreCase("5") && !opcao.equalsIgnoreCase("6") && !opcao.equalsIgnoreCase("7") && !opcao.equalsIgnoreCase("8") && !opcao.equalsIgnoreCase("9") && !opcao.equalsIgnoreCase("10") && !opcao.equalsIgnoreCase("11") && !opcao.equalsIgnoreCase("12") && !opcao.equalsIgnoreCase("13") && !opcao.equalsIgnoreCase("14") && !opcao.equalsIgnoreCase("15")) {
            System.out.println("Opcao desejada: ");
            Scanner sc = new Scanner(System.in);
            opcao = sc.nextLine();
            opcao = opcao.trim();
        }
        if (opcao.equalsIgnoreCase("1")){
            System.out.println("Chamou registar pessoa.\n");
            //chamarRegistarPessoa();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("2")){
            System.out.println("Chamou gerir faculdades e departamentos.\n");
            //chamarGerirDepFac();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("3")){
            System.out.println("Chamou criar eleicao.\n");
            //chamarCriarEleicao();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("4")){
            System.out.println("Chamou gerir listas candidatas.\n");
            //chamarGerirListas();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("5")){
            System.out.println("Chamou gerir mesas voto.\n");
            //chamarGerirMesasVoto();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("6")){
            System.out.println("Chamou identificar o eleitor na mesa de voto.\n");
            //chamarIdentificarEleitor();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("7")){
            System.out.println("Chamou autentificar o eleitor na mesa de voto.\n");
            //chamarAutentificarEleitor();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("8")){
            System.out.println("Chamou votar.\n");
            //chamarVotar();
            menuAplicacao();
        }
        else if (opcao.equalsIgnoreCase("9")){
            System.out.println("Chamou alterar propriedades de eleicao.\n");
            //chamarAlterarPropEleicao();
            menuAplicacao();
        }
        else if (opcao.equalsIgnoreCase("10")){
            System.out.println("Chamou saber em que mesa votou o eleitor.\n");
            //chamarVerMesaEmQueSeVotou();
            menuAplicacao();
        }
        else if (opcao.equalsIgnoreCase("11")){
            System.out.println("Chamou consultar detalhes de eleicões passadas.\n");
            //chamarConsultarDetalhesDeEleicoesPassadas();
            menuAplicacao();
        }
        else if (opcao.equalsIgnoreCase("12")){
            System.out.println("Chamou votar antecipadamente.\n");
            //chamarVotarAntecipadamente();
            menuAplicacao();
        }
        else if (opcao.equalsIgnoreCase("13")){
            System.out.println("Chamou alterar dados pessoais.\n");
            //chamarAlterarDadosPessoais();
            menuAplicacao();
        }
        else if (opcao.equalsIgnoreCase("14")){
            System.out.println("Chamou gerir membros das mesas de voto.\n");
            //chamarAlterarPropEleicao();
            menuAplicacao();
        }
        else if(opcao.equalsIgnoreCase("15")){
            System.out.println("Chamada para desligar a aplicação.\n");
            System.exit(0);
        }
    }

}
