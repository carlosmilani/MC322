public enum CalcSeguro
{
    //Constantes
    VALOR_BASE(10.0),
    FATOR_18_30(1.25),
    FATOR_30_60(1.0),
    FATOR_60plus(1.5);

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
