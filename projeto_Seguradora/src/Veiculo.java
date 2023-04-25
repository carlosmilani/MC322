public class Veiculo {

    //Propriedades
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    //Construtor
    public Veiculo(String _Placa, String _Marca, String _Modelo, int _AnoFabricacao)
    {
        placa = _Placa;
        marca = _Marca;
        modelo = _Modelo;
        anoFabricacao = _AnoFabricacao;
    }

    //Getters e Setters
    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String _Placa)
    {
        placa = _Placa;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String _Marca)
    {
        marca = _Marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String _Modelo)
    {
        modelo = _Modelo;
    }

    public int getAnoFabricacao()
    {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int _AnoFabricacao)
    {
        anoFabricacao = _AnoFabricacao;
    }

    //Métodos
    public String ToString()
    {
        return "\n---VEICULO---\nPlaca: %s\nMarca: %s\nModelo: %s\nAno de Fabricacao: %d\n".formatted(placa, marca, modelo, anoFabricacao);
    }
}
