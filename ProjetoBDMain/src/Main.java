import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Chamar menu da consola de administração:
        //menuConsola();
        // Chamar menu da mesa de voto
        menuMesa();
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
            System.out.print("Opção desejada: ");
            Scanner sc = new Scanner(System.in);
            opcao = sc.nextLine();
            opcao = opcao.trim();
        }
        if (opcao.equalsIgnoreCase("1")) {
            System.out.println("Chamou registar pessoa.\n");
            chamarRegistarPessoa();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("2")) {
            System.out.println("Chamou gerir faculdades e departamentos.\n");
            chamarGerirDepFac();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("3")) {
            System.out.println("Chamou criar eleicao.\n");
            //chamarCriarEleicao();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("4")) {
            System.out.println("Chamou gerir listas candidatas.\n");
            chamarGerirListas();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("5")) {
            System.out.println("Chamou gerir mesas voto.\n");
            //chamarGerirMesasVoto();
            menuConsola();
        }
        else if (opcao.equalsIgnoreCase("6")) {
            System.out.println("Chamou alterar propriedades de eleicao.\n");
            chamarAlterarPropEleicao();
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
            chamarAlterarDadosPessoais();
            menuConsola();
        }
        else if (opcao.equalsIgnoreCase("10")) {
            System.out.println("Chamou gerir membros das mesas de voto.\n");
            //chamarGerirMembrosMesaVoto();
            menuConsola();
        }
        else if(opcao.equalsIgnoreCase("11")) {
            System.out.println("Chamada para desligar a aplicação.\n");
            System.exit(0);
        }
    }

    // Função para chamar o método de registar pessoa: reutilizada da meta 1 do projeto de SD
    public static void chamarRegistarPessoa() {
        String argumentos;
        System.out.println("Insira os argumentos pela respectiva ordem e separados por ';' :");
        System.out.println("nome da pessoa;nomeUtilizador;password;telefone;morada;numeroCC;dataValidadeCC[dia-mes-ano];nome da unidade orgânica;função");
        Scanner sc = new Scanner(System.in);
        argumentos = sc.nextLine();
        //argumentos = "Bruna Marques;barm;barm;876543219;Coimbra;23456789;15-12-2017;FEUC;professor";
        //argumentos = "Maria Pedroso;mfp;mfp;987654321;Poiares;12345678;23-12-2017;FEUC;aluno";
        //argumentos = "Alda Fernandes;alfer;alfer;432198765;Ribas;45678912;22-12-2017;FEUC;funcionario";

        String[] arrayArgumentos = argumentos.split(";");
        if(arrayArgumentos.length != 9) {
            System.out.println("Erro no número de argumentos.");
            menuConsola();
        }

        String validadeCC = "";
        int numCC = 0, telefone = 0;
        // dataValCCArray= [0-dia:1-mes:2-ano]
        if(verificaIDCC(arrayArgumentos[5]) == false || verificaTelefone(arrayArgumentos[3]) == false){
            System.out.println("O número de Cartão de Cidadão inserido é inválido. / O número de telefone inserido é inválido.");
            menuConsola();
        }
        else{
            numCC = Integer.parseInt(arrayArgumentos[5]);
            telefone = Integer.parseInt(arrayArgumentos[3]);

            int tamanhoArrayDataValidadeCC = arrayArgumentos[6].split("-").length;
            if (tamanhoArrayDataValidadeCC != 3){
                System.out.println("Não inseriu o número correto de elementos para a data de validade do Cartão de Cidadão.");
                menuConsola();
            }
            else{
                String [] arrayStringDataValidadeCC = arrayArgumentos[6].split("-");
                int[] arrayValoresDataValidadeCC = {0,0,0};
                try{

                    arrayValoresDataValidadeCC[0] = Integer.parseInt(arrayStringDataValidadeCC[0]);
                    arrayValoresDataValidadeCC[1] = Integer.parseInt(arrayStringDataValidadeCC[1]);
                    arrayValoresDataValidadeCC[2] = Integer.parseInt(arrayStringDataValidadeCC[2]);
                    System.out.println(arrayValoresDataValidadeCC[0] + " : " + arrayValoresDataValidadeCC[1] + " : " + arrayValoresDataValidadeCC[2]);

                }catch(NumberFormatException e){
                    System.out.println("Erro. Os valores introduzidos não são do tipo correto para a data de validade do Cartão de Cidadão.");
                }

                Data agora = new Data();
                Data dataValidadeCC = new Data(arrayValoresDataValidadeCC[0], arrayValoresDataValidadeCC[1], arrayValoresDataValidadeCC[2], 0, 0);
                validadeCC = formataData2(dataValidadeCC);

                if (agora.validaDatas(agora, dataValidadeCC) == false){
                    System.out.println("A validade do Cartão de Cidadão expirou. Valide novamente o cartão para se registar.");
                    menuConsola();

                }
                if (verificaFuncao(arrayArgumentos[8]) == false){
                    System.out.println("Função da pessoa inválida. Certifique-se que desempenha a função de aluno, professor ou funcionário.");
                    menuConsola();
                }
            }
        }

        if(registarPessoa(arrayArgumentos[0], arrayArgumentos[1], arrayArgumentos[2], telefone, arrayArgumentos[4], validadeCC, numCC, arrayArgumentos[7], arrayArgumentos[8].trim().toLowerCase()))
            System.out.println("A pessoa foi registada com sucesso.");
        else
            System.out.println("A pessoa não foi registada.");
    }

    //Função utilizada para verificar a validade do código de um cartão de cidadão : Função reutilizada da meta 1 do projeto de SD
    public static boolean verificaIDCC(String id){
        try{
            Integer.parseInt(id);
            if(Integer.parseInt(id) >= 10000000 && Integer.parseInt(id) <= 99999999){
                return true;
            }
            else{
                return false;
            }

        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean verificaTelefone(String numeroTel){
        try{
            Integer.parseInt(numeroTel);
            if(Integer.parseInt(numeroTel) >= 100000000 && Integer.parseInt(numeroTel) <= 999999999){
                return true;
            }
            else{
                return false;
            }

        }catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean verificaFuncao(String funcao){
        if (funcao.equalsIgnoreCase("aluno") || funcao.equalsIgnoreCase("professor") || funcao.equalsIgnoreCase("funcionario")){
            return true;
        }
        else{
            return false;
        }
    }

    public static String formataData2(Data data) {
        String d = "";

        if(data.getMes() < 10) {
            if(data.getDia() < 10) {
                d = "0" + data.getDia() + "-0" + data.getMes() + "-" + data.getAno();
            }
            else {
                d = data.getDia() + "-0" + data.getMes() + "-" + data.getAno();
            }
        }
        else {
            if(data.getDia() < 10) {
                d = "0" + data.getDia() + "-" + data.getMes() + "-" + data.getAno();
            }
            else {
                d = data.getDia() + "-" + data.getMes() + "-" + data.getAno();
            }
        }

        return d;
    }

    public static boolean registarPessoa(String nomePessoa, String nomeUtilizador, String password, int numeroTelefone, String morada, String dataValidadeDoCC, int numeroCC, String unidadeOrganica, String funcaoPessoa) {
        System.out.println("1: " + nomePessoa + " | " + "2: " + nomeUtilizador + " | " + "3: " + password + " | " + "4: " + numeroTelefone + " | "+ "5: " + morada + " | " + "6: " + numeroCC + " | " + "7: " + dataValidadeDoCC + " | " +  "8: " + unidadeOrganica + " | " +  "9: " + funcaoPessoa + " | ");

        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
            return false;
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucesso");
        } else {
            System.out.println("Não conseguimos estabelecer a ligação.");
        }

        //(NOME, NOMEUTILIZADOR, PASSWORD, NUMTELEFONE, MORADA, VALIDADECC, NUMCC, UNIDADEORGANICANOME, TIPO)
        String query = "INSERT INTO PESSOA(Nome, NomeUtilizador, Password, NumTelefone, Morada, ValidadeCC, NumCC, UnidadeOrganicaNome, tipo)"
                + "VALUES (?, ?, ?, ?, ?, TO_DATE(?,'DD-MM-YYYY'), ?, (SELECT NOME FROM UNIDADEORGANICA WHERE NOME = ?), NLS_LOWER(?))";

        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1, nomePessoa);
            ps.setString(2, nomeUtilizador);
            ps.setString(3, password);
            ps.setInt(4, numeroTelefone);
            ps.setString(5, morada);
            ps.setString(6, dataValidadeDoCC);
            ps.setInt(7, numeroCC);
            ps.setString(8, unidadeOrganica);
            ps.setString(9, funcaoPessoa);

            ps.executeUpdate();
            connection.commit();
            return true;

        }
        catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    //Função para chamar o método para gerir departamentos e faculdades: Aproveitado da meta 1 do projeto de SD
    public static void chamarGerirDepFac() {
        System.out.println("-- Opções de gestão de faculdades e departamentos --");
        System.out.println("1 - Gerir faculdades");
        System.out.println("    a - Criar faculdade");
        System.out.println("    b - Apagar faculdade");
        System.out.println("    c - Editar faculdade");
        System.out.println("2 - Gerir departamentos");
        System.out.println("    a - Criar departamento");
        System.out.println("    b - Apagar departamento");
        System.out.println("    c - Editar departamento");

        String argumentosOperacaoGeral;
        String argumentoNome;
        String argumentoNovoNome;
        String argumentoNovoNovoNome;

        System.out.println("Insira os argumentos pela respectiva ordem e separados por ';' EX: 1;a ");
        Scanner sc = new Scanner(System.in);
        argumentosOperacaoGeral = sc.nextLine();
        if (argumentosOperacaoGeral.equalsIgnoreCase("1;a") || argumentosOperacaoGeral.equalsIgnoreCase("1;b") || argumentosOperacaoGeral.equalsIgnoreCase("1;c")) {
            System.out.println("Nome da faculdade:");
            if(argumentosOperacaoGeral.equalsIgnoreCase("1;a")) {
                argumentoNome = sc.nextLine();
                gerirDepFac(argumentosOperacaoGeral, argumentoNome, "","");
            }
            else if (argumentosOperacaoGeral.equalsIgnoreCase("1;b")) {
                argumentoNome = sc.nextLine();
                gerirDepFac(argumentosOperacaoGeral, argumentoNome, "","");
            }
            else if (argumentosOperacaoGeral.equalsIgnoreCase("1;c")) {
                argumentoNome = sc.nextLine();
                System.out.println("Novo nome da faculdade");
                argumentoNovoNome = sc.nextLine();
                gerirDepFac(argumentosOperacaoGeral,argumentoNome,argumentoNovoNome,"");
            }
        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("2;a") || argumentosOperacaoGeral.equalsIgnoreCase("2;b") || argumentosOperacaoGeral.equalsIgnoreCase("2;c")) {
            System.out.println("Nome da faculdade:");
            if(argumentosOperacaoGeral.equalsIgnoreCase("2;a")) {
                argumentoNome = sc.nextLine();
                System.out.println("Nome do departamento:");
                argumentoNovoNome = sc.nextLine();
                gerirDepFac(argumentosOperacaoGeral, argumentoNome, argumentoNovoNome,"");
            }
            else if (argumentosOperacaoGeral.equalsIgnoreCase("2;b")) {
                argumentoNome = sc.nextLine();
                System.out.println("Nome do departamento:");
                argumentoNovoNome = sc.nextLine();
                gerirDepFac(argumentosOperacaoGeral, argumentoNome, argumentoNovoNome,"");
            }
            else if (argumentosOperacaoGeral.equalsIgnoreCase("2;c")) {
                argumentoNome = sc.nextLine();
                System.out.println("Nome do departamento:");
                argumentoNovoNome = sc.nextLine();
                System.out.println("Novo nome do departamento:");
                argumentoNovoNovoNome = sc.nextLine();
                gerirDepFac(argumentosOperacaoGeral, argumentoNome, argumentoNovoNome, argumentoNovoNovoNome);
            }
        }
        else {
            System.out.println("Opcao inválida.");
            menuConsola();
        }
    }

    public static boolean gerirDepFac(String argumentosOperacaoGeral, String argumentoNome, String argumentoNovoNome, String argumentoNovoNovoNome){
        boolean successoAlterarDados1 = false;
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
            return false;
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Não conseguimos estabelecer a ligação");
        }

        if (argumentosOperacaoGeral.equalsIgnoreCase("1;a")) {
            String query = "INSERT into FACULDADE"
                    + " VALUES(NLS_UPPER(?),(select nome from UNIDADEORGANICA where nome = NLS_UPPER(?)))";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, argumentoNome);
                ps.setString(2, argumentoNome);
                ps.executeUpdate();
                connection.commit();
                return true;
            }
            catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("1;b")) {
            String query = "DELETE FROM FACULDADE WHERE NOME = NLS_UPPER(?)";
            successoAlterarDados1 = false;
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, argumentoNome);
                String query2 = "DELETE FROM DEPARTAMENTO WHERE FACULDADENOME = NLS_UPPER(?)";
                try(PreparedStatement ps2 = connection.prepareStatement(query2)) {
                    ps2.setString(1, argumentoNome);
                    ps2.executeUpdate();
                    connection.commit();
                    successoAlterarDados1 = true;
                }
                catch (SQLException e) {
                    System.out.println(e);
                    return false;
                }
                if (successoAlterarDados1 == true) { /*Garante que se 1ª operacao tem de ser feita com sucesso para a segunda ocorrer*/
                    ps.executeUpdate();
                    connection.commit();
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("1;c")) {
            successoAlterarDados1 = false;
            String query = "UPDATE FACULDADE SET NOME = NLS_UPPER(?), UONOME = NLS_UPPER(?) WHERE NOME = NLS_UPPER(?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, argumentoNovoNome);
                ps.setString(2, argumentoNovoNome);
                ps.setString(3, argumentoNome);
                String query2 = "UPDATE DEPARTAMENTO SET FACULDADENOME = NLS_UPPER(?) WHERE FACULDADENOME = NLS_UPPER(?)";
                try(PreparedStatement ps2 = connection.prepareStatement(query2)) {
                    ps2.setString(1, argumentoNovoNome);
                    ps2.setString(2, argumentoNome);
                    ps2.executeUpdate();
                    connection.commit();
                    successoAlterarDados1 = true;
                }
                catch (SQLException e) {
                    System.out.println(e);
                    return false;
                }
                if (successoAlterarDados1 == true) { /*Garante que se 1ª operacao tem de ser feita com sucesso para a segunda ocorrer*/
                    ps.executeUpdate();
                    connection.commit();
                    return true;
                }
                else {
                    return false;
                }
            }
            catch (SQLException e) {
                System.out.println(e);
                try {
                    connection.rollback(); /*SE FALHAR A 2ª MODIFICACAO (A DAS FACULDADES- CANCELA A ALTERACAO DOS DEPARTAMENTOS)*/
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                return false;
            }
        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("2;a")) {
            String query = "INSERT into DEPARTAMENTO"
                    + " VALUES(NLS_UPPER(?),(select nome from FACULDADE where nome = NLS_UPPER(?)),(select nome from UNIDADEORGANICA where nome = NLS_UPPER(?)))";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, argumentoNovoNome);
                ps.setString(2, argumentoNome);
                ps.setString(3, argumentoNovoNome);
                ps.executeUpdate();
                connection.commit();
                return true;
            }
            catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("2;b")) {
            String query = "DELETE FROM DEPARTAMENTO WHERE NOME = NLS_UPPER(?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, argumentoNovoNome);
                ps.executeUpdate();
                connection.commit();
                return true;
            }
            catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("2;c")) {
            String query = "UPDATE DEPARTAMENTO SET NOME = NLS_UPPER(?),UNIDADEORGANICANOME = NLS_UPPER(?) " +
                    "WHERE NOME = NLS_UPPER(?)";
            System.out.println(argumentoNome + " " + argumentoNovoNome + " " + argumentoNovoNovoNome);
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, argumentoNovoNovoNome);
                ps.setString(2, argumentoNovoNovoNome);
                ps.setString(3, argumentoNovoNome);
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        }
        return true;
    }

    public static void chamarCriarEleicao(){
        System.out.println("Inserir o tipo de eleicao [Selecione o nº da opção] (1-Conselho Geral(CG), 2-Núcleo(N), 3-Direção de Departamento(DD), 4-Direção de Faculdade(DF))");
        String argumentosOperacaoGeral;
        String argumentos;
        String [] argumentosDataInicio;
        String [] argumentosDataFim;
        int [] valDataInicio = {0,0,0,0,0};
        int [] valdataFim = {0,0,0,0,0};

        Scanner sc = new Scanner(System.in);
        argumentosOperacaoGeral = sc.nextLine();
        if (argumentosOperacaoGeral.trim().equalsIgnoreCase("1")){
            System.out.println("Insira os dados sobre a eleicao no seguinte formato:");
            System.out.println("titulo;breve descricao;data inicio[dia-mes-ano-horas-minutos];data fim[dia-mes-ano-horas-minutos]");
            argumentos = sc.nextLine();
            String[] arrayArgumentos= argumentos.split(";");
            //System.out.println(arrayArgumentos.length);
            if (arrayArgumentos.length != 4){
                System.out.println("Erro no numero de argumentos");
                menuConsola();
            }
            else{
                argumentosDataInicio = arrayArgumentos[2].trim().split("-");
                argumentosDataFim = arrayArgumentos[3].trim().split("-");

                //System.out.println(argumentosDataFim[0] + " " + argumentosDataFim[1] + " " + argumentosDataFim[2] + " " + argumentosDataFim[3] + " " + argumentosDataFim[4]);
                if (argumentosDataInicio.length != 5 || argumentosDataFim.length != 5){
                    System.out.println("Datas introduzidas incorretamente.");
                    menuConsola();
                }
                else{
                    try{
                        valDataInicio[0] = Integer.parseInt(argumentosDataInicio[0]);
                        valDataInicio[1] = Integer.parseInt(argumentosDataInicio[1]);
                        valDataInicio[2] = Integer.parseInt(argumentosDataInicio[2]);
                        valDataInicio[3] = Integer.parseInt(argumentosDataInicio[3]);
                        valDataInicio[4] = Integer.parseInt(argumentosDataInicio[4]);


                        valdataFim[0] = Integer.parseInt(argumentosDataFim[0]);
                        valdataFim[1] = Integer.parseInt(argumentosDataFim[1]);
                        valdataFim[2] = Integer.parseInt(argumentosDataFim[2]);
                        valdataFim[3] = Integer.parseInt(argumentosDataFim[3]);
                        valdataFim[4] = Integer.parseInt(argumentosDataFim[4]);

                    }
                    catch (NumberFormatException e){
                        System.out.println(e);
                        menuConsola();
                    }


                    Calendar calendario = Calendar.getInstance();
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);
                    int mes = calendario.get(Calendar.MONTH)+1;
                    int ano = calendario.get(Calendar.YEAR);
                    int hora = calendario.get(Calendar.HOUR_OF_DAY);
                    int minuto = calendario.get(Calendar.MINUTE);


                    Calendar dataInicioEl = Calendar.getInstance();
                    dataInicioEl.set(valDataInicio[2], valDataInicio[1]-1, valDataInicio[0], valDataInicio[3], valDataInicio[4]);
                    Calendar dataFimEl = Calendar.getInstance();
                    dataFimEl.set(valdataFim[2], valdataFim[1]-1, valdataFim[0], valdataFim[3], valdataFim[4]);

                    if (comparaDatas2(calendario,dataInicioEl) && comparaDatas2(dataInicioEl, dataFimEl)){
                        String novoFormatoDI = valDataInicio[0]+"-"+(valDataInicio[1]-1)+"-"+valDataInicio[2]+" "+valDataInicio[3]+":"+valDataInicio[4];
                        String novoFormatoDF = valdataFim[0]+"-"+(valdataFim[1]-1)+"-"+valdataFim[2]+" "+valdataFim[3]+":"+valdataFim[4];

                        criarEleicao("CG", arrayArgumentos[0], arrayArgumentos[1], novoFormatoDI, novoFormatoDF, "", "");

                    }
                    else{
                        System.out.println("Datas inválidas");
                        menuConsola();
                    }
                }
            }
        }
        else if(argumentosOperacaoGeral.trim().equalsIgnoreCase("2")){
            System.out.println("Insira os dados sobre a eleicao no seguinte formato:");
            System.out.println("titulo;breve descricao;data inicio[dia-mes-ano-horas-minutos];data fim[dia-mes-ano-horas-minutos];nomeFac;nomeNucleo");
            argumentos = sc.nextLine();
            String[] arrayArgumentos = argumentos.split(";");
            if (arrayArgumentos.length != 6){
                System.out.println("Erro no numero de argumentos");
                menuConsola();
            }
            else{
                argumentosDataInicio = arrayArgumentos[2].trim().split("-");
                argumentosDataFim= arrayArgumentos[3].trim().split("-");
                if (argumentosDataInicio.length != 5 || argumentosDataFim.length != 5){
                    System.out.println("Datas introduzidas incorretamente.");
                    menuConsola();
                }
                else{
                    try{
                        valDataInicio[0] = Integer.parseInt(argumentosDataInicio[0]);
                        valDataInicio[1] = Integer.parseInt(argumentosDataInicio[1]);
                        valDataInicio[2] = Integer.parseInt(argumentosDataInicio[2]);
                        valDataInicio[3] = Integer.parseInt(argumentosDataInicio[3]);
                        valDataInicio[4] = Integer.parseInt(argumentosDataInicio[4]);


                        valdataFim[0] = Integer.parseInt(argumentosDataFim[0]);
                        valdataFim[1] = Integer.parseInt(argumentosDataFim[1]);
                        valdataFim[2] = Integer.parseInt(argumentosDataFim[2]);
                        valdataFim[3] = Integer.parseInt(argumentosDataFim[3]);
                        valdataFim[4] = Integer.parseInt(argumentosDataFim[4]);

                    }
                    catch (NumberFormatException e){
                        System.out.println(e);
                        menuConsola();
                    }


                    Calendar calendario = Calendar.getInstance();
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);
                    int mes = calendario.get(Calendar.MONTH)+1;
                    int ano = calendario.get(Calendar.YEAR);
                    int hora = calendario.get(Calendar.HOUR_OF_DAY);
                    int minuto = calendario.get(Calendar.MINUTE);


                    Calendar dataInicioEl = Calendar.getInstance();
                    dataInicioEl.set(valDataInicio[2], valDataInicio[1]-1, valDataInicio[0], valDataInicio[3], valDataInicio[4]);
                    Calendar dataFimEl = Calendar.getInstance();
                    dataFimEl.set(valdataFim[2], valdataFim[1]-1, valdataFim[0], valdataFim[3], valdataFim[4]);

                    if (comparaDatas2(calendario,dataInicioEl) && comparaDatas2(dataInicioEl, dataFimEl)){
                        String novoFormatoDI = valDataInicio[0]+"-"+(valDataInicio[1]-1)+"-"+valDataInicio[2]+" "+valDataInicio[3]+":"+valDataInicio[4];
                        String novoFormatoDF = valdataFim[0]+"-"+(valdataFim[1]-1)+"-"+valdataFim[2]+" "+valdataFim[3]+":"+valdataFim[4];

                        criarEleicao("N", arrayArgumentos[0], arrayArgumentos[1], novoFormatoDI, novoFormatoDF, arrayArgumentos[4], arrayArgumentos[5]);

                    }
                    else{
                        System.out.println("Datas inválidas");
                        menuConsola();
                    }
                }
            }
        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("3")){
            System.out.println("Insira os dados sobre a eleicao no seguinte formato:");
            System.out.println("titulo;breve descricao;data inicio[dia-mes-ano-horas-minutos];data fim[dia-mes-ano-horas-minutos];nomeDep");
            argumentos = sc.nextLine();
            String[] arrayArgumentos = argumentos.split(";");
            if (arrayArgumentos.length != 5){
                System.out.println("Erro no numero de argumentos");
                menuConsola();
            }
            else{
                argumentosDataInicio = arrayArgumentos[2].trim().split("-");
                argumentosDataFim= arrayArgumentos[3].trim().split("-");
                if (argumentosDataInicio.length != 5 || argumentosDataFim.length != 5){
                    System.out.println("Datas introduzidas incorretamente.");
                    menuConsola();
                }
                else{
                    try{
                        valDataInicio[0] = Integer.parseInt(argumentosDataInicio[0]);
                        valDataInicio[1] = Integer.parseInt(argumentosDataInicio[1]);
                        valDataInicio[2] = Integer.parseInt(argumentosDataInicio[2]);
                        valDataInicio[3] = Integer.parseInt(argumentosDataInicio[3]);
                        valDataInicio[4] = Integer.parseInt(argumentosDataInicio[4]);


                        valdataFim[0] = Integer.parseInt(argumentosDataFim[0]);
                        valdataFim[1] = Integer.parseInt(argumentosDataFim[1]);
                        valdataFim[2] = Integer.parseInt(argumentosDataFim[2]);
                        valdataFim[3] = Integer.parseInt(argumentosDataFim[3]);
                        valdataFim[4] = Integer.parseInt(argumentosDataFim[4]);

                    }
                    catch (NumberFormatException e){
                        System.out.println(e);
                        menuConsola();
                    }


                    Calendar calendario = Calendar.getInstance();
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);
                    int mes = calendario.get(Calendar.MONTH)+1;
                    int ano = calendario.get(Calendar.YEAR);
                    int hora = calendario.get(Calendar.HOUR_OF_DAY);
                    int minuto = calendario.get(Calendar.MINUTE);


                    Calendar dataInicioEl = Calendar.getInstance();
                    dataInicioEl.set(valDataInicio[2], valDataInicio[1]-1, valDataInicio[0], valDataInicio[3], valDataInicio[4]);
                    Calendar dataFimEl = Calendar.getInstance();
                    dataFimEl.set(valdataFim[2], valdataFim[1]-1, valdataFim[0], valdataFim[3], valdataFim[4]);

                    if (comparaDatas2(calendario,dataInicioEl) && comparaDatas2(dataInicioEl, dataFimEl)){
                        String novoFormatoDI = valDataInicio[0]+"-"+(valDataInicio[1]-1)+"-"+valDataInicio[2]+" "+valDataInicio[3]+":"+valDataInicio[4];
                        String novoFormatoDF = valdataFim[0]+"-"+(valdataFim[1]-1)+"-"+valdataFim[2]+" "+valdataFim[3]+":"+valdataFim[4];

                        criarEleicao("DD", arrayArgumentos[0], arrayArgumentos[1], novoFormatoDI, novoFormatoDF, arrayArgumentos[4], "");

                    }
                    else{
                        System.out.println("Datas inválidas");
                        menuConsola();
                    }
                }
            }

        }
        else if (argumentosOperacaoGeral.equalsIgnoreCase("4")){
            System.out.println("Insira os dados sobre a eleicao no seguinte formato:");
            System.out.println("titulo;breve descricao;data inicio[dia-mes-ano-horas-minutos];data fim[dia-mes-ano-horas-minutos];nome Faculdade");
            argumentos = sc.nextLine();
            String[] arrayArgumentos = argumentos.split(";");
            if (arrayArgumentos.length != 5){
                System.out.println("Erro no numero de argumentos");
                menuConsola();
            }
            else{
                argumentosDataInicio = arrayArgumentos[2].trim().split("-");
                argumentosDataFim= arrayArgumentos[3].trim().split("-");
                if (argumentosDataInicio.length != 5 || argumentosDataFim.length != 5){
                    System.out.println("Datas introduzidas incorretamente.");
                    menuConsola();
                }
                else{
                    try{
                        valDataInicio[0] = Integer.parseInt(argumentosDataInicio[0]);
                        valDataInicio[1] = Integer.parseInt(argumentosDataInicio[1]);
                        valDataInicio[2] = Integer.parseInt(argumentosDataInicio[2]);
                        valDataInicio[3] = Integer.parseInt(argumentosDataInicio[3]);
                        valDataInicio[4] = Integer.parseInt(argumentosDataInicio[4]);


                        valdataFim[0] = Integer.parseInt(argumentosDataFim[0]);
                        valdataFim[1] = Integer.parseInt(argumentosDataFim[1]);
                        valdataFim[2] = Integer.parseInt(argumentosDataFim[2]);
                        valdataFim[3] = Integer.parseInt(argumentosDataFim[3]);
                        valdataFim[4] = Integer.parseInt(argumentosDataFim[4]);

                    }
                    catch (NumberFormatException e){
                        System.out.println(e);
                        menuConsola();
                    }


                    Calendar calendario = Calendar.getInstance();
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);
                    int mes = calendario.get(Calendar.MONTH)+1;
                    int ano = calendario.get(Calendar.YEAR);
                    int hora = calendario.get(Calendar.HOUR_OF_DAY);
                    int minuto = calendario.get(Calendar.MINUTE);


                    Calendar dataInicioEl = Calendar.getInstance();
                    dataInicioEl.set(valDataInicio[2], valDataInicio[1]-1, valDataInicio[0], valDataInicio[3], valDataInicio[4]);
                    Calendar dataFimEl = Calendar.getInstance();
                    dataFimEl.set(valdataFim[2], valdataFim[1]-1, valdataFim[0], valdataFim[3], valdataFim[4]);

                    if (comparaDatas2(calendario,dataInicioEl) && comparaDatas2(dataInicioEl, dataFimEl)){
                        String novoFormatoDI = valDataInicio[0]+"-"+(valDataInicio[1]-1)+"-"+valDataInicio[2]+" "+valDataInicio[3]+":"+valDataInicio[4];
                        String novoFormatoDF = valdataFim[0]+"-"+(valdataFim[1]-1)+"-"+valdataFim[2]+" "+valdataFim[3]+":"+valdataFim[4];

                        criarEleicao("DF", arrayArgumentos[0], arrayArgumentos[1], novoFormatoDI, novoFormatoDF, arrayArgumentos[4], "");

                    }
                    else{
                        System.out.println("Datas inválidas");
                        menuConsola();
                    }
                }
            }

        }
        else{
            System.out.println("Opcao invalida, selecionar uma opcao valida");
            menuConsola();
        }
    }

    public static boolean criarEleicao(String tipoEleicao, String titulo, String breveDescricao, String dataInicio, String dataFim, String nomeFac, String nomeDep){
        boolean encontrado=false;
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
            return false;
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }
        if (tipoEleicao.equalsIgnoreCase("CG")){
            String query = "INSERT into ELEICAO"
                    + " VALUES(?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),TO_DATE(?, 'DD-MM-YYYY HH24:MI'),0,0,0,?,SEQ_ELEICAO.nextval, (select nome FROM UNIDADEORGANICA WHERE nome='UC'))";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setString(1, titulo);
                ps.setString(2, breveDescricao);
                ps.setString(3, dataInicio);
                ps.setString(4, dataFim);
                ps.setString(5, tipoEleicao);
                ps.executeUpdate();
                connection.commit();
                return true;

            }
            catch (SQLException e){
                System.out.println(e);
                return false;
            }
        }
        else if (tipoEleicao.equalsIgnoreCase("N")){
            String searchQuery = "SELECT COUNT(*) AS CONTAGEM from NUCLEOESTUDANTES where NOMENUCLEO = ? AND FACULDADENOME = ?";
            try (PreparedStatement psQueryCount = connection.prepareStatement(searchQuery)) {
                psQueryCount.setString(1,nomeDep);
                psQueryCount.setString(2,nomeFac);
                ResultSet rs = psQueryCount.executeQuery();
                rs.next();
                if (rs.getInt("CONTAGEM")==1){
                    encontrado=true;

                }else {
                    System.out.println("Nao existe nucleo. Nao pode criar uma eleicao");
                    return false;
                }


            }
            catch (SQLException e){
                System.out.println(e);
                return false;
            }
            if (encontrado==true) {
                String query = "INSERT into ELEICAO"
                        + " VALUES(?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),TO_DATE(?, 'DD-MM-YYYY HH24:MI'),0,0,0,?,SEQ_ELEICAO.nextval, (select nome FROM UNIDADEORGANICA WHERE nome=?))";
                try (PreparedStatement ps = connection.prepareStatement(query)) {
                    ps.setString(1, titulo);
                    ps.setString(2, breveDescricao);
                    ps.setString(3, dataInicio);
                    ps.setString(4, dataFim);
                    ps.setString(5, tipoEleicao);
                    ps.setString(6, nomeFac);
                    ps.executeUpdate();
                    connection.commit();
                    return true;

                } catch (SQLException e) {
                    System.out.println(e);
                    return false;
                }
            }else {
                return false;
            }

        }
        else if (tipoEleicao.equalsIgnoreCase("DD")){

            String query = "INSERT into ELEICAO"
                    + " VALUES(?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),TO_DATE(?, 'DD-MM-YYYY HH24:MI'),0,0,0,?,SEQ_ELEICAO.nextval, (select nome FROM DEPARTAMENTO WHERE nome=?))";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setString(1, titulo);
                ps.setString(2, breveDescricao);
                ps.setString(3, dataInicio);
                ps.setString(4, dataFim);
                ps.setString(5, tipoEleicao);
                ps.setString(6, nomeFac);
                ps.executeUpdate();
                connection.commit();
                return true;

            }
            catch (SQLException e){
                System.out.println(e);
                return false;
            }

        }
        else if (tipoEleicao.equalsIgnoreCase("DF")){
            String query = "INSERT into ELEICAO"
                    + " VALUES(?,?,TO_DATE(?,'DD-MM-YYYY HH24:MI'),TO_DATE(?, 'DD-MM-YYYY HH24:MI'),0,0,0,?,SEQ_ELEICAO.nextval, (select nome FROM FACULDADE WHERE nome=?))";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setString(1, titulo);
                ps.setString(2, breveDescricao);
                ps.setString(3, dataInicio);
                ps.setString(4, dataFim);
                ps.setString(5, tipoEleicao);
                ps.setString(6, nomeFac);
                ps.executeUpdate();
                connection.commit();
                return true;

            }
            catch (SQLException e){
                System.out.println(e);
                return false;
            }

        }

        return true;
    }

    public static void chamarGerirListas(){
        String inserirOuRemoverLista;
        String idEleicao;
        String nomeLista;
        String cabecaDeLista;
        String tipoDeLista;
        if (contaLinhasTabela("ELEICAO")!=0){
            System.out.println("Deseja inserir (1), remover (2) uma lista de uma eleição ou associar uma pessoa a uma lista candidata:");
            Scanner sc= new Scanner(System.in);

            inserirOuRemoverLista = sc.nextLine();

            imprimeTabela("ELEICAO");
            System.out.println("Insira o id da eleição a que quer associar ou desassociar uma lista ou uma pessoa de uma lista");
            idEleicao=sc.nextLine();

            if (inserirOuRemoverLista.equalsIgnoreCase("1")){
                if (devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("DD") || devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("DF")){
                    System.out.println("Só pode inserir listas de professores: Insira o nome da lista");
                    nomeLista=sc.nextLine();
                    System.out.println("Insira o ID do cabeça de lista: Atenção tem de ser de um tipo válido");
                    cabecaDeLista=sc.nextLine();
                    gerirListas(inserirOuRemoverLista, idEleicao, nomeLista, "professor");

                }
                else if (devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("N")){
                    System.out.println("Só pode inserir listas de alunos: Insira o nome da lista");
                    nomeLista=sc.nextLine();
                    System.out.println("Insira o ID do cabeça de lista: Atenção tem de ser de um tipo válido");
                    cabecaDeLista=sc.nextLine();
                    gerirListas(inserirOuRemoverLista, idEleicao, nomeLista, "aluno");

                }
                else if (devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("CG")){
                    System.out.println("Pode inserir qualquer tipo de lista: Qual o tipo que deseja inserir");
                    tipoDeLista=sc.nextLine();
                    tipoDeLista=tipoDeLista.toLowerCase();
                    System.out.printf("Insira o nome da lista");
                    nomeLista=sc.nextLine();
                    System.out.println("Insira o id do representante da lista: Atenção, o seu perfil tem de ser compatível com o da lista");
                    cabecaDeLista=sc.nextLine();
                    gerirListas(inserirOuRemoverLista, idEleicao, nomeLista, tipoDeLista);

                }
                else {
                    System.out.println("Erro inesperado: O tipo de eleicão não corresponde a nenhum dos esperados");
                    menuConsola();
                }

            }
            else if (inserirOuRemoverLista.equalsIgnoreCase("2")){
                System.out.println("Insira o nome da lista que pretende desassociar");
                nomeLista=sc.nextLine();
                gerirListas(inserirOuRemoverLista,idEleicao,nomeLista,"");

            }//FAZER O REMOVER PESSOA
            else if (inserirOuRemoverLista.equalsIgnoreCase("3")){
                System.out.println("Qual o nome da lista a que quer associar uma pessoa?");
                nomeLista=sc.nextLine();
                if(verificaSeExisteChave("LISTA","NOMELISTA",nomeLista)>0){
                    System.out.println("Qual o id da pessoa que deseja associar?");
                    cabecaDeLista=sc.nextLine();
                    if(verificaIDCC(cabecaDeLista)){
                        if (devolveTipoPorID("PESSOA","NUMCC", cabecaDeLista).equalsIgnoreCase(devolveTipoPorID("LISTA","NOMLELISTA",nomeLista))){
                            inserirPessoaLista(idEleicao, nomeLista, cabecaDeLista, devolveTipoPorID("ELEICAO","ID",idEleicao));
                        }
                        else {
                            System.out.println("O perfil da pessoa não corresponde ao tipo de lista");
                            menuConsola();
                        }

                    }
                    else {
                        System.out.println("CC inválido.");
                        menuConsola();
                    }

                }else{
                    System.out.println("Nao existem listas para esta eleição.");
                    menuConsola();
                }

            }
            else {
                System.out.println("Opcao invalida");
                menuConsola();
            }

        }
        else{
            System.out.println("Não existem eleições na base de dados. Não pode associar listas");
            menuConsola();
        }
    }

    public static int contaLinhasTabela(String nomeTabela) {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucesso");
        } else {
            System.out.println("Não conseguimos estabelecer a ligação");
        }

        String query = "Select COUNT(*) from ?";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ResultSet rs= ps.executeQuery();

            return rs.getInt(1);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return 0;
    }

    public static void imprimeTabela(String nomeTabela){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select (*) from ?";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ResultSet rs= ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            for(int i = 1 ; i <= columnsNumber ; i++){
                System.out.print(rsmd.getColumnName(i) +", ");
            }
            System.out.println("");//Quando chegamos ao fim da linha com os cabeçalhos da tabela, saltamos uma linha

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");//Permite mostrar as linhas da tabela corretamente - Separa as linhas da tabela
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static String devolveTipoPorID(String nomeTabela, String paramID, String iD){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select TIPO from ? WHERE ? = ? ";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ps.setString(2, paramID);
            ps.setString(3, iD);
            ResultSet rs= ps.executeQuery();

            return rs.getString(1);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }

    public static boolean gerirListas(String opcao, String eleicaoID, String nomeLista, String tipoLista){
        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
            return false;
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }
        if (opcao.equalsIgnoreCase("1")){
            String query = "INSERT into LISTA"
                    + " VALUES(0,?,?,?)";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setString(1, nomeLista);
                ps.setString(2, eleicaoID);
                ps.setString(3, nomeLista);
                ps.executeUpdate();
                connection.commit();
                return true;

            }
            catch (SQLException e){
                System.out.println(e);
                return false;
            }


        }else if(opcao.equalsIgnoreCase("2")){
            boolean encontrouListaEmPessoaLista=false;
            String query = "DELETE FROM LISTA WHERE NOMELISTA=?";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setString(1, nomeLista);
                if (verificaSeExisteChave("PESSOA_LISTA","LISTANOMELISTA", nomeLista)>=1){
                    apagaAPedido("PESSOA_LISTA","LISTANOMELISTA",nomeLista);

                }
                ps.executeUpdate();
                connection.commit();
                return true;

            }
            catch (SQLException e){
                System.out.println(e);
                return false;
            }

        }
        return true;
    }

    public static int verificaSeExisteChave(String nomeTabela, String param, String valorParam){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select count(*) from ? WHERE ? = ? ";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ps.setString(2, param);
            ps.setString(3, valorParam);
            ResultSet rs= ps.executeQuery();

            return rs.getInt(1);
        }
        catch (SQLException e){
            System.out.println(e);
            return 0;
        }
    }

    public static void apagaAPedido(String nomeTabela, String param, String valorParam){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "DELETE from ? WHERE ? = ? ";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ps.setString(2, param);
            ps.setString(3, valorParam);
            ps.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static void inserirPessoaLista(String idEleicao, String nomeLista, String cabecaDeLista, String tipoEleicao){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }
        if (devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("CG")){
            String query = "INSERT INTO PESSOA_LISTA" +
                    "+VALUES(?,(SELECT UNIDADEORGANICANOME FROM PESSOA WHERE NUMCC = ?),?)";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setString(1, cabecaDeLista);
                ps.setString(2, cabecaDeLista);
                ps.setString(3, nomeLista);
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else if(devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("DD")) {
            if (devolveUOporChave("PESSOA","NUMCC",cabecaDeLista).equalsIgnoreCase(devolveUOporChave("ELEICAO","ID",idEleicao))){
                String query = "INSERT INTO PESSOA_LISTA" +
                        "+VALUES(?,(SELECT UNIDADEORGANICANOME FROM PESSOA WHERE NUMCC = ?),?)";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    ps.setString(1, cabecaDeLista);
                    ps.setString(2, cabecaDeLista);
                    ps.setString(3, nomeLista);

                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
        }
        else if (devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("DF")){
            if (devolveUOporChave("PESSOA","NUMCC",cabecaDeLista).equalsIgnoreCase(devolveUOporChave("FACULDADE","NOME",devolveUOporChave("ELEICAO","ID",idEleicao)))){
                String query = "INSERT INTO PESSOA_LISTA" +
                        "+VALUES(?,(SELECT UNIDADEORGANICANOME FROM PESSOA WHERE NUMCC = ?),?)";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    ps.setString(1, cabecaDeLista);
                    ps.setString(2, cabecaDeLista);
                    ps.setString(3, nomeLista);

                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
        }else if (devolveTipoPorID("ELEICAO","ID",idEleicao).equalsIgnoreCase("N")){
            if (devolveUOporChave("PESSOA","NUMCC",cabecaDeLista).equalsIgnoreCase(devolveUOporChave("DEPARTAMENTO","NOME",devolveUOporChave("ELEICAO","ID",idEleicao)))){
                String query = "INSERT INTO PESSOA_LISTA" +
                        "+VALUES(?,(SELECT UNIDADEORGANICANOME FROM PESSOA WHERE NUMCC = ?),?)";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    ps.setString(1, cabecaDeLista);
                    ps.setString(2, cabecaDeLista);
                    ps.setString(3, nomeLista);

                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
        }
        else {
            System.out.println("Erro inseperado");
            menuConsola();
        }
    }

    public static String devolveUOporChave(String nomeTabela, String param, String valorParam){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select UNIDADEORGANICANOME from ? WHERE ? = ? ";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ps.setString(2, param);
            ps.setString(3, valorParam);
            ResultSet rs= ps.executeQuery();

            return rs.getString(1);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }

    public static void chamarGerirMesasVoto() {
        String opcao;
        String localizacaoMesa;
        String idMesaVoto;
        int valorIdMesaVoto;
        String idEleicao;
        int valorIdEleicao;
        System.out.println("Deseja inserir (1), remover(2) uma mesa de voto associada a uma eleição");
        Scanner sc= new Scanner(System.in);
        opcao=sc.nextLine();
        if (opcao.equalsIgnoreCase("1")){
            System.out.println("A que mesa de voto deseja associar uma eleição");
            //imprimeTabela("MESA_VOTO");
            idMesaVoto = sc.nextLine();
            try{
                valorIdMesaVoto = Integer.parseInt(idMesaVoto);
                System.out.println("Eleições ainda não iniciadas:");
                Calendar sistema= Calendar.getInstance();
                String tempoSistema= sistema.get(Calendar.DAY_OF_MONTH)+"-"+sistema.get(Calendar.MONTH)+"-"+sistema.get(Calendar.YEAR)+" "+sistema.get(Calendar.HOUR_OF_DAY)+":"+sistema.get(Calendar.MINUTE);
                //devolveEleicoesParaMesa("ELEICAO","DATAINICIO",tempoSistema);
                System.out.println("Id da eleição a associar:");
                idEleicao=sc.nextLine();
                try {
                    valorIdEleicao=Integer.parseInt(idEleicao);
                    if (devolveTipoPorIDInt("ELEICAO","ID",idEleicao).equalsIgnoreCase("GC")){
                        gerirMesasVoto("1",idEleicao,devolveTipoPorID("ELEICAO","ID",idEleicao),idMesaVoto);
                    }
                    else if(devolveTipoPorIDInt("ELEICAO","ID",idEleicao).equalsIgnoreCase("N")){
                        if(devolveUOporChave("ELEICAO","ID",idEleicao).equalsIgnoreCase(devolveUODeMesaPorID("UONOME","MESA_VOTO",idMesaVoto))){
                            gerirMesasVoto("1",idEleicao,devolveTipoPorID("ELEICAO","ID",idEleicao),idMesaVoto);
                        }
                        else {
                            System.out.println("Não se pode adicionar esta eleição a esta mesa");
                            menuConsola();
                        }
                    }
                    else if (devolveTipoPorIDInt("ELEICAO","ID",idEleicao).equalsIgnoreCase("DD")){
                        if(devolveUOporChave("ELEICAO","ID",idEleicao).equalsIgnoreCase(devolveUODeMesaPorID("UONOME","MESA_VOTO",idMesaVoto))){
                            gerirMesasVoto("1",idEleicao,devolveTipoPorID("ELEICAO","ID",idEleicao),idMesaVoto);
                        }
                        else {
                            System.out.println("Não se pode adicionar esta eleição a esta mesa");
                            menuConsola();
                        }
                    }
                    else if (devolveTipoPorIDInt("ELEICAO","ID",idEleicao).equalsIgnoreCase("DF")){
                        if (devolveUOporChave("ELEICAO","ID",idEleicao).equalsIgnoreCase(devolveUOporChave("FACULDADE","NOME",devolveUOporChave("ELEICAO","ID",idEleicao)))){
                            gerirMesasVoto("1",idEleicao,devolveTipoPorID("ELEICAO","ID",idEleicao),idMesaVoto);
                        }
                        else {
                            System.out.println("Não se pode adicionar esta eleição a esta mesa");
                            menuConsola();
                        }
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Não foi inserido um número");
                    menuConsola();
                }

            }catch (NumberFormatException e){
                System.out.println("Nao foi inserido um número");
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("2")){
            System.out.println("A que mesa de voto deseja desassociar uma eleição");
            imprimeTabela("MESA_VOTO");
            idMesaVoto = sc.nextLine();
            try {
                valorIdMesaVoto=Integer.parseInt(idMesaVoto);
                imprimirTabelaEleicoesApagarMesa("MESA_VOTO_ELEICAO");
                System.out.println("Id da eleição a apagar");
                idEleicao=sc.nextLine();
                try {
                    valorIdEleicao=Integer.parseInt(idEleicao);
                    String dataI = devolveData1("ELEICAO","DATAINICIO",idEleicao);
                    String [] argsDataI = dataI.split(" ");
                    String [] argsDataDMA = argsDataI[0].split("-");
                    String [] argsDataDM = argsDataI[1].split(":");
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(argsDataDMA[0]), Integer.parseInt(argsDataDMA[1])-1, Integer.parseInt(argsDataDMA[0], Integer.parseInt(argsDataDM[0])));
                    System.out.println(cal.get(Calendar.DAY_OF_MONTH)+":"+cal.get(Calendar.MONTH)+":"+cal.get(Calendar.YEAR)+":"+ cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
                    Calendar horasSistema= Calendar.getInstance();
                    if (comparaDatas2(horasSistema,cal)){
                        gerirMesasVoto("2",idEleicao,devolveTipoPorID("ELEICAO","ID",idEleicao),idMesaVoto);
                    }
                    else{
                        System.out.println("A eleição já começou. Tarde de mais para inserir/remover mesas");
                        menuConsola();
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Nao foi inserido um número");
                    menuConsola();
                }
            }
            catch (NumberFormatException e){
                System.out.println("Nao foi inserido um número");
                menuConsola();
            }
        }
        else {
            System.out.println("Opção inválida");
            menuConsola();
        }
    }

    public static String devolveTipoPorIDInt(String nomeTabela, String paramID, String iD) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select TIPO from ? WHERE ? = ? ";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ps.setString(2, paramID);
            ps.setInt(3, Integer.parseInt(iD));
            ResultSet rs= ps.executeQuery();
            rs.next();

            return rs.getString(1);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }

    public static boolean gerirMesasVoto(String opcao, String idEleicao, String tipoEleicao, String idMesaVoto) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }
        if (opcao.equalsIgnoreCase("1")){
            String query = "INSERT INTO MESA_VOTO_ELEICAO" +
                    "+VALUES((SELECT UONOME FROM MESA_VOTO WHERE ID=?),(SELECT ID FROM MESA_VOTO WHERE ID=?),(SELECT ID FROM ELEICAO WHERE ID = ?))";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setInt(1, Integer.parseInt(idMesaVoto));
                ps.setInt(2, Integer.parseInt(idMesaVoto));
                ps.setInt(3, Integer.parseInt(idEleicao));

                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else if(opcao.equalsIgnoreCase("2")){
            String query = "DELETE FROM MESA_VOTO_ELEICAO WHERE MESA_VOTOUONOME=(SELECT UONOME FROM MESA_VOTO WHERE ID=?) AND MESA_VOTOID =? AND ELEICAOID=?";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setInt(1, Integer.parseInt(idMesaVoto));
                ps.setInt(2, Integer.parseInt(idMesaVoto));
                ps.setInt(3, Integer.parseInt(idEleicao));

                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        return true;
    }

    public static String devolveUODeMesaPorID(String oQueBuscar, String nomeTabela, String valorParam) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select ? from ? WHERE ID =?";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, oQueBuscar);
            ps.setString(2, nomeTabela);
            ps.setInt(3, Integer.parseInt(valorParam));
            ResultSet rs= ps.executeQuery();
            return rs.getString(1);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }

    public static void imprimirTabelaEleicoesApagarMesa(String nomeTabela) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select * from ? ";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setString(1, nomeTabela);
            ResultSet rs= ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            for(int i = 1 ; i <= columnsNumber ; i++){
                System.out.print(rsmd.getColumnName(i) +", ");
            }
            System.out.println("");//Quando chegamos ao fim da linha com os cabeçalhos da tabela, saltamos uma linha

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");//Permite mostrar as linhas da tabela corretamente - Separa as linhas da tabela
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public static String devolveData1(String nomeTabela, String param, String valorParam) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select DATAINCIO from ELEICAO WHERE ID =?";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            //ps.setString(1, param);
            //ps.setString(1, param);
            ps.setInt(1, Integer.parseInt(valorParam));
            ResultSet rs= ps.executeQuery();
            rs.next();
            return rs.getString(1);

        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }

    public static boolean comparaDatas2(Calendar dI, Calendar dF) {
        System.out.println("DATA dI-> "+dI.get(Calendar.DAY_OF_MONTH) + ":" + (dI.get(Calendar.MONTH)) + ":" + dI.get(Calendar.YEAR) + ":" + dI.get(Calendar.HOUR_OF_DAY) + ":" + dI.get(Calendar.MINUTE));
        System.out.println("DATA dF-> "+dF.get(Calendar.DAY_OF_MONTH) + ":" + (dF.get(Calendar.MONTH)) + ":" + dF.get(Calendar.YEAR) + ":" + dF.get(Calendar.HOUR_OF_DAY) + ":" + dF.get(Calendar.MINUTE));
        if(dI.YEAR >= dF.YEAR){
            if(dI.YEAR == dF.YEAR){
                if(dI.MONTH >= dF.MONTH){
                    if(dI.MONTH == dF.MONTH){
                        if(dI.get(Calendar.DAY_OF_MONTH) >= dF.get(Calendar.DAY_OF_MONTH)){
                            if(dI.get(Calendar.DAY_OF_MONTH) == dF.get(Calendar.DAY_OF_MONTH)){
                                if (dI.get(Calendar.HOUR_OF_DAY) >= dF.get(Calendar.HOUR_OF_DAY)){
                                    if (dI.get(Calendar.HOUR_OF_DAY) == dF.get(Calendar.HOUR_OF_DAY)){
                                        return (dI.get(Calendar.MINUTE) < dF.get(Calendar.MINUTE));
                                    }
                                    else return false;
                                }
                                else return true;
                            }
                            else return false;
                        }
                        else return true;
                    }
                    else return false;
                }
                else return true;
            }
            else return false;
        }
        else return true;
    }

    // O título de uma eleição é escrito em maiúscula e a descrição em minúsculas
    public static void chamarAlterarPropEleicao(){
        String idEleicao;
        String opcao;
        String subOpcao;
        String novoValor;
        System.out.println("Que tipo de campo de eleição pretende alterar? (1) textuais ou (2) datas");
        Scanner sc = new Scanner(System.in);
        opcao=sc.nextLine();
        if (opcao.equalsIgnoreCase("1")){
            System.out.println("Deseja alterar o titulo (1) ou a descrição (2)");
            subOpcao=sc.nextLine();
            if (subOpcao.equalsIgnoreCase("1")){
                System.out.println("Novo título da eleição:");
                novoValor = sc.nextLine();
                novoValor = novoValor.toUpperCase();
                System.out.println("Qual o id da eleição a alterar");
                idEleicao=sc.nextLine();
                try{
                    int valIdEleicao = Integer.parseInt(idEleicao);
                    alterarPropEleicao("1","TITULO",novoValor,idEleicao);
                }
                catch (NumberFormatException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
            else if (subOpcao.equalsIgnoreCase("2")){
                System.out.println("Nova descrição da eleição:");
                novoValor = sc.nextLine();
                novoValor = novoValor.toUpperCase();
                System.out.println("Qual o id da eleição a alterar");
                idEleicao=sc.nextLine();
                try{
                    int valIdEleicao = Integer.parseInt(idEleicao);
                    alterarPropEleicao("1","DESCRICAO",novoValor,idEleicao);
                }
                catch (NumberFormatException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
            else {
                System.out.println("Opção inválida");
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("2")){
            //menuAplicacao();
            System.out.println("Insira os dados da data de início de eleição");
            String diaI,mesI,anoI,horasI,minutosI;
            System.out.println("Dia da nova data");
            diaI=sc.nextLine();
            System.out.println("Mês da nova data");
            mesI=sc.nextLine();
            System.out.println("Ano da nova data");
            anoI=sc.nextLine();
            System.out.println("Horas da nova data");
            horasI=sc.nextLine();
            System.out.println("Minutos da nova data");
            minutosI=sc.nextLine();

            System.out.println("Insira os dados da data de início de eleição");
            String diaF,mesF,anoF,horasF,minutosF;
            System.out.println("Dia da nova data");
            diaF=sc.nextLine();
            System.out.println("Mês da nova data");
            mesF=sc.nextLine();
            System.out.println("Ano da nova data");
            anoF=sc.nextLine();
            System.out.println("Horas da nova data");
            horasF=sc.nextLine();
            System.out.println("Minutos da nova data");
            minutosF=sc.nextLine();
            System.out.println("Qual o id da eleição a alterar");
            idEleicao=sc.nextLine();

            try{
                int diaVI,mesVI,anoVI,horasVI,minutosVI;
                diaVI = Integer.parseInt(diaI);
                mesVI = Integer.parseInt(mesI);
                anoVI = Integer.parseInt(anoI);
                horasVI = Integer.parseInt(horasI);
                minutosVI = Integer.parseInt(minutosI);
                Calendar novaDataI = Calendar.getInstance();
                novaDataI.set(anoVI,mesVI-1,diaVI,horasVI,minutosVI);

                int diaVF,mesVF,anoVF,horasVF,minutosVF;
                diaVF = Integer.parseInt(diaF);
                mesVF = Integer.parseInt(mesF);
                anoVF = Integer.parseInt(anoF);
                horasVF = Integer.parseInt(horasF);
                minutosVF = Integer.parseInt(minutosF);
                Calendar novaDataF = Calendar.getInstance();
                novaDataI.set(anoVI,mesVI-1,diaVI,horasVI,minutosVI);

                int valorIdEleicao = Integer.parseInt(idEleicao);

                Calendar dataSistema = Calendar.getInstance();

                String dataInicioEleicao = devolveData1("Eleicao","ID",idEleicao);
                String [] argsDataI = dataInicioEleicao.split(" ");
                String [] argsDataDMA = argsDataI[0].split("-");
                String [] argsDataDM = argsDataI[1].split(":");
                System.out.println(argsDataDMA + " " +argsDataDM);
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(argsDataDMA[0]),(Integer.parseInt(argsDataDMA[1]))-1, Integer.parseInt(argsDataDMA[2], Integer.parseInt(argsDataDM[0],Integer.parseInt(argsDataDM[1]))));
                //cal.set(Integer.parseInt(argsDataDMA[0]), Integer.parseInt(argsDataDMA[1])-1, Integer.parseInt(argsDataDMA[2], Integer.parseInt(argsDataDM[0])));
                System.out.println(cal.get(Calendar.DAY_OF_MONTH)+":"+cal.get(Calendar.MONTH)+":"+cal.get(Calendar.YEAR)+":"+ cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));

                if (comparaDatas2(novaDataI,novaDataF) && comparaDatas2(dataSistema,novaDataI) /*&& comparaDatas2(novaDataI,cal)*/){
                    String strDataI=diaVI+"-"+(mesVI-1)+"-"+anoVI+" "+horasVI+":"+minutosVI;
                    String strDataF=diaVF+"-"+(mesVF-1)+"-"+anoVF+" "+horasVF+":"+minutosVF;
                    alterarPropEleicao("2", strDataI,strDataF,idEleicao);
                }
                else {
                    System.out.println("A eleição já começou/a data de fim esta antes da data de ínicio/ a eleição não pode começar no passado");
                    menuConsola();
                }
            }
            catch (NumberFormatException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else {
            System.out.println("Opção inválida");
            menuConsola();
        }
    }

    public static void alterarPropEleicao(String opcao, String arg1, String arg2, String idEleicao) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucesso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        if (opcao.equalsIgnoreCase("1")){
            if (arg1.equalsIgnoreCase("TITULO")){
                String query = "UPDATE ELEICAO SET TITULO = NLS_UPPER(?) WHERE ID = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    //ps.setString(1, arg1);
                    ps.setString(1, arg2);
                    ps.setInt(2,Integer.parseInt(idEleicao));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
            if (arg1.equalsIgnoreCase("DESCRICAO")){
                String query = "UPDATE ELEICAO SET DESCRICAO = NLS_UPPER(?) WHERE ID = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    //ps.setString(1, arg1);
                    ps.setString(1, arg2);
                    ps.setInt(2,Integer.parseInt(idEleicao));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
        }
        else if (opcao.equalsIgnoreCase("2")){
            String query = "UPDATE ELEICAO SET DATAINCIO = TO_DATE(?,'DD-MM-YYYY HH24:MI'), SET DATAFIM =TO_DATE(?,'DD-MM-YYYY HH24:MI') WHERE ID = ?";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                ps.setString(1, arg1);
                ps.setString(2, arg2);
                ps.setInt(3,Integer.parseInt(idEleicao));
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else {
            System.out.println("Opção inválida");
            menuConsola();
        }
    }

    public static void chamarAlterarDadosPessoais(){
        String idPessoa;
        String opcao;
        String novoValorColuna;
        System.out.println("Qual o id da pessoa cujos dados quer alterar:");
        Scanner sc = new Scanner(System.in);
        idPessoa=sc.nextLine();
        System.out.println("Qual o parâmetro que deseja mudar(insira o nome que se encontra entres parênteses):");
        System.out.println("1- nome [NOME]");
        System.out.println("2- nomeUtilizador [NOMEUTILIZADOR]");
        System.out.println("3- password [PASSWORD]");
        System.out.println("4- número de telefone [NUMTELEFONE]");
        System.out.println("5- morada [MORADA]");
        System.out.println("6- validade do cartão de cidadão [VALIDADECC]");
        System.out.println("7- número do cartão de cidadão [NUMCC]");
        System.out.println("8- unidade orgânica associada [UNIDADEORGANICANOME]");
        System.out.println("9- tipo de pessoa [TIPO]");
        opcao = sc.nextLine();
        opcao=opcao.toUpperCase();
        //|| opcao.equalsIgnoreCase("2") || opcao.equalsIgnoreCase("3") || opcao.equalsIgnoreCase("5")
        if (opcao.equalsIgnoreCase("NOME") || opcao.equalsIgnoreCase("NOMEUTILIZADOR") || opcao.equalsIgnoreCase("PASSWORD") || opcao.equalsIgnoreCase("NUMTELEFONE") || opcao.equalsIgnoreCase("MORADA") || opcao.equalsIgnoreCase("VALIDADECC") ||opcao.equalsIgnoreCase("NUMCC") || opcao.equalsIgnoreCase("UNIDADEORGANICANOME") || opcao.equalsIgnoreCase("TIPO")){
            try{
                int valNumCC = Integer.parseInt(idPessoa);
                System.out.println("Qual o novo valor do parâmetro selecionado[se for data: dia-mes-ano]");
                novoValorColuna = sc.nextLine();
                alterarDadosPessoais(opcao,idPessoa,novoValorColuna);
            }
            catch (NumberFormatException e ){
                System.out.println("O id introduzido não é um número");
                menuConsola();
            }
        }
        else {
            System.out.println("Operação inválida");
            menuConsola();
        }
    }

    public static boolean alterarDadosPessoais(String opcao, String idPessoa, String novoValorColuna) {
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        }
        else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        if (opcao.equalsIgnoreCase("NOME")){
            String query = "UPDATE PESSOA SET NOME = ? WHERE NUMCC = ?";
            try (PreparedStatement ps= connection.prepareStatement(query)){

                ps.setString(1, novoValorColuna);
                ps.setInt(2,Integer.parseInt(idPessoa));
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("NOMEUTILIZADOR")){ //|| opcao.equalsIgnoreCase("NOMEUTILIZADOR") || opcao.equalsIgnoreCase("PASSWORD") || opcao.equalsIgnoreCase("MORADA")){
            String query = "UPDATE PESSOA SET NOMEUTILIZADOR = ? WHERE NUMCC = ?";
            try (PreparedStatement ps= connection.prepareStatement(query)){

                ps.setString(1, novoValorColuna);
                ps.setInt(2,Integer.parseInt(idPessoa));
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("PASSWORD")){ // opcao.equalsIgnoreCase("PASSWORD") || opcao.equalsIgnoreCase("MORADA")){
            String query = "UPDATE PESSOA SET PASSWORD = ? WHERE NUMCC = ?";
            try (PreparedStatement ps= connection.prepareStatement(query)){

                ps.setString(1, novoValorColuna);
                ps.setInt(2,Integer.parseInt(idPessoa));
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("MORADA")){
            String query = "UPDATE PESSOA SET MORADA = ? WHERE NUMCC = ?";
            try (PreparedStatement ps= connection.prepareStatement(query)){

                ps.setString(1, novoValorColuna);
                ps.setInt(2,Integer.parseInt(idPessoa));
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("NUMTELEFONE")){
            if(verificaTelefone(novoValorColuna)){
                String query = "UPDATE PESSOA SET NUMTELEFONE = ? WHERE NUMCC = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    //ps.setString(1, opcao);
                    ps.setInt(1, Integer.parseInt(novoValorColuna));
                    ps.setInt(2,Integer.parseInt(idPessoa));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
            else {
                System.out.println("Número de telefone inválido.");
                menuConsola();
            }
        }
        else if(opcao.equalsIgnoreCase("TIPO")){
            if(verificaFuncao(novoValorColuna)){
                String query = "UPDATE PESSOA SET TIPO = NLS_LOWER(?) WHERE NUMCC = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    //ps.setString(1, opcao);
                    ps.setString(1, novoValorColuna);
                    ps.setInt(2,Integer.parseInt(idPessoa));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
            else{
                System.out.println("Tipo inválido");
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("UNIDADEORGANICANOME")){
            String query = "UPDATE PESSOA SET UNIDADEORGANICANOME = (select NOME from UNIDADEORGANICA WHERE NOME = NLS_UPPER(?)) WHERE NUMCC = ?";
            try (PreparedStatement ps= connection.prepareStatement(query)){
                //ps.setString(1, opcao);
                ps.setString(1, novoValorColuna);
                ps.setInt(1,Integer.parseInt(idPessoa));
                ps.executeUpdate();
                connection.commit();
            }
            catch (SQLException e){
                System.out.println(e);
                menuConsola();
            }
        }
        else if (opcao.equalsIgnoreCase("NUMCC")){
            if(verificaIDCC(novoValorColuna)){
                String query = "UPDATE PESSOA SET NUMCC = ? WHERE NUMCC = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    //ps.setString(1, opcao);
                    ps.setInt(1, Integer.parseInt(novoValorColuna));
                    ps.setInt(2,Integer.parseInt(idPessoa));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }

                String query2 = "UPDATE MESA_VOTO_PESSOA SET PESSOANUMCC = ? WHERE PESSOANUMCC = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    ps.setInt(1, Integer.parseInt(idPessoa));
                    ps.setInt(2, Integer.parseInt(novoValorColuna));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    menuConsola();
                }

                String query3 = "UPDATE PESSOA_LISTA SET PESSOANUMCC = ? WHERE PESSOANUMCC = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    ps.setInt(1, Integer.parseInt(idPessoa));
                    ps.setInt(2, Integer.parseInt(novoValorColuna));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    menuConsola();
                }
            }
            else {
                System.out.println("Número do CC inválido.");
            }
        }
        else if (opcao.equalsIgnoreCase("VALIDADECC")){
            String dataOriginal = devolveValidadeCC(idPessoa);
            String [] arrayData = dataOriginal.split("-");
            int dia = Integer.parseInt(arrayData[2]);
            int mes = Integer.parseInt(arrayData[1]);
            int ano = Integer.parseInt(arrayData[0]);
            Calendar DataCC = Calendar.getInstance();
            Calendar novaDataCC = Calendar.getInstance();
            DataCC.set(ano, (mes-1), dia);
            if (validaDatas(DataCC, novaDataCC)){
                String query = "UPDATE PESSOA SET VALIDADECC = TO_DATE(?,'DD-MM-YYYY') WHERE NUMCC = ?";
                try (PreparedStatement ps= connection.prepareStatement(query)){
                    //ps.setString(1, opcao);
                    ps.setString(1, novoValorColuna);
                    ps.setInt(2,Integer.parseInt(idPessoa));
                    ps.executeUpdate();
                    connection.commit();
                }
                catch (SQLException e){
                    System.out.println(e);
                    menuConsola();
                }
            }
            else {
                System.out.println("A nova validade do cartão de cidadão termina antes da original");
            }
        }
        else{
            System.out.println("Operação inválida");
        }
        return true;
    }

    public static String devolveValidadeCC(String idPessoa) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "utilizadorBD",
                    "utilizadorBD");
        } catch (SQLException e) {
            System.out.println("Ligacao falhou.. erro:");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Ligação feita com sucessso");
        } else {
            System.out.println("Nao conseguimos estabelecer a ligacao");
        }

        String query = "Select VALIDADECC from PESSOA WHERE NUMCC =?";
        try (PreparedStatement ps= connection.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(idPessoa));
            ResultSet rs= ps.executeQuery();
            return rs.getString(1);

        }
        catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }

    public static boolean validaDatas(Calendar dI, Calendar dF) {
        System.out.println("DATA dI-> "+dI.get(Calendar.DAY_OF_MONTH) + ":" + (dI.get(Calendar.MONTH)) + ":" + dI.get(Calendar.YEAR));
        System.out.println("DATA dF-> "+dF.get(Calendar.DAY_OF_MONTH) + ":" + (dF.get(Calendar.MONTH)) + ":" + dF.get(Calendar.YEAR));
        if(dI.YEAR >= dF.YEAR){
            if(dI.YEAR == dF.YEAR){
                if(dI.MONTH >= dF.MONTH){
                    if(dI.MONTH == dF.MONTH){
                        if(dI.get(Calendar.DAY_OF_MONTH) >= dF.get(Calendar.DAY_OF_MONTH)){
                            System.out.println(dI.get(Calendar.DAY_OF_MONTH));
                            if(dI.DAY_OF_MONTH == dF.DAY_OF_MONTH){
                                return false;
                            }
                            else return false;
                        }
                        else return true;
                    }
                    else return false;
                }
                else return true;
            }
            else return false;
        }
        else return true;
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