package utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class RandomUtils {

    public static void main(String[] args) {
        System.out.println(getRandomString(10));
        System.out.println(getRandomInt(10, 100));
        System.out.println(getRandomEmail());

        String[] names = {"a", "b", "c", "d", "e"};
        System.out.println(getRandomItemFromArray(names));
        System.out.println(getRandomItemsFromArray(names));
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomString(int length) {
//        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static String getRandomEmail() {
        return getRandomString(10) + "@qa.guru";
    }

    public static String getRandomItemFromArray(String[] values) {
        int index = getRandomInt(0, values.length - 1);

        return values[index];
    }

    public static String[] getRandomItemsFromArray(String[] values) {
        // Объявляем список, чтобы была возможность вернуть несколько рандомных значений.
        ArrayList<String> listValues = new ArrayList<String>();

        // Получаем рандомное число, которое покажет сколько вернём объектов из массива values.
        int countItems = getRandomInt(1, values.length - 1);

        // Объявляем список в который запишем все объекты, которые нужно вернуть.
        ArrayList<String> listItems = new ArrayList<String>();

        // Заполняем список рандомными индексами объектов, которые будут возвращены.
        for (int i = 0; i < countItems; i++) {
            int idxResident = getRandomInt(0, values.length - 1);

            // Если такой резидент уже есть, то запрашиваем нового.
            while (listItems.contains(values[idxResident]))
                idxResident = getRandomInt(0, values.length - 1);

            listItems.add(values[idxResident]);
        }

        // Возвращаем массив строк, предварительно конвертировав в String[].
        return listItems.toArray(new String[0]);
    }
}