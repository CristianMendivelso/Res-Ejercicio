/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.resuelve.ejercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author cristian mendivelso
 */
public class QuantityOfInvoices {

    private static DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static int numCalls = 0;

    /**
     * Método principal que ejecuta la consulta según los argumentos enviados
     *
     * @param args El orden de los argumentos esperados es host id fechaInicio
     * fechaFin
     * @throws tn.esprit.resuelve.ejercicio.InvoicesException Si faltan
     * argumentos para informar al ejecutor
     */
    public static void main(String[] args) throws InvoicesException {
        //Si no hay al menos 4 argumentos de entrada no se puede realizar la consulta
        if (args.length == 4) {
            String url = args[0];
            String id = args[1];
            try {
                DateTime startDate = dtf.parseDateTime(args[2]);
                DateTime finishDate = dtf.parseDateTime(args[3]);
                long numInvoices= getInvoicesbyDates(url, id, startDate, finishDate);
                if (numInvoices>100){
                    System.out.println("Hay más de 100 resultados");
                    
                }
                else{
                    System.out.println("La Cantidad de Facturas Entre las fechas " + args[2] + " y " + args[3] + " es: " +numInvoices );
                    System.out.println("Número de llamadas al servicio: " + numCalls);
                }
                
            } catch (InvoicesException ex) {
                System.out.println(ex.getMessage());
            }
            catch(IllegalFieldValueException e){
                System.out.println(e.getLocalizedMessage());
            }
        } else {
            throw new InvoicesException("Te faltan parámetros");
        }
    }

    /**
     * Método Utilizado para obtener la información de la cantidad de facturas
     * entre 2 fechas.
     *
     * @param url url del servicio del cual se van a consultar los datos
     * @param id Id del cliente
     * @param start fecha inicial de consulta
     * @param finish fecha final de consulta
     * @return Cantidad de facturas entre dos fechas ingresadas por parámetro
     * @throws tn.esprit.resuelve.ejercicio.InvoicesException En caso de que
     * falten parámetros
     *
     */
    public static long getInvoicesbyDates(String url, String id, DateTime start, DateTime finish) throws InvoicesException {
        long answer = 0;
        String output = getInvoicesFromWeb(url, id, start, finish);
        String startString = dtf.print(start);
        String finishString = dtf.print(finish);
        try{
            answer = Long.parseLong(output);
        }
        //Si Hay más de 100 resultados recurrir sobre diferencias de fechas más pequeñas (diferencia/2) e ir sumando
        catch(NumberFormatException e ){
            Days diffInDays = Days.daysBetween(start, finish);
            
            int days = diffInDays.getDays();
            answer = getInvoicesbyDates(url, id, start, start.plusDays(days / 2)) + getInvoicesbyDates(url, id, start.plusDays((days / 2) + 1), finish);
        }

        return answer;
    }

    /**
     * Método que permite realizar la conexión con el servicio web y obtener la
     * respuesta que el servicio genere.
     *
     * @param url url del servicio del cual se van a consultar los datos
     * @param id Id del cliente
     * @param start fecha inicial de consulta
     * @param finish fecha final de consulta
     * @return Una cadena que representa la respuesta del servicio sobre la
     * consulta
     * @throws tn.esprit.resuelve.ejercicio.InvoicesException
     *
     */
    public static String getInvoicesFromWeb(String url, String id, DateTime start, DateTime finish) throws InvoicesException {
        numCalls += 1;
        String output = "";
        String startString = dtf.print(start);
        String finishString = dtf.print(finish);
        String urlwithParam = url + "?id=" + id + "&start=" + startString + "&finish=" + finishString;
        try {
            URL urlFacturas = new URL(urlwithParam);
            URLConnection urlConnection = urlFacturas.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            output = in.readLine();
            in.close();
        } catch (MalformedURLException ex) {
            throw new InvoicesException("URL Inválida, Revise los parámetros");
        } catch (IOException ex) {
            throw new InvoicesException(ex.getMessage());
        }
        return output;
    }
}
