/*
 * Creato il 22-feb-05
 */
package it.almaviva.rules.dipendenza.utils;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * @author Barbara Parisi
 */
public class FormatDate extends GregorianCalendar implements Serializable, Cloneable {

    private static final SimpleTimeZone gmtTZ = new SimpleTimeZone(0, "GMT+1");
    private static final long VOIDTIME = -3600001;
    private static final long VOIDTIME2 = -3600000;
    private String frmDate = "dd/MM/yyyy";
    private String valDate = null;
//	public static String ORACLE_FORMAT = "dd-MM-yyyy HH:mm:ss";	
//	public static String DATE_FORMAT = "dd/MM/yyyy";	
//	public static String TIME_FORMAT = "HH:mm:ss";	
//	public static String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    //*** modifica per metriche ***
    private static String ORACLE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private static String DATE_FORMAT = "dd/MM/yyyy";
    private static String TIME_FORMAT = "HH:mm:ss";
    private static String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public FormatDate() {
        super();
    }

    /**
     * Constructs a GDate object with the parameter specified.
     * @param str String is the date.
     * @param fmt String is the format.
     * 			  Es: "dd/MM/yyyy HH:mm:ss"
     * 			  		"dd/MM/yyyy"
     * 			  		"dd/MM/yyyy HH:mm"
     */
//    public FormatDate(String str, String fmt) throws ParseException {
//        super();
//        setLenient(false);
//        this.valDate = str;
//        this.frmDate = fmt;
//        setDate();
//    }

    /**
     * Constructs a GDate object with the parameter specified.
     * @param str String is the date. Default Format = dd/MM/yyyy
     */
    public FormatDate(String str) throws ParseException {
        super();
        setLenient(false);
        this.valDate = str;
        setDate();
    }

    /**
     * Constructs a GDate object.
     * @param year is the year.
     * @param month is the month (0 based).
     * @param day is the day (1 based).
     */
//    public FormatDate(int year, int month, int day) throws ParseException {
//        super();
//        String str_day = day <= 9 ? "0" + day : "" + day;
//        String str = String.valueOf(str_day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
//        setLenient(false);
//        this.frmDate = "dd/MM/yyyy HH:mm:ss";
//        this.valDate = str + " 12:01:01";
//        setDate();
//    }

    /**
     * Constructs a GDate object.
     * @param year is the year.
     * @param month is the month (0 based).
     * @param day is the day (1 based).
     * @param hour is the hour value.
     * @param minute is the minute value.
     * @param second is the second value.
     */
//    public FormatDate(int year, int month, int day, int hour, int minute, int second) throws ParseException {
//        super();
//        String str = String.valueOf(day) + "/" +
//                String.valueOf(month) + "/" +
//                String.valueOf(year) + " " +
//                String.valueOf(hour) + ":" +
//                String.valueOf(minute) + ":" +
//                String.valueOf(second);
//        setLenient(false);
//        this.valDate = str;
//        this.frmDate = "dd/MM/yyyy HH:mm:ss";
//        setDate();
//    }

    /**
     * @param tempo
     * @throws ParseException
     */
//    public FormatDate(long tempo) throws ParseException {
//        setTime(new Date(tempo));
//    }

    /**
     * Gets a void time. A GDate object with all fields set to zero.
     * @return the void time GDate object.
     */
//    public static FormatDate getVoid() {
//        FormatDate nvp = new FormatDate();
//        nvp.setTimeInMillis(VOIDTIME);
//        return nvp;
//    }

    /**
     * Checks if this object represents a void time.
     * @return <CODE>true</CODE> if this object represents a void
     * time else <CODE>false</CODE>.
     * @modelguid {C6C98CE7-9217-4526-B0E1-1A9BC6D5C422}
     */
    public boolean isVoid() {
        return getTimeInMillis() <= VOIDTIME;
    }

