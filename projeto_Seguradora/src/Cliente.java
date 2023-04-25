import java.util.ArrayList;

public class Cliente {

    //Propriedades
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    

    //Construtor
    public Cliente(String _Nome, String _Endereco, ArrayList<Veiculo> _ListaVeiculos)
    {
        nome = _Nome;
        endereco = _Endereco;
        listaVeiculos = _ListaVeiculos;
    }

    //Getters e Setters
    public String getNome()
    {
        return nome;
    }

    public void setNome(String _Nome)
    {
        nome = _Nome;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String _Endereco)
    {
        endereco = _Endereco;
    }

    public ArrayList<Veiculo> getListaVeiculos()
    {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> _ListaVeiculos)
    {
        listaVeiculos = _ListaVeiculos;
    }

    //Métodos
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nEndereco: %s".formatted(nome, endereco);
    }
}
