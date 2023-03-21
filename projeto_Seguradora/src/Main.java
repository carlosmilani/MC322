public class Main {
    public static void main(String[] args) throws Exception
    {
        Cliente c1 = new Cliente("Carlos Eduardo", "448.939.196-01", "01/01/2003", 20, "Rua X, Campinas-SP");
        System.out.println(c1.ToString());
        
        Seguradora seg1 = new Seguradora("Seguros LTDA", "19-98765-4321", "segurosltda@gmail.com", "Rua Y, Campinas-SP");
        System.out.println(seg1.ToString());
        
        Sinistro sin1 = new Sinistro("01/01/2023", "Rua Z, Campinas-SP");
        System.out.println(sin1.ToString());
        
        Veiculo v1 = new Veiculo("ABCD-1234", "Fiat", "Uno");
        System.out.println(v1.ToString());

        c1.setNome("Tiago");
        c1.setCpf("fb236fb23fg32g");
        System.out.println(c1.ToString());
    }
}
