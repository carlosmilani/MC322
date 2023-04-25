import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception
    {
        // Instanciando uma lista de veículos genérica para os clientes:
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        Veiculo v1 = new Veiculo("ABCD-1234", "Fiat", "Uno", 2020);
        Veiculo v2 = new Veiculo("HUDX-3858", "Renault", "Sandero", 2018);
        listaVeiculos.add(v1);
        listaVeiculos.add(v2);
        
        // Instanciando objetos da classe LocalDate:
        LocalDate dataFundacao = LocalDate.of(1980, 11, 22);
        LocalDate dataAniversario = LocalDate.of(2000, 02, 15);
        LocalDate dataLicenca = LocalDate.of(2022, 10, 30);
        LocalDate dataSinistro = LocalDate.of(2023, 07, 29);

        //Instanciando clientes genéricos:
        ClientePJ c1 = new ClientePJ("Mercado Compre Mais", "Rua Bosque Verde 230, Campinas-SP", listaVeiculos, "60.840.297/0001-25", dataFundacao);
        c1.validarCNPJ(c1.getCnpj());

        ClientePJ c2 = new ClientePJ("Bar do Jose", "Rua Rio Branco 467, Campinas-SP", listaVeiculos, "56.999.619/0001-06", dataFundacao);
        c2.validarCNPJ(c2.getCnpj());

        ClientePF c3 = new ClientePF("Bruce Wayne", "Rua Parque Ecologico 109, Campinas-SP", listaVeiculos, "448.939.196-01", "Superior Completo", "Masculino", "Bilionário", dataAniversario, dataLicenca);
        c3.validarCPF(c3.getCpf());

        //Instanciando a seguradora e chamando os seus métodos:
        Seguradora seg1 = new Seguradora("Seguros 4EVER", "19-123456789", "seguros4ever@gmail.com", "Avenida Santa Isabel 852, Campinas-SP");
        seg1.cadastrarCliente(c1);
        seg1.cadastrarCliente(c2);
        seg1.cadastrarCliente(c3);
        seg1.listarClientes("PJ");
        seg1.gerarSinistro("Rua Castelo Branco 513, Campinas-SP", "ABCD-1234", "60.840.297/0001-25", dataSinistro);
        seg1.gerarSinistro("Avenida Presidente Kennedy 39, Campinas-SP", "HUDX-3858", "448.939.196-01", dataSinistro);
        seg1.visualizarSinistro("448.939.196-01");
        seg1.removerCliente(c2.getCnpj());
        seg1.listarSinistros();
        System.out.println(seg1.ToString());


        // Menu interativo onde é possível chamar os métodos da seguradora.
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running)
        {
            System.out.println("\nO que você gostaria de fazer?\n(1) Cadastrar novo cliente;\n(2) Remover cliente;\n(3) Listar clientes;\n(4) Gerar sinistro;\n(5) Visualizar sinistro;\n(6) Listar sinistros;\n(7) Sair do programa.\n");
            int comando = scanner.nextInt();
            scanner.nextLine();
            if (comando == 1)
            {
                System.out.println("\nQual tipo de cliente você quer cadastrar?\n(1) Pessoa física;\n(2) Pessoa jurídica.\n");
                int tipo = scanner.nextInt();
                scanner.nextLine();
                if (tipo == 1)
                {
                    ClientePF cliente = gerarClientePF(scanner);
                    if (cliente.validarCPF(cliente.getCpf()))
                    {
                        seg1.cadastrarCliente(cliente);
                    }
                }
                else if (tipo == 2)
                {
                    ClientePJ cliente = gerarClientePJ(scanner);
                    if (cliente.validarCNPJ(cliente.getCnpj()))
                    {
                        seg1.cadastrarCliente(cliente);
                    }
                }
            }
            else if (comando == 2)
            {
                System.out.print("\nDigite o CPF ou CNPJ do cliente a ser removido: ");
                String dados = scanner.nextLine();
                seg1.removerCliente(dados);
            }
            else if (comando == 3)
            {
                System.out.println("\nQual tipo de cliente você gostaria de visualizar?\n(PF) Pessoa física;\n(PJ) Pessoa jurídica;\n(x) Ambos os tipos.\n");
                String tipoCliente = scanner.nextLine();
                seg1.listarClientes(tipoCliente);
            }
            else if (comando == 4)
            {
                System.out.print("\nEndereço da ocorrência: ");
                String endereco = scanner.nextLine();
                System.out.print("Placa do veículo: ");
                String placaVeiculo = scanner.nextLine();
                System.out.print("CPF ou CNPJ do cliente: ");
                String dadosCliente = scanner.nextLine();
                System.out.print("Data da ocorrência (AAAA-MM-DD): ");
                String dataString = scanner.nextLine();
                LocalDate data = LocalDate.parse(dataString);
                seg1.gerarSinistro(endereco, placaVeiculo, dadosCliente, data);
            }
            else if (comando == 5)
            {
                System.out.print("\nCPF ou CNPJ do cliente: ");
                String dadosCliente = scanner.nextLine();
                seg1.visualizarSinistro(dadosCliente);
            }
            else if (comando == 6)
            {
                seg1.listarSinistros();
            }
            else
            {
                running = false;
            }
        }
        scanner.close();
    }

    // Método estático que gera um ClientePF por entradas do teclado.
    public static ClientePF gerarClientePF(Scanner scanner)
    {
        System.out.print("\nNome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();
        ArrayList<Veiculo> listaVeiculos = gerarListaVeiculos(scanner);
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
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

    // Método estático que gera um ClientePJ por entradas do teclado.
    public static ClientePJ gerarClientePJ(Scanner scanner)
    {
        System.out.print("\nNome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço do cliente: ");
        String endereco = scanner.nextLine();
        ArrayList<Veiculo> listaVeiculos = gerarListaVeiculos(scanner);
        System.out.print("CNPJ do cliente: ");
        String cnpj = scanner.nextLine();
        System.out.print("Data de fundação do cliente (AAAA-MM-DD): ");
        String dataString = scanner.nextLine();
        LocalDate dataFundacao = LocalDate.parse(dataString);
        ClientePJ cliente = new ClientePJ(nome, endereco, listaVeiculos, cnpj, dataFundacao);
        return cliente;
    }

    // Método estático que gera um ArrayList de veículos por entradas do teclado.
    public static ArrayList<Veiculo> gerarListaVeiculos(Scanner scanner)
    {
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        System.out.print("Número de veículos deste cliente: ");
        int numVeiculos = scanner.nextInt();
        scanner.nextLine();
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
}
