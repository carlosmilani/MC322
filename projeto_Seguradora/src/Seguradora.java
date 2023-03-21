public class Seguradora {

    //Propriedades
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    //Construtor
    public Seguradora(String _Nome, String _Telefone, String _Email, String _Endereco)
    {
        nome = _Nome;
        telefone = _Telefone;
        email = _Email;
        endereco = _Endereco;
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

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String _Telefone)
    {
        telefone = _Telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String _Email)
    {
        email = _Email;
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
        return "\n---SEGURADORA---\nNome: %s\nTelefone: %s\nE-mail: %s\nEndereco: %s".formatted(nome, telefone, email, endereco);
    }
}
