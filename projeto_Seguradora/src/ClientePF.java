import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientePF extends Cliente
{
    //Propriedades
    private final String cpf;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private LocalDate dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;
    private static Scanner scanner = new Scanner(System.in);
    
    //Construtor
    public ClientePF(String nome, String telefone, String endereco, String email, String cpf, String educacao, String genero, String classeEconomica, LocalDate dataNascimento)
    {
        super(nome, telefone, endereco, email);
        this.cpf = cpf;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataNascimento = dataNascimento;
        listaVeiculos = new ArrayList<Veiculo>();
    }

    //Getters e Setters
    public String getCpf()
    {
        return cpf;
    }

    public String getEducacao()
    {
        return educacao;
    }

    public void setEducacao(String educacao)
    {
        this.educacao = educacao;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    public String getClasseEconomica()
    {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica)
    {
        this.classeEconomica = classeEconomica;
    }

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Veiculo> getListaVeiculos()
    {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos)
    {
        this.listaVeiculos = listaVeiculos;
    }

    //Métodos
    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCPF: %s\nEndereco: %s".formatted(getNome(), cpf, getEndereco()); 
    }

    @Override
    public boolean cadastrarVeiculo()
    {
        Veiculo veiculo = Veiculo.gerarVeiculo();
        listaVeiculos.add(veiculo);
        System.out.println("\nVeículo cadastrado com sucesso!");
        return true;
    }

    @Override
    public boolean removerVeiculo(Seguradora seguradora)
    {
        if (listaVeiculos.size() == 0)
        {
            System.out.println("\nEste cliente não possui veículos cadastrados.");
            return false;
        }
        Veiculo veiculo = EscolherDaLista.escolherVeiculo(listaVeiculos);
        listaVeiculos.remove(veiculo);
        System.out.println("\nVeículo removido com sucesso!");
        for (Seguro seguro : seguradora.getSegurosPorCliente(this))
        {
            if (((SeguroPF)seguro).getVeiculo().equals(veiculo))
            {
                seguradora.cancelarSeguro(seguro);
            }
        }
        return true;
    }
    
    @Override
    public boolean listarVeiculos()
    {
        if (getListaVeiculos().size() == 0)
        {
            return false;
        }
        for (Veiculo veiculo : getListaVeiculos())
        {
            System.out.println(veiculo.ToString());    
        }
        return true;
    }

    public static ClientePF gerarClientePF()
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
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine().replaceAll("[^0-9]", "");
        while (Validacao.validarCPF(cpf) == false)
        {
            System.out.print("\nCPF inválido! Digite novamente: ");
            cpf = scanner.nextLine().replaceAll("[^0-9]", "");
        }
        System.out.print("Escolaridade do cliente: ");
        String educacao = scanner.nextLine();
        System.out.print("Gênero do cliente: ");
        String genero = scanner.nextLine();
        System.out.print("Classe econômica do cliente: ");
        String classeEconomica = scanner.nextLine();
        System.out.print("Data de nascimento do cliente (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataString);
        ClientePF cliente = new ClientePF(nome, telefone, endereco, email, cpf, educacao, genero, classeEconomica, dataNascimento);
        return cliente;
    }
}