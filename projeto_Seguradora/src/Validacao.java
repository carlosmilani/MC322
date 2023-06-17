public class Validacao
{
    //Métodos
    public static boolean validarCPF(String cpf)
    {
        if (cpf.length() == 11)
        {
            if (checarDigitos(cpf))
            {
                return calcularVerificadores(cpf);
            }
        }
        return false;
    }

    public static boolean validarCNPJ(String cnpj)
    {
        if (cnpj.length() == 14)
        {
            if (checarDigitos(cnpj))
            {
                return calcularVerificadores(cnpj);
            }
        }
        return false;
    }

    public static boolean validarNome(String nome)
    {
        if (nome.equals(""))
        {
            return false;
        }
        return checarDigitos(nome);
    }

    private static boolean checarDigitos(String identificador)
    {
        for (int index = 1; index < identificador.length(); index++)
        {
            if (identificador.charAt(0) != identificador.charAt(index))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean calcularVerificadores(String identificador)
    {
        int soma1 = 0, soma2 = 0;
        char verificador1, verificador2;
        if (identificador.length() == 11)
        {
            for (int index = 0; index < 9; index++)
            {
                soma1 += (10 - index) * (identificador.charAt(index) - 48);
                soma2 += (10 - index) * (identificador.charAt(index + 1) - 48);
            }
            if (soma1 % 11 == 0 || soma1 % 11 == 1)
            {
                verificador1 = '0';
            }
            else 
            {
                verificador1 = (char)(11 - soma1 % 11 + 48);
            }
            if (soma2 % 11 == 0 || soma2 % 11 == 1)
            {
                verificador2 = '0';
            }
            else 
            {
                verificador2 = (char)(11 - soma2 % 11 + 48);
            }
            if (verificador1 != identificador.charAt(9) || verificador2 != identificador.charAt(10))
            {
                return false;
            }
        }
        else if (identificador.length() == 14)
        {
            int array1[] = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2}, array2[] = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            for (int index = 0; index < 12; index++)
            {
                soma1 += array1[index] * (identificador.charAt(index) - 48);
                soma2 += array2[index] * (identificador.charAt(index) - 48);
            }
            soma2 += array2[12] * (identificador.charAt(12) - 48);
            if (soma1 % 11 == 0 || soma1 % 11 == 1)
            {
                verificador1 = '0';
            }
            else 
            {
                verificador1 = (char)(11 - soma1 % 11 + 48);
            }
            if (soma2 % 11 == 0 || soma2 % 11 == 1)
            {
                verificador2 = '0';
            }
            else 
            {
                verificador2 = (char)(11 - soma2 % 11 + 48);
            }
            if (verificador1 != identificador.charAt(12) || verificador2 != identificador.charAt(13))
            {
                return false;
            }
        }
        return true;
    }
}
