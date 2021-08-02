package business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class FlightManagerTest {
    private FlightManager flightManager;
    private String pilotIDTest1;
    private String pilotIDTest2;
    private String pilotIDTest3;
    private String pilotIDTest4;
    private String pilotIDTest5;
    private ArrayList<String> onLocationPilots;
    private ArrayList<String> allPilots;

    @BeforeEach
    public void setUp() {
        flightManager =  new FlightManager();

        pilotIDTest1 = "BTD4596";
        pilotIDTest2 = "DBS1562";
        pilotIDTest3 = "LCR1632";
        pilotIDTest4 = "PRT5595";
        pilotIDTest5 = "CRL5632";

        onLocationPilots = new ArrayList<>(Arrays.asList(pilotIDTest1,pilotIDTest3, pilotIDTest5));
        allPilots = new ArrayList<>(Arrays.asList(pilotIDTest1, pilotIDTest2, pilotIDTest3, pilotIDTest4, pilotIDTest5));
    }

    @Test
    public void getPilotsInOrder(){
        String []exceptedValue = {pilotIDTest1, pilotIDTest3, pilotIDTest5, pilotIDTest2, pilotIDTest4};
        assertArrayEquals(exceptedValue, flightManager.getPilotsInOrder(onLocationPilots, allPilots).toArray());
    }
}