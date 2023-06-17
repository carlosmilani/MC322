import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Seguradora
{
    //Propriedades
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;
    private static Scanner scanner = new Scanner(System.in);

    //Construtor
    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email)
    {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email= email;
        listaClientes = new ArrayList<Cliente>();
        listaSeguros = new ArrayList<Seguro>();
    }

    //Getters e Setters
    public String getCnpj()
    {
        return cnpj;
    }

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

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String _Endereco)
    {
        this.endereco = _Endereco;
    }


    public String getEmail()
    {
        return email;
    }

    public void setEmail(String _Email)
    {
        this.email = _Email;
    }

    public ArrayList<Cliente> getListaClientes()
    {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes)
    {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros()
    {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguro)
    {
        this.listaSeguros = listaSeguro;
    }

    //Métodos
    public String ToString()
    {
        return "\n---SEGURADORA---\nNome: %s\nCNPJ: %s\nTelefone: %s\nEndereco: %s".formatted(nome, cnpj, telefone, endereco);
    }

    public boolean cadastrarCliente(Cliente cliente)
    {
        listaClientes.add(cliente);
        System.out.println("\nCliente cadastrado com sucesso!");
        return true;
    }

    public boolean removerCliente(Cliente cliente)
    {
        listaClientes.remove(cliente);
        System.out.println("\nCliente removido com sucesso!");
        ArrayList<Seguro> segurosCliente = getSegurosPorCliente(cliente);
        for (Seguro seguro : segurosCliente)
        {
            cancelarSeguro(seguro);
        }
        return true;
    }

    public boolean listarClientes()
    {
        for (Cliente cliente : listaClientes)
        {
            System.out.println(cliente.ToString());
        }
        if (listaClientes.size() == 0)
        {
            System.out.println("\nNão há clientes nesta seguradora.");
            return false;
        }
        return true;
    }

    public boolean listarClientesPF()
    {
        ArrayList<Cliente> listaClientesPF = getClientesPF();
        for (Cliente cliente : listaClientesPF)
        {
            System.out.println(cliente.ToString());
        }
        if (listaClientesPF.size() == 0)
        {
            System.out.println("\nNão há clientes deste tipo na seguradora.");
            return false;
        }
        return true;
    }

    public boolean listarClientesPJ()
    {
        ArrayList<Cliente> listaClientesPJ = getClientesPJ();
        for (Cliente cliente : listaClientesPJ)
        {
            System.out.println(cliente.ToString());
        }
        if (listaClientesPJ.size() == 0)
        {
            System.out.println("\nNão há clientes deste tipo na seguradora.");
            return false;
        }
        return true;
    }

    public ArrayList<Cliente> getClientesPF()
    {
        ArrayList<Cliente> listaClientesPF = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes)
        {
            if (cliente instanceof ClientePF)
            {
                listaClientesPF.add(cliente);
            }    
        }
        return listaClientesPF;
    }

    public ArrayList<Cliente> getClientesPJ()
    {
        ArrayList<Cliente> listaClientesPJ = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes)
        {
            if (cliente instanceof ClientePJ)
            {
                listaClientesPJ.add(cliente);
            }    
        }
        return listaClientesPJ;
    }

    public boolean gerarSeguro(ClientePF cliente)
    {
        System.out.print("\nData de início do seguro (AAAA-MM-DD): ");
        String inicioString = scanner.nextLine();
        LocalDate dataInicio = LocalDate.parse(inicioString);
        System.out.print("Data de fim do seguro (AAAA-MM-DD): ");
        String fimString = scanner.nextLine();
        LocalDate dataFim = LocalDate.parse(fimString);
        Veiculo veiculo = EscolherDaLista.escolherVeiculo(cliente.getListaVeiculos());
        Seguro seguro = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
        listaSeguros.add(seguro);
        System.out.println("\nSeguro gerado com sucesso!");
        return true;
    }

    public boolean gerarSeguro(ClientePJ cliente)
    {
        System.out.print("\nData de início do seguro (AAAA-MM-DD): ");
        String inicioString = scanner.nextLine();
        LocalDate dataInicio = LocalDate.parse(inicioString);
        System.out.print("Data de fim do seguro (AAAA-MM-DD): ");
        String fimString = scanner.nextLine();
        LocalDate dataFim = LocalDate.parse(fimString);
        Frota frota = EscolherDaLista.escolherFrota(cliente.getListaFrota());
        Seguro seguro = new SeguroPJ(dataInicio, dataFim, this, frota, cliente);
        listaSeguros.add(seguro);
        System.out.println("\nSeguro gerado com sucesso!");
        return true;
    }

    public boolean cancelarSeguro(Seguro seguro)
    {
        listaSeguros.remove(seguro);
        System.out.println("\nSeguro removido com sucesso!");
        return true;
    }

    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente)
    {
        ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
        for (Seguro seguro : listaSeguros)
        {
            if (seguro instanceof SeguroPF && ((SeguroPF)seguro).getCliente().equals(cliente) ||
                seguro instanceof SeguroPJ && ((SeguroPJ)seguro).getCliente().equals(cliente))
            {
                segurosCliente.add(seguro);
            }
        }
        return segurosCliente;
    }

    public boolean listarSeguros()
    {
        if (listaSeguros.size() == 0)
        {
            System.out.println("\nEsta seguradora não possui seguros.");
            return false;
        }
        for (Seguro seguro : listaSeguros)
        {
            System.out.println(seguro.ToString());
        }
        return true;
    }

    public boolean listarSinistros()
    {
        boolean existeSinistro = false;
        for (Seguro seguro : listaSeguros)
        {
            for (Sinistro sinistro : seguro.getListaSinistros())
            {
                System.out.println(sinistro.ToString());
                existeSinistro = true;
            }
        }
        if (existeSinistro)
        {
            return true;
        }
        System.out.println("\nEsta seguradora não possui sinistros.");
        return false;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente)
    {
        ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();
        for (Seguro seguro : listaSeguros)
        {
            if (seguro instanceof SeguroPF && ((SeguroPF)seguro).getCliente().equals(cliente) ||
                seguro instanceof SeguroPJ && ((SeguroPJ)seguro).getCliente().equals(cliente))
            {
                sinistrosCliente.addAll(seguro.getListaSinistros());
            }
        }
        return sinistrosCliente;
    }

    public boolean listarVeiculos()
    {
        if (listaClientes.size() == 0)
        {
            System.out.println("\nEsta seguradora não possui veículos cadastrados.");
            return false;
        }
        for (Cliente cliente : listaClientes)
        {
            cliente.listarVeiculos();    
        }
        return true;
    }

    public double calcularReceita()
    {
        double receita = 0;
        for (Seguro seguro : listaSeguros)
        {
            receita += seguro.calcularValor();
        }
        return receita;
    }

    public static Seguradora gerarSeguradora()
    {
        System.out.print("\nNome da seguradora: ");
        String nome = scanner.nextLine();
        while (Validacao.validarNome(nome) == false)
        {
            System.out.print("\nNome invalido! Digite novamente: ");
            nome = scanner.nextLine();
        }
        System.out.print("CNPJ do cliente: ");
        String cnpj = scanner.nextLine().replaceAll("[^0-9]", "");
        while (Validacao.validarCNPJ(cnpj) == false)
        {
            System.out.print("\nCNPJ inválido! Digite novamente: ");
            cnpj = scanner.nextLine().replaceAll("[^0-9]", "");
        }
        System.out.print("Telefone da seguradora: ");
        String telefone = scanner.nextLine();
        System.out.print("Email da seguradora: ");
        String email = scanner.nextLine();
        System.out.print("Endereço da seguradora: ");
        String endereco = scanner.nextLine();
        Seguradora seguradora = new Seguradora(cnpj, nome, telefone, email, endereco);
        return seguradora;
    }
}
