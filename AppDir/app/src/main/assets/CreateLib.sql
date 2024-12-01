.echo ON
.mode list
.separator "  |  "
.open Lib.db

PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS FEEDBACK;
DROP TABLE IF EXISTS REGISTRATION;
DROP TABLE IF EXISTS CLASS;
DROP TABLE IF EXISTS ATTENDEE;
DROP TABLE IF EXISTS INSTRUCTOR;
DROP TABLE IF EXISTS CLASS_TYPE;

CREATE TABLE CLASS_TYPE (
    CTID CHAR(6) PRIMARY KEY,
    CTName NVARCHAR(7) NOT NULL,
    CTDescription NVARCHAR(30),
    CTDuration INT,
    CTPrice INT
);

CREATE TABLE INSTRUCTOR (
    InstID CHAR(4) PRIMARY KEY,
    InstName NVARCHAR(30),
    InstAddress NVARCHAR(30),
    InstFee INT
);

CREATE TABLE ATTENDEE (
    AttendID INTEGER PRIMARY KEY AUTOINCREMENT,
    AttendFirstName NVARCHAR(30),
    AttendLastName NVARCHAR(15),
    AttendAddress NVARCHAR(30),
    AttendEmail NVARCHAR(30),
    AttendDOB DATE
);

CREATE TABLE CLASS (
    ClID INTEGER PRIMARY KEY AUTOINCREMENT,
    ClDate DATE NOT NULL,
    ClTime TIME NOT NULL,
    ClSpotsAvail INT DEFAULT 20,
    ClLocAddress NVARCHAR(30),
    CTID CHAR(6) REFERENCES CLASS_TYPE(CTID) ON DELETE CASCADE,
    InstID CHAR(4) REFERENCES INSTRUCTOR(InstID) ON DELETE CASCADE
);

CREATE TABLE REGISTRATION (
    RegID INTEGER PRIMARY KEY AUTOINCREMENT,
    ClID CHAR(5) REFERENCES CLASS(ClID) ON DELETE CASCADE,
    AttendID CHAR(6) REFERENCES ATTENDEE(AttendID) ON DELETE CASCADE,
    RegStatus NVARCHAR(9) CHECK (RegStatus IN ('Confirmed', 'Pending', 'On Hold'))
);

CREATE TABLE FEEDBACK (
    FBID INTEGER PRIMARY KEY AUTOINCREMENT,
    FBRating TINYINT(1) CHECK (FBRating BETWEEN 1 AND 5),
    FBComments NVARCHAR(50),
    FBReviewed BOOLEAN DEFAULT false,
    RegID INTEGER REFERENCES REGISTRATION(RegID) ON DELETE CASCADE
);

-- Insert data into CLASS_TYPE
INSERT INTO CLASS_TYPE (CTID, CTName, CTDescription, CTDuration, CTPrice)
VALUES
('CT001', 'Yoga', 'Relaxing yoga class', 60, 20),
('CT002', 'Pilates', 'Core strengthening Pilates', 45, 25),
('CT003', 'Zumba', 'High-energy dance workout', 50, 18),
('CT004', 'HIIT', 'High-Intensity Interval Training', 30, 30),
('CT005', 'Spin', 'Intense cycling class', 45, 28),
('CT006', 'Meditation', 'Mindfulness and relaxation', 30, 15);

-- Insert data into INSTRUCTOR
INSERT INTO INSTRUCTOR (InstID, InstName, InstAddress, InstFee)
VALUES
('I001', 'Smith, John', '789 Health Dr', 50),
('I002', 'Doe, Jane', '101 Wellness Blvd', 60),
('I003', 'Lee, Alice', '555 Fit Ave', 55),
('I004', 'Taylor, Chris', '789 Calm Dr', 40),
('I005', 'Morgan, Alex', '111 Stamina Ln', 45);

-- Insert data into ATTENDEE
INSERT INTO ATTENDEE (AttendFirstName, AttendLastName, AttendAddress, AttendEmail, AttendDOB)
VALUES
('Emily', 'Stone', '321 Lake View', 'emily@example.com', '1992-03-05'),
('Michael', 'Brown', '654 Mountain Rd', 'michael@example.com', '1988-07-23'),
('Sarah', 'Johnson', '987 Ocean Dr', 'sarah@example.com', '1995-11-02'),
('David', 'Clark', '123 Forest St', 'david@example.com', '1990-04-12'),
('James', 'White', '999 Elm St', 'james@example.com', '1985-02-14'),
('Olivia', 'Green', '888 Maple Ave', 'olivia@example.com', '1998-06-21'),
('Lucas', 'Black', '777 Pine Rd', 'lucas@example.com', '1993-01-19');

-- Insert data into CLASS
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

-- Insert data into REGISTRATION
INSERT INTO REGISTRATION (ClID, AttendID, RegStatus)
VALUES
(1, 1, 'Confirmed'),
(2, 2, 'Pending'),
(3, 3, 'Confirmed'),
(4, 4, 'On Hold'),
(5, 5, 'Confirmed'),
(6, 6, 'Pending'),
(1, 7, 'Confirmed'),
(3, 5, 'On Hold');

-- Insert data into FEEDBACK
INSERT INTO FEEDBACK (FBRating, FBComments, FBReviewed, RegID)
VALUES
(5, 'Excellent class!', false, 1),
(4, 'Great instructor.', true, 2),
(3, 'Room for improvement.', false, 3),
(5, 'Loved the energy!', true, 4),
(4, 'Great workout!', true, 5),
(2, 'Not my favorite.', false, 6),
(5, 'Excellent and refreshing!', true, 7),
(3, 'Decent but needs better pacing.', false, 8),
(1, 'Worst class ever.', false, 6);


.output stdout
.echo OFF
