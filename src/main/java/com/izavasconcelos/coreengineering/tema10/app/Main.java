package com.izavasconcelos.coreengineering.tema10.app;

import com.izavasconcelos.coreengineering.tema10.dao.ContactDAO;
import com.izavasconcelos.coreengineering.tema10.model.Contact;


public class Main {
    public static void main(String[] args) {
        ContactDAO contactDAO = new ContactDAO();

        Contact contact1 = new Contact( 0,"Axl Rose", "51990099009", "new@mail.com");
        Contact contact2 = new Contact( 0,"Max Cavalera", "51990099009", "new@mail.com");
        Contact contact3 = new Contact( 0,"Ozzy Osbourne", "51990099009", "new@mail.com");
        Contact contact4 = new Contact( 0,"Robert Plant", "51990099009", "new@mail.com");

        contactDAO.addContact(contact1);
        contactDAO.addContact(contact2);
        contactDAO.addContact(contact3);
        contactDAO.addContact(contact4);

        System.out.println("################### Search Contact By Name ##################");
        System.out.println(contactDAO.searchContactByName(contact1.getName()));

        System.out.println("\n###################  Search Contact By Id ###################");
        System.out.println(contactDAO.searchContactById(3));

        System.out.println("\n-------------- List All Contacts Order By Id: ---------------");
        System.out.println(contactDAO.getAllContactsOrder("id"));
        System.out.println("\n************   List All Contacts Order By Name:   ************");
        System.out.println(contactDAO.getAllContactsOrder("name"));

        System.out.println("\n%%%%%%%%%%%%   List All Contacts Order By Id after DELETE: %%%%%%%%%%%%");
        contactDAO.deleteContactById((contactDAO.searchContactByName(contact1.getName())).get().getId());
        contactDAO.deleteContactById((contactDAO.searchContactByName(contact3.getName())).get().getId());
        System.out.println(contactDAO.getAllContactsOrder("id"));
    }
}

