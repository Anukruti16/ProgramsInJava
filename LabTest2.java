
package labtest2;
import java.util.Scanner;

class Menu
{
    void menu()
    {
        System.out.println("Enter the operation you want to perform (withdraw/deposit)");
        Scanner s=new Scanner(System.in);
        String str=s.nextLine();
        if(null == str)
        {
            System.out.println("Enter valid operation");
        }
        else switch (str) {
            case "withdraw":
                System.out.println("Starting withdraw operation");
                break;
            case "deposit":
                System.out.println("Starting deposit operation");
                break;
            default:
                System.out.println("Enter valid operation");
                break;
        }
    }
}

class Test
{
   int amount=0;
   int flag=0;
   public synchronized int withdraw(int amount)
   {
        System.out.println("going to withdraw");
        if(flag==0)
        {
        try
            {
                System.out.println("waiting....");
                wait();
            }
        catch(Exception e){}
        }
        
        this.amount-=amount;
        if(this.amount<10000)
        {
              System.out.println("withdraw cancelled");
              System.out.println("final balance remains unaltered i.e:"+amount);
        }
       else
        {
            System.out.println("withdraw completed");
        }
        return amount;
     }

    public synchronized void deposit(int amount)
    {
        System.out.println("going to  deposit");
        this.amount+=amount;
        System.out.println("deposit completed");
        notifyAll();
        flag=1;
    }
 }




public class LabTest2 {
     public static void main(String[] args) {
        Menu m=new Menu();
        m.menu();
         Test c = new Test();
    Thread t1 = new Thread()
    {
        public void run()
        {
        c.withdraw(500000);
 
        }
   };
 
    Thread t2 = new Thread()
    {
        public void run()
        {
        c.deposit(70000);

        }
    };

 t1.start();
 t2.start();
    }
    
}
