
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ABQueue {
    // verileri rastgele ekleyeceğiz.
    Random random = new Random();
    
    //15 boyutlu ArrayBlockingQueue oluşturduk içine intiger değerler alacak.
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(15);
    
    public void produce() {
        
        while (true) {            
            
            try {
                //threadi her seferinde 2000 ms beklettik.
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("Thread kesitiye uğradı");
            }

            try {
            //0 - 50 arası rastgele sayı üretip valueye aktardık
            Integer value = random.nextInt(50);
            
                // ürettiğimiz değerleri Arrayblockingqueue ye aktardık
                queue.put(value);
                
            System.out.println("Veriler üretiliyor üretilen değer : " + value);

            } catch (InterruptedException ex) {
                Logger.getLogger(ABQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void use(){
        
        while (true) {            
            try {
                /* 1 sn beklesin */
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ABQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                Integer value = queue.take();
                System.out.println("Veriler Kullanılıyor : " + value);
                //boyutu ekrana yazdırdık
                System.out.println("ArrayBlockingQueue Size : " + queue.size());
            } catch (InterruptedException ex) {
                Logger.getLogger(ABQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
