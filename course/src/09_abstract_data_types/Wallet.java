class Wallet {
    private int amount;

    public void loanTo(Wallet that) { 
        // put lal of this wallet's money into that wallet 
        that.amount += this.amount; /*A */
        // python would explictly require the self
        amount = 0; /*B */        
    }

    // runs only when running directly this, not when importing
    public static void main (String[] args){
        Wallet w = new Wallet(); /* C */
        w.amount = 100; /* D */
        w.loanTo(w); /* E */
    }
}

class Person {
    private Wallet w;
    public int getNetWorth() {
        return w.amount; /* F */
    }

    public boolean isBroke() {
        return Wallet.amount == 0; /* G */
    }
}