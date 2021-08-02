package com

import com.locationexplorer.data.model.database.Location
import com.locationexplorer.data.model.database.Venue
import com.locationexplorer.data.model.share.SimpleLocation

val location =
    Location(
        "1", "", "IR", "Tehran", "ایران",
        "", 2312, listOf(
            "Tehran, Tehran Province",
            "ایران"
        ), 35.709976, 51.315655,
        "", "", "Tehran"
    )
val venue = Venue("1", "coffeeShop")

val simpleLocation = SimpleLocation(0.0, 0.0)

const val totalResult = 78
const val firstPageVenueId="5211b2a911d260884f42387a"
const val firstPageJson = "{\n" +
        "    \"meta\": {\n" +
        "        \"code\": 200,\n" +
        "        \"requestId\": \"61050b9d0db274548d5545fb\"\n" +
        "    },\n" +
        "    \"response\": {\n" +
        "        \"warning\": {\n" +
        "            \"text\": \"There aren't a lot of results near you. Try something more general, reset your filters, or expand the search area.\"\n" +
        "        },\n" +
        "        \"suggestedRadius\": 2431,\n" +
        "        \"headerLocation\": \"Minṭaqa 9\",\n" +
        "        \"headerFullLocation\": \"Minṭaqa 9, Tehrān\",\n" +
        "        \"headerLocationGranularity\": \"neighborhood\",\n" +
        "        \"totalResults\": 78,\n" +
        "        \"suggestedBounds\": {\n" +
        "            \"ne\": {\n" +
        "                \"lat\": 35.70751882989273,\n" +
        "                \"lng\": 51.34681747781228\n" +
        "            },\n" +
        "            \"sw\": {\n" +
        "                \"lat\": 35.70481917010728,\n" +
        "                \"lng\": 51.353406522187726\n" +
        "            }\n" +
        "        },\n" +
        "        \"groups\": [\n" +
        "            {\n" +
        "                \"type\": \"Recommended Places\",\n" +
        "                \"name\": \"recommended\",\n" +
        "                \"items\": [\n" +
        "                    {\n" +
        "                        \"reasons\": {\n" +
        "                            \"count\": 0,\n" +
        "                            \"items\": [\n" +
        "                                {\n" +
        "                                    \"summary\": \"This spot is popular\",\n" +
        "                                    \"type\": \"general\",\n" +
        "                                    \"reasonName\": \"globalInteractionReason\"\n" +
        "                                }\n" +
        "                            ]\n" +
        "                        },\n" +
        "                        \"venue\": {\n" +
        "                            \"id\": \"5211b2a911d260884f42387a\",\n" +
        "                            \"name\": \"Sharif University Swimming Pool | استخر دانشگاه صنعتی شریف (استخر دانشگاه صنعتی شریف)\",\n" +
        "                            \"contact\": {},\n" +
        "                            \"location\": {\n" +
        "                                \"address\": \"Sharif University of Technology\",\n" +
        "                                \"lat\": 35.706169,\n" +
        "                                \"lng\": 51.350112,\n" +
        "                                \"labeledLatLngs\": [\n" +
        "                                    {\n" +
        "                                        \"label\": \"display\",\n" +
        "                                        \"lat\": 35.706169,\n" +
        "                                        \"lng\": 51.350112\n" +
        "                                    }\n" +
        "                                ],\n" +
        "                                \"distance\": 1274,\n" +
        "                                \"cc\": \"IR\",\n" +
        "                                \"city\": \"تهران\",\n" +
        "                                \"state\": \"تهران\",\n" +
        "                                \"country\": \"ایران\",\n" +
        "                                \"formattedAddress\": [\n" +
        "                                    \"Sharif University of Technology\",\n" +
        "                                    \"تهران, تهران\",\n" +
        "                                    \"ایران\"\n" +
        "                                ]\n" +
        "                            },\n" +
        "                            \"categories\": [\n" +
        "                                {\n" +
        "                                    \"id\": \"4bf58dd8d48988d15e941735\",\n" +
        "                                    \"name\": \"Pool\",\n" +
        "                                    \"pluralName\": \"Pools\",\n" +
        "                                    \"shortName\": \"Pool\",\n" +
        "                                    \"icon\": {\n" +
        "                                        \"prefix\": \"https://ss3.4sqi.net/img/categories_v2/parks_outdoors/pool_\",\n" +
        "                                        \"suffix\": \".png\"\n" +
        "                                    },\n" +
        "                                    \"primary\": true\n" +
        "                                }\n" +
        "                            ],\n" +
        "                            \"stats\": {\n" +
        "                                \"tipCount\": 0,\n" +
        "                                \"usersCount\": 0,\n" +
        "                                \"checkinsCount\": 0,\n" +
        "                                \"visitsCount\": 0\n" +
        "                            },\n" +
        "                            \"beenHere\": {\n" +
        "                                \"count\": 0,\n" +
        "                                \"lastCheckinExpiredAt\": 0,\n" +
        "                                \"marked\": false,\n" +
        "                                \"unconfirmedCount\": 0\n" +
        "                            },\n" +
        "                            \"hereNow\": {\n" +
        "                                \"count\": 0,\n" +
        "                                \"summary\": \"Nobody here\",\n" +
        "                                \"groups\": []\n" +
        "                            }\n" +
        "                        },\n" +
        "                        \"referralId\": \"e-1-5211b2a911d260884f42387a-0\"\n" +
        "                    }\n" +
        "                ]\n" +
        "            }\n" +
        "        ]\n" +
        "    }\n" +
        "}"

