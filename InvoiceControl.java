package invoicesystem;
import java.util.Scanner;
public class InvoiceControl {
    private Item item;
    private static final Scanner scanner=new Scanner(System.in);
    private Customer root;
    
    void startExecution()
    {
        while(true)
        {
            System.out.println("Enter your choice(1-5)");
            System.out.println("1.New customer");
            System.out.println("2.Existing Customer");
            System.out.println("3.Display invoice");
            System.out.println("4.Display Total invoice detail for a particular customer");
            System.out.println("5.Exit");
            int n=scanner.nextInt();
            execute(n);
            System.out.println("Do you want to continue enter 1 otherwise enter 0");
            int v=scanner.nextInt();
            if(v==0)
                break;
        }
    }
    
    void execute(int n)
    {
        switch(n)
        {
            case 1:
                this.addCustomer();
                break;
            case 2:
                this.addInvoiceToParticularCustomer();
                break;
            case 3:
                this.displayInvoiceToParticularCustomer();
                break;
            case 4:
                this.displayTotalInvoice();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Enter correct option");
        }
    }
    
    void addCustomer()
    {
        scanner.nextLine();
        System.out.println("Enter your name");
        String name=scanner.nextLine();
        System.out.println("Enter your mobile number");
        String mobileno=scanner.next();
        Customer temp=new Customer();
        temp.setCustomerName(name);
        temp.setMobileNumber(mobileno);
        if(this.root==null)
            this.root=temp;
        else
        {
            Customer t=root;
            while(t.getNextCustomer()!=null)
                t=t.getNextCustomer();
            t.setNextCustomer(temp);
        }
        temp.addInvoice(this.item);
    }
    
    void addInvoiceToParticularCustomer()
    {
        System.out.println("Enter your mobile number");
        String mobileno = scanner.next();
        Customer c = isValidCustomer(mobileno);
        if(c!=null)
        {
            c.addInvoice(this.item);
        }
        else
        {
            System.out.println("Your database is not available please add your details in new customer");
        }
    }
    
    Customer isValidCustomer(String mno)
    {
        Customer temp=this.root;
        while(temp!=null)
        {
            if(temp.getMobileNumber().equals(mno))
                return temp;
            temp=temp.getNextCustomer();
        }
        return null;
    }
    
    void displayInvoiceToParticularCustomer()
    {
        System.out.println("Enter your mobile number");
        String mobileno = scanner.next();
        Customer c = isValidCustomer(mobileno);
        if(c!=null)
        {
            c.displayParticularInvoice();
        }
        else
        {
            System.out.println("Your database is not available please add your details in new customer");
        }
    }
    
    void displayTotalInvoice()
    {
        System.out.println("Enter your mobile number");
        String mobileno = scanner.next();
        Customer c = isValidCustomer(mobileno);
        if(c!=null)
        {
            c.displayAllInvoice();
        }
        else
        {
            System.out.println("Your database is not available please add your details in new customer");
        }
    }
        
    void addItem(String name,float rate)
    {
        Item temp=new Item();
        temp.setItemName(name);
        temp.setItemRate(rate);
        if(this.item==null)
            this.item=temp;
        else
        {
            Item temp1=this.item;
            while(temp1.getNextItem()!=null)
                temp1=temp1.getNextItem();
            temp1.setNextItem(temp);
        }    
    }
    
    /*void initializeCustomer(String[] name,String[] mno)
    {
        
    }*/
    
    void initializeItem(String[] item,float[] rate)
    {
        for(int i=0;i<item.length;i++)
            addItem(item[i],rate[i]);
    }
}
