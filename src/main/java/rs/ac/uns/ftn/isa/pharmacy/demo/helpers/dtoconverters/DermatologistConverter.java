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
                    List<Pharmacy> pharmacies = dermatologist.getPharmacies();
                    List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

                    findAllRelevantPharmacies(pharmacies, dtoPharmacies);
                    DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.75, dtoPharmacies);
                    dermatologistsDto.add(dermatologistDto);
                }
        );
        return dermatologistsDto;
    }

    private void findAllRelevantPharmacies(List<Pharmacy> pharmacies, List<PharmacyNameAndAddressDto> dtoPharmacies) {
        pharmacies.forEach(pharmacy -> {
            PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress());
            dtoPharmacies.add(pharmacyNameAndAddressDto);
        });
    }
}
