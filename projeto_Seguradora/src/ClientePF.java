import java.util.ArrayList;
import java.time.LocalDate;

public class ClientePF extends Cliente
{
    //Propriedades
    private final String cpf;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private LocalDate dataNascimento;
    private LocalDate dataLicenca;
    
    //Construtor
    public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos,
                    String cpf, String educacao, String genero, String classeEconomica,
                    LocalDate dataNascimento, LocalDate dataLicenca)
    {
        super(nome, endereco, listaVeiculos);
        this.cpf = cpf.replaceAll("[^0-9]", "");
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataNascimento = dataNascimento;
        this.dataLicenca = dataLicenca;
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

    public LocalDate getDataLicenca()
    {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca)
    {
        this.dataLicenca = dataLicenca;
    }

    //Métodos
    private boolean checarDigitos(String cpf)
    {
        if (cpf.length() != 11)
        {
            return false;
        }
        for (int index = 1; index < cpf.length(); index++)
        {
            if (cpf.charAt(0) != cpf.charAt(index))
            {
                return true;
            }
        }
        return false;
    }

    private boolean calcularVerificadores(String cpf)
    {
        int soma1 = 0, soma2 = 0;
        char verificador1, verificador2;
        for (int index = 0; index < 9; index++)
        {
            soma1 += (10 - index) * (cpf.charAt(index) - 48);
            soma2 += (10 - index) * (cpf.charAt(index + 1) - 48);
        }
        if (soma1 % 11 == 0 || soma1 % 11 == 1)
        {
            verificador1 = '0';
        }
        else 
        {
            verificador1 = (char)(11 - soma1 % 11 + 48);
        }
        if (soma2 % 11 == 0 || soma2 % 11 == 1)
        {
            verificador2 = '0';
        }
        else 
        {
            verificador2 = (char)(11 - soma2 % 11 + 48);
        }
        if (verificador1 != cpf.charAt(9) || verificador2 != cpf.charAt(10))
        {
            return false;
        }
        return true;
    }

    public boolean validarCPF(String cpf)
    {
        boolean onzeDigitosDiferentes = checarDigitos(cpf);
        if (onzeDigitosDiferentes)
        {
            boolean digitosVerificadores = calcularVerificadores(cpf);
            return digitosVerificadores;
        }
        return false;
    }

    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCPF: %s\nEndereco: %s".formatted(getNome(), cpf, getEndereco()); 
    }
}