package kz.mounirmasterclass.repository;

import kz.mounirmasterclass.model.entity.Seat;
import kz.mounirmasterclass.model.enums.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByStatus(SeatStatus status);

    boolean existsByRowAndSeat(int rowNumber, int seatNumber);


}

