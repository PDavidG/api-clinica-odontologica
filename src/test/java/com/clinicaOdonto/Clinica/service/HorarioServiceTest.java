package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.dto.HorarioRequestDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.HorarioMapper;
import com.clinicaOdonto.Clinica.repository.HorarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorarioServiceTest {

    @Mock
    private HorarioRepository horarioRepository;

    @Mock
    private HorarioMapper horarioMapper;

    @InjectMocks
    private HorarioService horarioService;

    private Horario horario;
    private HorarioRequestDto horarioRequestDto;

    @BeforeEach
    void setUp() {

        horario = new Horario();
        horario.setIdHorario(10L);
        horario.setHorarioInicio("12:00");
        horario.setHorarioFin("18:00");

        horarioRequestDto = new HorarioRequestDto();
        horarioRequestDto.setHorarioInicio("01:00");
        horarioRequestDto.setHorarioFin("20:00");
    }

    @Test
    @DisplayName("Debe retornar un horario cuando el ID existe")
    void shouldReturnScheduleWhenIdExists() {

        // Arrange
        when(horarioRepository.findById(anyLong())).thenReturn(Optional.of(horario));

        // Action
        Horario foundHora = horarioService.findById(10L);

        // Assert
        assertNotNull(foundHora);
        assertEquals(horario.getIdHorario(), foundHora.getIdHorario());
        verify(horarioRepository, times(1)).findById(10L);
    }

    @Test
    @DisplayName("Debe lanzar un ResourceNotFoundException cuando el Id no existe")
    void shouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        when(horarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class, () -> {
                    horarioService.findById(99L);
                }
        );

        assertEquals("No se encontro un horario con el id: 99", thrown.getMessage());
        verify(horarioRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar un Horario exitosamente")
    void shouldScheduleSuccessfully() {

        Horario horaWithoutId = new Horario();
        horaWithoutId.setHorarioInicio(horarioRequestDto.getHorarioInicio());
        horaWithoutId.setHorarioFin(horarioRequestDto.getHorarioFin());

        when(horarioMapper.toEntity(any(HorarioRequestDto.class))).thenReturn(horaWithoutId);

        when(horarioRepository.save(any(Horario.class))).thenAnswer(
                invocationOnMock -> {
                    Horario savedHorario = invocationOnMock.getArgument(0);
                    savedHorario.setIdHorario(1L);
                    return savedHorario;
                });

        Horario savedHorario = horarioService.save(horarioRequestDto);

        assertNotNull(savedHorario);
        assertEquals(1L, savedHorario.getIdHorario());
        assertEquals(horarioRequestDto.getHorarioInicio(), savedHorario.getHorarioInicio());
        assertEquals(horarioRequestDto.getHorarioFin(), savedHorario.getHorarioFin());

        verify(horarioMapper, times(1)).toEntity(horarioRequestDto);
        verify(horarioRepository, times(1)).save(any(Horario.class));
    }

    @Test
    @DisplayName("Debe retornar una lista de horarios exitosamenete")
    void shouldReturnScheduleListSuccessfully() {
        List<Horario> listaHorarios = Arrays.asList(new Horario(), new Horario());

        when(horarioRepository.findAll()).thenReturn(listaHorarios);

        List<Horario> findHorarios = horarioService.findAll();

        assertNotNull(findHorarios);
        assertEquals(2, listaHorarios.size());

        verify(horarioRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe eliminar un horario cuando el ID existe")
    void shouldDeleteScheduleWhenIdExists() {

        final Long horarioIdToDelete = 10L;

        when(horarioRepository.findById(anyLong())).thenReturn(Optional.of(horario));

        doNothing().when(horarioRepository).deleteById(anyLong());

        horarioService.deleteById(horarioIdToDelete);

        verify(horarioRepository, times(1)).deleteById(horarioIdToDelete);
    }
}