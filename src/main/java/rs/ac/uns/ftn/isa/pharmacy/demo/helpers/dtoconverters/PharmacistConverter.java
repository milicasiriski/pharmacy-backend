package rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;

import java.util.ArrayList;
import java.util.List;

public interface PharmacistConverter {

    default List<PharmacistDto> createResponse(List<Pharmacist> pharmacists) {
        List<PharmacistDto> pharmacistsDto = new ArrayList<>();
        pharmacists.forEach(pharmacist -> {
                    List<Pharmacy> pharmacies = pharmacist.getPharmacies();
                    List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

                    findAllRelevantPharmacies(pharmacies, dtoPharmacies);
                    PharmacistDto pharmacistDto = new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(), 4.75, dtoPharmacies);
                    pharmacistsDto.add(pharmacistDto);
                }
        );
        return pharmacistsDto;
    }

    private void findAllRelevantPharmacies(List<Pharmacy> pharmacies, List<PharmacyNameAndAddressDto> dtoPharmacies) {
        pharmacies.forEach(pharmacy -> {
            PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress());
            dtoPharmacies.add(pharmacyNameAndAddressDto);
        });
    }
}
