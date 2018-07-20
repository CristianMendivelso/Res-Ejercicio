
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tn.esprit.resuelve.ejercicio.InvoicesException;
import tn.esprit.resuelve.ejercicio.QuantityOfInvoices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristian mendivelso
 */

public class InvoicesExcersiceTest {
    
    
    private static DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");

    /*
     * Verificar si extrae correctamente la información del servicio entre 2 fechas cuya cantidad de facturas es menor a 100
     * @throws InvoicesException  
     *  
     */
    @Test
    public void testLessThan100() throws InvoicesException { 
        String url="http://34.209.24.195/facturas";
        String id="4e25ce61-e6e2-457a-89f7-116404990967";
        DateTime start = dtf.parseDateTime("2017-01-01");
        DateTime finish = dtf.parseDateTime("2017-01-23");
        assertEquals("No calcula de manera correcta la cantidad de facturas entre 2 fechas en donde hay menos de 100 facturas ",82, QuantityOfInvoices.getInvoicesbyDates(url, id, start, finish));        
    }
    
    /*
     * Verificar si extrae correctamente la información del servicio entre 2 fechas cuya cantidad de facturas es mayor a 100
     * la diferencia entre las fechas es de 71 días 
     *@throws InvoicesException  
     *  
     */
    @Test
    public void higherThan100Odd() throws InvoicesException { 
        String url="http://34.209.24.195/facturas";
        String id="4e25ce61-e6e2-457a-89f7-116404990967";
        DateTime start = dtf.parseDateTime("2017-01-01");
        DateTime finish = dtf.parseDateTime("2017-03-13");
        assertEquals("No calcula de manera correcta la cantidad de facturas entre 2 fechas en donde hay más de 100 facturas y la diferencia es días es impar ",237, QuantityOfInvoices.getInvoicesbyDates(url, id, start, finish));
    }
    
    /*
     * Verificar si extrae correctamente la información del servicio entre 2 fechas cuya cantidad de facturas es mayor a 100
     * la diferencia entre las fechas es de 72 días 
     *@throws InvoicesException  
     *  
     */
    @Test
    public void higherThan100Even() throws InvoicesException { 
        String url="http://34.209.24.195/facturas";
        String id="4e25ce61-e6e2-457a-89f7-116404990967";
        DateTime start = dtf.parseDateTime("2017-01-01");
        DateTime finish = dtf.parseDateTime("2017-03-14");
        assertEquals("No calcula de manera correcta la cantidad de facturas entre 2 fechas en donde hay más de 100 facturas y la diferencia de días es par ",239, QuantityOfInvoices.getInvoicesbyDates(url, id, start, finish));
    }
    
}
