package com.ksv.hw17recyclerview.util

class LinkCorrector {
    companion object {
        fun change(url: String): String {
//        "http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG")
//        "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG")
            if (url.startsWith(BAD_URL)) {
                return RIGHT_URL + url.substring(BAD_URL.length)
            }
            return url
        }

        private const val BAD_URL = "http://mars.jpl.nasa.gov"
        private const val RIGHT_URL = "https://mars.nasa.gov"

    }
}