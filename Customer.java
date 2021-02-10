package invoicesystem;
import java.util.Scanner;
public class Customer {
    private String customerName;
    private String mobileNumber;
    private Invoice invoice;
    private Customer next;
    private int invoiceCount;
    private final static Scanner scanner=new Scanner(System.in);
    private float totalCostForAllInvoice;
    public Customer()
    {
        this.invoiceCount=1;
    }
    void setCustomerName(String name)
    {
        this.customerName = name;
    }
    
    void setMobileNumber(String number)
    {
        this.mobileNumber = number;
    }
    
    void setNextCustomer(Customer next)
    {
        this.next=next;
    }
    
    Customer getNextCustomer()
    {
        return this.next;
    }
    
    String getCustomerName()
    {
        return this.customerName;
    }
    
    String getMobileNumber()
    {
        return this.mobileNumber;
    }
    
    void addInvoice(Item item)
    {
        Invoice temp=new Invoice(this.invoiceCount);
        if(invoice==null)
        {
            this.invoice=temp;
        }
        else
        {
            Invoice t=invoice;
            while(t.getNextInvoice()!=null)
                t=t.getNextInvoice();
            t.setNextInvoice(temp);
        }
        temp.addItemToInvoice(item);
        System.out.println("Your Invoice Number is "+this.invoiceCount);
        this.invoiceCount++;
    }
    
    void displayParticularInvoice()
    {
        System.out.println("Enter your Invoice number");
        int invoiceNumber = scanner.nextInt();
        Invoice temp = isValidInvoice(invoiceNumber);
        System.out.println();
        if(temp != null)
        {
            System.out.println("Customer Name :"+this.getCustomerName());
            System.out.println("Customer Mobile Number :"+this.getMobileNumber());
            temp.displayInvoice();
        }
        else
        {
            System.out.println(" Enter correct invoice number ");
        }
    }
    
    void displayAllInvoice()
    {
        Invoice temp=this.invoice;    
        int value=0;
        //System.out.println("Name\tMobile Nuumber\tTotal Number of Items\t total sum");
        System.out.println();
        System.out.printf("%-20s%-15s%-25s%-15s", "Name","Mobile Nuumber","Total Number of Items","total sum");
        System.out.println();
        while(temp!=null)
        {
            value++;
            //System.out.println(this.customerName+"\t"+this.mobileNumber+"\t"+temp.getItemCount()+"\t" +temp.getSubTotal() * ((100-temp.getDiscount())/100));
            System.out.printf("%-20s%-15s%-25d%-15f",this.customerName,this.mobileNumber,temp.getItemCount(),temp.getSubTotal() * ((100-temp.getDiscount())/100));
            System.out.println();
            this.totalCostForAllInvoice = this.totalCostForAllInvoice + temp.getSubTotal() * ((100-temp.getDiscount())/100);
            temp=temp.getNextInvoice();
        }    
        System.out.println();
        System.out.println("Total number of invoice :"+value);
        System.out.println("Total cost for all invoice :"+this.totalCostForAllInvoice);        
    }
    
    Invoice isValidInvoice(int number){
        Invoice tempInvoice = this.invoice;
        while(tempInvoice != null)
        {
            if(tempInvoice.getInvoiceNumber() == number)
                return tempInvoice;
            tempInvoice=tempInvoice.getNextInvoice();
        }
        return null;
    }
}
