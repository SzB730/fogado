import model.Teacher;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        exercise1();
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        exercise6();
    }

    static private List<Teacher> teachers = new ArrayList<>();

    static void exercise1() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("fogado.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                teachers.add(getNewTeacher(line));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Teacher getNewTeacher (String stringData) {
        String[] oneLineData = stringData.split(" ");
        String lastName = oneLineData[0];
        String firstName = oneLineData[1];
        String bookedTime = oneLineData[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH:mm");
        LocalDateTime exactTimeOfBooking = LocalDateTime.parse(oneLineData[3], formatter);
        return new Teacher(lastName, firstName, bookedTime, exactTimeOfBooking);
    }

    static void exercise2() {
        System.out.println("2. feladat");
        System.out.println("Foglalások száma: "+ teachers.size());
    }

    static Scanner tempReader = new Scanner(System.in);

    static void exercise3() {
        System.out.println("3. feladat");
        System.out.print("Adjon meg egy nevet: ");
        String inputName = tempReader.nextLine();
        int counter = 0;
        for (Teacher teacher : teachers) {
            if (teacher.getFullName().equals(inputName)) {
                counter++;
            }
        }
        System.out.println(inputName+" néven "+counter+" időpontfoglalás van.");
    }

    static void exercise4() {
        List<String> tempNames = new ArrayList<>();
        System.out.println("4. feladat");
        System.out.print("Adjon meg egy érvényes időpontot (pl. 17:10): ");
        String inputTime = tempReader.nextLine();
        for (Teacher teacher : teachers) {
            if (teacher.getBookedTime().equals(inputTime)) {
                tempNames.add(teacher.getFullName());
            }
        }
        //tempNames.sort(String::compareTo);
        Collections.sort(tempNames, (t1, t2) -> t1.compareTo(t2));
        try {
            String fileName = inputTime.replaceAll(":","");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"));
            //BufferedWriter writer = new BufferedWriter(new FileWriter(inputTime.split(":")[0]+inputTime.split(":")[1]+".txt"));
            for (String tempName : tempNames) {
                writer.write(tempName);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void exercise5() {
        System.out.println("5. feladat");
        LocalDateTime tempDate = teachers.get(0).getExactTimeOfBooking();
        int minIndex = 0;
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getExactTimeOfBooking().isBefore(tempDate)) {
                minIndex = i;
            }
        }
        System.out.println("Tanár neve: "+teachers.get(minIndex).getFullName());
        System.out.println("Foglalt időpont: "+teachers.get(minIndex).getBookedTime());
        System.out.println("Foglalás ideje: "+teachers.get(minIndex).getNormalDateTime());
    }

    static void exercise6() {
        System.out.println("6. feladat");
        int[] tempFreeTimes = new int[12];
        List<String> times = new ArrayList<>();
        for (int hour = 16; hour < 18; hour++) {
            for (int minute = 0; minute < 60; minute += 10) {
                times.add(String.format("%d:%02d", hour, minute));
            }
        }
        for (Teacher teacher : teachers) {
            if (times.contains(teacher.getBookedTime()) && teacher.getFullName().equals("Barna Eszter")) {
                tempFreeTimes[times.indexOf(teacher.getBookedTime())] = 1;
            }
        }
        int i = tempFreeTimes.length-1;
        while (tempFreeTimes[i] == 0) {
            i--;
        }
        int leaveIndex = i+1;
        for (i = 0; i < tempFreeTimes.length; i++) {
            if (tempFreeTimes[i] == 0) {
                System.out.println(times.get(i));
            }
        }
        System.out.println("Barna Eszter legkorábban távozhat: "+times.get(leaveIndex));
    }
}