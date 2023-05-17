public enum SubMenuOperacoes
{
    //Constantes
    CADASTRAR_CLIENTE_PF("Cadastrar cliente PF"),
    CADASTRAR_CLIENTE_PJ("Cadastrar cliente PJ"),
    CADASTRAR_VEICULO("Cadastrar veiculo"),
    CADASTRAR_SEGURADORA("Cadastrar seguradora"),
    LISTAR_CLIENTES_PF("Listar clientes PF"),
    LISTAR_CLIENTES_PJ("Listar clientes PJ"),
    LISTAR_TODOS_CLIENTES("Listar todos os clientes"),
    LISTAR_SINISTROS_SEGURADORA("Listar sinistros por seguradora"),
    LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
    LISTAR_VEICULOS_SEGURADORA("Listar veiculos por seguradora"),
    LISTAR_VEICULOS_CLIENTE("Listar veiculos por cliente"),
    REMOVER_CLIENTE("Remover cliente"),
    REMOVER_VEICULO("Remover veiculo"),
    REMOVER_SINISTRO("Remover sinistro"),
    VOLTAR("Voltar");

    //Propriedade
    private final String descricao;

    //Construtor
    SubMenuOperacoes(String descricao)
    {
        this.descricao = descricao;
    }

    //Getter
    public String getDescricao()
    {
        return descricao;
    }
}
