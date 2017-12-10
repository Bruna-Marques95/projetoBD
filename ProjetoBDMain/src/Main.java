import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Chamar menu da consola de administração:
        menuConsola();
        // Chamar menu da mesa de voto
        //menuMesa();
    }

    /*Função de menu: Função aproveitada da meta 1 do projeto de SD*/
    public static void menuConsola() {

        String opcao = "";
        System.out.println("============== MENU : CONSOLA DE ADMINISTRAÇÃO ==============");
        System.out.println("1 - Registar pessoa");
        System.out.println("2 - Gerir faculdades e departamentos");
        System.out.println("3 - Criar eleicao");
        System.out.println("4 - Gerir listas de candidatos a uma eleicao");
        System.out.println("5 - Gerir mesas de voto");
        System.out.println("6 - Alterar propriedades de uma eleicao");
        System.out.println("7 - Consultar detalhes de eleições passadas");
        System.out.println("8 - Votar antecipadamente");
        System.out.println("9 - Alterar dados pessoais");
        System.out.println("10 - Gerir membros de cada mesa de voto");
        System.out.println("11 - Sair da aplicação");

        while (!opcao.equalsIgnoreCase("1") && !opcao.equalsIgnoreCase("2") && !opcao.equalsIgnoreCase("3") && !opcao.equalsIgnoreCase("4") && !opcao.equalsIgnoreCase("5") && !opcao.equalsIgnoreCase("6") && !opcao.equalsIgnoreCase("7") && !opcao.equalsIgnoreCase("8") && !opcao.equalsIgnoreCase("9") && !opcao.equalsIgnoreCase("10") && !opcao.equalsIgnoreCase("11")) {
            System.out.print("Opcao desejada: ");
            Scanner sc = new Scanner(System.in);
            opcao = sc.nextLine();
            opcao = opcao.trim();
        }
        if (opcao.equalsIgnoreCase("1")) {
            System.out.println("Chamou registar pessoa.\n");
            //chamarRegistarPessoa();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("2")) {
            System.out.println("Chamou gerir faculdades e departamentos.\n");
            //chamarGerirDepFac();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("3")) {
            System.out.println("Chamou criar eleicao.\n");
            //chamarCriarEleicao();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("4")) {
            System.out.println("Chamou gerir listas candidatas.\n");
            //chamarGerirListas();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("5")) {
            System.out.println("Chamou gerir mesas voto.\n");
            //chamarGerirMesasVoto();
            menuConsola();
        }
        else if (opcao.equalsIgnoreCase("6")) {
            System.out.println("Chamou alterar propriedades de eleicao.\n");
            //chamarAlterarPropEleicao();
            menuConsola();
        }
        else if (opcao.equalsIgnoreCase("7")) {
            System.out.println("Chamou consultar detalhes de eleicões passadas.\n");
            chamarConsultarDetalhesDeEleicoesPassadas();
            menuConsola();
        }
        else if (opcao.equalsIgnoreCase("8")) {
            System.out.println("Chamou votar antecipadamente.\n");
            //chamarVotarAntecipadamente();
            menuConsola();
        }
        else if (opcao.equalsIgnoreCase("9")) {
            System.out.println("Chamou alterar dados pessoais.\n");
            //chamarAlterarDadosPessoais();
            menuConsola();
        }
        else if (opcao.equalsIgnoreCase("10")) {
            System.out.println("Chamou gerir membros das mesas de voto.\n");
            //chamarAlterarPropEleicao();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("11")) {
            System.out.println("Chamada para desligar a aplicação.\n");
            System.exit(0);
        }
    }

    public static void chamarConsultarDetalhesDeEleicoesPassadas() {
        Data agora = new Data();
        String d = formataData(agora);

        HashMap<Integer,String> eleicoesFechadas = listaEleicoesFechadas(d);

        if(eleicoesFechadas != null) {
            if(eleicoesFechadas.isEmpty()) {
                System.out.println("Neste momento não há eleições fechadas.");
                return ;
            }
            else {
                System.out.println("======== LISTA DE ELEIÇÕES  ========");
                int i = 1;
                while(i <= eleicoesFechadas.size()) {
                    System.out.println(i + " - " + eleicoesFechadas.get(i));
                    i++;
                }
                System.out.println("Introduza o nº correspondente à eleição que deseja consultar");
                int op = 0;
                do {
                    System.out.print("Opção: ");
                    try {
                        Scanner sc = new Scanner(System.in);
                        op = sc.nextInt();
                    } catch(InputMismatchException e){
                        System.out.println("Introduza um inteiro");
                    }
                } while(op < 1 || op > eleicoesFechadas.size());

                String tituloEleicao = eleicoesFechadas.get(op);
                if(consultarDetalhesEleicao(tituloEleicao))
                    System.out.println("Os detalhes da eleição foram consultados com sucesso.");
                else
                    System.out.println("Não foi possível consultar os detalhes da eleição.");
            }
        }
    }

    public static HashMap<Integer,String> listaEleicoesFechadas(String agora) {
        HashMap<Integer,String> elFechadas = new HashMap<>();

        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return null;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT TITULO FROM ELEICAO " +
                        "WHERE DATAFIM < TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS') ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, agora);

            int i = 1;
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                elFechadas.put(i, rs.getString("TITULO"));
                i++;
            }

            return elFechadas;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar as eleições fechadas.\n" + e);
            return null;
        }
    }

    public static boolean consultarDetalhesEleicao(String tituloEleicao) {
        int numVotosBranco, numVotosNulos;
        HashMap<String,Integer> listasEleicao = new HashMap<>();

        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return false;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT VOTOSBRANCO , VOTOSNULOS FROM ELEICAO " +
                        "WHERE TITULO = ? ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, tituloEleicao);

            ResultSet rs = ps.executeQuery();
            rs.next();

            numVotosBranco = rs.getInt("VOTOSBRANCO");
            numVotosNulos = rs.getInt("VOTOSNULOS");

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar o nº de votos em branco e nulos da eleição.\n" + e);
            return false;
        }

        query = "SELECT NOMELISTA , NUM_VOTOS FROM LISTA " +
                         "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, tituloEleicao);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                listasEleicao.put(rs.getString("NOMELISTA"), rs.getInt("NUM_VOTOS"));
            }

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar o nº de votos das listas candidatas.\n" + e);
            return false;
        }

        System.out.println("========== DETALHES DA ELEIÇÃO ==========");
        System.out.println("Votos em branco: " + numVotosBranco);
        System.out.println("Votos nulos: " + numVotosNulos);
        System.out.println(listasEleicao);

        return true;
    }

    /*Função de menu: Função aproveitada da meta 1 do projeto de SD*/
    public static void menuMesa() {
        while(true) {
            int op = 0;
            boolean sai = false;
            System.out.println("========== MENU : MESA DE VOTO ==========");
            System.out.println("1 - Identificar eleitor");
            System.out.println("2 - Fechar mesa de voto");
            do {
                System.out.print("Opção: ");
                try {
                    Scanner sc = new Scanner(System.in);
                    op = sc.nextInt();
                } catch(InputMismatchException e){
                    System.out.println("Introduza um inteiro");
                }
            } while(op != 1 && op != 2);
            switch(op) {
                case 1:
                    chamarIdentificarEleitor(); break;
                case 2:
                    sai = true; break;
            }
            if(sai) System.exit(0);
        }
    }

    public static void chamarIdentificarEleitor() {
        System.out.print("Nº de Cartão de Cidadão: ");
        String CC = null;
        do {
            Scanner sc = new Scanner(System.in);
            CC = sc.nextLine();
        } while(CC == null);

        if(identificarEleitor(Integer.parseInt(CC))) {
            System.out.println("Foi identificado com sucesso.");
            // Chamo aqui a operação 7 do enunciado - Autentificação do eleitor no terminal de voto
            chamarAutentificarEleitor(Integer.parseInt(CC));
        }
    }

    public static boolean identificarEleitor(int numeroCC) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return false;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT  NOME "
                + "FROM PESSOA "
                + "WHERE NUMCC = ? ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setInt(1, numeroCC);

            ResultSet rs = ps.executeQuery();
            rs.next();

            System.out.println("Bem vindo(a) " + rs.getString("NOME") + ".");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro! Credencial errada.\n" + e);
            return false;
        }
    }

    public static void chamarAutentificarEleitor(int numeroCC) {
        System.out.print("Username: ");
        String username = null;
        do {
            Scanner sc = new Scanner(System.in);
            username = sc.nextLine();
        } while(username == null);

        System.out.print("Password: ");
        String password = null;
        do {
            Scanner sc = new Scanner(System.in);
            password = sc.nextLine();
        } while(password == null);

        int numCC = login(username, password);
        if(numCC != 1){
            if(numCC == numeroCC) {
                System.out.println("Foi autentificado com sucesso.");
                // Chamo aqui a operação 8 do enunciado - Votar
                chamarVotar(numCC);
            }
            else {
                System.out.println("Erro! As credenciais inseridas não pertencem ao utilizador com este nº de CC.");
            }
        }
    }

    public static int login(String username, String password) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return -1;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT  NUMCC "
                + "FROM PESSOA "
                + "WHERE NOMEUTILIZADOR = ? and PASSWORD = ? ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            rs.next();

            return rs.getInt("NUMCC");

        } catch (SQLException e) {
            System.out.println("Erro! Credenciais erradas.\n" + e);
            return -1;
        }
    }

    public static void chamarVotar(int numCC) {
        if(votar(numCC)) System.out.println("O seu voto foi registado com sucesso.");
        else System.out.println("O seu voto não foi registado.");
    }

    public static String formataData(Data agora) {
        String d = ""; // Formato data: 'YYYY-MM-DD HH24:MI:SS'

        if(agora.getMes() < 10) {
            if(agora.getDia() < 10) {
                if(agora.getHoras() < 10) {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-0" + agora.getMes() + "-0" + agora.getDia() + " 0" + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-0" + agora.getMes() + "-0" + agora.getDia() + " 0" + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
                else {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-0" + agora.getMes() + "-0" + agora.getDia() + " " + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-0" + agora.getMes() + "-0" + agora.getDia() + " " + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
            }
            else {
                if(agora.getHoras() < 10) {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-0" + agora.getMes() + "-" + agora.getDia() + " 0" + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-0" + agora.getMes() + "-" + agora.getDia() + " 0" + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
                else {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-0" + agora.getMes() + "-" + agora.getDia() + " " + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-0" + agora.getMes() + "-" + agora.getDia() + " " + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
            }
        }
        else {
            if(agora.getDia() < 10) {
                if(agora.getHoras() < 10) {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-" + agora.getMes() + "-0" + agora.getDia() + " 0" + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-" + agora.getMes() + "-0" + agora.getDia() + " 0" + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
                else {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-" + agora.getMes() + "-0" + agora.getDia() + " " + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-" + agora.getMes() + "-0" + agora.getDia() + " " + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
            }
            else {
                if(agora.getHoras() < 10) {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-" + agora.getMes() + "-" + agora.getDia() + " 0" + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-" + agora.getMes() + "-" + agora.getDia() + " 0" + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
                else {
                    if(agora.getMinutos() < 10) {
                        d = agora.getAno() + "-" + agora.getMes() + "-" + agora.getDia() + " " + agora.getHoras() + ":0" + agora.getMinutos() + ":00";
                    }
                    else {
                        d = agora.getAno() + "-" + agora.getMes() + "-" + agora.getDia() + " " + agora.getHoras() + ":" + agora.getMinutos() + ":00";
                    }
                }
            }
        }

        return d;
    }

    public static boolean votar(int numCC) {
        Data agora = new Data();
        String d = formataData(agora);

        HashMap<Integer,String> eleicoesAbertas = listaEleicoesAbertas(d);

        if(eleicoesAbertas != null) {
            if(eleicoesAbertas.isEmpty()) {
                System.out.println("Neste momento não há eleições a decorrer.");
                return false;
            }
            else{
                System.out.println("======== LISTA DE ELEIÇÕES  ========");
                int i = 1;
                while(i <= eleicoesAbertas.size()) {
                    System.out.println(i + " - " + eleicoesAbertas.get(i));
                    i++;
                }
                System.out.println("Introduza o nº correspondente à eleição que deseja votar");
                int op = 0;
                do {
                    System.out.print("Opção: ");
                    try {
                        Scanner sc = new Scanner(System.in);
                        op = sc.nextInt();
                    } catch(InputMismatchException e){
                        System.out.println("Introduza um inteiro");
                    }
                } while(op < 1 || op > eleicoesAbertas.size());

                if (verificaSeJaVotou(numCC, eleicoesAbertas.get(op))) {
                    return false;
                }
                else {
                    String tituloEleicao = eleicoesAbertas.get(op);
                    String tipoEleicao = getInfoEleicao(tituloEleicao)[0];
                    String nomeUOEleicao = getInfoEleicao(tituloEleicao)[1];
                    String tipoPessoa = getInfoPessoa(numCC)[0];
                    String nomeUOPessoa = getInfoPessoa(numCC)[1];

                    HashMap<Integer,String> opcoesParaVotar = listaOpcoesParaVotar(tituloEleicao, tipoEleicao, nomeUOEleicao, tipoPessoa, nomeUOPessoa);

                    if(opcoesParaVotar != null) {
                        if(opcoesParaVotar.isEmpty()) {
                            System.out.println("Não existem listas candidatas a esta eleição em que possa votar.");
                            return false;
                        }
                        else {
                            System.out.println("======== LISTAS CANDIDATAS  ========");
                            int j = 1;
                            while(j <= opcoesParaVotar.size()) {
                                System.out.println(j + " - " + opcoesParaVotar.get(j));
                                j++;
                            }
                            System.out.println("Introduza o nº correspondente ao seu voto");
                            int op1 = 0;
                            do {
                                System.out.print("Opção: ");
                                try {
                                    Scanner sc = new Scanner(System.in);
                                    op1 = sc.nextInt();
                                } catch(InputMismatchException e){
                                    System.out.println("Introduza um inteiro");
                                }
                            } while(op1 < 1 || op1 > opcoesParaVotar.size());

                            if(registarVoto(eleicoesAbertas.get(op), opcoesParaVotar.get(op1), numCC, d)) return true;
                            else return false;
                        }
                    }

                    return false;
                }
            }
        }

        return false;
    }

    public static HashMap<Integer,String> listaEleicoesAbertas(String agora) {
        HashMap<Integer,String> elAbertas = new HashMap<>();

        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return null;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT  TITULO "
                + "FROM ELEICAO "
                + "WHERE DATAINICIO <= TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS') and DATAFIM >= TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS') ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, agora);
            ps.setString(2, agora);

            int i = 1;
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                elAbertas.put(i, rs.getString("TITULO"));
                i++;
            }

            return elAbertas;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar as eleições abertas.\n" + e);
            return null;
        }
    }

    public static boolean verificaSeJaVotou(int numCC, String tituloEleicao) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return false;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT MESA_VOTOID FROM MESA_VOTO_ELEICAO " +
                        "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

        int idMesa;
        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, tituloEleicao);

            ResultSet rs = ps.executeQuery();
            rs.next();

            idMesa = rs.getInt("MESA_VOTOID");

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar a mesa de voto da eleição.\n" + e);
            return false;
        }

        query = "SELECT VOTAROUASSOCIAR FROM MESA_VOTO_PESSOA " +
                "WHERE PESSOANUMCC = ? and MESA_VOTOID = ? ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setInt(1, numCC);
            ps.setInt(2, idMesa);

            ResultSet rs = ps.executeQuery();
            rs.next();

            int votarOuAssociar = rs.getInt("VOTAROUASSOCIAR");
            if(votarOuAssociar == 1) {
                System.out.println("Já votou nesta eleição.");
                return true;
            }
            else return false;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar a mesa de voto em que o eleitor votou porque não existe.\n" + e);
            return false;
        }
    }

    public static String [] getInfoEleicao(String tituloEleicao) {
        String infoEleicao [] = {"",""};

        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return null;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT TIPO , UNIDADEORGANICANOME FROM ELEICAO " +
                        "WHERE TITULO = ? ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, tituloEleicao);

            ResultSet rs = ps.executeQuery();
            rs.next();

            infoEleicao[0] = rs.getString("TIPO");
            infoEleicao[1] = rs.getString("UNIDADEORGANICANOME");

            return infoEleicao;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar o tipo e unidade orgânica da eleição.\n" + e);
            return null;
        }
    }

    public static String [] getInfoPessoa(int numCC) {
        String infoPessoa [] = {"",""};

        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return null;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT TIPO , UNIDADEORGANICANOME FROM PESSOA " +
                        "WHERE NUMCC = ? ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setInt(1, numCC);

            ResultSet rs = ps.executeQuery();
            rs.next();

            infoPessoa[0] = rs.getString("TIPO");
            infoPessoa[1] = rs.getString("UNIDADEORGANICANOME");

            return infoPessoa;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar o tipo e unidade orgânica do eleitor.\n" + e);
            return null;
        }
    }

    public static HashMap<Integer,String> listaOpcoesParaVotar(String tituloEleicao, String tipoEleicao, String nomeUOEleicao, String tipoPessoa, String nomeUOPessoa) {
        HashMap<Integer,String> opVotar = new HashMap<>();

        if(tipoEleicao.equalsIgnoreCase("Nucleo")) {
            if(tipoPessoa.equalsIgnoreCase("Aluno") && nomeUOPessoa.equalsIgnoreCase(nomeUOEleicao)) {
                Connection conexao = null;
                try {
                    conexao = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:xe",
                            "utilizadorBD",
                            "utilizadorBD");
                } catch (SQLException e) {
                    System.out.println("Erro! A ligação falhou.");
                    e.printStackTrace();
                    return null;
                }

                if (conexao != null) {
                    System.out.println("A ligação à base de dados foi efectuada com sucesso.");
                }
                else {
                    System.out.println("Não foi possível estabelecer ligação com a base de dados.");
                }

                String query = "SELECT NOMELISTA FROM LISTA " +
                        "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

                try(PreparedStatement ps = conexao.prepareStatement(query)) {
                    ps.setString(1, tituloEleicao);

                    int i = 1;
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()) {
                        opVotar.put(i, rs.getString("NOMELISTA"));
                        i++;
                    }
                    if(i != 1){
                        opVotar.put(i, "Voto em branco");
                        i++;
                        opVotar.put(i, "Voto nulo");
                    }

                    return opVotar;

                } catch (SQLException e) {
                    System.out.println("Ocorreu um erro ao procurar as listas candidatas da eleição.\n" + e);
                    return null;
                }
            }
            else System.out.println("Não pode votar nesta eleição.");
        }
        else if(tipoEleicao.equalsIgnoreCase("Conselho Geral")) {
            Connection conexao = null;
            try {
                conexao = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "utilizadorBD",
                        "utilizadorBD");
            } catch (SQLException e) {
                System.out.println("Erro! A ligação falhou.");
                e.printStackTrace();
                return null;
            }

            if (conexao != null) {
                System.out.println("A ligação à base de dados foi efectuada com sucesso.");
            }
            else {
                System.out.println("Não foi possível estabelecer ligação com a base de dados.");
            }

            String query = "SELECT NOMELISTA FROM LISTA " +
                    "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, tituloEleicao);

                int i = 1;
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    if(verificaTipoLista(rs.getString("NOMELISTA"), tipoPessoa)) {
                        opVotar.put(i, rs.getString("NOMELISTA"));
                        i++;
                    }
                }
                if(i != 1){
                    opVotar.put(i, "Voto em branco");
                    i++;
                    opVotar.put(i, "Voto nulo");
                }

                return opVotar;

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar as listas candidatas da eleição.\n" + e);
                return null;
            }
        }
        else {
            if(tipoPessoa.equalsIgnoreCase("Professor") && nomeUOPessoa.equalsIgnoreCase(nomeUOEleicao)) {
                Connection conexao = null;
                try {
                    conexao = DriverManager.getConnection(
                            "jdbc:oracle:thin:@localhost:1521:xe",
                            "utilizadorBD",
                            "utilizadorBD");
                } catch (SQLException e) {
                    System.out.println("Erro! A ligação falhou.");
                    e.printStackTrace();
                    return null;
                }

                if (conexao != null) {
                    System.out.println("A ligação à base de dados foi efectuada com sucesso.");
                }
                else {
                    System.out.println("Não foi possível estabelecer ligação com a base de dados.");
                }

                String query = "SELECT NOMELISTA FROM LISTA " +
                        "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

                try(PreparedStatement ps = conexao.prepareStatement(query)) {
                    ps.setString(1, tituloEleicao);

                    int i = 1;
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()) {
                        opVotar.put(i, rs.getString("NOMELISTA"));
                        i++;
                    }
                    if(i != 1){
                        opVotar.put(i, "Voto em branco");
                        i++;
                        opVotar.put(i, "Voto nulo");
                    }

                    return opVotar;

                } catch (SQLException e) {
                    System.out.println("Ocorreu um erro ao procurar as listas candidatas da eleição.\n" + e);
                    return null;
                }
            }
            else System.out.println("Não pode votar nesta eleição.");
        }

        return opVotar;
    }

    public static boolean verificaTipoLista(String nomeLista, String tipoPessoa) {
        String tipoLista = "";

        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Erro! A ligação falhou.");
            e.printStackTrace();
            return false;
        }

        if (conexao != null) {
            System.out.println("A ligação à base de dados foi efectuada com sucesso.");
        }
        else {
            System.out.println("Não foi possível estabelecer ligação com a base de dados.");
        }

        String query = "SELECT TIPOLISTA FROM LISTA " +
                        "WHERE NOMELISTA = ? ";

        try(PreparedStatement ps = conexao.prepareStatement(query)) {
            ps.setString(1, nomeLista);

            ResultSet rs = ps.executeQuery();
            rs.next();

            tipoLista = rs.getString("TIPOLISTA");
            if(tipoLista.equalsIgnoreCase(tipoPessoa))
                return true;
            else
                return false;

        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao procurar o tipo da lista candidata.\n" + e);
            return false;
        }
    }

    // Regista voto na base de dados
    public static boolean registarVoto(String tituloEleicao, String voto, int numCC, String agora) {
        if(voto.equalsIgnoreCase("Voto em branco")) { // Caso o voto seja em branco
            Connection conexao = null;
            try {
                conexao = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "utilizadorBD",
                        "utilizadorBD");
            } catch (SQLException e) {
                System.out.println("Erro! A ligação falhou.");
                e.printStackTrace();
                return false;
            }

            if (conexao != null) {
                System.out.println("A ligação à base de dados foi efectuada com sucesso.");
            }
            else {
                System.out.println("Não foi possível estabelecer ligação com a base de dados.");
            }

            String query = "SELECT VOTOSBRANCO FROM ELEICAO " +
                            "WHERE TITULO = ?";

            int numVotosBranco;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, tituloEleicao);

                ResultSet rs = ps.executeQuery();
                rs.next();

                numVotosBranco =  rs.getInt("VOTOSBRANCO");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar o nº de votos em branco da eleição.\n" + e);
                return false;
            }

            query = "UPDATE ELEICAO " +
                    "SET VOTOSBRANCO = ? " +
                    "WHERE TITULO = ? ";

            numVotosBranco++;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, numVotosBranco);
                ps.setString(2, tituloEleicao);

                ps.executeUpdate();
                conexao.commit();

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao editar o nº de votos em branco da eleição.\n" + e);
                return false;
            }

            query = "SELECT MESA_VOTOUONOME, MESA_VOTOID FROM MESA_VOTO_ELEICAO " +
                    "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

            String nomeUOMesa;
            int idMesa;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, tituloEleicao);

                ResultSet rs = ps.executeQuery();
                rs.next();

                nomeUOMesa = rs.getString("MESA_VOTOUONOME");
                idMesa = rs.getInt("MESA_VOTOID");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar a mesa de voto da eleição.\n" + e);
                return false;
            }

            query = "SELECT UNIDADEORGANICANOME FROM PESSOA " +
                    "WHERE NUMCC = ? ";

            String nomeUOPessoa;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, numCC);

                ResultSet rs = ps.executeQuery();
                rs.next();

                nomeUOPessoa = rs.getString("UNIDADEORGANICANOME");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar a unidade orgânica do eleitor.\n" + e);
                return false;
            }

            query = "INSERT INTO MESA_VOTO_PESSOA(Mesa_VotoUONome, Mesa_VotoID, PessoaNumCC, PessoaUnidadeOrganicaNome, votarOuAssociar, dataVoto)"
                    + "VALUES ((SELECT UONome FROM MESA_VOTO WHERE UONome = ? and ID = ? ), (SELECT ID FROM MESA_VOTO WHERE ID = ? ), (SELECT NumCC FROM PESSOA WHERE NumCC = ? ), (SELECT Nome FROM UNIDADEORGANICA WHERE Nome = ? ), ? , TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'))";

            int votarOuAssociar = 1; // 1 - Votar, 2 - Associar
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, nomeUOMesa);
                ps.setInt(2, idMesa);
                ps.setInt(3, idMesa);
                ps.setInt(4, numCC);
                ps.setString(5, nomeUOPessoa);
                ps.setInt(6, votarOuAssociar);
                ps.setString(7, agora);

                ps.executeUpdate();
                conexao.commit();
                return true;

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao criar uma Mesa_Voto_Pessoa.\n" + e);
                return false;
            }
        }
        else if(voto.equalsIgnoreCase("Voto nulo")) { // Caso o voto seja nulo
            Connection conexao = null;
            try {
                conexao = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "utilizadorBD",
                        "utilizadorBD");
            } catch (SQLException e) {
                System.out.println("Erro! A ligação falhou.");
                e.printStackTrace();
                return false;
            }

            if (conexao != null) {
                System.out.println("A ligação à base de dados foi efectuada com sucesso.");
            }
            else {
                System.out.println("Não foi possível estabelecer ligação com a base de dados.");
            }

            String query = "SELECT VOTOSNULOS FROM ELEICAO " +
                            "WHERE TITULO = ?";

            int numVotosNulos;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, tituloEleicao);

                ResultSet rs = ps.executeQuery();
                rs.next();

                numVotosNulos =  rs.getInt("VOTOSNULOS");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar o nº de votos nulos da eleição.\n" + e);
                return false;
            }

            query = "UPDATE ELEICAO " +
                    "SET VOTOSNULOS = ? " +
                    "WHERE TITULO = ? ";

            numVotosNulos++;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, numVotosNulos);
                ps.setString(2, tituloEleicao);

                ps.executeUpdate();
                conexao.commit();

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao editar o nº de votos nulos da eleição.\n" + e);
                return false;
            }

            query = "SELECT MESA_VOTOUONOME, MESA_VOTOID FROM MESA_VOTO_ELEICAO " +
                    "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

            String nomeUOMesa;
            int idMesa;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, tituloEleicao);

                ResultSet rs = ps.executeQuery();
                rs.next();

                nomeUOMesa = rs.getString("MESA_VOTOUONOME");
                idMesa = rs.getInt("MESA_VOTOID");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar a mesa de voto da eleição.\n" + e);
                return false;
            }

            query = "SELECT UNIDADEORGANICANOME FROM PESSOA " +
                    "WHERE NUMCC = ? ";

            String nomeUOPessoa;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, numCC);

                ResultSet rs = ps.executeQuery();
                rs.next();

                nomeUOPessoa = rs.getString("UNIDADEORGANICANOME");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar a unidade orgânica do eleitor.\n" + e);
                return false;
            }

            query = "INSERT INTO MESA_VOTO_PESSOA(Mesa_VotoUONome, Mesa_VotoID, PessoaNumCC, PessoaUnidadeOrganicaNome, votarOuAssociar, dataVoto)"
                    + "VALUES ((SELECT UONome FROM MESA_VOTO WHERE UONome = ? and ID = ? ), (SELECT ID FROM MESA_VOTO WHERE ID = ? ), (SELECT NumCC FROM PESSOA WHERE NumCC = ? ), (SELECT Nome FROM UNIDADEORGANICA WHERE Nome = ? ), ? , TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'))";

            int votarOuAssociar = 1; // 1 - Votar, 2 - Associar
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, nomeUOMesa);
                ps.setInt(2, idMesa);
                ps.setInt(3, idMesa);
                ps.setInt(4, numCC);
                ps.setString(5, nomeUOPessoa);
                ps.setInt(6, votarOuAssociar);
                ps.setString(7, agora);

                ps.executeUpdate();
                conexao.commit();
                return true;

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao criar uma Mesa_Voto_Pessoa.\n" + e);
                return false;
            }
        }
        else { // Caso o voto seja numa lista candidata
            Connection conexao = null;
            try {
                conexao = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:xe",
                        "utilizadorBD",
                        "utilizadorBD");
            } catch (SQLException e) {
                System.out.println("Erro! A ligação falhou.");
                e.printStackTrace();
                return false;
            }

            if (conexao != null) {
                System.out.println("A ligação à base de dados foi efectuada com sucesso.");
            }
            else {
                System.out.println("Não foi possível estabelecer ligação com a base de dados.");
            }

            String query = "SELECT NUM_VOTOS FROM LISTA " +
                            "WHERE NOMELISTA = ? ";

            int numVotos;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, voto);

                ResultSet rs = ps.executeQuery();
                rs.next();

                numVotos =  rs.getInt("NUM_VOTOS");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar o nº de votos da lista candidata da eleição.\n" + e);
                return false;
            }

            query = "UPDATE LISTA " +
                    "SET NUM_VOTOS = ? " +
                    "WHERE NOMELISTA = ? ";

            numVotos++;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, numVotos);
                ps.setString(2, voto);

                ps.executeUpdate();
                conexao.commit();

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao editar o nº de votos da lista candidata da eleição.\n" + e);
                return false;
            }

            query = "SELECT MESA_VOTOUONOME, MESA_VOTOID FROM MESA_VOTO_ELEICAO " +
                    "WHERE ELEICAOID in (SELECT ID FROM ELEICAO WHERE TITULO = ? )";

            String nomeUOMesa;
            int idMesa;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, tituloEleicao);

                ResultSet rs = ps.executeQuery();
                rs.next();

                nomeUOMesa = rs.getString("MESA_VOTOUONOME");
                idMesa = rs.getInt("MESA_VOTOID");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar a mesa de voto da eleição.\n" + e);
                return false;
            }

            query = "SELECT UNIDADEORGANICANOME FROM PESSOA " +
                    "WHERE NUMCC = ? ";

            String nomeUOPessoa;
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setInt(1, numCC);

                ResultSet rs = ps.executeQuery();
                rs.next();

                nomeUOPessoa = rs.getString("UNIDADEORGANICANOME");

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao procurar a unidade orgânica do eleitor.\n" + e);
                return false;
            }

            query = "INSERT INTO MESA_VOTO_PESSOA(Mesa_VotoUONome, Mesa_VotoID, PessoaNumCC, PessoaUnidadeOrganicaNome, votarOuAssociar, dataVoto)"
                    + "VALUES ((SELECT UONome FROM MESA_VOTO WHERE UONome = ? and ID = ? ), (SELECT ID FROM MESA_VOTO WHERE ID = ? ), (SELECT NumCC FROM PESSOA WHERE NumCC = ? ), (SELECT Nome FROM UNIDADEORGANICA WHERE Nome = ? ), ? , TO_TIMESTAMP(?, 'YYYY/MM/DD HH24:MI:SS'))";

            int votarOuAssociar = 1; // 1 - Votar, 2 - Associar
            try(PreparedStatement ps = conexao.prepareStatement(query)) {
                ps.setString(1, nomeUOMesa);
                ps.setInt(2, idMesa);
                ps.setInt(3, idMesa);
                ps.setInt(4, numCC);
                ps.setString(5, nomeUOPessoa);
                ps.setInt(6, votarOuAssociar);
                ps.setString(7, agora);

                ps.executeUpdate();
                conexao.commit();
                return true;

            } catch (SQLException e) {
                System.out.println("Ocorreu um erro ao criar uma Mesa_Voto_Pessoa.\n" + e);
                return false;
            }
        }
    }
}