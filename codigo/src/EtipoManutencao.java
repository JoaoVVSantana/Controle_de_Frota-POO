package src;

enum ETipoManutencao {
    PERIODICA("periodica", 150d),
    PECAS("pecas", 200d);

    String descricao;
    double valor;

    // #region construtor
    ETipoManutencao(String d, double v) {
        this.descricao = d;
        this.valor = v;
    }
    // #endregion

    // #region Getters
    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
    // #endregion

}
