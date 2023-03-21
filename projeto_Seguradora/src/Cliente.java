public class Cliente {

    //Propriedades
    private String nome;
    private String cpf;
    private boolean cpf_valido;
    private String dataNascimento;
    private int idade;
    private String endereco;

    //Construtor
    public Cliente(String _Nome, String _Cpf, String _DataNascimento, int _Idade, String _Endereco)
    {
        nome = _Nome;
        dataNascimento = _DataNascimento;
        idade = _Idade;
        endereco = _Endereco;
        cpf_valido = validarCPF(_Cpf);
        if (cpf_valido)
        {
            cpf = _Cpf.replaceAll("[^0-9]", "");
        }
        else
        {
            cpf = "CPF inválido";
        }
    }

    //Getters e Setters
    public String getNome()
    {
        return nome;
    }

    public void setNome(String _Nome)
    {
        nome = _Nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String _Cpf)
    {
        cpf_valido = validarCPF(_Cpf);
        if (cpf_valido)
        {
            cpf = _Cpf.replaceAll("[^0-9]", "");
        }
        else
        {
            cpf = "CPF inválido";
        }
    }

    public Boolean getCpfValido()
    {
        return cpf_valido;
    }

    public String getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(String _DataNascimento)
    {
        dataNascimento = _DataNascimento;
    }

    public int getIdade()
    {
        return idade;
    }

    public void setIdade(int _Idade)
    {
        idade = _Idade;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String _Endereco)
    {
        endereco = _Endereco;
    }

    //Métodos
    public String ToString()
    {
        return "\n---CLIENTE---\nNome: %s\nCPF: %s\nData de nascimento: %s\nIdade: %d anos\nEndereco: %s".formatted(nome, cpf, dataNascimento, idade, endereco);
    }

    private boolean formatarCpf(String _Cpf)
    {
        String cpf_numerico = _Cpf.replaceAll("[^0-9]", "");
        if (cpf_numerico.length() != 11)
        {
            return false;
        }
        for (int index = 1; index < cpf_numerico.length(); index++)
        {
            if (cpf_numerico.charAt(0) != cpf_numerico.charAt(index))
            {
                return true;
            }
        }
        return false;
    }

    private boolean calcularVerificadores(String _Cpf)
    {
        int soma_1 = 0, soma_2 = 0;
        char verificador_1, verificador_2;
        String cpf_numerico = _Cpf.replaceAll("[^0-9]", "");
        for (int index = 0; index < 9; index++)
        {
            soma_1 += (10 - index) * (cpf_numerico.charAt(index) - 48);
            soma_2 += (10 - index) * (cpf_numerico.charAt(index + 1) - 48);
        }
        if (soma_1 % 11 == 0 || soma_1 % 11 == 1)
        {
            verificador_1 = '0';
        }
        else 
        {
            verificador_1 = (char)(11 - soma_1 % 11 + 48);
        }
        if (soma_2 % 11 == 0 || soma_2 % 11 == 1)
        {
            verificador_2 = '0';
        }
        else 
        {
            verificador_2 = (char)(11 - soma_2 % 11 + 48);
        }
        if (verificador_1 != cpf_numerico.charAt(9) || verificador_2 != cpf_numerico.charAt(10))
        {
            return false;
        }
        return true;
    }

    private boolean validarCPF(String _Cpf)
    {
        boolean onze_digitos_diferentes = formatarCpf(_Cpf);
        if (onze_digitos_diferentes)
        {
            boolean digitos_verificadores = calcularVerificadores(_Cpf);
            return digitos_verificadores;
        }
        return false;
    }
}
