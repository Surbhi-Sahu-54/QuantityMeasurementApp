package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    private static QuantityMeasurementApp instance;

    private final IQuantityMeasurementRepository repository;
    private final IQuantityMeasurementService service;
    private final QuantityMeasurementController controller;

    private QuantityMeasurementApp() {
        this.repository = createRepository();
        this.service = createService(repository);
        this.controller = createController(service);
    }

    public static QuantityMeasurementApp getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementApp();
        }
        return instance;
    }

    private IQuantityMeasurementRepository createRepository() {
        return QuantityMeasurementCacheRepository.getInstance();
    }

    private IQuantityMeasurementService createService(IQuantityMeasurementRepository repository) {
        return new QuantityMeasurementServiceImpl(repository);
    }

    private QuantityMeasurementController createController(IQuantityMeasurementService service) {
        return new QuantityMeasurementController(service);
    }

    public QuantityMeasurementController getController() {
        return controller;
    }

    public IQuantityMeasurementRepository getRepository() {
        return repository;
    }

    public static void main(String[] args) {
        QuantityMeasurementApp app = QuantityMeasurementApp.getInstance();
        QuantityMeasurementController controller = app.getController();

        QuantityDTO oneFoot = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO twelveInches = new QuantityDTO(12.0, "INCHES", "LENGTH");

        System.out.println("Length Equality:");
        System.out.println(controller.performEquality(oneFoot, twelveInches));

        System.out.println("\nLength Conversion:");
        System.out.println(controller.performConversion(oneFoot, "INCHES"));

        System.out.println("\nLength Addition:");
        System.out.println(controller.performAddition(oneFoot, twelveInches));

        System.out.println("\nTemperature Addition Attempt:");
        QuantityDTO temp1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");
        System.out.println(controller.performAddition(temp1, temp2));

        System.out.println("\nCross Category Prevention:");
        QuantityDTO oneKg = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");
        System.out.println(controller.performEquality(oneFoot, oneKg));

        System.out.println("\nStored Measurements Count:");
        System.out.println(app.getRepository().getAllMeasurements().size());
    }
}