package application.commands.common.address;

import base.utils.Mapper;
import domain.common.Address;
import domain.common.AddressBuilder;

public class AddressCommandDataMapper implements Mapper<AddressCommandData, Address> {
    
    @Override
    public Address map(AddressCommandData from) {
        AddressBuilder addressBuilder = AddressBuilder.createBuilder();
        from.getBuilding().ifPresent(addressBuilder::withBuilding);
        from.getCity().ifPresent(addressBuilder::withCity);
        from.getCountryRegion().ifPresent(addressBuilder::withCountryRegion);
        from.getPostalCode().ifPresent(addressBuilder::withPostalCode);
        from.getStateProvince().ifPresent(addressBuilder::withStateProvince);
        from.getStreet().ifPresent(addressBuilder::withStreet);
        return addressBuilder.build();
    }
    
}
