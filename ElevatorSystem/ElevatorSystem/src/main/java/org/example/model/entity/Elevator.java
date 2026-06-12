package org.example.model.entity;

import org.example.model.Direction;
import org.example.model.DoorStatus;
import org.example.model.requests.ElevatorRequest;
import org.example.state.ElevatorState;
import org.example.state.IdleState;

import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Elevator implements Runnable {
    private final int id;
    private AtomicInteger currentFloor;
    private int minFloor;
    private int maxFloor;
    private Direction direction;
    private DoorStatus doorStatus;
    TreeSet<Integer> upRequest;
    TreeSet<Integer> downRequest;
    private ElevatorState state;
    private volatile boolean isMoving;
    private ReentrantLock lock;
    private final Condition lockCondition;

    public Elevator(int id, int minFloor, int maxFloor) {
        this.id = id;
        this.currentFloor = new AtomicInteger(0);
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.direction = Direction.IDLE;
        this.doorStatus = DoorStatus.CLOSED;
        this.upRequest = new TreeSet<>((a, b) -> a - b);
        this.downRequest = new TreeSet<>((a, b) -> b - a);
        this.isMoving = true;
        this.state = new IdleState();
        this.lock = new ReentrantLock();
        this.lockCondition = lock.newCondition();
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public TreeSet<Integer> getUpRequest() {
        return upRequest;
    }

    public TreeSet<Integer> getDownRequest() {
        return downRequest;
    }

    public void setIdle() {
        this.direction = Direction.IDLE;
        this.state = new IdleState();
    }

    public void openDoor() {
        this.doorStatus = DoorStatus.OPEN;
    }

    public void closeDoor() {
        this.doorStatus = DoorStatus.CLOSED;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public ElevatorState getState() {
        return this.state;
    }

    public void addRequest(ElevatorRequest request) {
        lock.lock();
        try {
            int targetFloor = request.getFloor();
            if (targetFloor < minFloor || targetFloor > maxFloor) {
                throw new IllegalArgumentException("Invalid floor request");
            }
            if (request.getFloor() > currentFloor.get()) {
                upRequest.add(targetFloor);
            } else if (request.getFloor() < currentFloor.get()) {
                downRequest.add(targetFloor);
            }
            if (targetFloor == this.getCurrentFloor()) {
                if (doorStatus == DoorStatus.CLOSED) {
                    openDoor();
                }
                System.out.println("Elevator " + id + " is already at floor " + targetFloor + ". Door opened.");
                closeDoor();
                return;
            }
            lockCondition.signal();
        } finally {
            lock.unlock();
        }


    }


    public void run() {
        while (true) {
            try {
                lock.lock();
                try {
                    while (!hasRequest()) {
                        lockCondition.await();
                    }
                    this.state.move(this);
                } finally {
                    lock.unlock();
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public boolean hasRequest() {
        return !upRequest.isEmpty() || !downRequest.isEmpty();
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", currentFloor=" + currentFloor +
                ", minFloor=" + minFloor +
                ", maxFloor=" + maxFloor +
                ", direction=" + direction +
                ", doorStatus=" + doorStatus +
                ", upRequest=" + upRequest +
                ", downRequest=" + downRequest +
                '}';
    }
}

