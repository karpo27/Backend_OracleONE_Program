public class Ejemplo2 {

    public static void main(String[] args) {
        
        int factoriala = 1;
        for (int k = 1; k < 11; k += 1){
            factoriala *= k;
            System.out.println("Factorial de " + k + " = " + factoriala);
        }
    }
}
