public class ContaPoupanca extends Conta implements ITaxas {
    private double limite;
    ContaPoupanca(Cliente dono) {}
    @Override
    public double setLimite(double limite) throws SemLimiteExeption {
        if(limite < 1000 && limite > 100){
            this.limite = limite;
            return this.limite;
        }else
            throw new SemLimiteExeption("Limite insuficiente");
    }

    @Override
    public double calculaTaxas() {
        return 0;
    }
}
