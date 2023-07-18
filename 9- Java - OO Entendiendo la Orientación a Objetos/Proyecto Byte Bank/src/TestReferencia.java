public class TestReferencia {
    public static void main(String[] args) {
        Cliente diego = new Cliente();
        diego.nombre = "Diego";
        diego.documento = "44332257";
        diego.telefono = "15263901";

        Cuenta cuentaDiego = new Cuenta();
        cuentaDiego.agencia = 1;
        cuentaDiego.titular = diego;

        System.out.println(cuentaDiego.titular);
        System.out.println(cuentaDiego.titular.nombre);

    }
}
