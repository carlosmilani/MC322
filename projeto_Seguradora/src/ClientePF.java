import java.util.ArrayList;
import java.time.LocalDate;

public class ClientePF extends Cliente
{
    private final String cpf;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private LocalDate dataNascimento;
    private LocalDate dataLicenca;
    
    //Construtor
    public ClientePF(String _Nome, String _Endereco, ArrayList<Veiculo> _ListaVeiculos,
                    String _Cpf, String _Educacao, String _Genero, String _ClasseEconomica,
                    LocalDate _DataNascimento, LocalDate _DataLicenca)
    {
        super(_Nome, _Endereco, _ListaVeiculos);
        cpf = _Cpf.replaceAll("[^0-9]", "");
        educacao = _Educacao;
        genero = _Genero;
        classeEconomica = _ClasseEconomica;
        dataNascimento = _DataNascimento;
        dataLicenca = _DataLicenca;
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

    public void setEducacao(String _Educacao)
    {
        educacao = _Educacao;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String _Genero)
    {
        genero = _Genero;
    }

    public String getClasseEconomica()
    {
        return classeEconomica;
    }

    public void setClasseEconomica(String _ClasseEconomica)
    {
        classeEconomica = _ClasseEconomica;
    }

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate _DataNascimento)
    {
        dataNascimento = _DataNascimento;
    }

    public LocalDate getDataLicenca()
    {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate _DataLicenca)
    {
        dataLicenca = _DataLicenca;
    }

    //Métodos
    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCPF: %s\nEndereco: %s".formatted(getNome(), cpf, getEndereco()); 
    }

    private boolean checarDigitos(String _Cpf)
    {
        if (_Cpf.length() != 11)
        {
            return false;
        }
        for (int index = 1; index < _Cpf.length(); index++)
        {
            if (_Cpf.charAt(0) != _Cpf.charAt(index))
            {
                return true;
            }
        }
        return false;
    }

    private boolean calcularVerificadores(String _Cpf)
    {
        int soma_1 = 0, soma_2 = 0;
        char verificador_1, verificador_2;
        for (int index = 0; index < 9; index++)
        {
            soma_1 += (10 - index) * (_Cpf.charAt(index) - 48);
            soma_2 += (10 - index) * (_Cpf.charAt(index + 1) - 48);
        }
        if (soma_1 % 11 == 0 || soma_1 % 11 == 1)
        {
            verificador_1 = '0';
        }
        else 
        {
            verificador_1 = (char)(11 - soma_1 % 11 + 48);
        }
        if (soma_2 % 11 == 0 || soma_2 % 11 == 1)
        {
            verificador_2 = '0';
        }
        else 
        {
            verificador_2 = (char)(11 - soma_2 % 11 + 48);
        }
        if (verificador_1 != _Cpf.charAt(9) || verificador_2 != _Cpf.charAt(10))
        {
            return false;
        }
        return true;
    }

    public boolean validarCPF(String _Cpf)
    {
        boolean onze_digitos_diferentes = checarDigitos(_Cpf);
        if (onze_digitos_diferentes)
        {
            boolean digitos_verificadores = calcularVerificadores(_Cpf);
            return digitos_verificadores;
        }
        return false;
    }
}