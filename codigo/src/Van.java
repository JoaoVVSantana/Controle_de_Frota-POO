package src;

public class Van extends Veiculo {

    // #region atributos
    private static final int CAPACIDADE_TANQUE = 600;
    private EMaxManutencoes tipo;
    // #endregion

    // #region construtor
    public Van(String placa, COMBUSTIVEL tipoCombustivel) {
        super(placa, CAPACIDADE_TANQUE, tipoCombustivel);
        this.tipo = EMaxManutencoes.VAN;
        this.setManutencoesIniciais(this.tipo);
    }
    // #endregion

    // #region métodos
    /**
     * Método que informa com quantos quilômetros foi gerada a manutenção preventiva
     * 
     * @return a quilometragem com que a manutenção foi feita
     */
    @Override
    public double gerarNovaManutencaoPreventiva() {
        return this.getKmTotal() + this.tipo.getMaxKm();
    };

    /**
     * Método que informa com quantos quilômetros foi gerada a manutenção de peças
     * 
     * @return a quilometragem com que a manutenção foi feita
     */
    @Override
    public double gerarNovaManutencaoPecas() {
        return this.getKmTotal() + this.tipo.getMaxPecas();
    };
    // #endregion
}
