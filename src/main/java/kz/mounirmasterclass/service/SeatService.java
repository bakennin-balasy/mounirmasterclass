package kz.mounirmasterclass.service;

import jakarta.transaction.Transactional;
import kz.mounirmasterclass.model.dto.SeatCreateDto;
import kz.mounirmasterclass.model.entity.Seat;
import kz.mounirmasterclass.model.enums.SeatStatus;
import kz.mounirmasterclass.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public List<Seat> getAllFreeSeats() {
        return seatRepository.findByStatus(SeatStatus.FREE);
    }

    public Seat addNewSeat(SeatCreateDto dto) {

        if (seatRepository.existsByRowAndSeat(dto.getRowNumber(), dto.getSeatNumber())) {
            throw new RuntimeException("Это место уже существует!");
        }

        Seat seat = new Seat();
        seat.setRow(dto.getRowNumber());
        seat.setSeat(dto.getSeatNumber());
        seat.setSection(dto.getSection());
        seat.setPrice(dto.getPrice());
        seat.setStatus(SeatStatus.FREE);
        seat.setCategory(dto.getCategory());

        return seatRepository.save(seat);
    }

    @Transactional
    public Seat bookSeat(Long seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Место не найдено"));

        if (seat.getStatus().equals(SeatStatus.BOOKED)) {
            throw new RuntimeException("Это место уже забронировано!");
        }

        seat.setStatus(SeatStatus.BOOKED);
        return seatRepository.save(seat);
    }

    @Transactional
    public Seat cancelBooking(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Место не найдено"));

        if (seat.getStatus() != SeatStatus.BOOKED) {
            throw new RuntimeException("Место не забронировано!");
        }

        seat.setStatus(SeatStatus.FREE);
        return seatRepository.save(seat);
    }
}

