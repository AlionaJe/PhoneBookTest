// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что в во входной структуре будут повторяющиеся 
// имена с разными телефонами, их необходимо считать, как одного человека с разными 
// телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.

import java.util.*;

class PhoneBook {
    private static HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public void add(String name, Integer phoneNum) {
        // Получаем список номеров телефона для данного имени
        ArrayList<Integer> phoneNumbers = phoneBook.getOrDefault(name, new ArrayList<>());

        phoneNumbers.add(phoneNum);
        phoneBook.put(name, phoneNumbers);
    }

    public ArrayList<Integer> find(String name) {
        // Возвращаем список номеров телефона для данного имени
        return phoneBook.getOrDefault(name, new ArrayList<>());
    }

    // Метод для получения всей телефонной книги
    public static HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return phoneBook;
    }

    // Метод для получения отсортированного списка записей
    public static List<Map.Entry<String, ArrayList<Integer>>> getSortedPhoneBookEntries() {
        // Преобразуем в список и сортируем по убыванию
        List<Map.Entry<String, ArrayList<Integer>>> entryList = new ArrayList<>(phoneBook.entrySet());
        entryList.sort((entry1, entry2) -> compare(entry2, entry1));
        return entryList;
    }

    // Метод для сравнения записей по числу телефонов
    private static int compare(Map.Entry<String, ArrayList<Integer>> entry1, Map.Entry<String, ArrayList<Integer>> entry2) {
        return Integer.compare(entry2.getValue().size(), entry1.getValue().size());
    }
}

public class PhoneBookTest {
    public static void main(String[] args) {
        String name1;
        String name2;
        int phone1;
        int phone2;

        if (args.length == 0) {
            name1 = "Ivanov";
            name2 = "Petrov";
            phone1 = 123456;
            phone2 = 654321;
        } else {
            name1 = args[0];
            name2 = args[1];
            phone1 = Integer.parseInt(args[2]);
            phone2 = Integer.parseInt(args[3]);
        }

        PhoneBook myPhoneBook = new PhoneBook();
        myPhoneBook.add(name1, phone1);
        myPhoneBook.add(name1, phone2);
        myPhoneBook.add(name2, phone2);

        System.out.println("Phones for " + name1 + ": " + myPhoneBook.find(name1));
        System.out.println("Phone book: " + PhoneBook.getPhoneBook());
        System.out.println("Phones for 'Me': " + myPhoneBook.find("Me"));
        System.out.println("Sorted phone book: " + PhoneBook.getSortedPhoneBookEntries());
    }
}
