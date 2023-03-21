public class Sinistro {

    //Propriedades
    private static int count = 0;
    private int id;
    private String data;
    private String endereco;

    //Construtor
    public Sinistro(String _Data, String _Endereco)
    {
        data = _Data;
        endereco = _Endereco;
        ++count;
        id = count;
    }

    //Getters e Setters
    public int getId()
    {
        return id;
    }

    public void setId(int _Id)
    {
        id = _Id;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String _Data)
    {
        data = _Data;
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
        return "\n---SINISTRO---\nID: %d\nData: %s\nEndereco: %s".formatted(id, data, endereco);
    }
}