const val secondPageJson = "{\n" +
        "    \"meta\": {\n" +
        "        \"code\": 200,\n" +
        "        \"requestId\": \"6107830ac098077d9cf7da8b\"\n" +
        "    },\n" +
        "    \"response\": {\n" +
        "        \"warning\": {\n" +
        "            \"text\": \"There aren't a lot of results near you. Try something more general, reset your filters, or expand the search area.\"\n" +
        "        },\n" +
        "        \"suggestedRadius\": 2431,\n" +
        "        \"headerLocation\": \"Minṭaqa 9\",\n" +
        "        \"headerFullLocation\": \"Minṭaqa 9, Tehrān\",\n" +
        "        \"headerLocationGranularity\": \"neighborhood\",\n" +
        "        \"totalResults\": 78,\n" +
        "        \"suggestedBounds\": {\n" +
        "            \"ne\": {\n" +
        "                \"lat\": 35.701018705652224,\n" +
        "                \"lng\": 51.33470502207672\n" +
        "            },\n" +
        "            \"sw\": {\n" +
        "                \"lat\": 35.698319045866775,\n" +
        "                \"lng\": 51.341200205584414\n" +
        "            }\n" +
        "        },\n" +
        "        \"groups\": [\n" +
        "            {\n" +
        "                \"type\": \"Recommended Places\",\n" +
        "                \"name\": \"recommended\",\n" +
        "                \"items\": [\n" +
        "                    {\n" +
        "                        \"reasons\": {\n" +
        "                            \"count\": 0,\n" +
        "                            \"items\": [\n" +
        "                                {\n" +
        "                                    \"summary\": \"This spot is popular\",\n" +
        "                                    \"type\": \"general\",\n" +
        "                                    \"reasonName\": \"globalInteractionReason\"\n" +
        "                                }\n" +
        "                            ]\n" +
        "                        },\n" +
        "                        \"venue\": {\n" +
        "                            \"id\": \"4e1bc7896284102ec19762b9\",\n" +
        "                            \"name\": \"Azadi Square | میدان آزادی (میدان آزادی)\",\n" +
        "                            \"contact\": {},\n" +
        "                            \"location\": {\n" +
        "                                \"address\": \"Azadi Sq., Azadi St.\",\n" +
        "                                \"lat\": 35.6996688757595,\n" +
        "                                \"lng\": 51.337952613830566,\n" +
        "                                \"labeledLatLngs\": [\n" +
        "                                    {\n" +
        "                                        \"label\": \"display\",\n" +
        "                                        \"lat\": 35.6996688757595,\n" +
        "                                        \"lng\": 51.337952613830566\n" +
        "                                    }\n" +
        "                                ],\n" +
        "                                \"distance\": 51,\n" +
        "                                \"cc\": \"IR\",\n" +
        "                                \"city\": \"تهران\",\n" +
        "                                \"state\": \"تهران\",\n" +
        "                                \"country\": \"ایران\",\n" +
        "                                \"formattedAddress\": [\n" +
        "                                    \"Azadi Sq., Azadi St.\",\n" +
        "                                    \"تهران, تهران\",\n" +
        "                                    \"ایران\"\n" +
        "                                ]\n" +
        "                            },\n" +
        "                            \"categories\": [\n" +
        "                                {\n" +
        "                                    \"id\": \"4bf58dd8d48988d164941735\",\n" +
        "                                    \"name\": \"Plaza\",\n" +
        "                                    \"pluralName\": \"Plazas\",\n" +
        "                                    \"shortName\": \"Plaza\",\n" +
        "                                    \"icon\": {\n" +
        "                                        \"prefix\": \"https://ss3.4sqi.net/img/categories_v2/parks_outdoors/plaza_\",\n" +
        "                                        \"suffix\": \".png\"\n" +
        "                                    },\n" +
        "                                    \"primary\": true\n" +
        "                                }\n" +
        "                            ],\n" +
        "                            \"stats\": {\n" +
        "                                \"tipCount\": 0,\n" +
        "                                \"usersCount\": 0,\n" +
        "                                \"checkinsCount\": 0,\n" +
        "                                \"visitsCount\": 0\n" +
        "                            },\n" +
        "                            \"beenHere\": {\n" +
        "                                \"count\": 0,\n" +
        "                                \"lastCheckinExpiredAt\": 0,\n" +
        "                                \"marked\": false,\n" +
        "                                \"unconfirmedCount\": 0\n" +
        "                            },\n" +
        "                            \"hereNow\": {\n" +
        "                                \"count\": 0,\n" +
        "                                \"summary\": \"Nobody here\",\n" +
        "                                \"groups\": []\n" +
        "                            }\n" +
        "                        },\n" +
        "                        \"referralId\": \"e-1-4e1bc7896284102ec19762b9-0\"\n" +
        "                    }\n" +
        "                ]\n" +
        "            }\n" +
        "        ]\n" +
        "    }\n" +
        "}"