import java.time.LocalDate;

public class Sinistro
{
    //Propriedades
    private static int count = 0;
    private final int id;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    //Construtor
    public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente)
    {
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
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

    public void setData(LocalDate data)
    {
        this.data = data;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }
    
    public Seguradora getSeguradora()
    {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora)
    {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    //Métodos
    public String ToString()
    {
        return "\n---SINISTRO---\nID: %d\nData: %s\nEndereco: %s\nSeguradora: %s\nVeiculo: %s\nCliente: %s".formatted(id, data.toString(), endereco, seguradora.getNome(), veiculo.getModelo(), cliente.getNome());
    }
}
