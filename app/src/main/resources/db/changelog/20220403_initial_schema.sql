CREATE TABLE staff_member
(
    id                BIGINT      NOT NULL AUTO_INCREMENT,
    name              VARCHAR(50) NOT NULL,
    uuid              CHAR(36)    NOT NULL,
    registration_date DATE        NOT NULL,
    PRIMARY KEY (id)
);
CREATE INDEX idx_staff_member_uid ON staff_member (uuid);

CREATE TABLE patient
(
    id              BIGINT      NOT NULL AUTO_INCREMENT,
    name            VARCHAR(50) NOT NULL,
    age             SMALLINT    NOT NULL,
    last_visit_date DATE        NOT NULL,
    PRIMARY KEY (id)
);
CREATE INDEX idx_patient_a ON patient(age);
CREATE INDEX idx_patient_lvd ON patient(last_visit_date);