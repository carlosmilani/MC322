import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class Seguro
{
    //Propriedades
    private static int count = 0;
    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;
    private static Scanner scanner = new Scanner(System.in);

    //Construtor
    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora)
    {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
        count++;
        id = count;
    }

    //Getters e Setters
    public int getId()
    {
        return id;
    }

    public LocalDate getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim)
    {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora()
    {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora)
    {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros()
    {
        return listaSinistros;
    }

    public void setListaSinistro(ArrayList<Sinistro> listaSinistros)
    {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores()
    {
        return listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores)
    {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal()
    {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal)
    {
        this.valorMensal = valorMensal;
    }

    //Métodos
    public abstract String ToString();

    public boolean autorizarCondutor()
    {
        Condutor condutor = Condutor.gerarCondutor();
        if (getListaCondutores().contains(condutor))
        {
            System.out.println("\nO condutor já está autorizado.");
            return false;
        }
        getListaCondutores().add(condutor);
        System.out.println("\nCondutor autorizado com sucesso!");
        return true;
    }

    public boolean desautorizarCondutor(Condutor condutor)
    {
        if (getListaCondutores().contains(condutor))
        {
            getListaCondutores().remove(condutor);
            System.out.println("Condutor desautorizado com sucesso!");
            return true;
        }
        System.out.println("O condutor não estava autorizado.");
        return false;
    }

    public boolean gerarSinistro()
    {
        Condutor condutor = EscolherDaLista.escolherCondutor(getListaCondutores());
        System.out.print("\nData da ocorrência (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataString);
        System.out.print("Endereço da ocorrência: ");
        String endereco = scanner.nextLine();
        Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
        System.out.println("\nSinistro gerado com sucesso!");
        getListaSinistros().add(sinistro);
        condutor.adicionarSinistro(sinistro);
        return true;
    }

    public boolean removerSinistro()
    {
        if (listaSinistros.size() == 0)
        {
            System.out.println("\nEste seguro não possui sinistros cadastrados.");
            return false;
        }
        Sinistro sinistro = EscolherDaLista.escolherSinistro(listaSinistros);
        sinistro.getCondutor().removerSinistro(sinistro);
        listaSinistros.remove(sinistro);
        System.out.println("\nSinistro removido com sucesso!");
        return true;
    }
    
    public abstract double calcularValor();
}
