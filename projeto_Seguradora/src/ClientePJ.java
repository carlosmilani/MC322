import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class ClientePJ extends Cliente
{
    //Propriedades
    private final String cnpj;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;
    private ArrayList<Frota> listaFrota;
    private static Scanner scanner = new Scanner(System.in);

    //Construtor
    public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, LocalDate dataFundacao, int qtdeFuncionarios)
    {
        super(nome, telefone, endereco, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
        listaFrota = new ArrayList<Frota>();
    }

    //Getters e Setters
    public String getCnpj()
    {
        return cnpj;
    }

    public LocalDate getDataFundacao()
    {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao)
    {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdeFuncionarios()
    {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios)
    {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public ArrayList<Frota> getListaFrota()
    {
        return listaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota)
    {
        this.listaFrota = listaFrota;
    }

    //Métodos
    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCNPJ: %s\nEndereco: %s".formatted(getNome(), cnpj, getEndereco()); 
    }

    @Override
    public boolean cadastrarVeiculo()
    {
        if (listaFrota.size() == 0)
        {
            System.out.println("\nEste cliente não possui nenhuma frota.");
            return false;
        }
        Frota frota = EscolherDaLista.escolherFrota(listaFrota);
        frota.adicionarVeiculo();
        return true;
    }

    @Override
    public boolean removerVeiculo(Seguradora seguradora)
    {
        if (listaFrota.size() == 0)
        {
            System.out.println("\nEste cliente não possui nenhum veículo cadastrado.");
            return false;
        }
        Frota frota = EscolherDaLista.escolherFrota(listaFrota);
        if (frota.getListaVeiculos().size() == 0)
        {
            System.out.println("\nEsta frota não possui nenhum veículo.");
            return false;
        }
        frota.removerVeiculo();
        return true;
    }

    @Override
    public boolean listarVeiculos()
    {
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        for (Frota frota : listaFrota)
        {
            listaVeiculos.addAll(getVeiculosPorFrota(frota));
        }
        if (listaVeiculos.size() == 0)
        {
            return false;
        }
        for (Veiculo veiculo : listaVeiculos)
        {
            System.out.println(veiculo.ToString());
        }
        return true;
    }

    public boolean cadastrarFrota()
    {
        Frota frota = Frota.gerarFrota();
        listaFrota.add(frota);
        System.out.println("\nFrota cadastrada com sucesso!");
        return true;
    }

    public boolean removerFrota(Frota frota, Seguradora seguradora)
    {
        listaFrota.remove(frota);
        System.out.println("\nFrota removida com sucesso.");
        for (Seguro seguro : seguradora.getSegurosPorCliente(this))
        {
            if (((SeguroPJ)seguro).getFrota().equals(frota))
            {
                seguradora.cancelarSeguro(seguro);
            }
        }
        return true;
    }

    public boolean atualizarFrota(Frota frota, Seguradora seguradora)
    {
        System.out.println("\nAtualizar Frota");
        System.out.print("0 - Adicionar veículo\n1 - Remover veículo\n2 - Excluir frota\nO que você gostaria de fazer? ");
        int comando = scanner.nextInt();
        scanner.nextLine();
        while (comando < 0 || comando >= 3)
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            comando = scanner.nextInt();
            scanner.nextLine();
        }
        if (comando == 0)
        {
            frota.adicionarVeiculo();
        }
        else if (comando == 1)
        {
            frota.removerVeiculo();
        }
        else if (comando == 2)
        {
            removerFrota(frota, seguradora);
        }
        return true;
    }
    
    public ArrayList<Veiculo> getVeiculosPorFrota(Frota frota)
    {
        ArrayList<Veiculo> veiculosFrota = new ArrayList<Veiculo>();
        for (Veiculo veiculo : frota.getListaVeiculos())
        {
            veiculosFrota.add(veiculo);    
        }
        return veiculosFrota;
    }

    public static ClientePJ gerarClientePJ()
    {
        System.out.print("\nNome do cliente: ");
        String nome = scanner.nextLine().replaceAll("\\d", "");;
        while (Validacao.validarNome(nome) == false)
        {
            System.out.print("\nNome inválido! Digite novamente: ");
            nome = scanner.nextLine().replaceAll("\\d", "");
        }
        System.out.print("Telefone do cliente: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();
        System.out.print("Email do cliente: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ do cliente: ");
        String cnpj = scanner.nextLine().replaceAll("[^0-9]", "");
        while (Validacao.validarCNPJ(cnpj) == false)
        {
            System.out.print("\nCNPJ inválido! Digite novamente: ");
            cnpj = scanner.nextLine().replaceAll("[^0-9]", "");
        }
        System.out.print("Data de fundação do cliente (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate dataFundacao = LocalDate.parse(dataString);
        System.out.print("Quantidade de funcionários do cliente: ");
        int qtdeFuncionarios = scanner.nextInt();
        scanner.nextLine();
        ClientePJ cliente = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, qtdeFuncionarios);
        return cliente;
    }
}