    /**
     * Sets the date and time.
     * @param date is the date to set to this object.
     * @param time is the time to set to this object.
     * @modelguid {6817904B-A037-4B2B-B482-96438C598A3B}
     */
//    public void setDateTime(Date date, Date time) {
//        setTime(date);
//        int year = get(Calendar.YEAR);
//        int month = get(Calendar.MONTH);
//        int day = get(Calendar.DAY_OF_MONTH);
//        setTime(time);
//        int hour = get(Calendar.HOUR_OF_DAY);
//        int minute = get(Calendar.MINUTE);
//        int second = get(Calendar.SECOND);
//        set(year, month, day, hour, minute, second);
//    }

    /**
     * Sets the date
     * @param date is the date to set to this object.
     * modifica Damiano Rigoli 27/9/2006
     */
//    public void setDate(Date date) {
//        if (date != null) {
//            setTime(date);
//            int year = get(Calendar.YEAR);
//            int month = get(Calendar.MONTH);
//            int day = get(Calendar.DAY_OF_MONTH);
//            int hour = get(Calendar.HOUR_OF_DAY);
//            int minute = get(Calendar.MINUTE);
//            int second = get(Calendar.SECOND);
//            set(year, month, day, hour, minute, second);
//        } else {
//            this.setTimeInMillis(VOIDTIME);
//        }
//    }

    /**
     * Gets this Calendar's current time as a long value.
     * @return the current time as UTC milliseconds from the epoch.
     * @modelguid {04E12485-1932-41F5-8C1A-70FF43E1DF5A}
     */
//    public long getMillis() {
//        return getTimeInMillis();
//    }

    /**
     * Sets this GDate current time with the actual Date.
     * @modelguid {38560194-77B6-43F1-B368-E1534A868868}
     */
    private void setDate() throws ParseException {
        String res = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(this.frmDate);
            sdf.setLenient(false);
            setTime(sdf.parse(this.valDate));
            sdf = null;
        } catch (ParseException prse) {
            throw new ParseException("Data non valida", prse.getErrorOffset());
        }
    }

    /**
     * Converts the GDate object to a string using a predefined or a
     * specific format.
     * @param format is the format string (see SimpleDateFormat class) if
     * null a predefined format string is used.
     * @return the formatted date/time string or "" if the GDate object is void .
     * @modelguid {8E340C41-857C-4010-9CE6-513EF0CCE9D0}
     * Mod Rigoli Damiano 27/09/2006 :
     * @return "-" se la data non � valida.
     * Mod Rigoli Damiano 03/11/2006 :
     * @return "01/01/2004" se la data non � valida ma il format � impostato a "valid".
     */
    public String toString(String format) {
        if (this.isVoid()) {
            if (format != null) {
                if (format.equals("valid")) {
                    return "01/01/2004";
                } else if (format.equals("base")) {
                	//       System.out.println("base");
                } else {
                    return "-";
                }
            } else {
                return "-";
            }
        }

        /** Date/Time formatting symbols */
        DateFormatSymbols dateFmtSym = new DateFormatSymbols();
        if ((((format == null) || (format == "")) || (format.equals("valid"))) || (format.equals("base"))) {
            format = "dd/MM/yyyy";
        }
        if (format.equals("ora")) {
            format = DATETIME_FORMAT;
        }
        /** Date format for generic time */
        SimpleDateFormat fmtDTimey = new SimpleDateFormat(format, dateFmtSym);
        fmtDTimey.setLenient(false);
        return (fmtDTimey.format(getTime()));
    }

    /**
     * Check if an GDate object is greater than an other GDate object . 
     * @param GDate 
     * @return true if the GDate object are greater than the GDate parameter object.  
     * @modelguid {FC6B72AD-B3BD-4623-AADF-4930A98D83BB}
     */
//    public static long getDiff(FormatDate nvp1, FormatDate nvp2) {
//        long diff = 0;
//        long res = 0;
//        if (nvp1.getTimeInMillis() > nvp2.getTimeInMillis()) {
//            diff = nvp1.getTimeInMillis() - nvp2.getTimeInMillis();
//        } else if (nvp2.getTimeInMillis() > nvp1.getTimeInMillis()) {
//            diff = nvp2.getTimeInMillis() - nvp1.getTimeInMillis();
//        }
//        return diff / 86400000;
//    }

    /**
     * Check if an GDate object is greater than an other GDate object . 
     * @param GDate 
     * @return true if the GDate object are greater than the GDate parameter object.  
     * @modelguid {356B8B78-DFDE-4B1E-8BCA-824C9F244D5B}
     */
