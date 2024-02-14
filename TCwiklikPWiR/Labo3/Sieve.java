class Sieve extends Thread{
    
    private static int count = 1;
    private Buffor bufforIn;
    private Buffor bufforOut;
    private int prime;

    public Sieve(int prime, Buffor bufforIn){
        this.prime = prime;
        this.bufforIn = bufforIn;
    }
    
    public void run(){
        Sieve sieve = null;
        
        while(true){
            int value = bufforIn.getProduct();
            if(value % prime != 0){
                if(bufforOut == null){
                    bufforOut = new Buffor();
                    sieve = new Sieve(value, bufforOut);
                    System.out.println(count + " . " + sieve);
                }
            }
            else{
                bufforOut.setProduct(value);
            }
        }
    }
    
    @Override
    public String toString(){
        return " " + prime;
    }
    
}