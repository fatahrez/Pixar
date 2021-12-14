package com.fatah.pixar.utils

import com.fatah.pixar.feature_search.domain.model.Hit

class TestDataGenerator {
    companion object {
        fun generateHits(): List<Hit> {
            val hit1 = Hit(
                2839,
                160,
                148246,
                887443,
                3005,
                2611531,
                3867,
                "https://pixabay.com/get/g5995b8a3eefc22fe9439bd73399866b000c150064dd04843df07ba62ec6861fdddecfd077ac96865d3cf13f8beb377e39be395307d44c3443ef8a8ee586ae345_1280.jpg",
                997,
                "https://pixabay.com/photos/flower-life-yellow-flower-crack-887443/",
                116,
                "https://cdn.pixabay.com/photo/2015/08/13/20/06/flower-887443_150.jpg",
                150,
                "flower, life, yellow flower",
                "photo",
                "klimkin",
                "https://cdn.pixabay.com/user/2017/07/18/13-46-18-393_250x250.jpg",
                1298145,
                251085,
                497,
                "https://pixabay.com/get/gcbd2b02abc26c0d28c63d76e6096362e3fa87859948acfeab6ab37219eb9482aca892ccaf3c33def09da79f7bac93ae6_640.jpg",
                640
            )
            val hit2 = Hit(
                2839,
                160,
                148246,
                3063284,
                3005,
                2611531,
                3867,
                "https://pixabay.com/get/g5861b1e50324ca6456796ff288b85289051cf10d0e7e4b7d7098b0395d9e61162aed8a3accd04eeb714f68556cf059139dbadc26f84914395ac72273c9be0fbe_1280.jpg",                997,
                "https://pixabay.com/photos/rose-flower-petal-floral-noble-3063284/",
                116,
                "https://cdn.pixabay.com/photo/2018/01/05/16/24/rose-3063284_150.jpg",
                150,
                "rose, flower, petal",
                "photo",
                "anncapictures",
                "https://cdn.pixabay.com/user/2015/11/27/06-58-54-609_250x250.jpg",
                1298145,
                251085,
                497,
                "https://pixabay.com/get/g30e4a1938fc1958a9836dc33bb4d8abb34e453412954c11a57114f40e1a50054233bb03da693fa42b17f28f245149ed0b0d3d08f902a5f12c08f99762875f244_640.jpg",
                640
            )

            return listOf(hit1, hit2)
        }
    }
}