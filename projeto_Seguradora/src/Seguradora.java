import java.util.ArrayList;
import java.time.LocalDate;

public class Seguradora {

    //Propriedades
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    //Construtor
    public Seguradora(String _Nome, String _Telefone, String _Email, String _Endereco)
    {
        nome = _Nome;
        telefone = _Telefone;
        email = _Email;
        endereco = _Endereco;
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
        nome = _Nome;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String _Telefone)
    {
        telefone = _Telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String _Email)
    {
        email = _Email;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String _Endereco)
    {
        endereco = _Endereco;
    }

    //Métodos
    public boolean cadastrarCliente(Cliente _Cliente)
    {
        listaClientes.add(_Cliente);
        System.out.println("\nCliente adicionado com sucesso!");
        return true;
    }

    public boolean removerCliente(String _Dados)
    {
        String dados = _Dados.replaceAll("[^0-9]", "");
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

    public void listarClientes(String _TipoCliente)
    {
        for (Cliente cliente : listaClientes) {
            if (_TipoCliente.equals("PF") && cliente instanceof ClientePF)
            {
                System.out.println(cliente.ToString());
            }
            else if (_TipoCliente.equals("PJ") && cliente instanceof ClientePJ)
            {
                System.out.println(cliente.ToString());
            }
            else if (_TipoCliente.equals("x"))
            {
                System.out.println(cliente.ToString());
            }
        }
    }

    public boolean gerarSinistro(String _Endereco, String _PlacaVeiculo, String _Dados, LocalDate _Data)
    {
        String dados = _Dados.replaceAll("[^0-9]", "");
        for (Cliente cliente : listaClientes)
        {
            if (cliente instanceof ClientePF && ((ClientePF)cliente).getCpf().equals(dados) ||
                cliente instanceof ClientePJ && ((ClientePJ)cliente).getCnpj().equals(dados))
            {
                for (Veiculo veiculo : cliente.getListaVeiculos())
                {
                    if (veiculo.getPlaca().equals(_PlacaVeiculo))
                    {
                        Sinistro sinistro = new Sinistro(_Data, _Endereco, this, veiculo, cliente);
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

    public boolean visualizarSinistro(String _Dados)
    {
        String dados = _Dados.replaceAll("[^0-9]", "");
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
