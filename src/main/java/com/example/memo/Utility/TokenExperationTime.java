package com.example.memo.Utility;

import java.time.Duration;
import java.time.LocalDateTime;

public class TokenExperationTime {

    private static final long Experation_time=20;

    public static LocalDateTime calculateExperationTome()
    {
        LocalDateTime now=LocalDateTime.now();
        LocalDateTime experationTime=now.plusMinutes(Experation_time);
        return experationTime;
    }

    public static int calculateDifferenceInTime(LocalDateTime time1,LocalDateTime time2)
    {
        Duration duration=Duration.between(time1, time2).abs();

        if(duration.toSeconds()>1)
        {
            return 1;
        }
        else{
            return -1;
        }
    }


}
