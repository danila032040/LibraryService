package application.validators.common;

import java.util.Optional;
import java.util.stream.Stream;

import application.commands.common.address.AddressCommandData;
import base.result.ErrorResult;
import base.result.ValidationResult;
import base.utils.Validator;

public class AddressCommandDataValidator implements Validator<AddressCommandData> {
    
    @Override
    public ValidationResult validate(AddressCommandData address) {
        long providedFieldsCount = Stream
                .of(
                        address.getBuilding(),
                        address.getCity(),
                        address.getCountryRegion(),
                        address.getPostalCode(),
                        address.getStateProvince(),
                        address.getStreet())
                .filter(Optional::isPresent)
                .count();
        
        return ValidationResult
                .create()
                .withErrorIf(
                        () -> providedFieldsCount == 0,
                        ErrorResult.from("At least one field of address must be present"));
    }
    
}
