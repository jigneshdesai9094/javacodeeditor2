import java.util.*;
class Producer extends Thread
{
   int container[];
   Producer(int container[])
   {
    this.container=container;
   }
   @Override
   public void run() {
    while(true)
    {
       if((ProducerConsumer.top)<4 )
       {
           synchronized(container)
           {
            ProducerConsumer.top++;
            System.out.println("top  ="+ProducerConsumer.top);
            Scanner s1 = new Scanner(System.in);
            System.out.println("Enter New Value");
            int value = s1.nextInt();
            container[ProducerConsumer.top]=value;
            container.notify();
           }
       }
       else
       {
        try {
            synchronized(container)
            {
            System.out.println("producer is wait");
            container.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
       }
    }
   }
}
class Consumer extends Thread
{
   int container[];
   Consumer(int container[])
   {
    this.container=container;
   }
   @Override
   public void run() {
    while (true) {
        
    
       if(ProducerConsumer.top>=0)
       {
           synchronized(container)
           {
            System.out.println("top value get : "+container[ProducerConsumer.top]);
            ProducerConsumer.top--;
            try
            {
            Thread.sleep(1000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
          container.notify();
           }
       }
       else
       {
        try {
            synchronized(container)
            {
            System.out.println("consumer is wait");
           container.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         
       }
    }
   }
}

public class ProducerConsumer {
    static int top = -1;
    public static void main(String[] args) {
        int container[] = new int[5];


        Producer t1 = new Producer(container);
        Consumer c2 = new Consumer(container);
        c2.start();
        t1.start();
    }
}
