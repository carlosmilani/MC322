import java.util.ArrayList;

public abstract class Cliente
{
    //Propriedades
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private double valorSeguro;

    //Construtor
    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos)
    {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
        valorSeguro = 0;
    }

    //Getters e Setters
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public ArrayList<Veiculo> getListaVeiculos()
    {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos)
    {
        this.listaVeiculos = listaVeiculos;
    }

    public double getValorSeguro()
    {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro)
    {
        this.valorSeguro = valorSeguro;
    }

    //Métodos
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nEndereco: %s".formatted(nome, endereco);
    }

    public abstract double calcularScore();
}
