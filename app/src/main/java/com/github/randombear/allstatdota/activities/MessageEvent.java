package com.github.randombear.allstatdota.activities;

import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;


/**
 * =================================
 * Created by randomBEAR on 25/10/2017.
 * =================================
 */
public class MessageEvent {

    public final MatchDetails[] message;

    public MessageEvent(MatchDetails[] message) {
        this.message = message;
    }
}