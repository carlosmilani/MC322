import java.util.ArrayList;

public abstract class Cliente
{
    //Propriedades
    private String nome;
    private String telefone;
    private String endereco;
    private String email;

    //Construtor
    public Cliente(String nome, String telefone, String endereco, String email)
    {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
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

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    //Métodos
    public abstract String ToString();

    public abstract boolean cadastrarVeiculo();

    public abstract boolean removerVeiculo(Seguradora seguradora);

    public abstract boolean listarVeiculos();

    public static boolean listarSegurosCliente(ArrayList<Seguro> segurosCliente)
    {
        if (segurosCliente.size() == 0)
        {
            System.out.println("\nEste cliente não possui nenhum seguro.");
            return false;
        }
        for (Seguro seguro : segurosCliente)
        {
            System.out.println(seguro.ToString());    
        }
        return true;
    }

    public static boolean listarSinistrosCliente(ArrayList<Sinistro> sinistrosCliente)
    {
        if (sinistrosCliente.size() == 0)
        {
            System.out.println("\nEste cliente não possui nenhum sinistro.");
            return false;
        }
        for (Sinistro sinistro : sinistrosCliente)
        {
            System.out.println(sinistro.ToString());    
        }
        return true;
    }
}
