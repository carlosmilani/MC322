import java.util.ArrayList;
import java.util.Scanner;

public class Frota
{
    //Propriedades
    private String code;
    private ArrayList<Veiculo> listaVeiculos;
    private static Scanner scanner = new Scanner(System.in);

    //Construtor
    public Frota(String code, ArrayList<Veiculo> listaVeiculos)
    {
        this.code = code;
        this.listaVeiculos = listaVeiculos;
    }

    //Getters e Setters
    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos()
    {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos)
    {
        this.listaVeiculos = listaVeiculos;
    }

    //Métodos
    public String ToString()
    {
        return "\n---FROTA---\nPrincipal função: %s\nQuantidade de veículos: %d".formatted(code, listaVeiculos.size());
    }

    public boolean adicionarVeiculo()
    {
        System.out.print("\nPlaca do veículo: ");
        String placa = scanner.nextLine();
        System.out.print("Marca do veículo: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo do veículo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ano de fabricação do veículo: ");
        int anoFabricacao = scanner.nextInt();
        scanner.nextLine();
        Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
        if (listaVeiculos.contains(veiculo))
        {
            System.out.println("\nEste veículo já está cadastrado!");
            return false;
        }
        System.out.println("\nVeículo cadastrado com sucesso!");
        listaVeiculos.add(veiculo);
        return true;
    }

    public boolean removerVeiculo()
    {
        Veiculo veiculo = EscolherDaLista.escolherVeiculo(listaVeiculos);
        System.out.println("\nVeículo removido com sucesso!");
        listaVeiculos.remove(veiculo);
        return true;
    }

    public static Frota gerarFrota()
    {
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        System.out.print("\nQual a finalidade desta frota? ");
        String code = scanner.nextLine();
        System.out.print("Quantos veículos existem na frota? ");
        int numVeiculos = scanner.nextInt();
        scanner.nextLine();
        while (numVeiculos <= 0)
        {
            System.out.print("\nOperação inválida! Adicione pelo menos um veículo: ");
            numVeiculos = scanner.nextInt();
            scanner.nextLine();
        }
        for (int i = 0; i < numVeiculos; i++)
        {
            Veiculo veiculo = Veiculo.gerarVeiculo();
            listaVeiculos.add(veiculo);
        }        
        Frota frota = new Frota(code, listaVeiculos);
        return frota;
    }
}
