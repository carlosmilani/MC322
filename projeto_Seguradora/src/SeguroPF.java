import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class SeguroPF extends Seguro
{
    //Propriedades
    private Veiculo veiculo;
    private ClientePF cliente;
    
    //Construtor
    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente)
    {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
        calcularValor();
    }

    //Getters e Setters
    public Veiculo getVeiculo()
    {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente()
    {
        return cliente;
    }

    public void setCliente(ClientePF cliente)
    {
        this.cliente = cliente;
    }

    //Métodos
    @Override
    public String ToString()
    {
        return "\n---SEGURO---\nID: %d\nData início: %s\nData fim: %s\nSeguradora: %s\nValor mensal: %f\nVeiculo: %s\nCliente: %s".formatted(getId(), getDataInicio().toString(), getDataFim().toString(), getSeguradora().getNome(), getValorMensal(), veiculo.getModelo(), cliente.getNome());
    }

    @Override
    public double calcularValor()
    {
        LocalDate hoje = LocalDate.now();
        int idade = Period.between(cliente.getDataNascimento(), hoje).getYears();
        ArrayList<Sinistro> sinistrosCliente = getSeguradora().getSinistrosPorCliente(cliente);
        int qtdeSinistrosCliente = sinistrosCliente.size();
        int qtdeSinistrosCondutores = 0;
        for (Condutor condutor : getListaCondutores())
        {
            qtdeSinistrosCondutores += condutor.getListaSinistros().size();
        }
        if (18 <= idade && idade < 30)
        {
            setValorMensal(CalcSeguro.VALOR_BASE.getMultiplicador() * CalcSeguro.FATOR_18_30.getMultiplicador() * (1 + 1.0/(cliente.getListaVeiculos().size() + 2))
                    * (2 + qtdeSinistrosCliente/10.0) * (5 + qtdeSinistrosCondutores/10.0));
            return getValorMensal();
        }
        else if (30 <= idade && idade < 60)
        {
            setValorMensal(CalcSeguro.VALOR_BASE.getMultiplicador() * CalcSeguro.FATOR_30_60.getMultiplicador() * (1 + 1.0/(cliente.getListaVeiculos().size() + 2))
                    * (2 + qtdeSinistrosCliente/10.0) * (5 + qtdeSinistrosCondutores/10.0));
            return getValorMensal();
        }
        else if (60 <= idade)
        {
            setValorMensal(CalcSeguro.VALOR_BASE.getMultiplicador() * CalcSeguro.FATOR_60plus.getMultiplicador() * (1 + 1.0/(cliente.getListaVeiculos().size() + 2))
                    * (2 + qtdeSinistrosCliente/10.0) * (5 + qtdeSinistrosCondutores/10.0));
            return getValorMensal();
        }
        return 0;
    }
}
