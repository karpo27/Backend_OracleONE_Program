public class TestConversion {

    public static void main(String[] args){
        float puntoFlotante = 3.14f;

        double salario = 1270.50;
        int valor = (int) salario;

        System.out.println(valor);

        double valor1 = 0.2;
        double valor2 = 0.1;
        double total = valor1 + valor2;

        System.out.println(total);

        String saludo = "Hola, mi nombre es ";
        String nombre = "Rómulo ";
        String continuacion = "y mi edad es ";
        int edad = 100;
        System.out.println (saludo + nombre + continuacion + edad);
    }

}
