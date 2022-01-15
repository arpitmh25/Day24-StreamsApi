package com.bridgelabz.stream;

import java.util.*;
import java.util.stream.Collectors;

class FirstNameComp implements Comparator<PersonInfo> {

    @Override
    public int compare(PersonInfo p1, PersonInfo p2) {

        return p1.firstName.compareTo(p2.firstName);
    }
}

class ZipCodeSort implements Comparator<PersonInfo> {

    @Override
    public int compare(PersonInfo p1, PersonInfo p2) {

        if (p1.zipCode == p2.zipCode) {
            return 0;
        } else if (p1.zipCode < p2.zipCode) {
            return 1;
        } else {
            return -1;
        }
    }
}

public class AddressBook {

    List<PersonInfo> list = new ArrayList<>();
    // as we use hashSet here multiple entries are not allowed.
    Scanner scanner;

    public void addContact() {
        scanner = new Scanner(System.in);

        System.out.print("Enter first name :: ");
        String firstName = scanner.next();

        System.out.print("Enter last name :: ");
        String lastName = scanner.next();

        System.out.print("Enter your address :: ");
        String address = scanner.next();

        System.out.print("Enter your city :: ");
        String city = scanner.next();

        System.out.print("Enter zip code :: ");
        int zipCode = scanner.nextInt();

        System.out.print("Enter phone number :: ");
        String phoneNumber = scanner.next();

        System.out.print("Enter email :: ");
        String email = scanner.next();

        list.add(new PersonInfo(firstName, lastName, address, city, zipCode, phoneNumber, email));
    }

    private void editContact() {

        System.out.print("Enter person name to edit person :: ");

        String editPersonName = scanner.next();

        PersonInfo personInfo = getPerson(editPersonName);

        System.out.print("Enter what you want to edit :: 1-first name\n2-lastname\n3-address\n4-city\n5-zip code\n6-phone number\n7-email\n::-> ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                editFirstName(personInfo);
                break;
            case 2:
                editLastName(personInfo);
                break;
            case 3:
                editAddress(personInfo);
                break;
            case 4:
                editCity(personInfo);
                break;
            case 5:
                editZipCode(personInfo);
                break;
            case 6:
                editPhoneNumber(personInfo);
                break;
            case 7:
                editEmail(personInfo);
                break;
            default:
                System.out.println("Enter valid choice :");
        }
    }

    private void editEmail(PersonInfo personInfo) {

        scanner = new Scanner(System.in);

        System.out.print("Enter new email here :-> ");

        personInfo.email = scanner.next();
    }

    private void editPhoneNumber(PersonInfo personInfo) {

        scanner = new Scanner(System.in);

        System.out.print("Enter new phone number here :-> ");

        personInfo.phoneNumber = scanner.next();

        System.out.println("Done");
    }

    private void editZipCode(PersonInfo personInfo) {

        scanner = new Scanner(System.in);

        System.out.print("Enter new zip code here :-> ");

        personInfo.zipCode = scanner.nextInt();

        System.out.println("Done");
    }

    private void editCity(PersonInfo personInfo) {

        scanner = new Scanner(System.in);

        System.out.print("Enter new city name :: ");

        personInfo.city = scanner.next();

        System.out.println("Done");

    }

    private void editAddress(PersonInfo personInfo) {

        scanner = new Scanner(System.in);

        System.out.print("Enter new address here :-> ");

        personInfo.address = scanner.next();

        System.out.println("Done");
    }

    private void editLastName(PersonInfo personInfo) {

        scanner = new Scanner(System.in);

        System.out.print("Enter new last name here :->");

        personInfo.lastName = scanner.next();

        System.out.println("Done");
    }

    private void editFirstName(PersonInfo personInfo) {

        scanner = new Scanner(System.in);

        System.out.print("Enter new First name here  :->");

        personInfo.firstName = scanner.next();

        System.out.println("Done");

    }

    private PersonInfo getPerson(String editPersonName) {

        Iterator iterator = list.iterator();

        if (iterator.hasNext()) {

            for (int i = 0; i < list.size(); i++) {

                PersonInfo personInfo = (PersonInfo) iterator.next();

                if (personInfo.firstName.equalsIgnoreCase(editPersonName)) {

                    return personInfo;
                }
            }
        }
        return null;
    }

    private void deletePerson() {

        scanner = new Scanner(System.in);

        System.out.print("Enter name of person to delete its contact :-> ");

        String personName = scanner.next();

        PersonInfo personInfo = getPerson(personName);

        if (personInfo == null) {

            System.out.println(":: sorry No person found for given name ::");

        } else {

            list.remove(personInfo);
        }

    }

    private void searchByCity() {

        int count = 0;

        scanner = new Scanner(System.in);

        System.out.print("enter city :-> ");

        String city = scanner.next();

        for (PersonInfo personInfo : list) {

            if (personInfo.city.equalsIgnoreCase(city)) {

                System.out.println(personInfo);

                count++;
            }
        }
        System.out.println("Total number of person from entered city is :-> " + count);
    }

    private void sortByNameUsingCollections() {

        Collections.sort(list, new FirstNameComp());

        System.out.println("The sorted list by name of person is as follows");

        for (PersonInfo personInfo : list) {

            System.out.println(personInfo);
        }
    }

    private void sortByNameUsingStreamAPI() {

        ArrayList<PersonInfo> sortedByName = (ArrayList<PersonInfo>) list.stream().sorted(new FirstNameComp()).collect(Collectors.toList());

        for (PersonInfo personInfo : sortedByName) {

            System.out.println(personInfo);
        }
    }

    public void sortedByZipCodeUsingCollection() {

        Collections.sort(list, new ZipCodeSort());

        for (PersonInfo personInfo : list) {

            System.out.println(personInfo);
        }

    }

    public void sortByZipCodeUsingStreamAPI() {

        ArrayList<PersonInfo> sortByZipCode = (ArrayList<PersonInfo>) list.stream().sorted(new ZipCodeSort()).collect(Collectors.toList());

        for (PersonInfo personInfo : sortByZipCode) {

            System.out.println(personInfo);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AddressBook addressBook = new AddressBook();

        int choice = 0;

        while (choice != 10) {

            System.out.print("please choose from below\n1-add contact\n2-edit contact\n3-delete person\n4-search person\n5-sort by name\n10-exit \n:-> ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addressBook.addContact();
                    break;
                case 2:
                    addressBook.editContact();
                    break;
                case 3:
                    addressBook.deletePerson();
                    break;
                case 4:
                    addressBook.searchByCity();
                    break;
                case 5:
                    addressBook.sortByNameUsingStreamAPI();
                    break;
                case 10:
                    break;

            }
        }
    }
}