import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // Instanciando listas de veículos genéricas para os clientes:
        Veiculo v1 = new Veiculo("ABCD-1234", "Fiat", "Uno", 2020);
        Veiculo v2 = new Veiculo("HUDX-3858", "Renault", "Sandero", 2018);
        Veiculo v3 = new Veiculo("B4T-M4N", "BMW", "BatMovel", 2015);
        ArrayList<Veiculo> listaVeiculos1 = new ArrayList<Veiculo>();
        listaVeiculos1.add(v1);
        ArrayList<Veiculo> listaVeiculos2 = new ArrayList<Veiculo>();
        listaVeiculos2.add(v2);
        ArrayList<Veiculo> listaVeiculos3 = new ArrayList<Veiculo>();
        listaVeiculos3.add(v3);
        
        // Instanciando objetos da classe LocalDate:
        LocalDate dataFundacao = LocalDate.of(1980, 11, 22);
        LocalDate dataAniversario = LocalDate.of(2000, 02, 15);
        LocalDate dataLicenca = LocalDate.of(2022, 10, 30);
        LocalDate dataSinistro = LocalDate.of(2023, 07, 29);

        //Instanciando clientes genéricos:
        ClientePJ c1 = new ClientePJ("Mercado Compre Mais", "Rua Bosque Verde 230, Campinas-SP", listaVeiculos1, "60840297000125", dataFundacao, 15);
        ClientePJ c2 = new ClientePJ("Bar do Jose", "Rua Rio Branco 467, Campinas-SP", listaVeiculos2, "56999619000106", dataFundacao, 4);
        ClientePF c3 = new ClientePF("Bruce Wayne", "Rua Parque Ecologico 109, Campinas-SP", listaVeiculos3, "44893919601", "Superior Completo", "Masculino", "Bilionário", dataAniversario, dataLicenca);

        //Instanciando a seguradora e chamando os seus métodos:
        Seguradora seg1 = new Seguradora("Seguros Forever", "19-123456789", "seguros4ever@gmail.com", "Avenida Santa Isabel 852, Campinas-SP");
        if (Validacao.validarCNPJ(c1.getCnpj()) && Validacao.validarNome(c1.getNome()))
        {
            seg1.cadastrarCliente(c1);
        }
        if (Validacao.validarCNPJ(c2.getCnpj()) && Validacao.validarNome(c2.getNome()))
        {
            seg1.cadastrarCliente(c2);
        }
        if (Validacao.validarCPF(c3.getCpf()) && Validacao.validarNome(c3.getNome()))
        {
            seg1.cadastrarCliente(c3);
        }
        seg1.listarClientes("PJ");
        seg1.gerarSinistro("Rua Castelo Branco 513, Campinas-SP", "ABCD-1234", "60840297000125", dataSinistro);
        seg1.gerarSinistro("Avenida Presidente Kennedy 39, Campinas-SP", "B4T-M4N", "44893919601", dataSinistro);
        seg1.visualizarSinistro("44893919601");
        seg1.removerCliente(c2.getCnpj());
        seg1.listarSinistros();
        System.out.print("\nReceita da Seguradora Seguros Forever: ");
        System.out.println(seg1.calcularReceita());
        System.out.println(seg1.ToString());

        //Instancionando uma lista de seguradoras e uma lista de clientes para serem usadas no menu:
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        listaSeguradoras.add(seg1);
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        listaClientes.add(c1);
        listaClientes.add(c3);

        Scanner scanner = new Scanner(System.in);
        MenuOperacoes operacao;
		do
        {
			exibirMenuPrincipal();
			operacao = lerOperacaoMenuPrincipal(scanner);
			executarOperacaoMenuPrincipal(listaSeguradoras, listaClientes, operacao, scanner);
		}
        while(operacao != MenuOperacoes.SAIR);
    }

    private static void exibirMenuPrincipal()
    {
        MenuOperacoes menuOperacoes[] = MenuOperacoes.values();
        System.out.println("\nMenu Principal");
        for (MenuOperacoes operacao : menuOperacoes)
        {
            System.out.println(operacao.ordinal() + " - " + operacao.getDescricao());
        }
    }

    private static void exibirSubMenu(MenuOperacoes operacao)
    {
        SubMenuOperacoes subMenu[] = operacao.getSubMenuOperacoes();
        System.out.println("\n" + operacao.getDescricao());
        for (int i = 0; i < subMenu.length; i++)
        {
            System.out.println(i + " - " + subMenu[i].getDescricao());
        }
    }

    private static MenuOperacoes lerOperacaoMenuPrincipal(Scanner scanner)
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

    private static void executarOperacaoMenuPrincipal(ArrayList<Seguradora> listaSeguradoras, ArrayList<Cliente> listaClientes, MenuOperacoes operacao, Scanner scanner)
    {
        Seguradora seguradora;
        switch(operacao)
        {
            case CADASTRAR:
            case LISTAR:
            case REMOVER:
                executarSubMenu(listaSeguradoras, listaClientes, operacao, scanner);
                break;
            case GERAR_SINISTRO:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                gerarSinistro(seguradora, scanner);
                break;
            case TRANSFERIR_SEGURO:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                transferirSeguro(seguradora, scanner);
                break;
            case CALCULAR_RECEITA_SEGURADORA:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                System.out.print("\nReceita da Seguradora " + seguradora.getNome() + ": ");
                System.out.println(seguradora.calcularReceita());
                break;
            case SAIR:
                System.out.println("\nSaiu do sistema");
                break;
        }
    }

    private static void executarOperacaoSubMenu(ArrayList<Seguradora> listaSeguradoras, ArrayList<Cliente> listaClientes, SubMenuOperacoes subOperacao, Scanner scanner)
    {
        Seguradora seguradora;
        Cliente cliente;
        String dados;
        switch(subOperacao)
        {
            case CADASTRAR_CLIENTE_PF:
                ClientePF clientePF = gerarClientePF(scanner);
                if (Validacao.validarCPF(clientePF.getCpf()) && Validacao.validarNome(clientePF.getNome()))
                {
                    seguradora = escolherSeguradora(listaSeguradoras, scanner);
                    seguradora.cadastrarCliente(clientePF);
                    listaClientes.add(clientePF);
                }
                break;
            case CADASTRAR_CLIENTE_PJ:
                ClientePJ clientePJ = gerarClientePJ(scanner);
                if (Validacao.validarCNPJ(clientePJ.getCnpj()) && Validacao.validarNome(clientePJ.getNome()))
                {
                    seguradora = escolherSeguradora(listaSeguradoras, scanner);
                    seguradora.cadastrarCliente(clientePJ);
                    listaClientes.add(clientePJ);
                }
                break;
            case CADASTRAR_VEICULO:
                ArrayList<Veiculo> listaVeiculo = gerarListaVeiculos(scanner);
                cliente = escolherCliente(listaClientes, scanner);
                cliente.getListaVeiculos().addAll(listaVeiculo);
                break;
            case CADASTRAR_SEGURADORA:
                seguradora = gerarSeguradora(scanner);
                listaSeguradoras.add(seguradora);
                break;
            case LISTAR_CLIENTES_PF:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                seguradora.listarClientes("PF");
                break;
            case LISTAR_CLIENTES_PJ:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                seguradora.listarClientes("PJ");
                break;
            case LISTAR_TODOS_CLIENTES:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                seguradora.listarClientes("x");
                break;
            case LISTAR_SINISTROS_SEGURADORA:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                seguradora.listarSinistros();
                break;
            case LISTAR_SINISTROS_CLIENTE:
                cliente = escolherCliente(listaClientes, scanner);
                for (Seguradora seg : listaSeguradoras)
                {
                    if (cliente instanceof ClientePF)
                    {
                        seg.visualizarSinistro(((ClientePF)cliente).getCpf());
                    }
                    else if (cliente instanceof ClientePJ)
                    {
                        seg.visualizarSinistro(((ClientePJ)cliente).getCnpj());
                    }
                }
                break;
            case LISTAR_VEICULOS_SEGURADORA:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                for (Cliente clientes : seguradora.getListaClientes())
                {
                    listarVeiculos(clientes);
                }
                break;
            case LISTAR_VEICULOS_CLIENTE:
                cliente = escolherCliente(listaClientes, scanner);
                listarVeiculos(cliente);
                break;
            case REMOVER_CLIENTE:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                System.out.print("\nDigite o CPF ou CNPJ do cliente a ser removido: ");
                dados = scanner.nextLine().replaceAll("[^0-9]", "");
                break;
            case REMOVER_SINISTRO:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                System.out.print("\nDigite o CPF ou CNPJ do cliente cujos sinistros serão removidos: ");
                dados = scanner.nextLine().replaceAll("[^0-9]", "");
                seguradora.removerSinistro(dados);
                break;
            case REMOVER_VEICULO:
                seguradora = escolherSeguradora(listaSeguradoras, scanner);
                cliente = escolherCliente(listaClientes, scanner);
                System.out.print("\nDigite a placa do veículo a ser removido: ");
                String placa = scanner.nextLine();
                for (Veiculo veiculo : cliente.getListaVeiculos())
                {
                    if (veiculo.getPlaca().equals(placa))
                    {
                        System.out.println("Veículo de placa " + placa + " removido com sucesso!");
                        cliente.getListaVeiculos().remove(veiculo);
                    }
                }
                break;
            case VOLTAR:
                break;
        }
    }

    private static void executarSubMenu(ArrayList<Seguradora> listaSeguradoras, ArrayList<Cliente> listaClientes, MenuOperacoes operacao, Scanner scanner)
    {
        SubMenuOperacoes subOperacao;
        do
        {
            exibirSubMenu(operacao);
            subOperacao = lerOperacaoSubMenu(operacao, scanner);
            executarOperacaoSubMenu(listaSeguradoras, listaClientes, subOperacao, scanner);
        }
        while (subOperacao != SubMenuOperacoes.VOLTAR);
    }

    //Método estático que gera um ClientePF por entradas do teclado.
    private static ClientePF gerarClientePF(Scanner scanner)
    {
        System.out.print("\nNome do cliente: ");
        String nome = scanner.nextLine().replaceAll("\\d", "");;
        while (Validacao.validarNome(nome) == false)
        {
            System.out.print("\nNome inválido! Digite novamente: ");
            nome = scanner.nextLine().replaceAll("\\d", "");
        }
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();
        ArrayList<Veiculo> listaVeiculos = gerarListaVeiculos(scanner);
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine().replaceAll("[^0-9]", "");
        while (Validacao.validarCPF(cpf) == false)
        {
            System.out.print("\nCPF inválido! Digite novamente: ");
            cpf = scanner.nextLine().replaceAll("[^0-9]", "");
        }
        System.out.print("Escolaridade do cliente: ");
        String educacao = scanner.nextLine();
        System.out.print("Gênero do cliente: ");
        String genero = scanner.nextLine();
        System.out.print("Classe econômica do cliente: ");
        String classeEconomica = scanner.nextLine();
        System.out.print("Data de nascimento do cliente (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataString);
        System.out.print("Data da licença do cliente (AAAA-MM-DD): ");
        dataString = scanner.nextLine();
        LocalDate dataLicenca = LocalDate.parse(dataString);
        ClientePF cliente = new ClientePF(nome, endereco, listaVeiculos, cpf, educacao, genero, classeEconomica, dataNascimento, dataLicenca);
        return cliente;
    }

    //Método estático que gera um ClientePJ por entradas do teclado.
    private static ClientePJ gerarClientePJ(Scanner scanner)
    {
        System.out.print("\nNome do cliente: ");
        String nome = scanner.nextLine();
        while (Validacao.validarNome(nome) == false)
        {
            System.out.print("\nNome inválido! Digite novamente: ");
            nome = scanner.nextLine().replaceAll("\\d", "");
        }
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();
        ArrayList<Veiculo> listaVeiculos = gerarListaVeiculos(scanner);
        System.out.print("CNPJ do cliente: ");
        String cnpj = scanner.nextLine();
        while (Validacao.validarCNPJ(cnpj) == false)
        {
            System.out.print("\nCNPJ inválido! Digite novamente: ");
            cnpj = scanner.nextLine().replaceAll("[^0-9]", "");
        }
        System.out.print("Data de fundação do cliente (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate dataFundacao = LocalDate.parse(dataString);
        System.out.print("Quantidade de funcionários do cliente: ");
        int qtdeFuncionarios = scanner.nextInt();
        scanner.nextLine();
        ClientePJ cliente = new ClientePJ(nome, endereco, listaVeiculos, cnpj, dataFundacao, qtdeFuncionarios);
        return cliente;
    }

    //Método estático que gera um ArrayList de veículos por entradas do teclado.
    private static ArrayList<Veiculo> gerarListaVeiculos(Scanner scanner)
    {
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        System.out.print("Número de veículos deste cliente: ");
        int numVeiculos = scanner.nextInt();
        scanner.nextLine();
        while (numVeiculos <= 0)
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            numVeiculos = scanner.nextInt();
            scanner.nextLine();
        }
        for (int i = 0; i < numVeiculos; i++)
        {
            System.out.print("Placa do veículo: ");
            String placa = scanner.nextLine();
            System.out.print("Marca do veículo: ");
            String marca = scanner.nextLine();
            System.out.print("Modelo do veículo: ");
            String modelo = scanner.nextLine();
            System.out.print("Ano de fabricação do veículo: ");
            int anoFabricacao = scanner.nextInt();
            scanner.nextLine();
            Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
            listaVeiculos.add(veiculo);
            System.out.println(veiculo.ToString());
        }
        return listaVeiculos;
    }

    private static boolean gerarSinistro(Seguradora seguradora, Scanner scanner)
    {
        System.out.print("\nEndereço da ocorrência: ");
        String endereco = scanner.nextLine();
        System.out.print("Placa do veículo: ");
        String placaVeiculo = scanner.nextLine();
        System.out.print("CPF ou CNPJ do cliente: ");
        String dadosCliente = scanner.nextLine().replaceAll("[^0-9]", "");
        System.out.print("Data da ocorrência (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataString);
        return seguradora.gerarSinistro(endereco, placaVeiculo, dadosCliente, data);
    }

    //Método estático que gera uma seguradora por entradas do teclado.
    private static Seguradora gerarSeguradora(Scanner scanner)
    {
        System.out.print("\nNome da seguradora: ");
        String nome = scanner.nextLine();
        while (Validacao.validarNome(nome) == false)
        {
            System.out.print("\nNome invalido! Digite novamente: ");
            nome = scanner.nextLine();
        }
        System.out.print("Telefone da seguradora: ");
        String telefone = scanner.nextLine();
        System.out.print("Email da seguradora: ");
        String email = scanner.nextLine();
        System.out.print("Endereço da seguradora: ");
        String endereco = scanner.nextLine();
        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
        return seguradora;
    }

    //Método estático que transfere um seguro entre dois clientes de uma mesma seguradora.
    private static boolean transferirSeguro(Seguradora seguradora, Scanner scanner)
    {
        if (seguradora.getListaClientes().size() <= 1)
        {
            System.out.println("\nNão foi possível transferir o seguro.");
            return false;
        }
        System.out.println("\nClientes Disponíveis");
        for (Cliente cliente : seguradora.getListaClientes())
        {
            System.out.println(seguradora.getListaClientes().indexOf(cliente) + " - " + cliente.getNome());
        }
        System.out.print("Digite qual cliente transferirá seu seguro: ");
        int index1 = scanner.nextInt();
        scanner.nextLine();
        while (index1 < 0 || index1 >= seguradora.getListaClientes().size())
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index1 = scanner.nextInt();
            scanner.nextLine();
        }
        Cliente c1 = seguradora.getListaClientes().get(index1);
        System.out.print("Digite qual cliente receberá o seguro transferido: ");
        int index2 = scanner.nextInt();
        scanner.nextLine();
        while (index2 < 0 || index2 >= seguradora.getListaClientes().size() || index1 == index2)
        {
            System.out.print("\nOperação inválida! Digite novamente: ");
            index2 = scanner.nextInt();
            scanner.nextLine();
        }
        Cliente c2 = seguradora.getListaClientes().get(index2);
        ArrayList<Veiculo> listaVeiculos = c1.getListaVeiculos();
        // Verificar se isso funciona
        
        c2.getListaVeiculos().addAll(listaVeiculos);
        
        //
        if (c1 instanceof ClientePF)
        {
            seguradora.removerCliente(((ClientePF)c1).getCpf());
        }
        else if (c1 instanceof ClientePJ)
        {
            seguradora.removerCliente(((ClientePJ)c1).getCnpj());
        }
        seguradora.calcularPrecoSeguroCliente(c2);
        System.out.println("\nSeguro transferido com sucesso!");
        return true;
    }

    //Método estático que permite escolher uma seguradora da lista de seguradoras.
    private static Seguradora escolherSeguradora(ArrayList<Seguradora> listaSeguradoras, Scanner scanner)
    {
        System.out.println("\nSeguradoras Disponíveis");
        for (Seguradora seguradora : listaSeguradoras)
        {
            System.out.println(listaSeguradoras.indexOf(seguradora) + " - " + seguradora.getNome());
        }
        System.out.print("Digite qual seguradora realizará esta operação: ");
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

    //Método estático que permite escolher um cliente da lista de clientes.
    private static Cliente escolherCliente(ArrayList<Cliente> listaClientes, Scanner scanner)
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

    private static void listarVeiculos(Cliente cliente)
    {
        for (Veiculo veiculos : cliente.getListaVeiculos())
        {
            System.out.println(veiculos.ToString());
        }
    }
}
