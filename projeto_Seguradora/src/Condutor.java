import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public class Condutor
{
    //Propriedades
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNascimento;
    private ArrayList<Sinistro> listaSinistros;
    private static Scanner scanner = new Scanner(System.in);

    //Construtor
    public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNascimento)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        listaSinistros = new ArrayList<Sinistro>();
    }

    //Getters e Setters
    public String getCpf()
    {
        return cpf;
    }

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

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Sinistro> getListaSinistros()
    {
        return listaSinistros;
    }

    public void setListaSinistro(ArrayList<Sinistro> listaSinistros)
    {
        this.listaSinistros = listaSinistros;
    }

    //Métodos
    public String ToString()
    {
        return "\n---CONDUTOR---\nNome: %s\nCPF: %s\nEndereco: %s".formatted(getNome(), cpf, getEndereco());
    }

    public boolean adicionarSinistro(Sinistro sinistro)
    {
        listaSinistros.add(sinistro);
        return true;
    }

    public boolean removerSinistro(Sinistro sinistro)
    {
        listaSinistros.remove(sinistro);
        return true;
    }

    public static Condutor gerarCondutor()
    {
        System.out.print("\nNome do condutor: ");
        String nome = scanner.nextLine().replaceAll("\\d", "");;
        while (Validacao.validarNome(nome) == false)
        {
            System.out.print("\nNome inválido! Digite novamente: ");
            nome = scanner.nextLine().replaceAll("\\d", "");
        }
        System.out.print("Telefone do condutor: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço do condutor: ");
        String endereco = scanner.nextLine();
        System.out.print("Email do condutor: ");
        String email = scanner.nextLine();
        System.out.print("CPF do condutor: ");
        String cpf = scanner.nextLine().replaceAll("[^0-9]", "");
        while (Validacao.validarCPF(cpf) == false)
        {
            System.out.print("\nCPF inválido! Digite novamente: ");
            cpf = scanner.nextLine().replaceAll("[^0-9]", "");
        }
        System.out.print("Data de nascimento do condutor (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataString);
        Condutor condutor = new Condutor(cpf, nome, telefone, endereco, email, dataNascimento);
        return condutor;
    }

}
