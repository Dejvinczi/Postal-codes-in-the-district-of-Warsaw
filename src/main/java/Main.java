import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("*************WITAM W SYSTEMIE WYSZUKIWANIA OKRĘGU WARSZAWSKIEGO*************\n");

        while(true){
            System.out.println("Wybierz metodę wyszukiwania:");
            System.out.println("1)Podaj KOD POCZTOWY by uzyskać ADRES, MIEJSCOWOŚĆ, POWIAT, WOJEWÓDZTWO");
            System.out.println("2)Podaj ULICE oraz MIASTO by uzyskać KOD POCZTOWY.");
            System.out.println("3)Podaje MIASTO LUB POWIAT by uzyskać WSZYSTKIE KODY POCZTOWE.");
            System.out.print("\nWybieram: ");
            int temp = sc.nextInt();
            switch(temp){
                case 1:
                    System.out.print("Kod pocztowy: ");
                    sc.nextLine();
                    String kp = sc.nextLine();
                    opcjaJeden(kp);
                    break;
                case 2:
                    System.out.print("Ulica: ");
                    sc.nextLine();
                    String ulica = sc.nextLine();
                    System.out.print("Miasto: ");
                    String miasto = sc.nextLine();
                    opcjaDwa(ulica,miasto);
                    break;
                case 3:
                    System.out.print("Miasto lub powiat: ");
                    sc.nextLine();
                    String mlubp = sc.nextLine();
                    opcjaTrzy(mlubp);
                    break;
                default:
                    System.out.println("Podano niepoprawną wartość.");
            }
        }
    }

    public static void opcjaJeden(String kp){
        boolean brak = false;
        Element doc = null;
        try{
            doc = Jsoup.connect("https://www.kody-pocztowe.dokladnie.com/").get();
        }catch (Exception e){System.out.println("*Brak połączenia ze stroną*");}
        Element table = doc.getElementById("page-table");
        Elements rows = table.getElementsByTag("tr");
        for(int i = 1; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements wartosci = row.getElementsByTag("td");
            String kodpocztowy = wartosci.get(0).text();
            if(kodpocztowy.equalsIgnoreCase(kp)){
                String adres = wartosci.get(1).text();
                String miejscowosc = wartosci.get(2).text();
                String wojewodztwo = wartosci.get(3).text();
                String powiat = wartosci.get(4).text();
                System.out.println(kodpocztowy+":\n"+
                                "Adres: "+adres+"\n"+
                                "Miejscowosc: "+miejscowosc+"\n"+
                                "Wojewodztwo: "+wojewodztwo+"\n"+
                                "Powiat: "+powiat+"\n"+
                                "--------------------------------------------------");
                brak = true;
            }
        }
    if(brak != true) System.out.println("--------------------------------------------------" +
            "\n*Brak danych o podanym kodzie pocztowym*\n" +
            "--------------------------------------------------");
    }

    public static void opcjaDwa(String ulica, String miasto){
        boolean brak = false;
        Element doc = null;
        try{
            doc = Jsoup.connect("https://www.kody-pocztowe.dokladnie.com/").get();
        }catch (Exception e){System.out.println("*Brak połączenia ze stroną*");}
        Element table = doc.getElementById("page-table");
        Elements rows = table.getElementsByTag("tr");
        for(int i = 1; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements wartosci = row.getElementsByTag("td");
            String adres = wartosci.get(1).text();
            String miejscowosc = wartosci.get(2).text();
            if(adres.contains(ulica) && miejscowosc.equalsIgnoreCase(miasto)){
                String kodpocztowy = wartosci.get(0).text();
                System.out.println("Kod pocztowy: "+kodpocztowy+"\n"+
                        "Ulica: "+adres+"\n"+
                        "Miasto: "+miasto+"\n"+
                        "--------------------------------------------------");
                brak = true;
            }
        }
        if(brak != true) System.out.println("--------------------------------------------------" +
                "\n*Brak danych o podanym kodzie pocztowym*\n" +
                "--------------------------------------------------");
    }

    public static void opcjaTrzy(String mlubp){
        boolean brak = false;
        Element doc = null;
        try{
            doc = Jsoup.connect("https://www.kody-pocztowe.dokladnie.com/").get();
        }catch (Exception e){System.out.println("*Brak połączenia ze stroną*");}
        Element table = doc.getElementById("page-table");
        Elements rows = table.getElementsByTag("tr");
        for(int i = 1; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements wartosci = row.getElementsByTag("td");
            String miasto = wartosci.get(2).text();
            String powiat = wartosci.get(4).text();
            if(miasto.equalsIgnoreCase(mlubp) || powiat.equalsIgnoreCase(mlubp)){
                String kodpocztowy = wartosci.get(0).text();
                System.out.println("Kod pocztowy: "+kodpocztowy+"\n"+
                        "--------------------------------------------------");
                brak = true;
            }
        }
        if(brak != true) System.out.println("--------------------------------------------------" +
                "\n*Brak danych o podanym mieście lub powiecie*\n" +
                "--------------------------------------------------");
    }


}
