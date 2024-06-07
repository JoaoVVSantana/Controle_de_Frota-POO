package src;

import java.text.DecimalFormat;

import Util.Data;
public class Rota {
	//#region atributos
	private double quilometragem;
	private Data data;
	//#endregion


	//#region construtor
	/*
     * Construtor da classe Rota
     * @param Quilometragem da Rota tipo Double e a data tipo Data
     */
	public Rota(double km, Data data) {
		this.quilometragem = km;
		this.data = data;
	}
	//#endregion

	//region metodos

	/*
     * Método que retorna uma string representando um relatório de rota percorrida.
     * @return String com a descrição do relatorio da rota
     */
	public String relatorio(String placa) {
		StringBuilder aux = new StringBuilder();
		aux.append("A rota percorrida do dia ").append(data.dataFormatada()).append(" pelo veículo de placa: ").append(placa)
		.append(" possui ").append(quilometragem).append(" km"+ "\n");
		return aux.toString();
	}

	public double getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(double quilometragem) {
		this.quilometragem = quilometragem;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		DecimalFormat formato = new DecimalFormat("0.00");
		
		return "Rota [quilometragem= " + formato.format(quilometragem) + ", data=" + data.dataFormatada() + "]";
	}

	//endregion
}

