public class Cuenta {
    double saldo;
    int agencia;
    int numero;
    Cliente titular;

    public void depositar(double saldo) {
        this.saldo += saldo;
    }

    public boolean retirar(double valor) {
        if (this.saldo >= valor){
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public boolean transferir(double valor, Cuenta cuenta) {
        if (this.saldo >= valor){
            retirar(valor);
            cuenta.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public double getSaldo(){
        return this.saldo;
    }

    public void setAgencia(int agencia){
        if (agencia > 0){
            this.agencia = agencia;
        } else {
            System.out.println("No están permitidos valores negativos");
        }

    }

    public int getAgencia(){
        return this.agencia;
    }

    public static void main(String[] args) {

    }
}
