package src;

enum COMBUSTIVEL {
    ALCCOL(7.0, 3.29),
    GASOLINA(10.0, 5.19),
    DIESEL(4.0, 6.09);

    double consumoMedio;
    double precoMedio;

    // #region construtor
    COMBUSTIVEL(double consumoMedio, double precoMedio) {
        this.precoMedio = precoMedio;
        this.consumoMedio = consumoMedio;
    }
    // #endregion

    // #region Getters
    public double getConsumoMedio() {
        return consumoMedio;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }
    // #endregion

}
