import java.time.LocalDate;

public class Sinistro {

    //Propriedades
    private static int count = 0;
    private final int id;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    //Construtor
    public Sinistro(LocalDate _Data, String _Endereco, Seguradora _Seguradora, Veiculo _Veiculo, Cliente _Cliente)
    {
        data = _Data;
        endereco = _Endereco;
        seguradora = _Seguradora;
        veiculo = _Veiculo;
        cliente = _Cliente;
        ++count;
        id = count;
    }

    //Getters e Setters
    public int getId()
    {
        return id;
    }

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate _Data)
    {
        data = _Data;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String _Endereco)
    {
        endereco = _Endereco;
    }
    
    public Seguradora getSeguradora()
    {
        return seguradora;
    }

    public void setSeguradora(Seguradora _Seguradora)
    {
        seguradora = _Seguradora;
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo _Veiculo)
    {
        veiculo = _Veiculo;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente _Cliente)
    {
        cliente = _Cliente;
    }

    //Métodos
    public String ToString()
    {
        return "\n---SINISTRO---\nID: %d\nData: %s\nEndereco: %s\nSeguradora: %s\nVeiculo: %s\nCliente: %s".formatted(id, data.toString(), endereco, seguradora.getNome(), veiculo.getModelo(), cliente.getNome());
    }
}
