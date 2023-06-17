import java.time.LocalDate;

public class Sinistro
{
    //Propriedades
    private static int count = 0;
    private final int id;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    //Construtor
    public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro)
    {
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
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

    public Condutor getCondutor()
    {
        return condutor;
    }

    public void setCondutor(Condutor condutor)
    {
        this.condutor = condutor;
    }
    
    public Seguro getSeguro()
    {
        return seguro;
    }

    public void setSeguro(Seguro seguro)
    {
        this.seguro = seguro;
    }

    //Métodos
    public String ToString()
    {
        return "\n---SINISTRO---\nID: %d\nData: %s\nEndereco: %s\nCondutor: %s\nID do seguro: %s".formatted(id, data.toString(), endereco, condutor.getNome(), seguro.getId());
    }
}
