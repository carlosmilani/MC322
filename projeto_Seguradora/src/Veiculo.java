public class Veiculo {

    //Propriedades
    private String placa;
    private String marca;
    private String modelo;

    //Construtor
    public Veiculo(String _Placa, String _Marca, String _Modelo)
    {
        placa = _Placa;
        marca = _Marca;
        modelo = _Modelo;
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

    //Métodos
    public String ToString()
    {
        return "\n---VEICULO---\nPlaca: %s\nMarca: %s\nModelo: %s".formatted(placa, marca, modelo);
    }
}
