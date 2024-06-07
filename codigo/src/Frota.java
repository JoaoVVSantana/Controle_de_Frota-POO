package src;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Frota {

	// #region atributos
	private int tamanhoFrota;
	private List<Veiculo> veiculos;
	private int qtdVeiculos;
	// #endregion

	// #region construtor

	/*
	 * Construtor da classe Frota
	 *
	 * @param tamanhoFrota a quantidade da frota tipo Int
	 */

	public Frota(int tamanhoFrota) {
		this.tamanhoFrota = tamanhoFrota;
		veiculos = new ArrayList<Veiculo>();
		this.qtdVeiculos = 0;
	}
	// #endregion

	// #region métodos
	/**
	 * Metódo que retorna uma String com um relatório da frota de veículos.
	 * Este método utiliza um StringBuilder chamado aux2 para concatenar informações
	 * sobre cada veículo em um array (veiculos).
	 * Finalmente, ele retorna a string concatenada.
	 *
	 * @return String com a descrição do relatorio da frota
	 */

	public String relatorioFrota() {
		StringBuilder aux = new StringBuilder();

		veiculos.stream().forEach(v -> aux.append(v.toString() + "\n"));

		return aux.toString();
	}

	/**
	 * Método para exibir
	 */
	public String relatorioGeralFrota() {
		StringBuilder aux = new StringBuilder();

		veiculos.stream().forEach(v -> aux.append(v.relatorioGeral(v.getPlaca())));

		return aux.toString();
	}

	/**
	 * Adiciona veículo na frota
	 *
	 * @param add veículo a ser adicionado
	 * @throws IllegalArgumentException quando a frota já possui um veículo com a
	 *                                  mesma placa
	 * @throws IllegalStateException    quando a frota já atingiu sua capacidade
	 *                                  máxima
	 */
	public void adicionarVeiculo(Veiculo add) {

		try {

			//boolean placaExistente = veiculos.stream().anyMatch(v -> v.getPlaca().equalsIgnoreCase(add.getPlaca()));

			if (localizarVeiculo(add.getPlaca())!=null) {
				throw new IllegalArgumentException("Erro ao adicionar: Veículo com a mesma placa já existe na frota");
			}

			if (qtdVeiculos < this.tamanhoFrota) {
				veiculos.add(add);
				qtdVeiculos++;
			} else {
				throw new IllegalStateException("Erro ao adicionar: Capacidade máxima da frota atingida");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Método que tem como objetivo procurar um veículo com uma placa específica
	 * dentro da lista
	 *
	 * @param placa identificador do veículo
	 * @return objeto veículo se foi localizado e null caso não foi encontrado
	 * @throws NoSuchElementException quando a placa não faz parte da frota
	 */
	public Veiculo localizarVeiculo(String placa) throws NoSuchElementException {
		Veiculo atual;

		try {
			atual = veiculos.stream()
					.filter(v -> v.getPlaca().equalsIgnoreCase(placa))
					.findFirst().orElseThrow(NoSuchElementException::new);

			return atual;
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage() + " - Placa não encontrada na frota");
			return null;
		}
	}

	/**
	 * Método que calcula a quilometragem total da lista de veículos.
	 * Ela é obtida somando os quilômetros totais individuais de cada um dos
	 * veículos
	 *
	 * @return a quilometragem total da frota
	 */
	public double quilometragemTotal() {
		return veiculos.stream()
				.mapToDouble(Veiculo::getKmTotal)
				.sum();
	}

	/**
	 * Método que percorre a lista de veículos para encontrar o que possui maior
	 * quilometragem total
	 *
	 * @return Veiculo com a maior quilometragem
	 */
	public String maiorKmTotal() {

		Veiculo aux = veiculos.stream()
				.max((v1, v2) -> v1.getKmTotal() > v2.getKmTotal() ? 1 : -1)
				.get();

		return aux.toString();
	}

	/**
	 * Método que percorre a lista de veículos para encontrar o que possui a maior
	 * média de quilometragem por rota entre todos os veículos
	 *
	 * @return Veiculo com a maior média de km
	 */
	public String maiorKmMedia() {

		Veiculo aux = veiculos.stream()
				.max((v1, v2) -> v1.mediakm() > v2.mediakm() ? 1 : -1)
				.get();

		return aux.getPlaca();
	}

	/**
	 * Método que retorna a quilometragem total de um veículo se fizer parte da
	 * frota
	 * 
	 * @return as informações de placa e quilometragem do veículo solicitado
	 */
	public String quilometragemTotalVeiculo(String placa) {

		Veiculo v = localizarVeiculo(placa);
		StringBuilder aux = new StringBuilder();

		if (v != null) {
			aux.append("A quilometragem total do veículo com a placa ").append(placa).append(" é de: ");
			aux.append(v.getKmTotal()).append(" km");
		}

		return aux.toString();
	}

	/*
	 * Método que utiliza um stream para acessar o método "relatorioManutencao" de
	 * todos os veículo da frota.
	 * 
	 * @return as informações de relatórios de manutenção dos veículos da frota
	 */

	public String relatorioManutencao() {

		return veiculos.stream()
				.map(Veiculo::relatorioManutencao)
				.reduce((a, b) -> a.concat("\n" + b))
				.orElse(null);
	}

	/**
	 * Método que utiliza um strea
	 */

	public String gastosTotais() {
		StringBuilder aux = new StringBuilder();

		String base = "Valor gasto pelo veículo: ";

		veiculos.stream()
				.forEach(v -> aux.append(base +v.relatorioGasto()+"\n"));

		return aux.toString();
	}

	// #endregion

	public List<String> getPlacasVeiculo() {
		return veiculos.stream()
				.map(v -> v.getPlaca()).collect(Collectors.toList());
	}

	public Veiculo getVeiculo(String placa) {

		return veiculos.stream()
				.filter(v -> v.getPlaca().equalsIgnoreCase(placa))
				.findFirst()
				.orElse(null);
	}

}
