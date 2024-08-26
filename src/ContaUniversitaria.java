public class ContaUniversitaria extends Conta implements ITaxas{
    private double limite;
    ContaUniversitaria(Cliente dono) {}
    @Override
    public double setLimite(double limite) throws IllegalArgumentException{
        if(limite > 0 && limite < 500){
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
