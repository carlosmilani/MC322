import java.util.ArrayList;
import java.time.LocalDate;

public class Seguradora
{
    //Propriedades
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    //Construtor
    public Seguradora(String nome, String telefone, String email, String endereco)
    {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        listaClientes = new ArrayList<Cliente>();
        listaSinistros = new ArrayList<Sinistro>();
    }

    //Getters e Setters
    public String getNome()
    {
        return nome;
    }

    public void setNome(String _Nome)
    {
        this.nome = _Nome;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String _Telefone)
    {
        this.telefone = _Telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String _Email)
    {
        this.email = _Email;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String _Endereco)
    {
        this.endereco = _Endereco;
    }

    //Métodos
    public boolean cadastrarCliente(Cliente cliente)
    {
        listaClientes.add(cliente);
        System.out.println("\nCliente adicionado com sucesso!");
        return true;
    }

    public boolean removerCliente(String dados)
    {
        dados = dados.replaceAll("[^0-9]", "");
        for (Cliente cliente : listaClientes) {
            if (cliente instanceof ClientePF && ((ClientePF)cliente).getCpf().equals(dados))
            {
                listaClientes.remove(cliente);
                System.out.println("\nCliente removido com sucesso!");
                return true;
            }
            else if (cliente instanceof ClientePJ && ((ClientePJ)cliente).getCnpj().equals(dados))
            {
                listaClientes.remove(cliente);
                System.out.println("\nCliente removido com sucesso!");
                return true;
            }
        }
        System.out.println("\nNão foi possível remover o cliente.");
        return false;
    }

    public void listarClientes(String tipoCliente)
    {
        for (Cliente cliente : listaClientes) {
            if (tipoCliente.equals("PF") && cliente instanceof ClientePF)
            {
                System.out.println(cliente.ToString());
            }
            else if (tipoCliente.equals("PJ") && cliente instanceof ClientePJ)
            {
                System.out.println(cliente.ToString());
            }
            else if (tipoCliente.equals("x"))
            {
                System.out.println(cliente.ToString());
            }
        }
    }

    public boolean gerarSinistro(String endereco, String placaVeiculo, String dados, LocalDate data)
    {
        dados = dados.replaceAll("[^0-9]", "");
        for (Cliente cliente : listaClientes)
        {
            if (cliente instanceof ClientePF && ((ClientePF)cliente).getCpf().equals(dados) ||
                cliente instanceof ClientePJ && ((ClientePJ)cliente).getCnpj().equals(dados))
            {
                for (Veiculo veiculo : cliente.getListaVeiculos())
                {
                    if (veiculo.getPlaca().equals(placaVeiculo))
                    {
                        Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
                        listaSinistros.add(sinistro);
                        System.out.println("\nSinistro gerado com sucesso!");
                        return true;
                    }
                }
            }
        }
        System.out.println("\nNão foi possível gerar o sinistro.");
        return false;
    }

    public boolean visualizarSinistro(String dados)
    {
        dados = dados.replaceAll("[^0-9]", "");
        for (Sinistro sinistro : listaSinistros) {
            if (sinistro.getCliente() instanceof ClientePF && ((ClientePF)sinistro.getCliente()).getCpf().equals(dados) ||
                sinistro.getCliente() instanceof ClientePJ && ((ClientePJ)sinistro.getCliente()).getCnpj().equals(dados))
            {
                System.out.println(sinistro.ToString());
                return true;
            }
        }
        return false;
    }

    public void listarSinistros()
    {
        for (Sinistro sinistro : listaSinistros)
        {
            System.out.println(sinistro.ToString());
        }
    }

    public String ToString()
    {
        return "\n---SEGURADORA---\nNome: %s\nTelefone: %s\nE-mail: %s\nEndereco: %s".formatted(nome, telefone, email, endereco);
    }
}
