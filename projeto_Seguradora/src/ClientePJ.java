import java.util.ArrayList;
import java.time.LocalDate;

public class ClientePJ extends Cliente
{
    //Propriedades
    private final String cnpj;
    private LocalDate dataFundacao;
    private int qtdeFuncionarios;

    //Construtor
    public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, LocalDate dataFundacao, int qtdeFuncionarios)
    {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
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

    //Métodos
    @Override
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCNPJ: %s\nEndereco: %s".formatted(getNome(), cnpj, getEndereco()); 
    }

    @Override
    public double calcularScore()
    {
        return CalcSeguro.VALOR_BASE.getMultiplicador() * (qtdeFuncionarios / 100 + 1) * getListaVeiculos().size();
    }
}
