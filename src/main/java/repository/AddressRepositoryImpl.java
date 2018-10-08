package main.java.repository;

import main.java.dbConnectionMenager.ConnectionMenager;
import main.java.entities.Address;
import main.java.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressRepositoryImpl implements AddressRepository {

    private Connection connection = ConnectionMenager.getConnectionToDatabase();
    private TypeRepositoryImpl typeRepo = new TypeRepositoryImpl();

    @Override
    public List<Address> getAddressListForUser(User user) throws SQLException {

        List<Address> addresses = new ArrayList<>();

        String query = "SELECT * FROM addresses WHERE user_id = ?";

        ResultSet rs = null;

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, user.getId());

            rs = statement.executeQuery();

            while (rs.next()) {
                Address address = new Address(rs.getLong("id"), rs.getString("address_description"),
                        rs.getString("state"), rs.getString("city"), rs.getString("street"),
                        rs.getString("zip_code"), typeRepo.getTypeById(rs.getLong("type")));
                addresses.add(address);
            }
        }

        return addresses;
    }

    @Override
    public void updateAddressListForUser(User user) throws SQLException {

        for (int i = 0; i < user.getAddressList().size(); i++) {

            Address address = user.getAddressList().get(i);

            if (address.getId() != 0) {
                updateAddress(address);
            } else {
                saveAddress(address, user.getId());
            }
        }
    }

    @Override
    public void updateAddress(Address address) throws SQLException {

        String query = "UPDATE addresses SET address_description = ?, state = ?, city = ?, street = ?, zip_code = ?, " +
                "type = ? WHERE id = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setString(1, address.getAddressDescription());
            statement.setString(2, address.getState());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getStreet());
            statement.setString(5, address.getZipCode());
            statement.setLong(6, address.getType().getId());
            statement.setLong(7,address.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void saveAddress(Address address, Long userId) throws SQLException {

        String query = "INSERT INTO addresses(user_id, address_description, state, city, street, zip_code, type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setLong(1, userId);
            statement.setString(2, address.getAddressDescription());
            statement.setString(3, address.getState());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getStreet());
            statement.setString(6, address.getZipCode());
            statement.setLong(7, address.getType().getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteAddressListForUser(List<Address> addressList) throws SQLException {

        if (addressList != null) {

            for (int i = 0; i < addressList.size(); i++) {

                Address address = addressList.get(i);

                String query = "DELETE FROM addresses WHERE id = ?";

                try (PreparedStatement statement = connection.prepareStatement(query);) {

                    statement.setLong(1, address.getId());

                    statement.executeUpdate();
                }
            }
        }
    }
}
