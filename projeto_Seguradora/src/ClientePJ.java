import java.util.ArrayList;
import java.time.LocalDate;

public class ClientePJ extends Cliente
{
    //Propriedades
    private final String cnpj;
    private LocalDate dataFundacao;

    //Construtor
    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, LocalDate dataFundacao)
    {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj.replaceAll("[^0-9]", "");;
        this.dataFundacao = dataFundacao;
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

    //Métodos
    private boolean checarDigitos(String cnpj)
    {
        if (cnpj.length() != 14)
        {
            return false;
        }
        for (int index = 1; index < cnpj.length(); index++)
        {
            if (cnpj.charAt(0) != cnpj.charAt(index))
            {
                return true;
            }
        }
        return false;
    }

    private boolean calcularVerificadores(String cnpj)
    {
        int soma1 = 0, soma2 = 0, array1[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, array2[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        char verificador1, verificador2;
        for (int index = 0; index < 12; index++)
        {
            soma1 += array1[index] * (cnpj.charAt(index) - 48);
            soma2 += array2[index] * (cnpj.charAt(index) - 48);
        }
        soma2 += array2[12] * (cnpj.charAt(12) - 48);
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
        if (verificador1 != cnpj.charAt(12) || verificador2 != cnpj.charAt(13))
        {
            return false;
        }
        return true;
    }

    public boolean validarCNPJ(String cnpj)
    {
        boolean catorzeDigitosDiferentes = checarDigitos(cnpj);
        if (catorzeDigitosDiferentes)
        {
            boolean digitosVerificadores = calcularVerificadores(cnpj);
            return digitosVerificadores;
        }
        return false;
    }

    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCNPJ: %s\nEndereco: %s".formatted(getNome(), cnpj, getEndereco()); 
    }
}
