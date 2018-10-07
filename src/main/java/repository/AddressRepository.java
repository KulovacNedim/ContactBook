package main.java.repository;

import main.java.entities.Address;
import main.java.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface AddressRepository {

    public List<Address> getAddressListForUser(User user) throws SQLException;

    public void updateAddressListForUser(User user) throws SQLException;

    public void updateAddress(Address address) throws SQLException;

    public void saveAddress(Address address, Long userId) throws SQLException;

    public void deleteAddressListForUser(List<Address> addressList) throws SQLException;
}
