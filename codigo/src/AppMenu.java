package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import Util.Data;

public class AppMenu {

    static Scanner teclado = new Scanner(System.in);
    static Map<String, Frota> frotas;
    static Map<String, Rota> rotasDisponiveis;

    // #region utilidades

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Pausa para leitura de mensagens em console
     * 
     * @param teclado Scanner de leitura
     */
    static void pausa() {
        System.out.println("Enter para continuar.");
        teclado.nextLine();
    }

    /**
     * Encapsula uma leitura de teclado, com mensagem personalizada. A mensagem
     * sempre é completada com ":". Retorna uma string
     * que pode ser posteriormente convertida.
     * 
     * @param mensagem A mensagem a ser exibida, sem pontuação final.
     * @return String lida do usuário.
     */
    public static String leitura(String mensagem) {
        System.out.print(mensagem + ": ");
        return teclado.nextLine();
    }

    /**
     * Método para montagem de menu. Lê as opções de um arquivo e as numera
     * automaticamente a partir de 1.
     * 
     * @param nomeArquivo Nome do arquivo texto com as opções (uma por linha)
     * @return Opção do usuário (int)
     * @throws FileNotFoundException em caso de arquivo não encontrado.
     */
    public static int menu(String nomeArquivo) throws FileNotFoundException {
        limparTela();
        File arqMenu = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqMenu, "UTF-8");
        System.out.println(leitor.nextLine());
        System.out.println("==========================");
        int contador = 1;
        while (leitor.hasNextLine()) {
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        leitor.close();
        return opcao;
    }

    // #endregion
    /**
     * Método que permite a criação de uma nova frota com o tamanho desejado,
     * chama o método de gerar cógido e atribui o mesmo a ela ao adicionar como
     * chave
     * no Map de frotas.
     * 
     */
    public static void criarFrota() {
        // teclado = new Scanner(System.in);

        System.out.println("Digite o tamanho da frota (Quantidade de veículos): ");

        int tamanhoFrota = Integer.parseInt(teclado.nextLine());

        Frota frota = new Frota(tamanhoFrota);
        String codigoFrota = gerarCodigoAleatorio();

        frotas.put(codigoFrota, frota);

        System.out.println("Frota criada com sucesso!\nCódigo da Frota: " + codigoFrota);
    }

    /**
     * método que gera códigos aleatórios que seram atribuídos como chave no Map
     * frotas
     * 
     * @return código aleatório
     */
    public static String gerarCodigoAleatorio() {
        // Gera dois números aleatórios entre 0 e 9
        int numero1 = gerarNumeroAleatorio(0, 9);
        int numero2 = gerarNumeroAleatorio(0, 9);

        // Gera três letras aleatórias
        String letrasAleatorias = gerarLetrasAleatorias(3);

        // Monta o código seguindo o padrão
        String codigoAleatorio = "#" + numero1 + numero2 + letrasAleatorias;

        return codigoAleatorio;
    }

    /**
     * método que fornece o menu de Combustíveis disponíveis para a criação de novo
     * veículo
     * 
     * @return tipo combustível escolhido
     * @throws FileNotFoundException arquivo não encontrado
     */
    public static COMBUSTIVEL coletaCombustivel() throws FileNotFoundException {
        teclado = new Scanner(System.in);

        String nomeArq = "menuCombustiveis";
        limparTela();
        int opcao = menu(nomeArq);

        switch (opcao) {
            case 1:
                return COMBUSTIVEL.ALCCOL;
            case 2:
                return COMBUSTIVEL.GASOLINA;
            case 3:
                return COMBUSTIVEL.DIESEL;
            default:
                return COMBUSTIVEL.GASOLINA;
        }
    }

    /**
     * método que permite selecionar a frota que deseja exbindo as chaves disponíves
     * no Map de frotas.
     * 
     * @return frota escolhida
     */
    public static String selecionarFrota() {

        teclado = new Scanner(System.in);

        System.out.println("FROTAS DISPONIVEIS:");
        List<String> keysList = new ArrayList<>(frotas.keySet());
        StringBuilder aux = new StringBuilder();

        keysList.stream().forEach(key -> aux.append(key + " " + "\n"));
        System.out.println(aux.toString());

        System.out.println("\nDigite o código da frota:");

        String codigoFrota = teclado.nextLine();

        if (frotas.containsKey(codigoFrota)) {
            return codigoFrota;
        } else {
            System.out.println("Frota inexistente!");
            return selecionarFrota();
        }
    }

