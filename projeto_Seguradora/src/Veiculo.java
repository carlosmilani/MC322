public class Veiculo
{
    //Propriedades
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    //Construtor
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao)
    {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    //Getters e Setters
    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public int getAnoFabricacao()
    {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao)
    {
        this.anoFabricacao = anoFabricacao;
    }

    //Métodos
    public String ToString()
    {
        return "\n---VEICULO---\nPlaca: %s\nMarca: %s\nModelo: %s\nAno de Fabricacao: %d\n".formatted(placa, marca, modelo, anoFabricacao);
    }
}
