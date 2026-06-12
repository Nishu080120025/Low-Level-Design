package org.example;

import org.example.entities.*;
import org.example.entities.enums.PaymentMethod;
import org.example.entities.enums.VehicleType;
import org.example.factory.ParkingSpotFactory;
import org.example.factory.PaymentProcessorFactory;
import org.example.manager.ParkingSpotManager;
import org.example.repository.ParkingRepository;
import org.example.repository.ParkingRepositoryImpl;
import org.example.repository.TicketRepository;
import org.example.repository.TicketRepositoryImpl;
import org.example.service.TicketService;
import org.example.service.TicketServiceImpl;
import org.example.strategy.CreditCardPaymentProcessor;
import org.example.strategy.PaymentProcessor;
import org.example.strategy.PricingStrategy;
import org.example.strategy.TimeBasedPricingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLotMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot System!");
        System.out.println("------------------------------");
        System.out.println("Initializing the parking lot...");
        System.out.println("------------------------------");

        ParkingRepository parkingRepository = new ParkingRepositoryImpl();
        TicketRepository ticketRepository = new TicketRepositoryImpl();
        TicketService ticketService = new TicketServiceImpl(ticketRepository);
        PricingStrategy pricingStrategy = new TimeBasedPricingStrategy();

        System.out.println("Parking lot initialized successfully!");
        System.out.println("------------------------------");
        System.out.println("Now initialising ParkingSpots and vehicle and all other entities.....");
        System.out.println("------------------------------");

        ParkingSpot carSpot1 = ParkingSpotFactory.createParkingSpot(VehicleType.CAR, "C1", "F1");
        ParkingSpot carSpot2 = ParkingSpotFactory.createParkingSpot(VehicleType.CAR, "C2", "F1");
        ParkingSpot bikeSpot1 = ParkingSpotFactory.createParkingSpot(VehicleType.MOTORCYCLE, "M1", "F1");
        parkingRepository.addParkingSpot(carSpot1.getSpotId(), carSpot1);
        parkingRepository.addParkingSpot(carSpot2.getSpotId(), carSpot2);
        parkingRepository.addParkingSpot(bikeSpot1.getSpotId(), bikeSpot1);
        Vehicle car1 = new Vehicle("Car_V1", VehicleType.CAR);
        Vehicle car2 = new Vehicle("Car_V2", VehicleType.CAR);
        Vehicle bike1 = new Vehicle("Bike_V1", VehicleType.MOTORCYCLE);
        HashMap<VehicleType, List<ParkingSpot>> floorParkingSpots = new HashMap<>();
        Floor floor1 = new Floor("F1", floorParkingSpots);
        floor1.addParkingSpot(VehicleType.CAR, carSpot1);
        floor1.addParkingSpot(VehicleType.CAR, carSpot2);
        floor1.addParkingSpot(VehicleType.MOTORCYCLE, bikeSpot1);
        List<Floor> floorList = new ArrayList<>();
        floorList.add(floor1);
        Map<VehicleType, Queue<ParkingSpot>> availableSpots = new HashMap<>();
        ExitGate exitGate1 = new ExitGate("E1", ticketService);
        EntryGate entryGate1 = new EntryGate("G1", ticketService);
        List<ExitGate> exitGateList = new ArrayList<>();
        List<EntryGate> entryGateList = new ArrayList<>();
        entryGateList.add(entryGate1);
        exitGateList.add(exitGate1);
        ParkingSpotManager parkingSpotManager = new ParkingSpotManager(availableSpots, parkingRepository, floorList);
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.initialize(exitGateList, entryGateList, ticketService, PaymentProcessorFactory.choosePaymentProcessor(PaymentMethod.CREDIT_CARD), pricingStrategy, parkingSpotManager, parkingRepository);

        System.out.println("Parking lot setup completed successfully!");
        System.out.println("------------------------------");

        System.out.println("Simulating parking and unparking of vehicles...");
        System.out.println("------------------------------");
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime parkTime = currentTime.minus(Duration.ofHours(2));
        parkingLot.parkVehicle("G1", car1.getVehicleId(), car1.getVehicleType(), parkTime);
        System.out.println("Car parked Successfully!");
        System.out.println("------------------------------");
        LocalDateTime exitTime = currentTime;
        parkingLot.unparkVehicle("E1",car1.getVehicleId(),exitTime);
        System.out.println("Car unparked Successfully!");
        System.out.println("------------------------------");

    }
}