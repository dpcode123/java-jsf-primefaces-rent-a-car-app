package com.example.rentacarapp.domain.vehicle;

public enum VehicleType {
    COMPACT,
    LIMO,
    CARAVAN,
    SUV,
    CARGO;

    private static final VehicleType values[] = values();

    public static VehicleType[] getValues() { return values; }
    public static VehicleType getValue(int ordinal) { return values[ordinal]; }
}