public abstract class Conta implements ITaxas{

    private int numero;

    protected Cliente dono;

    protected double saldo;

    private double limite;

    private Operacao[] operacoes = new Operacao[6];

    private int proximaOperacao = 0;

    private static int totalContas = 0;

    public Conta() {}
    public void sacar(double valor)
        throws ValorNegativoException,SemLimiteExeption{
        if(valor < limite+saldo) {
            if (valor > 0) {
                this.saldo -= valor;

                this.operacoes[proximaOperacao] = new OperacaoSaque(valor);
                this.proximaOperacao++;
            } else {
                throw new ValorNegativoException("Digite um valor possitivo");
            }
        }else{
            throw new SemLimiteExeption("Sem limite tente um valor menor");
        }
    }

    public void depositar(double valor) throws ValorNegativoException{
        if(valor <= 0) {
            throw new ValorNegativoException("Digite um valor possitivo");
        }
        this.saldo += valor;
        this.operacoes[proximaOperacao] = new OperacaoDeposito(valor);
        this.proximaOperacao++;
    }

    public void transferir(Conta destino, double valor)
            throws ValorNegativoException,SemLimiteExeption{
        if (valor >= 0 && valor <= this.limite) {
            this.sacar(valor);
            try {
                destino.depositar(valor);
            }catch (ValorNegativoException e) {
                throw new ValorNegativoException("Digite um valor possitivo");
            }
        }else{
            throw new SemLimiteExeption("Sem limite tente um valor menor");
        }
    }
    @Override
    public String toString() {
        return this.numero + this.dono.toString() + this.saldo + this.limite;
    }
    @Override
    public boolean equals(Object obj) {
        return this.numero == ((Conta) obj).numero;
    }

    public void imprimirExtrato() {
        System.out.println("======= Extrato " + dono.getNome() + " ======");
        for(Operacao atual : this.operacoes) {
            if (atual != null) {
                System.out.println(atual.toString());
            }
        }
        System.out.println("=======================");
    }
    public double calculaTaxas(){return 0;}
    public void imprimirExtratoTaxas(){
        double Total = calculaTaxas();
        System.out.printf("======= Extrato %s Taxas ======",dono.getNome());
        System.out.printf("Manutenção da conta: %.2f\n" ,this.calculaTaxas());
        System.out.printf("Operações\n");
        for(int i = 0; i < proximaOperacao; i++) {
            if(operacoes[i].calculaTaxas() != 0) {
                System.out.printf("%s %.2f\n", operacoes[i].getTipo(), operacoes[i].calculaTaxas());
                Total += operacoes[i].calculaTaxas();
            }
        }
        this.saldo -= Total;
        System.out.printf("Total: %.2f\n", Total);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public abstract double setLimite(double limite);
}
