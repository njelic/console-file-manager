package filemanager;

import java.util.Scanner;

public class korisnik {

    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Unesite lokaciju sa kojom zelite da upravljate u formatu \"C:\\ProgramFiles\": ");

        String str = scan.nextLine();
        logika l = new logika(str);

        while (!l.lista.isDirectory()) {

            System.out.println("Molimo vas da unesete ispravnu lokaciju sa kojom zelite da upravljate u formatu \"C:\\ProgramFiles\": ");

            str = scan.nextLine();
            l = new logika(str);

            if (l.lista.isDirectory()) {

                break;
            }

        }

        System.out.println("Komande koje Vam ovaj program dozvoljava su:\nLIST - pregled foldera, INFO - prikaz informacija o folderima i fajlovima \nCREATE_DIR - kreiranje direktorijuma, RENAME - promena imena, COPY - kopiranje, \nMOVE - premestanje, DELETE - brisanje, EXIT - izlaz iz aplikacije \nUnesite zeljenu komandu: ");

        while (scan.hasNext()) {

            if (scan.hasNext("LIST")) {

                l.LIST();
                scan.next();
            } else if (scan.hasNext("INFO")) {

                l.INFO();
                scan.next();
            } else if (scan.hasNext("CREATE_DIR")) {

                l.CREATE_DIR();
                scan.next();
            } else if (scan.hasNext("DELETE")) {

                l.DELETE();
                scan.next();
            } else if (scan.hasNext("RENAME")) {

                l.RENAME();
                scan.next();
            } else if (scan.hasNext("COPY")) {

                l.COPY();
                scan.next();
            } else if (scan.hasNext("MOVE")) {

                l.MOVE();
                scan.next();
            } else if (scan.hasNext("EXIT")) {

                l.EXIT();
                break;
            } else {

                System.out.println("Molimo Vas unesite neku od ponudjenih komandi ili EXIT ukoliko zelite da zavrsite sa programom.");
                scan.next();
            }

        }

    }

}
