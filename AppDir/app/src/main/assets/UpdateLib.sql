DROP TABLE IF EXISTS REGISTRATION_OLD;
DROP TABLE IF EXISTS CLASS_OLD;
DROP TABLE IF EXISTS ATTENDEE_OLD;



-- Update to DB to autogenerate IDs on Insert

ALTER TABLE CLASS RENAME TO CLASS_OLD;

CREATE TABLE CLASS (
    ClID INTEGER PRIMARY KEY AUTOINCREMENT,
    ClDate DATE NOT NULL,
    ClTime TIME NOT NULL,
    ClSpotsAvail INT DEFAULT 20,
    ClLocAddress NVARCHAR(30),
    CTID CHAR(6) REFERENCES CLASS_TYPE(CTID) ON DELETE CASCADE,
    InstID CHAR(4) REFERENCES INSTRUCTOR(InstID) ON DELETE CASCADE
);

INSERT INTO CLASS (ClDate, ClTime, ClSpotsAvail, ClLocAddress, CTID, InstID)
VALUES
('2024-11-10', '10:00:00', 20, '123 Fitness St', 'CT001', 'I001'),
('2024-11-11', '14:00:00', 15, '456 Wellness Ave', 'CT002', 'I002'),
('2024-11-12', '09:00:00', 10, '789 Park Ln', 'CT003', 'I003'),
('2024-11-13', '18:30:00', 18, '321 Gym Rd', 'CT004', 'I001'),
('2024-11-14', '12:00:00', 12, '456 Cycle Studio', 'CT005', 'I004'),
('2024-11-15', '17:00:00', 25, '123 Calm Center', 'CT006', 'I005'),
('2024-12-10', '07:00:00', 20,	'123 Fitness St', 'CT001', 'I001'),
('2024-12-21', '10:00:00', 15, '123 Fitness St', 'CT002', 'I004'),
('2024-12-11', '11:00:00', 20, '123 Calm Center', 'CT003', 'I003'),
('2024-12-10', '12:00:00', 15, '123 Fitness St', 'CT004', 'I002');



ALTER TABLE ATTENDEE RENAME TO ATTENDEE_OLD;

CREATE TABLE ATTENDEE (
    AttendID INTEGER PRIMARY KEY AUTOINCREMENT,
    AttendFirstName NVARCHAR(30),
    AttendLastName NVARCHAR(15),
    AttendAddress NVARCHAR(30),
    AttendEmail NVARCHAR(30),
    AttendDOB DATE
);

INSERT INTO ATTENDEE (AttendFirstName, AttendLastName, AttendAddress, AttendEmail, AttendDOB)
VALUES
('Emily', 'Stone', '321 Lake View', 'emily@example.com', '1992-03-05'),
('Michael', 'Brown', '654 Mountain Rd', 'michael@example.com', '1988-07-23'),
('Sarah', 'Johnson', '987 Ocean Dr', 'sarah@example.com', '1995-11-02'),
('David', 'Clark', '123 Forest St', 'david@example.com', '1990-04-12'),
('James', 'White', '999 Elm St', 'james@example.com', '1985-02-14'),
('Olivia', 'Green', '888 Maple Ave', 'olivia@example.com', '1998-06-21'),
('Lucas', 'Black', '777 Pine Rd', 'lucas@example.com', '1993-01-19');



ALTER TABLE REGISTRATION RENAME TO REGISTRATION_OLD;

CREATE TABLE REGISTRATION (
    RegID INTEGER PRIMARY KEY AUTOINCREMENT,
    ClID CHAR(5) REFERENCES CLASS(ClID) ON DELETE CASCADE,
    AttendID CHAR(6) REFERENCES ATTENDEE(AttendID) ON DELETE CASCADE,
    RegStatus NVARCHAR(9) CHECK (RegStatus IN ('Confirmed', 'Pending', 'On Hold'))
);

INSERT INTO REGISTRATION (ClID, AttendID, RegStatus)
VALUES
('1', '1', 'Confirmed'),
('2', '2', 'Pending'),
('3', '3', 'Confirmed'),
('4', '4', 'On Hold'),
('5', '5', 'Confirmed'),
('6', '6', 'Pending'),
('1', '7', 'Confirmed'),
('3', '5', 'On Hold');


 DROP TABLE CLASS_OLD;
 DROP TABLE ATTENDEE_OLD;
 DROP TABLE REGISTRATION_OLD;

