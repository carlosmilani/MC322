public enum MenuOperacoes
{
    //Constantes
    CADASTRAR("Cadastrar", new SubMenuOperacoes[]
    {
        SubMenuOperacoes.CADASTRAR_CLIENTE_PF,
        SubMenuOperacoes.CADASTRAR_CLIENTE_PJ,
        SubMenuOperacoes.CADASTRAR_VEICULO,
        SubMenuOperacoes.CADASTRAR_SEGURADORA,
        SubMenuOperacoes.VOLTAR
    }),
    LISTAR("Listar", new SubMenuOperacoes[]
    {
        SubMenuOperacoes.LISTAR_CLIENTES_PF,
        SubMenuOperacoes.LISTAR_CLIENTES_PJ,
        SubMenuOperacoes.LISTAR_TODOS_CLIENTES,
        SubMenuOperacoes.LISTAR_SINISTROS_SEGURADORA,
        SubMenuOperacoes.LISTAR_SINISTROS_CLIENTE,
        SubMenuOperacoes.LISTAR_VEICULOS_SEGURADORA,
        SubMenuOperacoes.LISTAR_VEICULOS_CLIENTE,
        SubMenuOperacoes.VOLTAR
    }),
    REMOVER("Remover", new SubMenuOperacoes[]
    {
        SubMenuOperacoes.REMOVER_CLIENTE,
        SubMenuOperacoes.REMOVER_VEICULO,
        SubMenuOperacoes.REMOVER_SINISTRO,
        SubMenuOperacoes.VOLTAR
    }),
    GERAR_SINISTRO("Gerar Sinistro", new SubMenuOperacoes[] {SubMenuOperacoes.VOLTAR}),
    TRANSFERIR_SEGURO("Transferir Seguro", new SubMenuOperacoes[] {SubMenuOperacoes.VOLTAR}),
    CALCULAR_RECEITA_SEGURADORA("Calcular Receita", new SubMenuOperacoes[] {SubMenuOperacoes.VOLTAR}),
    SAIR("Sair", new SubMenuOperacoes[] {});

    //Propriedades
    private final String descricao;

    private final SubMenuOperacoes[] subMenuOperacoes;

    //Construtor
    MenuOperacoes(String descricao, SubMenuOperacoes[] subMenuOperacoes)
    {
        this.descricao = descricao;
        this.subMenuOperacoes = subMenuOperacoes;
    }

    //Getters
    public String getDescricao()
    {
        return descricao;
    }

    public SubMenuOperacoes[] getSubMenuOperacoes()
    {
        return subMenuOperacoes;
    }
}
