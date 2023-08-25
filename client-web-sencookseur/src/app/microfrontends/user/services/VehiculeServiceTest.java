package sa.sencookseur.servicevehicule.services;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sa.sencookseur.servicevehicule.dto.VehiculeDto;
import sa.sencookseur.servicevehicule.exceptions.SencookseurExceptions;
import sa.sencookseur.servicevehicule.mappers.VehiculeMapper;
import sa.sencookseur.servicevehicule.repositories.VehiculeRepository;
import sa.sencookseur.servicevehicule.services.impl.VehiculeServiceImpl;

import java.util.*;
import java.util.stream.Collectors;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class VehiculeServiceTest {
    @InjectMocks
    private VehiculeServiceImpl classUnderTest;
    @Mock
    private VehiculeRepository vehiculeRepository;
    private VehiculeDto vehicule;
    private List<VehiculeDto> vehiculeList;
    VehiculeMapper vehiculeMapper = Mappers.getMapper(VehiculeMapper.class);
    private final EasyRandom generator = new EasyRandom();
    @BeforeEach
    void init() {
        vehicule = generator.nextObject(VehiculeDto.class);
        vehicule.setId(1L);
        VehiculeDto vehicule1 = generator.nextObject(VehiculeDto.class);
        vehicule1.setId(2L);
        vehiculeList = Arrays.asList(vehicule, vehicule1);
    }
    @Test
    void givenVehicule_whenCreateVehicule_thenReturnSavedVehicule() {
        when(vehiculeRepository.save(vehiculeMapper.vehiculeDtoToVehicule(vehicule))).thenReturn(vehiculeMapper.vehiculeDtoToVehicule(vehicule));
        VehiculeDto result = classUnderTest.createVehicule(vehicule);
        assertThat(result).isEqualToComparingFieldByField(vehicule);
    }
    @Test
    void givenVehiculeId_whenGetVehiculeById_thenReturnVehicule() {
        when(vehiculeRepository.findById(vehicule.getId())).thenReturn(Optional.ofNullable(vehiculeMapper.vehiculeDtoToVehicule(vehicule)));
        VehiculeDto result = classUnderTest.findVehiculeById(vehicule.getId());
        assertThat(result).isEqualToComparingFieldByField(vehicule);
    }
    @Test
    void givenVehiculeId_whenGetVehiculeById_thenReturnEmpty() {
        when(vehiculeRepository.findById(3L)).thenThrow(SencookseurExceptions.class);
        assertThrows(SencookseurExceptions.class, () -> {
            classUnderTest.findVehiculeById(3L);
        });
    }
    @Test
    void givenListOfVehicule_whenGetAllVehicule_thenReturnVehiculeList() {
        when(vehiculeRepository.findAll()).thenReturn(vehiculeList.stream().map(vehiculeDto -> vehiculeMapper.vehiculeDtoToVehicule(vehiculeDto)).collect(Collectors.toList()));
        List<VehiculeDto> result = classUnderTest.findAllVehicules();
        assertThat(result).isEqualTo(vehiculeList);
    }
    @Test
    void givenVehiculeId_whenDeleteVehiculeById_thenReturnEmpty() {
        doNothing().when(vehiculeRepository).deleteById(vehicule.getId());
        classUnderTest.deleteVehicule(vehicule.getId());
        verify(vehiculeRepository).deleteById(vehicule.getId());
    }
    @Test
    void givenUpdateVehicule_whenUpdateVehicule_thenReturnUpdatedVehicule() {
        when(vehiculeRepository.findById(1L)).thenReturn(Optional.ofNullable(vehiculeMapper.vehiculeDtoToVehicule(vehicule)));
        when(vehiculeRepository.save(vehiculeMapper.vehiculeDtoToVehicule(vehicule))).thenReturn(vehiculeMapper.vehiculeDtoToVehicule(vehicule));
        VehiculeDto result = classUnderTest.updateVehicule(1L, vehicule);
        assertThat(result).isEqualToComparingFieldByField(vehicule);
    }
    @Test
    void givenUpdateVehicule_whenUpdateVehicule_thenReturnEmpty() {
        when(vehiculeRepository.findById(3L)).thenThrow(SencookseurExceptions.class);
        assertThrows(SencookseurExceptions.class, () -> {
            classUnderTest.updateVehicule(3L, vehicule);
        });
    }
}