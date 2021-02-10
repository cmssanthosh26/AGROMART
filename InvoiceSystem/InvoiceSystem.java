package invoicesystem;
import java.util.LinkedHashMap;
public class InvoiceSystem 
{
    public static void main(String[] args) 
    {
        /*String[] name={"Santhosh","Soundar","Mohan"};
        String[] mno={"8072023642","9159563724","9876543210"};*/
        
        String[] item={"Dosa","Poori","Rice","Briyani"};
        float[] itemRate={10,20,40,70};
        InvoiceControl ic=new InvoiceControl();
        ic.initializeItem(item,itemRate);
        //ic.initializeCustomer(name, mno);
        ic.startExecution();
    }    
}