    /**
     * método que cria e adiciona novos veículos a uma frota já existente
     * 
     * @throws FileNotFoundException aquivo não encontrado
     */
    public static void adicionarVeiculoFrota() throws FileNotFoundException {

        try {
            String codigoFrota = selecionarFrota();
            Frota frota = frotas.get(codigoFrota);
            String nomeArq = "menuVeiculos";

            limparTela();

            int opcao = menu(nomeArq);
            Veiculo veiculo;

            System.out.println("Digite a placa do veículo: ");

            String placa = teclado.nextLine();

            COMBUSTIVEL combustivel = coletaCombustivel();

            switch (opcao) {
                case 1:
                    veiculo = new Carro(placa, combustivel);
                    addFrota(veiculo, frota);
                    pausa();
                    break;
                case 2:
                    veiculo = new Caminhao(placa, combustivel);
                    addFrota(veiculo, frota);
                    break;
                case 3:
                    veiculo = new Furgao(placa, combustivel);
                    addFrota(veiculo, frota);
                    break;
                case 4:
                    veiculo = new Van(placa, combustivel);
                    addFrota(veiculo, frota);
                    break;
                default:
                    System.out.println("Opcão inválida");
                    break;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
    
     /**
     *  método que adiciona veículo a uma frota
     * @param veiculo
     * @param frota
     */
    public static void addFrota(Veiculo veiculo, Frota frota) {
        frota.adicionarVeiculo(veiculo);
    }

    /**
     * Exibe relatórios de uma frota
     * 
     * @param frota 
     * @throws FileNotFoundException arquivo não encontrado
     */
    public static void relatoriosFrota(Frota frota) throws FileNotFoundException {
        // teclado = new Scanner(System.in);

        try {
            String nomeArq = "menuRelatoriosFrotas";
            int opcao = -1;
            while (opcao != 0) {
                limparTela();
                opcao = menu(nomeArq);
                switch (opcao) {
                    case 0:
                        System.out.println("");
                        break;
                    case 1:
                        System.out.println(frota.relatorioGeralFrota());
                        pausa();
                        break;
                    case 2:
                        System.out.println(frota.relatorioManutencao() + "\n");
                        pausa();
                        break;
                    case 3:
                        System.out.println("O veículo com maior quilometragem é: " + frota.maiorKmTotal());
                        pausa();
                        break;
                    case 4:
                        System.out.println("O veículo com maior quilometragem média é: " + frota.maiorKmMedia());
                        pausa();
                        break;

                    case 5:
                        System.out
                                .println("A quilometragem total da frota é de: " + frota.quilometragemTotal() + " km");
                        pausa();
                        break;

                    case 6:
                        System.out.println(frota.gastosTotais());
                        pausa();
                        break;

                    case 7:
                        System.out.println(frota.relatorioFrota());
                        pausa();
                        break;

                    default:
                        System.out.println("Opcão inválida");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }

    }

    /**
     * Exibe rotas dispoíveis para serem relacionadas a veículos
     */
    public static String selecionarRota() {

        teclado = new Scanner(System.in);

        System.out.println("ROTAS DISPONIVEIS:");
        StringBuilder aux = new StringBuilder();

        rotasDisponiveis.forEach((codigo, rota) -> {
            aux.append(codigo + ":" + rota.toString() + "\n");
        });

        System.out.println(aux.toString());

        System.out.println("\nDigite o código da rota:");

        String codigoRota = teclado.nextLine();

        if (rotasDisponiveis.containsKey(codigoRota)) {
            return codigoRota;
        } else {
            System.out.println("Rota inexistente!");
            return selecionarRota();
        }
    }

    /**
     * método que atribui uma rota a um veículo
     */
    public static void adicionarRotaVeiculo() {
        teclado = new Scanner(System.in);

        String codigoFrota = selecionarFrota();
        Frota frota = frotas.get(codigoFrota);

        System.out.println("Veiculos disponíveis:");

        frota.getPlacasVeiculo().stream().forEach(placa -> System.out.println(placa));

        System.out.println("Digite a placa do veículo: ");

        String placa = teclado.nextLine();

        Veiculo veiculo = frota.getVeiculo(placa);

        String codRota = selecionarRota();

        if (veiculo != null) {
            if (veiculo.addRota(rotasDisponiveis.get(codRota))) {
                System.out.println("Rota adicionada com sucesso!");
                rotasDisponiveis.remove(codRota);
                pausa();
            } else {
                System.out.println("Não foi possível adicionar rota!");
                pausa();
            }
        }
    }
    
    /**
     * Método responsável por exbir o menu de acesso aos relatórios da frota
     * @throws FileNotFoundException aquivo não existe
     */
    public static void menuRelatorios() throws FileNotFoundException {

        try {
            String nomeArq = "menuRelatorios";
            String codigoFrota;
            Frota frota;
            int opcao = -1;
            while (opcao != 0) {
                limparTela();
                opcao = menu(nomeArq);

                switch (opcao) {
                    case 0:
                        System.out.println("");
                        break;
                    case 1:
                        codigoFrota = selecionarFrota();
                        frota = frotas.get(codigoFrota);
                        relatoriosFrota(frota);
                        pausa();
                        break;
                    default:
                        System.out.println("Opcão inválida");
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
    
    /**
     *  Método que gera distâncias e datas aleatórias para a criação de novas rotas
     */
    public static Rota gerarRotaAleatoria() {
        Random random = new Random();
        double distancia = random.nextDouble() * 500.0;
        int dia = random.nextInt(28) + 1;
        int mes = random.nextInt(12) + 1;
        int ano = 2023;

        return new Rota(distancia, new Data(dia, mes, ano));
    }

    /**
     * Gerador de número aleatório
     * 
     * @param min número base
     * @param max número limite
     * @return
     */
    private static int gerarNumeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Gerador de String
     * 
     * @param quantidade tamanho da String
     * @return String com caractéres aleatórios
     */
    private static String gerarLetrasAleatorias(int quantidade) {
        Random random = new Random();
        StringBuilder letras = new StringBuilder();

        for (int i = 0; i < quantidade; i++) {
            char letra = (char) (random.nextInt(26) + 'A');
            letras.append(letra);
        }

        return letras.toString();
    }

    /**
     * Abre o arquivo do menu de frotas, podendo criar uma nova frota ou adicionar
     * um veículo.
     * 
     * @throws FileNotFoundException
     */
    public static void gerenciamentoFrota() throws FileNotFoundException {
        teclado = new Scanner(System.in);
        String nomeArq = "menuFrota";
        int opcao = -1;
        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 0:
                    System.out.println("");
                    break;
                case 1:
                    criarFrota();
                    pausa();
                    break;
                case 2:
                    adicionarVeiculoFrota();
                    break;
                case 3:
                    adicionarRotaVeiculo();
                    break;
                default:
                    System.out.println("Opcão inválida");
                    break;
            }
        }
    }
    /* Método que cria e adiciona os veículos iniciais à frota inicial
    */
    public static Frota criar() {
        Frota frota = new Frota(5);

        // Criando alguns veículos e adicionando-os à frota
        Veiculo veiculo1 = new Carro("ABC1234", COMBUSTIVEL.ALCCOL);
        Veiculo veiculo2 = new Caminhao("DEF5678", COMBUSTIVEL.DIESEL);
        Veiculo veiculo3 = new Van("GHI9012", COMBUSTIVEL.GASOLINA);
        Veiculo veiculo4 = new Furgao("JKL3456", COMBUSTIVEL.GASOLINA);

        frota.adicionarVeiculo(veiculo1);
        frota.adicionarVeiculo(veiculo2);
        frota.adicionarVeiculo(veiculo3);
        frota.adicionarVeiculo(veiculo4);

        // Criando algumas rotas para os veículos
        Rota rota1 = new Rota(3500.0, new Data(10, 1, 2023));
        Rota rota2 = new Rota(750.0, new Data(15, 1, 2023));
        Rota rota3 = new Rota(200.0, new Data(20, 1, 2023));
        Rota rota4 = new Rota(120.0, new Data(12, 1, 2023));

        Rota r1 = new Rota(150.0, new Data(2, 1, 2023));
        Rota r2 = new Rota(225.0, new Data(5, 1, 2023));
        Rota r11 = new Rota(226.0, new Data(2, 1, 2023));
        Rota r22 = new Rota(227.0, new Data(5, 1, 2023));
        Rota r3 = new Rota(228.0, new Data(7, 1, 2023));
        Rota r4 = new Rota(751.0, new Data(2, 1, 2023));

        Rota t1 = new Rota(1010.0, new Data(12, 2, 2023));
        Rota t2 = new Rota(3000.0, new Data(27, 2, 2023));
        Rota t3 = new Rota(3050.0, new Data(30, 2, 2023));
        Rota t4 = new Rota(4020.0, new Data(30, 2, 2023));
        Rota t5 = new Rota(129.0, new Data(13, 2, 2023));

        //Adiciona aos veículos
        veiculo1.addRota(rota1);
        veiculo1.addRota(rota1);
        veiculo1.addRota(rota2);
        veiculo1.addRota(rota3);
        veiculo1.addRota(r1);
        veiculo1.addRota(r2);
        veiculo1.addRota(r3);
        veiculo1.addRota(r4);
        veiculo1.addRota(r11);
        veiculo1.addRota(r22);
        veiculo1.addRota(r4);

        veiculo2.addRota(r1);
        veiculo2.addRota(r2);
        veiculo2.addRota(r11);
        veiculo2.addRota(r22);
        veiculo2.addRota(r3);

        veiculo3.addRota(t1);
        veiculo3.addRota(t2);
        veiculo3.addRota(t3);
        veiculo3.addRota(t4);

        veiculo4.addRota(rota4);
        veiculo4.addRota(r4);
        veiculo4.addRota(t5);
        veiculo4.addRota(r1);

        return frota;
    }
    
    /**
     * Método que gera rotas aleatórias que podem ser escolhidas para serem adicionadas aos veículos da frota
     */
    public static void adicionarRotaAleatoria() {
        for (int i = 0; i < 50; i++) {
            rotasDisponiveis.put(gerarCodigoAleatorio(), gerarRotaAleatoria());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        frotas = new HashMap<>();
        rotasDisponiveis = new HashMap<>();
        teclado = new Scanner(System.in);
        String nomeArq = "menuPrincipal";
        int opcao = -1;
        
        adicionarRotaAleatoria();
        frotas.put("#22ABC", criar());

        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 0:
                    System.out.println("Saindo aqui, bjs...");
                    break;
                case 1:
                    gerenciamentoFrota();
                    pausa();
                    break;
                case 2:
                    menuRelatorios();
                    break;
                default:
                    System.out.println("Opcão inválida");
                    break;
            }

        }
    }
}
