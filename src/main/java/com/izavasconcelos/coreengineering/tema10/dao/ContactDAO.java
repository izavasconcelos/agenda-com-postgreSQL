package com.izavasconcelos.coreengineering.tema10.dao;

import com.izavasconcelos.coreengineering.tema10.connection.ConnectionDatabase;
import com.izavasconcelos.coreengineering.tema10.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class ContactDAO {
    Connection connection = null;

    public Optional<Contact> addContact(Contact contact) {
        Optional<Contact> duplicateName = searchContactByName(contact.getName());
        connection = ConnectionDatabase.getConnect();

        String query = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?);";
        if(!duplicateName.isPresent()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1 ,contact.getName());
                preparedStatement.setString(2 ,contact.getPhone());
                preparedStatement.setString(3 ,contact.getEmail());
                int insertOk = preparedStatement.executeUpdate();
                if(insertOk !=0 ) {
                    return searchContactByName(contact.getName());
                }
                return Optional.empty();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return Optional.empty();
    }

    public Optional<Contact> searchContactByName(String nameToFind) {
        String query = "SELECT * FROM contacts WHERE name= ?";
        List<Contact> contactList = new ArrayList<>();
        connection = ConnectionDatabase.getConnect();

        if(nameToFind != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1,nameToFind);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()){
                    return Optional.empty();
                }
                Contact contact = new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"));
                contactList.add(contact);
            }catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return contactList.stream().findFirst();
    }

    public Optional<Contact> searchContactById(int idToFind) {
        String query = "SELECT * FROM contacts WHERE id= ?";
        List<Contact> contactList = new ArrayList<>();
        connection = ConnectionDatabase.getConnect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,idToFind);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                return Optional.empty();
            }
            Contact contact = new Contact(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("phone"),
                    resultSet.getString("email"));
            contactList.add(contact);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contactList.stream().findFirst();
    }

    public boolean deleteContactById(int idToRemove) {
        String query = "DELETE FROM contacts WHERE id= ?";
        connection = ConnectionDatabase.getConnect();

        if(idToRemove != 0) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setInt(1,idToRemove);
                int deleteOk = preparedStatement.executeUpdate();
                if(deleteOk != 0){
                    return true;
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public List<Contact> getAllContactsOrder(String order) {
        connection = ConnectionDatabase.getConnect();
        List<Contact> contactList = new ArrayList<>();
        String query = "SELECT * FROM contacts";

        if(order.equals("id")){
            query = query.concat(" ORDER BY id ASC");
        }else if(order.equals("name")) {
            query = query.concat(" ORDER BY name ASC");
        }
        try (PreparedStatement pst = connection.prepareStatement(query);
             ResultSet resultSet = pst.executeQuery()) {

            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"));
                contactList.add(contact);
            }

            return contactList;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

}

