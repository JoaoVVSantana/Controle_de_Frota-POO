package src;
import java.util.Scanner;

import Util.Data;

public class App {
  public static void main(String[] args) {

    Scanner teclado = new Scanner(System.in);
    // Criando uma frota de veículos com capacidade para 5 veículos
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
    Rota rota1 = new Rota(7500.0, new Data(10, 1, 2023));
    Rota rota2 = new Rota(75.0, new Data(15, 1, 2023));
    Rota rota3 = new Rota(2000.0, new Data(20, 1, 2023));
    Rota rota4 = new Rota(1250.0, new Data(12, 1, 2023));

    Rota r1 = new Rota(150.0, new Data(2, 2, 2023));
    Rota r2 = new Rota(200.0, new Data(5, 3, 2023));
    Rota r11 = new Rota(200.0, new Data(2, 3, 2023));
    Rota r22 = new Rota(200.0, new Data(5, 3, 2023));
    Rota r3 = new Rota(200.0, new Data(7, 3, 2023));
    Rota r4 = new Rota(75.0, new Data(2, 3, 2023));

    Rota t1 = new Rota(100.0, new Data(12, 1, 2023));
    Rota t2 = new Rota(400.0, new Data(27, 1, 2023));
    Rota t3 = new Rota(300.0, new Data(30, 1, 2023));
    Rota t4 = new Rota(400.0, new Data(30, 2, 2023));
    Rota t5 = new Rota(125.0, new Data(13, 1, 2023));

    // Adicionando as rotas aos veículos
    veiculo1.addRota(rota1);
    veiculo1.addRota(rota1);
    veiculo1.addRota(rota2);
    veiculo1.addRota(rota3);

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

    // Testando os métodos da classe Frota
    System.out.println();
    System.out.println(">>>>>>>>////////// Relatório da Frota //////////>>>>>>>>");
    System.out.println(frota.relatorioFrota() + "\n");

    System.out.println(">>>>>>>>////////// Veículo com maior quilometragem total: //////////>>>>>>>>");
    System.out.println(frota.maiorKmTotal() + "\n");

    System.out.println(">>>>>>>>////////// Veículo com maior média de quilometragem: //////////>>>>>>>>");
    System.out.println(frota.maiorKmMedia() + "\n"); /// **************************************** */

    System.out.println(">>>>>>>>////////// Veículo com maior quilometragem total //////////>>>>>>>>");
    System.out.println(frota.maiorKmTotal() + "\n");

    System.out.println(">>>>>>>>////////// Quilometragem total da frota //////////>>>>>>>>");

    System.out.println(frota.quilometragemTotal() + " km" + "\n ");

    // ALTERNATIVA A
    System.out.println(">>>>>>>>////////// Relatório de rotas de cada veículo //////////>>>>>>>>");
    System.out.println(frota.relatorioRotas());

    // ALTERNATIVA B
    System.out.println(">>>>>>>>////////// Quilometragem total de um veículo //////////>>>>>>>>");
    System.out.println("Digite a placa do veículo que deseja verificar sua quilometragem total: ");
    String placa = teclado.next();
    frota.localizarVeiculo(placa);
    System.out.println(frota.quilometragemTotalVeiculo(placa) + "\n");

    //ALTERNATIVA C
    System.out.println(">>>>>>>>////////// Manutenções dos veículos //////////>>>>>>>>");
    System.out.println(frota.relatorioManutencao() + "\n");

    //ALTERNATIVA D
    System.out.println(">>>>>>>>////////// Gastos totais com manutenção e combustível //////////>>>>>>>>");
    System.out.println(frota.gastosTotais() + "\n");

    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

    teclado.close();

  }

}
