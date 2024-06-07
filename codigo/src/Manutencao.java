package src;

public class Manutencao {

    // #region atributos
    private double km;
    private ETipoManutencao tipoManutencao;
    // #endregion

    // #region construtor
    public Manutencao(double km, String descricao) {
        this.km = km;
        if (descricao.equals("preventiva")) {
            this.tipoManutencao = ETipoManutencao.PERIODICA;
        } else {
            this.tipoManutencao = ETipoManutencao.PECAS;
        }
    }
    // #endregion

    //#region métodos

    /**
     * Método que realiza o relatorio de manutenções de um veículo
     * 
     * @return o relatório das manutenções com o seu tipo, a quilometragem com que foi realizada e o valor
     */
    public String relatorioManutencao() {
        StringBuilder aux = new StringBuilder();
        aux.append("Manutenção do tipo " + tipoManutencao.getDescricao() + " realizada com: " + km + " km"
                + " com valor de R$" + tipoManutencao.getValor() + "\n");
        return aux.toString();
    }

    // @Override
    // public String toString() {
    //     return ("Manutenção do tipo " + tipoManutencao.getDescricao() + " realizada com: " + km + " km"
    //             + " com valor de R$" + tipoManutencao.getValor() + "\n");
    // }

    // #region Getters
    public double getValor() {
        return tipoManutencao.getValor();
    }
    //#endregion

}