//    public static long getTimeDiff(FormatDate nvp1, FormatDate nvp2) {
//        long diff = 0;
//        long res = 0;
//        if (nvp1.getTimeInMillis() > nvp2.getTimeInMillis()) {
//            diff = nvp1.getTimeInMillis() - nvp2.getTimeInMillis();
//        } else if (nvp2.getTimeInMillis() > nvp1.getTimeInMillis()) {
//            diff = nvp2.getTimeInMillis() - nvp1.getTimeInMillis();
//        }
//        return diff / 1000;
//
//    }

    /**
     * Check if an GDate object is greater than an other GDate object . 
     * @param GDate 
     * @return true if the GDate object are greater than the GDate parameter object. (seconds).  
     * @modelguid {CB768350-0C98-437F-9085-7AC4C65DDF9A}
     */
//    public boolean isgreaterthaninsec(FormatDate dtime) {
//        return (getTimeInMillis() / 1000) > (dtime.getTimeInMillis() / 1000);
//    }

    /**
     * Check if an GDate object is greater than an other GDate object . 
     * @param GDate 
     * @return true if the GDate object are greater than the GDate parameter object. (minute).  
     * @modelguid {BA83AA44-8D20-43E0-BFDC-9F0D9A6563F6}
     */
//    public boolean isgreaterthan(FormatDate dtime) {
//        return (getTimeInMillis() / 60000) > (dtime.getTimeInMillis() / 60000);
//    }

    /**
     * Check if the actual GDate object is equal than an other GDate object. 
     * @param GDate 
     * @return true if the value of the two GDate object are equal. (millis).  
     * @modelguid {1F1A437B-B7C5-411B-BE17-7DCA3D2B9F47}
     */
//    public boolean isequalthan(FormatDate GDate) {
//        return getTimeInMillis() == GDate.getTimeInMillis();
//    }

    /**
     * Check if an GDate object is greater than an other GDate object. 
     * @param GDate 
     * @return true if the GDate object are less than the GDate parameter object. (minute).  
     * @modelguid {E1F0B2CD-EC17-4F04-82A6-1D61481B8771}
     */
//    public boolean islessthan(FormatDate dtime) {
//        return (getTimeInMillis() / 60000) < (dtime.getTimeInMillis() / 60000);
//    }

    /**
     * Check if an GDate object is greater than an other GDate object. 
     * @param GDate 
     * @return true if the GDate object are less than the GDate parameter object. (minute).  
     * @modelguid {5FD62E8A-525F-4666-893C-302C2A971D13}
     */
//    public boolean islessthaninsec(FormatDate dtime) {
//        return (getTimeInMillis() / 1000) < (dtime.getTimeInMillis() / 1000);
//    }

    /**
     * Checks if the specified date/time object
     * @param dtime is the date/time value.
     * @param extendedCompare true if checks also Hour and Minute.
     * @return <CODE>true</CODE> if the date/time has the same date/time
     * of the specified GDate object.
     * @modelguid {EB3220C8-90F7-4F87-AF71-D8AC856B594F}
     */
//    public boolean sameAs(FormatDate dtime, boolean extendedCompare) throws ParseException {
//
//        if (extendedCompare) {
//            return (getTimeInMillis() == dtime.getTimeInMillis());
//        } else {
//            FormatDate nvp1 = new FormatDate(this.toString("dd/MM/yyyy"));
//            FormatDate nvp2 = new FormatDate(dtime.toString("dd/MM/yyyy"));
//
//            return (nvp1.getTimeInMillis()) == (nvp2.getTimeInMillis());
//        }
//    }

    /**
     * Adds the specified (signed) amount of time to the given time field,
     * based on the calendar's rules.
     * @param field String. The time field to be changed. 
     * 								  Only "MONTH", "DATE" and "YEAR" are correct value. 
     * @param   amount int.  the specified (signed) amount of time to the given time field,
     * @modelguid {8CAE356F-F491-48D4-B0E6-526D92D11F70}
     */
