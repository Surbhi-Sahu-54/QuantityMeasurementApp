package com.apps.quantitymeasurement;
import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    @BeforeEach
    void setUp() {
        repository = QuantityMeasurementCacheRepository.getInstance();
        repository.clear();
        service = new QuantityMeasurementServiceImpl(repository);
        controller = new QuantityMeasurementController(service);
    }

    void testQuantityEntity_SingleOperandConstruction() {
        QuantityDTO input = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO result = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("CONVERT", input, result);

        assertEquals("CONVERT", entity.getOperationType());
        assertEquals(input, entity.getOperand1());
        assertEquals(result, entity.getResult());
    }

   
    void testQuantityEntity_BinaryOperandConstruction() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");
        QuantityDTO result = new QuantityDTO(2.0, "FEET", "LENGTH");

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", q1, q2, result);

        assertEquals("ADD", entity.getOperationType());
        assertEquals(q1, entity.getOperand1());
        assertEquals(q2, entity.getOperand2());
        assertEquals(result, entity.getResult());
    }

  
    void testQuantityEntity_ErrorConstruction() {
        QuantityDTO q1 = new QuantityDTO(1.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(10.0, "CELSIUS", "TEMPERATURE");

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", q1, q2, "Unsupported");

        assertTrue(entity.hasError());
        assertEquals("Unsupported", entity.getErrorMessage());
    }

   
    void testQuantityEntity_ToString_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO result = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("CONVERT", q1, result);

        assertTrue(entity.toString().contains("Operation=CONVERT"));
    }

  
    void testQuantityEntity_ToString_Error() {
        QuantityDTO q1 = new QuantityDTO(1.0, "CELSIUS", "TEMPERATURE");
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity("ADD", q1, "Unsupported");

        assertTrue(entity.toString().contains("ERROR"));
    }

  
    void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, "FEET", "LENGTH");

        QuantityMeasurementEntity entity = service.compare(q1, q2);

        assertFalse(entity.hasError());
        assertTrue(entity.getComparisonResult());
    }

 
    void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = service.compare(q1, q2);

        assertFalse(entity.hasError());
        assertTrue(entity.getComparisonResult());
    }

   
    void testService_CompareEquality_CrossCategory_Error() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");

        QuantityMeasurementEntity entity = service.compare(q1, q2);

        assertFalse(entity.getComparisonResult());
    }

 
    void testService_Convert_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");

        QuantityMeasurementEntity entity = service.convert(q1, "INCHES");

        assertFalse(entity.hasError());
        assertEquals("INCHES", entity.getResult().getUnit());
        assertEquals(12.0, entity.getResult().getValue(), 1e-4);
    }

   
    void testService_Add_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = service.add(q1, q2);

        assertFalse(entity.hasError());
        assertEquals("FEET", entity.getResult().getUnit());
        assertEquals(2.0, entity.getResult().getValue(), 1e-4);
    }

   
    void testService_Add_UnsupportedOperation_Error() {
        QuantityDTO q1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");

        QuantityMeasurementEntity entity = service.add(q1, q2);

        assertTrue(entity.hasError());
    }

  
    void testService_Subtract_Success() {
        QuantityDTO q1 = new QuantityDTO(10.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(6.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = service.subtract(q1, q2);

        assertFalse(entity.hasError());
    }

   
    void testService_Divide_Success() {
        QuantityDTO q1 = new QuantityDTO(10.0, "KILOGRAM", "WEIGHT");
        QuantityDTO q2 = new QuantityDTO(5.0, "KILOGRAM", "WEIGHT");

        QuantityMeasurementEntity entity = service.divide(q1, q2);

        assertFalse(entity.hasError());
        assertEquals(2.0, entity.getScalarResult(), 1e-6);
    }

    
    void testService_Divide_ByZero_Error() {
        QuantityDTO q1 = new QuantityDTO(10.0, "KILOGRAM", "WEIGHT");
        QuantityDTO q2 = new QuantityDTO(0.0, "KILOGRAM", "WEIGHT");

        QuantityMeasurementEntity entity = service.divide(q1, q2);

        assertTrue(entity.hasError());
    }

 
    void testController_DemonstrateEquality_Success() {
        String result = controller.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertTrue(result.startsWith("SUCCESS"));
    }


    void testController_DemonstrateConversion_Success() {
        String result = controller.performConversion(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                "INCHES"
        );

        assertTrue(result.startsWith("SUCCESS"));
    }

  
    void testController_DemonstrateAddition_Success() {
        String result = controller.performAddition(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertTrue(result.startsWith("SUCCESS"));
    }

   
    void testController_DemonstrateAddition_Error() {
        String result = controller.performAddition(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                new QuantityDTO(10.0, "CELSIUS", "TEMPERATURE")
        );

        assertTrue(result.startsWith("ERROR"));
    }

  
    void testController_DisplayResult_Success() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "COMPARE",
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH"),
                true
        );

        assertTrue(controller.displayResult(entity).startsWith("SUCCESS"));
    }

   
    void testController_DisplayResult_Error() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "ADD",
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                "Unsupported"
        );

        assertTrue(controller.displayResult(entity).startsWith("ERROR"));
    }

  
    void testLayerSeparation_ServiceIndependence() {
        assertNotNull(service);
    }

   
    void testLayerSeparation_ControllerIndependence() {
        IQuantityMeasurementService fakeService = new IQuantityMeasurementService() {
            @Override public QuantityMeasurementEntity compare(QuantityDTO o1, QuantityDTO o2) {
                return new QuantityMeasurementEntity("COMPARE", o1, o2, true);
            }
            @Override public QuantityMeasurementEntity convert(QuantityDTO o1, String targetUnit) {
                return new QuantityMeasurementEntity("CONVERT", o1, new QuantityDTO(1, targetUnit, o1.getMeasurementType()));
            }
            @Override public QuantityMeasurementEntity add(QuantityDTO o1, QuantityDTO o2) {
                return new QuantityMeasurementEntity("ADD", o1, o2, new QuantityDTO(2, o1.getUnit(), o1.getMeasurementType()));
            }
            @Override public QuantityMeasurementEntity add(QuantityDTO o1, QuantityDTO o2, String targetUnit) { return add(o1, o2); }
            @Override public QuantityMeasurementEntity subtract(QuantityDTO o1, QuantityDTO o2) { return add(o1, o2); }
            @Override public QuantityMeasurementEntity subtract(QuantityDTO o1, QuantityDTO o2, String targetUnit) { return add(o1, o2); }
            @Override public QuantityMeasurementEntity divide(QuantityDTO o1, QuantityDTO o2) {
                return new QuantityMeasurementEntity("DIVIDE", o1, o2, 1.0);
            }
        };

        QuantityMeasurementController fakeController = new QuantityMeasurementController(fakeService);
        assertNotNull(fakeController);
    }

 
    void testDataFlow_ControllerToService() {
        controller.performAddition(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertFalse(repository.getAllMeasurements().isEmpty());
    }

    
    void testDataFlow_ServiceToController() {
        String result = controller.performDivision(
                new QuantityDTO(10.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(5.0, "KILOGRAM", "WEIGHT")
        );

        assertTrue(result.contains("2.0"));
    }

   
    void testService_AllMeasurementCategories() {
        assertFalse(service.compare(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")).hasError());

        assertFalse(service.compare(
                new QuantityDTO(1.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(1000.0, "GRAM", "WEIGHT")).hasError());

        assertFalse(service.compare(
                new QuantityDTO(1.0, "LITRE", "VOLUME"),
                new QuantityDTO(1000.0, "MILLILITRE", "VOLUME")).hasError());

        assertFalse(service.compare(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE")).hasError());
    }

  
    void testController_AllOperations() {
        assertNotNull(controller.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")));

        assertNotNull(controller.performConversion(
                new QuantityDTO(1.0, "FEET", "LENGTH"), "INCHES"));

        assertNotNull(controller.performAddition(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")));

        assertNotNull(controller.performSubtraction(
                new QuantityDTO(10.0, "FEET", "LENGTH"),
                new QuantityDTO(6.0, "INCHES", "LENGTH")));

        assertNotNull(controller.performDivision(
                new QuantityDTO(10.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(5.0, "KILOGRAM", "WEIGHT")));
    }

  
    void testService_ValidationConsistency() {
        QuantityMeasurementEntity addError = service.add(null, null);
        QuantityMeasurementEntity divideError = service.divide(null, null);

        assertTrue(addError.hasError());
        assertTrue(divideError.hasError());
    }

  
    void testEntity_Immutability() {
        long setterCount = java.util.Arrays.stream(QuantityMeasurementEntity.class.getDeclaredMethods())
                .filter(m -> m.getName().startsWith("set"))
                .count();

        assertEquals(0, setterCount);
    }

   
    void testService_ExceptionHandling_AllOperations() {
        assertTrue(service.add(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                new QuantityDTO(1.0, "CELSIUS", "TEMPERATURE")).hasError());

        assertTrue(service.divide(
                new QuantityDTO(10.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(0.0, "KILOGRAM", "WEIGHT")).hasError());
    }

  
    void testController_ConsoleOutput_Format() {
        String result = controller.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertTrue(result.startsWith("SUCCESS"));
    }

  
    void testIntegration_EndToEnd_LengthAddition() {
        String result = controller.performAddition(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        );

        assertTrue(result.contains("2.0"));
    }

  
    void testIntegration_EndToEnd_TemperatureUnsupported() {
        String result = controller.performAddition(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE")
        );

        assertTrue(result.startsWith("ERROR"));
    }

 
    void testService_NullEntity_Rejection() {
        QuantityMeasurementEntity result = service.convert(null, "INCHES");
        assertTrue(result.hasError());
    }

   
    void testController_NullService_Prevention() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityMeasurementController(null));
    }


    void testService_AllUnitImplementations() {
        assertFalse(service.convert(new QuantityDTO(1.0, "YARDS", "LENGTH"), "INCHES").hasError());
        assertFalse(service.convert(new QuantityDTO(1.0, "POUND", "WEIGHT"), "GRAM").hasError());
        assertFalse(service.convert(new QuantityDTO(1.0, "GALLON", "VOLUME"), "LITRE").hasError());
        assertFalse(service.convert(new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"), "KELVIN").hasError());
    }

  
    void testEntity_OperationType_Tracking() {
        QuantityMeasurementEntity entity = service.convert(
                new QuantityDTO(1.0, "FEET", "LENGTH"), "INCHES");

        assertEquals("CONVERT", entity.getOperationType());
    }

   
    void testLayerDecoupling_ServiceChange() {
        IQuantityMeasurementService anotherService = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController anotherController = new QuantityMeasurementController(anotherService);

        assertNotNull(anotherController.performEquality(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")));
    }

  
    void testLayerDecoupling_EntityChange() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
                "COMPARE",
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH"),
                true
        );
        assertNotNull(controller.displayResult(entity));
    }

   
    void testScalability_NewOperation_Addition() {
        QuantityMeasurementEntity entity = service.add(
                new QuantityDTO(1.0, "LITRE", "VOLUME"),
                new QuantityDTO(1000.0, "MILLILITRE", "VOLUME")
        );

        assertFalse(entity.hasError());
    }

  
    void testRepository_HistorySaved() {
        service.convert(new QuantityDTO(1.0, "FEET", "LENGTH"), "INCHES");
        service.add(new QuantityDTO(1.0, "FEET", "LENGTH"), new QuantityDTO(12.0, "INCHES", "LENGTH"));

        List<QuantityMeasurementEntity> history = repository.getAllMeasurements();
        assertEquals(2, history.size());
    }
}