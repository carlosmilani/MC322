import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;

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
        this.cpf = cpf;
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
    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCPF: %s\nEndereco: %s".formatted(getNome(), cpf, getEndereco()); 
    }

    @Override
    public double calcularScore()
    {
        LocalDate hoje = LocalDate.now();
        int idade = Period.between(dataNascimento, hoje).getYears();
        if (18 <= idade && idade < 30)
        {
            return CalcSeguro.VALOR_BASE.getMultiplicador() * CalcSeguro.FATOR_18_30.getMultiplicador() * getListaVeiculos().size();
        }
        else if (30 <= idade && idade < 60)
        {
            return CalcSeguro.VALOR_BASE.getMultiplicador() * CalcSeguro.FATOR_30_60.getMultiplicador() * getListaVeiculos().size();
        }
        else if (60 <= idade && idade < 90)
        {
            return CalcSeguro.VALOR_BASE.getMultiplicador() * CalcSeguro.FATOR_60_90.getMultiplicador() * getListaVeiculos().size();
        }
        return 0;
    }
}