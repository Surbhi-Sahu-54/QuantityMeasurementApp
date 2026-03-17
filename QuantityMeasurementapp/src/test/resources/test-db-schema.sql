DROP TABLE IF EXISTS quantity_measurement_entity;

CREATE TABLE quantity_measurement_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    this_value DOUBLE NOT NULL,
    this_unit VARCHAR(50) NOT NULL,
    this_measurement_type VARCHAR(50) NOT NULL,
    that_value DOUBLE,
    that_unit VARCHAR(50),
    that_measurement_type VARCHAR(50),
    operation VARCHAR(20) NOT NULL,
    result_string VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);