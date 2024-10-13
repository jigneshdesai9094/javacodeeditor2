class mythread extends Thread
{
    @Override
    public void run() {
        for(int i=1;i<=10;i++)
        {
            System.out.println("i = "+i);
            try
            {
                sleep(1000);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            
        }
    }
}
public class JoinThread {
    public static void main(String[] args) {
        mythread t1 =new mythread();
        t1.start();
        try {
            t1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        for(int j=1;j<=10;j++)
        {
            System.out.println("j = "+j);
        }
    }
}
