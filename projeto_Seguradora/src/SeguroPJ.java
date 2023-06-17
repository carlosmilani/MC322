import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class SeguroPJ extends Seguro
{
    //Propriedades
    private Frota frota;
    private ClientePJ cliente;
    
    //Construtor
    public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente)
    {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
        calcularValor();
    }

    //Getters e Setters
    public Frota getFrota()
    {
        return frota;
    }

    public void setFrota(Frota frota)
    {
        this.frota = frota;
    }

    public ClientePJ getCliente()
    {
        return cliente;
    }

    public void setCliente(ClientePJ cliente)
    {
        this.cliente = cliente;
    }

    //Métodos
    @Override
    public String ToString()
    {
        return "\n---SEGURO---\nID: %d\nData início: %s\nData fim: %s\nSeguradora: %s\nValor mensal: %f\nFrota: %s\nCliente: %s".formatted(getId(), getDataInicio().toString(), getDataFim().toString(), getSeguradora().getNome(), getValorMensal(), frota.getCode(), cliente.getNome());
    }

    @Override
    public double calcularValor()
    {
        LocalDate hoje = LocalDate.now();
        int anosPosFundacao = Period.between(cliente.getDataFundacao(), hoje).getYears();
        ArrayList<Sinistro> sinistrosCliente = getSeguradora().getSinistrosPorCliente(cliente);
        int qtdeSinistrosCliente = sinistrosCliente.size();
        int qtdeSinistrosCondutores = 0;
        for (Condutor condutor : getListaCondutores())
        {
            qtdeSinistrosCondutores += condutor.getListaSinistros().size();
        }
        setValorMensal(CalcSeguro.VALOR_BASE.getMultiplicador() * (10 + cliente.getQtdeFuncionarios()/10.0) * (1 + 1.0/(frota.getListaVeiculos().size() + 2)) *
                (1 + 1.0/(anosPosFundacao + 2)) * (2 + qtdeSinistrosCliente/10.0) * (5 + qtdeSinistrosCondutores/10.0));
        return getValorMensal();
    }
}
