/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abada.generator.main;





import com.abada.generator.domain.Domain;
import com.abada.generator.domain.Property;
import com.abada.generator.enums.Messages;
import com.abada.generator.object.Order;
import com.abada.generator.object.Patient;
import com.abada.selene.v25.message.SeleneMessagesMaker;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author david
 */
public class Main {

    private static Log log = LogFactory.getLog(Main.class);
 private ExecutorService executor = Executors.newCachedThreadPool(); 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
          Main t= new Main();
        t.spawnThreads(1);
        
    }
 protected void spawnThreads( int numberOfThreads ) throws Exception
    {
        
        List lojbect = new ArrayList();
        StatisticsModule statistics = new StatisticsModule();
        statistics.pickStartTime();
        Property p = new Property();
        int nmessage = Integer.parseInt(p.getProperty().getProperty("nmesagge"));
        Domain domain = new Domain(p.getProperty());
        Messages message[] = Messages.values();
         SeleneMessagesMaker s=new SeleneMessagesMaker();
        String encode;
        for (int a = 0; a < nmessage; a++) {
           domain.load(lojbect, message[(int) (Math.random() * message.length)]);
        }
       

        for(int b=0;b<lojbect.size();b++){
            if(lojbect.get(b) instanceof Order) {
                  encode=s.createListOMP_O09((Order)lojbect.get(b));
            }else {
                encode=s.createListADT((Patient)lojbect.get(b));
              
            }
             HttpThread thread = new HttpThread( "thread-" + b, encode );
              executor.execute( thread );
                System.out.println( "Launching: thread-" + b );
        }
        

}




}