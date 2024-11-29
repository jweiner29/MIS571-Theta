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
    //Insert Attendee
    //Insert Registration
    //Insert Class
    //View Feedback
    //Update Feedback
    //View Profitability 
}
