public enum CalcSeguro
{
    //Constantes
    VALOR_BASE(100.0),
    FATOR_18_30(1.2),
    FATOR_30_60(1.0),
    FATOR_60_90(1.5);

    //Propriedade
    private final double multiplicador;

    //Construtor
    CalcSeguro(double multiplicador)
    {
        this.multiplicador = multiplicador;
    }

    //Getter
    public double getMultiplicador()
    {
        return multiplicador;
    }
}
