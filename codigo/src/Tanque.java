package src;
public class Tanque {
	//#region atributos
	private double capacidadeMaxima;
	private double capacidadeAtual;
	private final static double CONSUMO;
	private COMBUSTIVEL combustivel;
	//#endregion

	/*
     * Construtor da classe Tanque
     * @param a capacidade Maxima e capacidade atual de um tanque. Ambos tipo double
     */
	public Tanque(double capacidadeMaxima, COMBUSTIVEL c) {
		this.capacidadeMaxima = capacidadeMaxima;
		this.capacidadeAtual=0;
		this.combustivel = c;
	}
	static{
		
		CONSUMO = 8.2;
	}
	//#endregion

	//#region métodos

	/**
	 * Abastece o tanque de combustível.
	 * @param litros recebe volume de litros
	 * @return o valor abastecido
	 */
	public double abastecer (double litros) 
	{

		double volumeFuturo = capacidadeAtual+litros;

		double valorAbastecido;

		if (volumeFuturo==capacidadeMaxima)
		{
			valorAbastecido=this.capacidadeMaxima-this.capacidadeAtual;

			encherTanque ();
		
		}
		else if(volumeFuturo>capacidadeMaxima)
		{
			return 0;
		}
		else
		{
			this.capacidadeAtual=volumeFuturo;

			valorAbastecido=litros;
		}
		

		return valorAbastecido;
	}

	//Abastece o tanque até sua capacidade máxima.
	private void encherTanque ()
	{
		capacidadeAtual=capacidadeMaxima;
	}
	/**
     * Método que calcula a autonomia máxima, com base no consumo e na capacidade máxima, do tanque.
	 *  @return Double com a autonomia máxima do tanque
	 */
	public double autonomiaMaxima() {
		return capacidadeMaxima*CONSUMO;
		
	}
	/**
     * Método que calcula a autonomia atual, com base no consumo e na capacidade atual, do tanque.
	 *  @return Double com a autonomia atual do tanque
	 */
	public double autonomiaAtual() {
		return capacidadeAtual*CONSUMO;
		
	}

	/**
	 * Verifica se o tamanho do tanque é suficiente para cumprir a rota
	 * @param km recebe a quilometragem da rota
	 * @return TRUE se for capaz, FALSE se não é.
	 */
	public boolean possuiAutonomia (double km)
	{
		boolean resposta=true;

		if(autonomiaMaxima()<km)
		{
			resposta=false;
		}
		else resposta=true;

		return resposta;
	}

	/**
	 * Verifica quantos litros são necessários para que o veículo possua autonomia para completar a viagem.
	 * @param km da rota a ser feita.
	 * @return quantos litros precisam ser abastecidos.
	 */
	public double litrosFaltando ( double km)
	{
		double litrosPrecisam = km/CONSUMO;
		
		return litrosPrecisam-capacidadeAtual;
	}

	public void atualizarTanque (double km)
	{
		double valorGasto=km/CONSUMO;
		capacidadeAtual-=valorGasto;
	}

	public double calcularPreco(double kmTotal){
		return (kmTotal*combustivel.getPrecoMedio())/combustivel.getConsumoMedio();
	}

	public double getCapacidadeMaxima() {
		return capacidadeMaxima;
	}
	public void setCapacidadeMaxima(double capacidadeMaxima) {
		this.capacidadeMaxima = capacidadeMaxima;
	}
	public double getCapacidadeAtual() {
		return capacidadeAtual;
	}
	public void setCapacidadeAtual(double capacidadeAtual) {
		this.capacidadeAtual = capacidadeAtual;
	}

	//#endregion
}

