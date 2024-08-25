public class ContaUniversitaria extends Conta implements ITaxas{
    private double limite;
    ContaUniversitaria(Cliente dono) {}
    @Override
    public double setLimite(double limite) throws SemLimiteExeption{
        if(limite > 0 && limite < 500){
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
