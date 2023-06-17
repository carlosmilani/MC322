import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception
    {
        // Instanciando listas de veículos e frotas genéricas para os clientes:
        Veiculo veiculo1 = new Veiculo("ABCD-1234", "Fiat", "Uno", 2020);
        Veiculo veiculo2 = new Veiculo("HUDX-3858", "Renault", "Sandero", 2018);
        Veiculo veiculo3 = new Veiculo("B4T-M4N", "BMW", "BatMovel", 2015);
        Veiculo veiculo4 = new Veiculo("LMAO-3592", "Ford", "Focus", 2017);
        ArrayList<Veiculo> listaVeiculos1 = new ArrayList<Veiculo>();
        ArrayList<Veiculo> listaVeiculos2 = new ArrayList<Veiculo>();
        listaVeiculos1.add(veiculo1);
        listaVeiculos1.add(veiculo2);
        listaVeiculos2.add(veiculo4);
        ArrayList<Frota> listaFrotas = new ArrayList<Frota>();
        Frota frota1 = new Frota("Carros para entrega", listaVeiculos1);
        Frota frota2 = new Frota("Carros para transporte de funcionários", listaVeiculos2);
        listaFrotas.add(frota1);
        listaFrotas.add(frota2);
        System.out.println(veiculo1.ToString());
        System.out.println(frota1.ToString());
        
        // Instanciando objetos da classe LocalDate:
        LocalDate dataFundacao = LocalDate.of(1980, 11, 22);
        LocalDate dataNascimento = LocalDate.of(2000, 02, 15);
        LocalDate dataSinistro = LocalDate.of(2023, 07, 29);
        LocalDate dataInicio = LocalDate.of(1998, 9, 03);
        LocalDate dataFim = LocalDate.of(2025, 11, 24);

        //Instanciando clientes genéricos:
        ClientePJ cliente1 = new ClientePJ("Mercado Compre Mais", "19 1234-5678", "Rua Bosque Verde 230, Campinas-SP", "mercadinhobaratinho@gmail.com", "60840297000125", dataFundacao, 15);
        ClientePJ cliente2 = new ClientePJ("Bar do Jose", "19 98822-3546", "Rua Rio Branco 467, Campinas-SP", "barcombebida.gmail.com", "56999619000106", dataFundacao, 4);
        ClientePF cliente3 = new ClientePF("Bruce Wayne", "19 00000-0000", "Rua Parque Ecologico 109, Campinas-SP", "cavaleirodastrevas.gmail.com", "44893919601", "Superior Completo", "Masculino", "Bilionário", dataNascimento);
        cliente1.getListaFrota().addAll(listaFrotas);
        cliente3.getListaVeiculos().add(veiculo3);

        //Instanciando a seguradora e cadastrando alguns clientes nela:
        Seguradora seguradora1 = new Seguradora("42.034.477/0001-38", "Seguros Forever", "19-123456789", "Avenida Santa Isabel 852, Campinas-SP", "seguros4ever@gmail.com");
        if (Validacao.validarCNPJ(cliente1.getCnpj()) && Validacao.validarNome(cliente1.getNome()))
        {
            seguradora1.cadastrarCliente(cliente1);
        }
        if (Validacao.validarCNPJ(cliente2.getCnpj()) && Validacao.validarNome(cliente2.getNome()))
        {
            seguradora1.cadastrarCliente(cliente2);
        }
        if (Validacao.validarCPF(cliente3.getCpf()) && Validacao.validarNome(cliente3.getNome()))
        {
            seguradora1.cadastrarCliente(cliente3);
        }

        //Instancionando seguros:
        SeguroPF seguro1 = new SeguroPF(dataInicio, dataFim, seguradora1, veiculo3, cliente3);
        SeguroPJ seguro2 = new SeguroPJ(dataInicio, dataFim, seguradora1, frota1, cliente1);
        SeguroPJ seguro3 = new SeguroPJ(dataInicio, dataFim, seguradora1, frota2, cliente2);
        System.out.println(seguro1.ToString());

        //Instanciando condutores:
        Condutor condutor1 = new Condutor("776.036.160-26", "Joãozinho", "19-1029-4857", "Rua Aquarela 210, Campinas - SP", "joãozinholeklek@gmail.com", dataNascimento);
        Condutor condutor2 = new Condutor("965.496.940-86", "Mariazinha", "19-94351-5573", "Rua Aquarela 210, Campinas - SP", "mariazinhatektek@gmail.com", dataNascimento);
        Condutor condutor3 = new Condutor("928.573.460-00", "Yudi", "19-4002-8922", "Avenida Brás Cunha 79, Campinas - SP", "yudiplaystation@hotmail.com", dataNascimento);
        System.out.println(condutor1.ToString());

        //Instanciando sinistros:
        Sinistro sinistro1 = new Sinistro(dataSinistro, "Rua Marechal Deodoro 15, Campinas - SP", condutor1, seguro1);
        Sinistro sinistro2 = new Sinistro(dataSinistro, "Rodovia dos Bandeirantes, São Paulo - SP", condutor2, seguro1);
        Sinistro sinistro3 = new Sinistro(dataSinistro, "Estacionamento Shopping Dom Pedro, Campinas - SP", condutor3, seguro2);
        System.out.println(sinistro2.ToString());
        seguro1.getListaCondutores().add(condutor1);
        seguro1.getListaCondutores().add(condutor2);
        seguro1.getListaSinistros().add(sinistro1);
        seguro1.getListaSinistros().add(sinistro2);
        seguro2.getListaCondutores().add(condutor3);
        seguro2.getListaSinistros().add(sinistro3);

        //Chamando alguns métodos da seguradora:
        seguradora1.getListaSeguros().add(seguro1);
        seguradora1.getListaSeguros().add(seguro2);
        seguradora1.getListaSeguros().add(seguro3);
        seguradora1.listarClientes();
        System.out.println(seguradora1.ToString());
        seguradora1.removerCliente(cliente2);

        //Instancionando uma lista de seguradoras para ser usada no menu:
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        listaSeguradoras.add(seguradora1);

        //Laço que mantém o menu principal aberto até que o usuário saia do programa.
        MenuOperacoes operacao;
		do
        {
			exibirMenuPrincipal();
			operacao = lerOperacaoMenuPrincipal();
			executarOperacaoMenuPrincipal(listaSeguradoras, operacao);
		}
        while(operacao != MenuOperacoes.SAIR);
    }

    //Método estático que imprime o menu principal na tela.
    private static void exibirMenuPrincipal()
    {
        MenuOperacoes menuOperacoes[] = MenuOperacoes.values();
        System.out.println("\nMenu Principal");
        for (MenuOperacoes operacao : menuOperacoes)
        {
            System.out.println(operacao.ordinal() + " - " + operacao.getDescricao());
        }
    }

    //Método estático que imprime o sub menu na tela.
    private static void exibirSubMenu(MenuOperacoes operacao)
    {
        SubMenuOperacoes subMenu[] = operacao.getSubMenuOperacoes();
        System.out.println("\n" + operacao.getDescricao());
        for (int i = 0; i < subMenu.length; i++)
        {
            System.out.println(i + " - " + subMenu[i].getDescricao());
        }
    }

    //Método que lê as operações do menu principal por entrada do teclado.
    private static MenuOperacoes lerOperacaoMenuPrincipal()
    {
        System.out.print("Digite uma operação: ");
        int comando = scanner.nextInt();
        scanner.nextLine();
        while (comando < 0 || comando >= MenuOperacoes.values().length)
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            comando = scanner.nextInt();
            scanner.nextLine();
        }
        MenuOperacoes operacao = MenuOperacoes.values()[comando];
        return operacao;
    }

    //Método que lê as operações do sub menu por entrada do teclado.
    private static SubMenuOperacoes lerOperacaoSubMenu(MenuOperacoes operacao, Scanner scanner)
    {
        System.out.print("Digite uma operação: ");
        int comando = scanner.nextInt();
        scanner.nextLine();
        while (comando < 0 || comando >= operacao.getSubMenuOperacoes().length)
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            comando = scanner.nextInt();
            scanner.nextLine();
        }
        SubMenuOperacoes subOperacao = operacao.getSubMenuOperacoes()[comando];
        return subOperacao;
    }

    //Método que executa as operações do menu principal.
    private static void executarOperacaoMenuPrincipal(ArrayList<Seguradora> listaSeguradoras, MenuOperacoes operacao)
    {
        Seguradora seguradora;
        Cliente cliente;
        switch(operacao)
        {
            case CADASTRAR:
            case LISTAR:
            case REMOVER:
                executarSubMenu(listaSeguradoras, operacao);
                break;
            case GERAR_SEGURO:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaClientes().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui nenhum cliente cadastrado.");
                    break;
                }
                cliente = EscolherDaLista.escolherCliente(seguradora.getListaClientes());
                if (cliente instanceof ClientePF)
                {
                    ClientePF clientePF = (ClientePF)cliente;
                    if (clientePF.getListaVeiculos().size() == 0)
                    {
                        System.out.println("\nEste cliente não possui nenhum veículo cadastrado.");
                        break;
                    }
                    seguradora.gerarSeguro(clientePF);
                }
                else
                {
                    ClientePJ clientePJ = (ClientePJ)cliente;
                    if (clientePJ.getListaFrota().size() == 0)
                    {
                        System.out.println("\nEste cliente não possui nenhuma frota cadastrada.");
                        break;
                    }
                    seguradora.gerarSeguro(clientePJ);
                }
                break;
            case GERAR_SINISTRO:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaSeguros().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui nenhum seguro cadastrado.");
                    break;
                }
                Seguro seguro = EscolherDaLista.escolherSeguro(seguradora.getListaSeguros());
                if (seguro.getListaCondutores().size() == 0)
                {
                    System.out.println("\nEste seguro não possui nenhum condutor cadastrado.");
                    break;
                }
                seguro.gerarSinistro();
                break;
            case ATUALIZAR_FROTA:
                ClientePJ clientePJ;
                ArrayList<Cliente> listaClientesPJ;
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                listaClientesPJ = seguradora.getClientesPJ();
                if (listaClientesPJ.size() == 0)
                {
                System.out.println("\nNão é possível atualizar uma frota pois esta seguradora não possui nenhum clientePJ.");
                    break;
                }
                clientePJ = (ClientePJ)EscolherDaLista.escolherCliente(listaClientesPJ);
                if (clientePJ.getListaFrota().size() == 0)
                {
                    System.out.println("\nEste cliente não possui uma frota para ser atualizada.");
                    break;
                }
                Frota frota = EscolherDaLista.escolherFrota(clientePJ.getListaFrota());
                clientePJ.atualizarFrota(frota, seguradora);
                break;    
            case CALCULAR_RECEITA_SEGURADORA:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                System.out.print("\nReceita da Seguradora " + seguradora.getNome() + ": ");
                System.out.println(seguradora.calcularReceita());
                break;
            case SAIR:
                System.out.println("\nSaiu do sistema");
                break;
        }
    }

    //Método que executa as operações do sub menu.
    private static void executarOperacaoSubMenu(ArrayList<Seguradora> listaSeguradoras, SubMenuOperacoes subOperacao)
    {
        Seguradora seguradora;
        Cliente cliente;
        ClientePF clientePF;
        ClientePJ clientePJ;
        ArrayList<Cliente> listaClientesPJ;
        Seguro seguro;
        switch(subOperacao)
        {
            case CADASTRAR_CLIENTE_PF:
                clientePF = ClientePF.gerarClientePF();
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.cadastrarCliente(clientePF);
                break;
            case CADASTRAR_CLIENTE_PJ:
                clientePJ = ClientePJ.gerarClientePJ();
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.cadastrarCliente(clientePJ);
                break;
            case CADASTRAR_FROTA:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                listaClientesPJ = seguradora.getClientesPJ();
                if (listaClientesPJ.size() == 0)
                {
                    System.out.println("\nNão é possível atualizar uma nova frota pois esta seguradora não possui nenhum clientePJ.");
                    break;
                }
                clientePJ = (ClientePJ)EscolherDaLista.escolherCliente(listaClientesPJ);
                clientePJ.cadastrarFrota();
                break;
            case CADASTRAR_VEICULO:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaClientes().size() == 0)
                {
                    System.out.println("\nNão é possível cadastrar um novo veículo pois esta seguradora não possui nenhum cliente.");
                    break;
                }
                cliente = EscolherDaLista.escolherCliente(seguradora.getListaClientes());
                cliente.cadastrarVeiculo();
                break;
            case CADASTRAR_SEGURADORA:
                seguradora = Seguradora.gerarSeguradora();
                listaSeguradoras.add(seguradora);
                System.out.println("\nSeguradora gerada com sucesso!");
                break;
            case CADASTRAR_CONDUTOR:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaSeguros().size() == 0)
                {
                    System.out.println("\nNão é possível cadastrar um novo condutor pois esta seguradora não possui nenhum seguro.");
                    break;
                }
                seguro = EscolherDaLista.escolherSeguro(seguradora.getListaSeguros());
                seguro.autorizarCondutor();
                break;
            case LISTAR_CLIENTES_PF:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.listarClientesPF();
                break;
            case LISTAR_CLIENTES_PJ:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.listarClientesPJ();
                break;
            case LISTAR_TODOS_CLIENTES:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.listarClientes();
                break;
            case LISTAR_SEGUROS_SEGURADORA:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.listarSeguros();
                break;
            case LISTAR_SEGUROS_CLIENTE:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaClientes().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui clientes.");
                    break;
                }
                cliente = EscolherDaLista.escolherCliente(seguradora.getListaClientes());
                Cliente.listarSegurosCliente(seguradora.getSegurosPorCliente(cliente));
                break;
            case LISTAR_SINISTROS_SEGURADORA:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.listarSinistros();
                break;
            case LISTAR_SINISTROS_CLIENTE:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaClientes().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui clientes.");
                    break;
                }
                cliente = EscolherDaLista.escolherCliente(seguradora.getListaClientes());
                Cliente.listarSinistrosCliente(seguradora.getSinistrosPorCliente(cliente));
                break;
            case LISTAR_VEICULOS_SEGURADORA:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                seguradora.listarVeiculos();
                break;
            case LISTAR_VEICULOS_CLIENTE:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaClientes().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui clientes.");
                    break;
                }
                cliente = EscolherDaLista.escolherCliente(seguradora.getListaClientes());
                cliente.listarVeiculos();
                break;
            case REMOVER_CLIENTE:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaClientes().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui nenhum cliente para ser removido.");
                    break;
                }
                cliente = EscolherDaLista.escolherCliente(seguradora.getListaClientes());
                seguradora.removerCliente(cliente);
                break;
            case REMOVER_CONDUTOR:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaSeguros().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui nenhum condutor para ser removido.");
                    break;
                }
                seguro = EscolherDaLista.escolherSeguro(seguradora.getListaSeguros());
                if (seguro.getListaCondutores().size() == 0)
                {
                    System.out.println("\nEste seguro não possui nenhum condutor cadastrado.");
                    break;
                }
                Condutor condutor = EscolherDaLista.escolherCondutor(seguro.getListaCondutores());
                seguro.desautorizarCondutor(condutor);
                break;
            case REMOVER_VEICULO:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaClientes().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui nenhum veículo para ser removido.");
                    break;
                }
                cliente = EscolherDaLista.escolherCliente(seguradora.getListaClientes());
                cliente.removerVeiculo(seguradora);
                break;
            case REMOVER_SEGURO:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaSeguros().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui nenhum seguro para ser removido.");
                    break;
                }
                seguro = EscolherDaLista.escolherSeguro(seguradora.getListaSeguros());
                seguradora.cancelarSeguro(seguro);
                break;
            case REMOVER_SINISTRO:
                seguradora = EscolherDaLista.escolherSeguradora(listaSeguradoras);
                if (seguradora.getListaSeguros().size() == 0)
                {
                    System.out.println("\nEsta seguradora não possui nenhum sinistro para ser removido.");
                    break;
                }
                seguro = EscolherDaLista.escolherSeguro(seguradora.getListaSeguros());
                seguro.removerSinistro();
                break;
            case VOLTAR:
                break;
        }
    }

    //Método que abre o sub menu.
    private static void executarSubMenu(ArrayList<Seguradora> listaSeguradoras, MenuOperacoes operacao)
    {
        SubMenuOperacoes subOperacao;
        do
        {
            exibirSubMenu(operacao);
            subOperacao = lerOperacaoSubMenu(operacao, scanner);
            executarOperacaoSubMenu(listaSeguradoras, subOperacao);
        }
        while (subOperacao != SubMenuOperacoes.VOLTAR);
    }
}
