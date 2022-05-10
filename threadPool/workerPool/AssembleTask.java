package threadPool.workerPool;

import factory.assemble.Assembler;
import factory.dealers.Dealer;

public class AssembleTask {
    public Assembler assembler;
    public Dealer askedDealer;

    public AssembleTask(Assembler assembler, Dealer dealer){
        this.assembler = assembler;
        askedDealer = dealer;
    }
}
