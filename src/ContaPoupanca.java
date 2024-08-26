public class ContaPoupanca extends Conta implements ITaxas {
    private double limite;
    ContaPoupanca(Cliente dono) {}
    @Override
    public double setLimite(double limite) throws IllegalArgumentException{
        if(limite < 1000 && limite > 100){
            this.limite = limite;
            return this.limite;
        }else
            throw new IllegalArgumentException("Não é possivel definir o limite para esse valor");
    }

    @Override
    public double calculaTaxas() {
        return 0;
    }
}