//    public void changeGDate(String field, int amount) {
//        int i = 0;
//        if (field.equalsIgnoreCase("MONTH")) {
//            i = MONTH;
//        } else if (field.equalsIgnoreCase("DATE")) {
//            i = DATE;
//        } else if (field.equalsIgnoreCase("YEAR")) {
//            i = YEAR;
//        }
//        if (i != 0) {
//            add(i, amount);
//        }
//    }

    /**
     * Gets the value for the YEAR field of GDate
     * @return int.
     * @modelguid {A698503A-3718-4B4C-9A2F-8505DECAD319}
     */
//    public int getYear() {
//        return this.get(YEAR);
//    }

    /**
     * Gets the value for the Date field of GDate
     * @return int.
     * @modelguid {5A2C905A-0C3B-4DD2-8E9D-4F4D9940B7B5}
     */
//    public int getDay() {
//        return this.get(DATE);
//    }

    /**
     * Gets the value for the Month field of GDate
     * @return int.
     * @modelguid {B9E0836F-9D0B-46B0-AFC6-70C88DAA1A91}
     */
//    public int getMonth() {
//        return this.get(MONTH) + 1;
//    }

    /**
     * Return the maximum value that DAY_OF_MONTH could have, given the current date.
     * @return <CODE>int /CODE> the maximum value.
     * @modelguid {F627756A-53D8-4966-AC03-CF233EE0F1A5}
     */
//    public int getActualMaxDay() {
//        return this.getActualMaximum(it.corteconti.siquel.business.utente.utility.FormatDate.DAY_OF_MONTH);
//    }

    /**
     * Sets the current time from the given long value.
     * @param millis is the new time in UTC milliseconds from the epoch.
     * @modelguid {2764540B-7A41-44A6-B246-D17444FCF182}
     */
    public void setTimeInMillis(long millis) {
        super.setTimeInMillis(millis);
    }

    /**
     * Sets the format of the current time.
     * @param str 
     * @modelguid {569C3728-60EF-455B-A5BC-DA2711BEDB97}
     */
//    public void setfrmDate(String str) {
//        this.frmDate = str;
//    }

    /**
     * Gets this String parameter current time as a long value.
     * @param strDate String.  
     * @return a long value.
     * @modelguid {A02B7C43-A940-460D-8CF0-083FC5CA09DE}
     */
//    public static long getSec(String strDate) throws ParseException {
//        long sec = 0;
//        FormatDate localGDate = new FormatDate(strDate);
//        System.out.println(localGDate.toString(""));
//        sec = localGDate.getTimeInMillis() / 1000;
//        return sec;
//    }

    /** @modelguid {76E0CB64-080D-4438-99BB-AB1A5EBADA83} */
//    private boolean isValid() {
//        return frmDate.length() != valDate.length() ? false : true;
//    }

    /**
     * Converts the GDate object to a string format for ORACLE DB.
     * @return the formatted date/time string or "" if the GDate object is void .
     * @modelguid {C07BC709-4DF1-44B2-9BA4-6F6DB578635E}
     */
//    public String toOracleString() {
//        if (this.isVoid()) {
//            return "";
//        }
//        return this.toString(it.corteconti.siquel.business.utente.utility.FormatDate.ORACLE_FORMAT);
//    }

    public static String getDATETIME_FORMAT() {
        return DATETIME_FORMAT;
    }

    public static void setDATETIME_FORMAT(String DATETIME_FORMAT) {
        FormatDate.DATETIME_FORMAT = DATETIME_FORMAT;
    }

    public static String getDATE_FORMAT() {
        return DATE_FORMAT;
    }

    public static void setDATE_FORMAT(String DATE_FORMAT) {
        FormatDate.DATE_FORMAT = DATE_FORMAT;
    }

    public static String getORACLE_FORMAT() {
        return ORACLE_FORMAT;
    }

    public static void setORACLE_FORMAT(String ORACLE_FORMAT) {
        FormatDate.ORACLE_FORMAT = ORACLE_FORMAT;
    }

    public static String getTIME_FORMAT() {
        return TIME_FORMAT;
    }

    public static void setTIME_FORMAT(String TIME_FORMAT) {
        FormatDate.TIME_FORMAT = TIME_FORMAT;
    }
}

