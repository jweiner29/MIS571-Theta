package com.example.mis571_finalproject.constant;

/**
 * SQL commands
 * Including select/delete/update/insert
 */

public abstract class SQLCommand
{
    //query upcoming classes
    public static String Schedule =
            "SELECT \n" +
                    "    C.ClID AS 'Class ID',"+
                    "    C.ClDate AS 'Class Date',\n" +
                    "    C.ClTime AS 'Class Time',\n" +
                    "    CT.CTName AS 'Class Name',\n" +
                    "    CT.CTDescription AS Description,\n" +
                    "    CT.CTDuration AS 'Duration (min)',\n" +
                    "    CT.CTPrice AS 'Price ($)',\n" +
                    "    C.ClLocAddress AS Location,\n" +
                    "    I.InstName AS Instructor,\n" +
                    "    (C.ClSpotsAvail - COUNT(R.RegID)) AS 'Remaining Spots'\n" +
                    "FROM \n" +
                    "    CLASS C\n" +
                    "JOIN \n" +
                    "    CLASS_TYPE CT ON C.CTID = CT.CTID\n" +
                    "JOIN \n" +
                    "    INSTRUCTOR I ON C.InstID = I.InstID\n" +
                    "LEFT JOIN \n" +
                    "    REGISTRATION R ON C.ClID = R.ClID\n" +
                    "WHERE C.ClDate >= date('now')\n" +
                    "GROUP BY \n" +
                    "    C.ClID, C.ClDate, C.ClTime, CT.CTName, CT.CTDescription, CT.CTDuration, CT.CTPrice, C.ClLocAddress, I.InstName, C.ClSpotsAvail;\n" +
                    "ORDER BY C.ClDate";
    //All Classes
    public static String AllClasses = "SELECT * FROM CLASS";
    //Insert Attendee
    public static String AddAttendee = "INSERT INTO ATTENDEE "+
            "(AttendFirstName, AttendLastName, AttendAddress, AttendEmail, AttendDOB) "+
            "VALUES (?,?,?,?,?)";
    //Insert Registration
    public static String AddReg = "INSERT INTO REGISTRATION " +
            "(ClID, AttendID, RegStatus) "+
            "VALUES (?,?,?)";
    //Insert Class
    public static String AddClass = "INSERT INTO CLASS "+
            "(ClID, ClDate, ClTime, ClSpotsAvail, ClLocAddress, CTID, InstID) "+
            "VALUES (?,?,?,?,?,?,?)";
    //Delete Registrations
    public static String DeleteReg = "DELETE FROM REGISTRATIONS "+
            "WHERE ClID = ?";
    //Delete Class (Delete Registration needs to happen first)
    public static String DeleteClass = "DELETE FROM CLASS " +
            "WHERE ClID = ?";
    //View Feedback
    public static  String Feedback = "SELECT FBRating, FBComments, RegID FROM FEEDBACK";
    //View Registrations
    public static String AllReg = "SELECT REGISTRATION.ClID, REGISTRATION.RegStatus, ATTENDEE.AttendID, ATTENDEE.AttendFirstName, ATTENDEE.AttendLastName "+
            "FROM REGISTRATION, ATTENDEE "+
            "WHERE REGISTRATION.AttendID = ATTENDEE.AttendID";

}


