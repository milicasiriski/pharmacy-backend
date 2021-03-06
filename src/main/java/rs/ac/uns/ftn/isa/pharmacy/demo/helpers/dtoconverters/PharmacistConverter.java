package rs.ac.uns.ftn.isa.pharmacy.demo.helpers.dtoconverters;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;

import java.util.ArrayList;
import java.util.List;

public interface PharmacistConverter {

    default List<PharmacistDto> createResponse(Iterable<Pharmacist> pharmacists) {
        List<PharmacistDto> pharmacistsDto = new ArrayList<>();
        pharmacists.forEach(pharmacist -> {
            List<Pharmacy> pharmacies;
                    if (pharmacist.getPharmacy() == null) {
                        pharmacies = new ArrayList<>();
                    } else {
                        pharmacies = List.of(pharmacist.getPharmacy());// TODO: refactor to a field NOSONAR
                    }
                    PharmacistDto pharmacistDto = new PharmacistDto(pharmacist.getName(), pharmacist.getSurname(), 4.75, extractPharmacyInfo(pharmacies));
                    pharmacistDto.setId(pharmacist.getId());
                    pharmacistsDto.add(pharmacistDto);
                }
        );
        return pharmacistsDto;
    }

    private List<PharmacyNameAndAddressDto> extractPharmacyInfo(List<Pharmacy> pharmacies) {
        List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();

        pharmacies.forEach(pharmacy -> {
            PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress().getStreet(), pharmacy.getId());
            dtoPharmacies.add(pharmacyNameAndAddressDto);
        });
        return dtoPharmacies;
    }
}
