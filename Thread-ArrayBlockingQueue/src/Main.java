
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    /* ArrayBlockingQueue diğerlerinden farklı olarak Thread safe olarak çalışır. Put ile veri ekleyip Take ile kullanacağız istisna olarak yapının içi dolu olursa Put bekler ve veri ekleyemez Take verileri kullanmaya devam eder tam tersi durumda ise Take bekler Put veri eklemeye devam eder. */
    public static void main(String[] args) {
        ABQueue pc = new ABQueue();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.produce();
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.use();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

/* Bu örnekte verileri üretip 3 saniye beklettik kullandıktan sonra ise 1 saniye beklettik. Yani veri kullanma hızı Take() metodu
verileri üretip eklemekten Put metodunan daha az süre beklediği için veriler üretildiği gibi kullanılıyor. ArrayBlockingQueue yapısal olarak Thread Safe olarak çalışıtığı için veri olmadığı zaman consumer threadimiz bekler çalışmaz.
*/
