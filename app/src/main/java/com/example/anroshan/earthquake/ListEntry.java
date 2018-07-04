package com.example.anroshan.earthquake;

import android.support.v4.content.ContextCompat;

public class ListEntry {

    //The magnitude of the earthquake
    private Double mMagnitude;

    //The place where earthquake happened
    private String mDirection;

    //The place where earthquake happened
    private String mPlace;

    //The day when earthquake was felt
    private String mTime;

    //The time you felt it
    private String mDate;

    //To get the url
    private String mUrl;

    //A constructor to create an object of the class ListEntry
    public ListEntry(Double magnitude,String direction, String place, String date, String time, String url)
    {
        mMagnitude = magnitude;
        mDirection = direction;
        mPlace = place;
        mTime = time;
        mDate = date;
        mUrl = url;
    }

    //To get the magnitude
    public Double getMagnitude() { return mMagnitude; }

    //To get the direction of earthquake
    public String getmDirection() {
        return mDirection;
    }

    //To get the name of the place
    public String getPlace() { return  mPlace; }


    //To get the date of the disaster
    public String getmDate() {
        return mDate;
    }

    //To get the time of the disaster
    public String getTime() { return mTime; }

    //To get the url of current earthquake

    public String getUrl() {
        return mUrl;
    }


    //To get the background color
//    public int getMagnitudeColor(double magnitude) {
//        int magnitudeColorResourceId;
//        int magnitudeFloor = (int) Math.floor(magnitude);
//        switch (magnitudeFloor) {
//            case 0:
//            case 1:
//                magnitudeColorResourceId = R.color.magnitude1;
//                break;
//            case 2:
//                magnitudeColorResourceId = R.color.magnitude2;
//                break;
//            case 3:
//                magnitudeColorResourceId = R.color.magnitude3;
//                break;
//            case 4:
//                magnitudeColorResourceId = R.color.magnitude4;
//                break;
//            case 5:
//                magnitudeColorResourceId = R.color.magnitude5;
//                break;
//            case 6:
//                magnitudeColorResourceId = R.color.magnitude6;
//                break;
//            case 7:
//                magnitudeColorResourceId = R.color.magnitude7;
//                break;
//            case 8:
//                magnitudeColorResourceId = R.color.magnitude8;
//                break;
//            case 9:
//                magnitudeColorResourceId = R.color.magnitude9;
//                break;
//            default:
//                magnitudeColorResourceId = R.color.magnitude10plus;
//                break;
//        }
//        return magnitudeColorResourceId;


}
