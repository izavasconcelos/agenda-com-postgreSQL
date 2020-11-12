package com.izavasconcelos.coreengineering.tema10.dao;

import com.izavasconcelos.coreengineering.tema10.model.Contact;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ContactDAOTest {
    ContactDAO contactDAO = new ContactDAO();

    Contact contato1 = new Contact(0,"Gabriel", "51992299223", "gabriel@ilegra.com");
    Contact contato2 = new Contact(0,"NewContact", "5199776655", "novo@ilegra.com");

    @Test
    public void addContactTest() {
        Optional<Contact> addContact = contactDAO.addContact(contato2);
        assertTrue(addContact.isPresent());

        contactDAO.deleteContactById(contato2.getId());
    }

    @Test
    public void addExistingContactTest() {
        contactDAO.addContact(contato1);
        assertEquals(8, contactDAO.getAllContactsOrder("id").size());

        Optional<Contact> addContact = contactDAO.addContact(contato1);
        assertFalse(addContact.isPresent());
        assertEquals(8, contactDAO.getAllContactsOrder("id").size());
    }

    @Test
    public void getAllContactsOrderByIdTest() {
        List<Contact> allContactsById = contactDAO.getAllContactsOrder("id");
        assertEquals(8, allContactsById.size());
    }

    @Test
    public void getAllContactsOrderByNameTest() {
        List<Contact> allContactsOrderByName = contactDAO.getAllContactsOrder("name");
        assertEquals(9, allContactsOrderByName.size());
    }

    @Test
    public void deleteContactByNameTest() {
        contactDAO.addContact(contato2);

        List<Contact> listAllContacts = contactDAO.getAllContactsOrder("id");
        assertEquals(9, listAllContacts.size());

        Optional<Contact> getIdContact2 = contactDAO.searchContactByName(contato2.getName());
        assertTrue(contactDAO.deleteContactById(getIdContact2.get().getId()));

        listAllContacts = contactDAO.getAllContactsOrder("id");
        assertEquals(8, listAllContacts.size());
    }

    @Test
    public void deleteContactByNullIdTest() {
        List<Contact> listAllContacts = contactDAO.getAllContactsOrder("id");
        assertEquals(8, listAllContacts.size());

        assertFalse(contactDAO.deleteContactById(0));

        listAllContacts = contactDAO.getAllContactsOrder("id");
        assertEquals(8, listAllContacts.size());
    }

    @Test
    public void searchContactByNameTest() {
        String nameContact = "Isadora";
        Optional<Contact> searchContact = contactDAO.searchContactByName(nameContact);
        assertTrue(searchContact.isPresent());
        assertThat(searchContact.get().getName(), is(nameContact));

    }
    @Test
    public void searchContactByNullNameTest() {
        Optional<Contact> searchContact = contactDAO.searchContactByName("invalidname");
        assertFalse(searchContact.isPresent());

        searchContact = contactDAO.searchContactByName(null);
        assertFalse(searchContact.isPresent());
    }
    @Test
    public void searchContactByIdTest() {
        Optional<Contact> searchContact = contactDAO.searchContactById(5);
        assertTrue(searchContact.isPresent());

        assertThat(searchContact.get().getId(), is(5));

    }
}

