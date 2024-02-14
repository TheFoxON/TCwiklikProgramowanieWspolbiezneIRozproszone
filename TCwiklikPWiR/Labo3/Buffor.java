public class Buffor {
    
    private volatile boolean isProduct = false;
    private int product;

    synchronized public void setProduct(int val){
        while (isProduct == true){
            try{
                wait();
            }
            catch (Exception e){}
        }
        product = val;
        isProduct = true;
        notifyAll();
    }

    synchronized public int getProduct(){
        while (isProduct == false){
            try{
                wait();
            }
            catch (Exception e){}
        }
        isProduct = false;
        notifyAll();
        return product;
    }

}
