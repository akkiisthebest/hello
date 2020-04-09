package adi.app.taskmanager;

import android.content.Context;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class ReadFromFile {
    Date currDate;
    public LinkedList<String> read(Context hi) {
        LinkedList<String> retval = new LinkedList<String>();
        try {
            System.out.println("hi");
            String line;
            File file = new File(hi.getFilesDir().toString() + "/File1.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {

                line = scan.nextLine();
                String [] add = line.split(" ");
                int len = add.length - 2;
                System.out.println(len + " len");
                SimpleDateFormat hi3 = new SimpleDateFormat("MM/dd/yyyy");
                String date2 = hi3.format(new Date());
                try {
                    currDate = hi3.parse(date2.trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                long hi2 = 0;
                try {
                    hi2 = ((hi3.parse(add[add.length - 1].trim()).getTime()+20000000)/86400000 - (currDate.getTime())/86400000);
                    System.out.println(hi + "lllllllllllllllllllllllllllllllllllllll");
                } catch(Exception e) {
                    e.printStackTrace();
                }
                String addStr = "";
                for (int i = 0; i < len-1; i++) {
                    addStr = addStr + add[i]+ " ";
                }
                addStr = addStr + add[len - 1];
                if (hi2 < 0) {
                    addStr = addStr + "\n" + -(hi2) + " days past due date\n" + "Due:" + " " + add[add.length - 1];
                } else {
                    addStr = addStr + "\nDays left: " + hi2 + "\n" + "Due:" + " " + add[add.length - 1];
                }
                retval.add(addStr);
            }
            System.out.println(retval);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return retval;
    }
}
