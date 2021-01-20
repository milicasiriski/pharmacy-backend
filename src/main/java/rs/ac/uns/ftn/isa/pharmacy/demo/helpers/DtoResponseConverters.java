package rs.ac.uns.ftn.isa.pharmacy.demo.helpers;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.Dermatologist;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.Pharmacy;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.DermatologistDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.PharmacyNameAndAddressDto;

import java.util.ArrayList;
import java.util.List;

public abstract class DtoResponseConverters {

    public static void createDermatologistsResponseDtoFromDermatologists(List<Dermatologist> dermatologists, List<DermatologistDto> dermatologistsDto) {
        dermatologists.forEach(dermatologist -> {
                    List<Pharmacy> pharmacies = dermatologist.getPharmacies();
                    List<PharmacyNameAndAddressDto> dtoPharmacies = new ArrayList<>();
                    pharmacies.forEach(pharmacy -> {
                        PharmacyNameAndAddressDto pharmacyNameAndAddressDto = new PharmacyNameAndAddressDto(pharmacy.getName(), pharmacy.getAddress());
                        dtoPharmacies.add(pharmacyNameAndAddressDto);
                    });

                    DermatologistDto dermatologistDto = new DermatologistDto(dermatologist.getName(), dermatologist.getSurname(), 4.75, dtoPharmacies);
                    dermatologistsDto.add(dermatologistDto);
                }
        );
    }

}
