package rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;

import java.util.ArrayList;
import java.util.List;

public interface DermatologistConverter {

    default List<DermatologistDto> createResponse(List<Dermatologist> dermatologists) {
        List<DermatologistDto> dermatologistsDto = new ArrayList<>();
        dermatologists.forEach(dermatologist -> {
                    Iterable<Pharmacy> pharmacies = dermatologist.getPharmacies().keySet();
                    DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.75, dermatologist.getId(), extractPharmacyInfo(pharmacies));
                    dermatologistsDto.add(dermatologistDto);
                }
        );
        return dermatologistsDto;
    }

    private List<PharmacyNameAndAddressDto> extractPharmacyInfo(Iterable<Pharmacy> pharmacies) {
        List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

        pharmacies.forEach(pharmacy -> {
            PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress().getStreet());
            dtoPharmacies.add(pharmacyNameAndAddressDto);
        });
        return dtoPharmacies;
    }
}
