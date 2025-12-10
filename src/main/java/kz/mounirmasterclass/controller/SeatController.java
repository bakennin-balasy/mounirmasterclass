package kz.mounirmasterclass.controller;

import kz.mounirmasterclass.model.dto.SeatCreateDto;
import kz.mounirmasterclass.model.entity.Seat;
import kz.mounirmasterclass.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/all")
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/free")
    public List<Seat> getFreeSeats() {
        return seatService.getAllFreeSeats();
    }

    @PostMapping("/add")
    public Seat addSeat(@RequestBody SeatCreateDto dto) {
        return seatService.addNewSeat(dto);
    }

    @PostMapping("/book/{id}")
    public Seat book(@PathVariable Long id) {
        return seatService.bookSeat(id);
    }

    @PostMapping("/cancel/{id}")
    public Seat cancel(@PathVariable Long id) {
        return seatService.cancelBooking(id);
    }
}

