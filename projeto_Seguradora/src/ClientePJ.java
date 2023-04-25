import java.util.ArrayList;
import java.time.LocalDate;

public class ClientePJ extends Cliente
{
    private final String cnpj;
    private LocalDate dataFundacao;

    //Construtor
    public ClientePJ(String _Nome, String _Endereco, ArrayList<Veiculo> _ListaVeiculos, String _Cnpj, LocalDate _DataFundacao)
    {
        super(_Nome, _Endereco, _ListaVeiculos);
        cnpj = _Cnpj.replaceAll("[^0-9]", "");;
        dataFundacao = _DataFundacao;
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

    public void setDataFundacao(LocalDate _DataFundacao)
    {
        dataFundacao = _DataFundacao;
    }

    //Métodos
    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCNPJ: %s\nEndereco: %s".formatted(getNome(), cnpj, getEndereco()); 
    }

    private boolean checarDigitos(String _Cnpj)
    {
        if (_Cnpj.length() != 14)
        {
            return false;
        }
        for (int index = 1; index < _Cnpj.length(); index++)
        {
            if (_Cnpj.charAt(0) != _Cnpj.charAt(index))
            {
                return true;
            }
        }
        return false;
    }

    private boolean calcularVerificadores(String _Cnpj)
    {
        int soma_1 = 0, soma_2 = 0, array1[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, array2[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        char verificador_1, verificador_2;
        for (int index = 0; index < 12; index++)
        {
            soma_1 += array1[index] * (_Cnpj.charAt(index) - 48);
            soma_2 += array2[index] * (_Cnpj.charAt(index) - 48);
        }
        soma_2 += array2[12] * (_Cnpj.charAt(12) - 48);
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
        if (verificador_1 != _Cnpj.charAt(12) || verificador_2 != _Cnpj.charAt(13))
        {
            return false;
        }
        return true;
    }

    public boolean validarCNPJ(String _Cnpj)
    {
        boolean catorze_digitos_diferentes = checarDigitos(_Cnpj);
        if (catorze_digitos_diferentes)
        {
            boolean digitos_verificadores = calcularVerificadores(_Cnpj);
            return digitos_verificadores;
        }
        return false;
    }
}
