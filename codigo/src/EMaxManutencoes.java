package src;

enum EMaxManutencoes {
    VAN(10_000, 12_000),
    CAMINHAO(10_000, 10_000),
    FURGAO(200, 12000),
    CARRO(10000, 10000);

    double maxKm, maxPecas;

    // #region construtor
    EMaxManutencoes(double max, double maxKmPecas) {
        this.maxKm = max;
        this.maxPecas = maxKmPecas;
    }
    // #endregion

    // #region Getters
    public double getMaxKm() {
        return maxKm;
    }

    public double getMaxPecas() {
        return maxPecas;
    }
    // #endregion

}