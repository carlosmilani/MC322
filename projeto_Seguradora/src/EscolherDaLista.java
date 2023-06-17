import java.util.ArrayList;
import java.util.Scanner;

public class EscolherDaLista
{
    //Os métodos desta classe permitem escolher um objeto de um determinado tipo dado um ArrayList deste tipo.

    //Propriedade
    private static Scanner scanner = new Scanner(System.in);

    //Métodos
    public static Seguradora escolherSeguradora(ArrayList<Seguradora> listaSeguradoras)
    {
        System.out.println("\nSeguradoras Disponíveis");
        for (Seguradora seguradora : listaSeguradoras)
        {
            System.out.println(listaSeguradoras.indexOf(seguradora) + " - " + seguradora.getNome());
        }
        System.out.print("Digite sobre qual seguradora esta operação será realizada: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 0 || index >= listaSeguradoras.size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        return listaSeguradoras.get(index);
    }

    public static Cliente escolherCliente(ArrayList<Cliente> listaClientes)
    {
        System.out.println("\nClientes Disponíveis");
        for (Cliente cliente : listaClientes)
        {
            System.out.println(listaClientes.indexOf(cliente) + " - " + cliente.getNome());
        }
        System.out.print("Digite sobre qual cliente esta operação será realizada: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 0 || index >= listaClientes.size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        return listaClientes.get(index);
    }

    public static Frota escolherFrota(ArrayList<Frota> listaFrota)
    {
        System.out.println("\nFrotas Disponíveis");
        for (Frota frota : listaFrota)
        {
            System.out.println(listaFrota.indexOf(frota) + " - " + frota.getCode());
        }
        System.out.print("Digite sobre qual frota esta operação será realizada: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 0 || index >= listaFrota.size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        return listaFrota.get(index);
    }

    public static Veiculo escolherVeiculo(ArrayList<Veiculo> listaVeiculos)
    {
        System.out.println("\nVeículos Disponíveis");
        for (Veiculo veiculo : listaVeiculos)
        {
            System.out.println(listaVeiculos.indexOf(veiculo) + " - " + veiculo.getModelo());
        }
        System.out.print("Digite sobre qual veículo esta operação será realizada: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 0 || index >= listaVeiculos.size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        return listaVeiculos.get(index);
    }

    public static Condutor escolherCondutor(ArrayList<Condutor> listaCondutores)
    {
        System.out.println("\nCondutores Disponíveis");
        for (Condutor condutor : listaCondutores)
        {
            System.out.println(listaCondutores.indexOf(condutor) + " - " + condutor.getNome());
        }
        System.out.print("Digite sobre qual condutor esta operação será realizada: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 0 || index >= listaCondutores.size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        return listaCondutores.get(index);
    }

    public static Seguro escolherSeguro(ArrayList<Seguro> listaSeguros)
    {
        System.out.println("\nSeguros Disponíveis");
        for (Seguro seguro : listaSeguros)
        {
            if (seguro instanceof SeguroPF)
            {
                System.out.println(listaSeguros.indexOf(seguro) + " - " + ((SeguroPF)seguro).getCliente().getNome() + " - " + ((SeguroPF)seguro).getVeiculo().getModelo());
            }
            else
            {
                System.out.println(listaSeguros.indexOf(seguro) + " - " + ((SeguroPJ)seguro).getCliente().getNome() + " - " + ((SeguroPJ)seguro).getFrota().getCode());
            }
        }
        System.out.print("Digite sobre qual seguradora esta operação será realizada: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 0 || index >= listaSeguros.size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        return listaSeguros.get(index);
    }

    public static Sinistro escolherSinistro(ArrayList<Sinistro> listaSinistros)
    {
        System.out.println("\nSinistros Disponíveis");
        for (Sinistro sinistro : listaSinistros)
        {
            System.out.println(listaSinistros.indexOf(sinistro) + " - " + sinistro.getEndereco() + " - " + sinistro.getCondutor().getNome());
        }
        System.out.print("Escolha o sinistro a ser removido: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 0 || index >= listaSinistros.size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        return listaSinistros.get(index);
    }
}
