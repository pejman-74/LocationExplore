package com.locationexplorer

import com.google.common.truth.Truth.assertThat
import com.locationexplorer.data.model.response.ExploreResponse
import com.squareup.moshi.Moshi
import org.junit.Test

class MoshiTest {

    private val moshi: Moshi = Moshi.Builder().build()

    /**
     *Check moshi can convert sample api response to data classes
     * */
    @Test
    fun t1() {
        val exploreResponse = moshi.adapter(ExploreResponse::class.java).fromJson(rawSampleResponse)
        assertThat(exploreResponse?.response?.groups?.first()?.items?.size).isEqualTo(2)
        println(exploreResponse?.response?.groups?.first()?.items?.first()?.venue?.location)
        assertThat(exploreResponse?.response?.groups?.first()?.items?.first()?.venue?.location?.address).isNotNull()
        assertThat(exploreResponse?.response?.groups?.first()?.items?.last()?.venue?.location?.address).isNotNull()
    }

    private val rawSampleResponse = "{\n" +
            "    \"meta\": {\n" +
            "        \"code\": 200,\n" +
            "        \"requestId\": \"6102527a467eec2d57969bd5\"\n" +
            "    },\n" +
            "    \"response\": {\n" +
            "        \"warning\": {\n" +
            "            \"text\": \"There aren't a lot of results near you. Try something more general, reset your filters, or expand the search area.\"\n" +
            "        },\n" +
            "        \"suggestedRadius\": 2431,\n" +
            "        \"headerLocation\": \"Minṭaqa 9\",\n" +
            "        \"headerFullLocation\": \"Minṭaqa 9, Tehrān\",\n" +
            "        \"headerLocationGranularity\": \"neighborhood\",\n" +
            "        \"totalResults\": 74,\n" +
            "        \"suggestedBounds\": {\n" +
            "            \"ne\": {\n" +
            "                \"lat\": 35.71108850880026,\n" +
            "                \"lng\": 51.35502341427818\n" +
            "            },\n" +
            "            \"sw\": {\n" +
            "                \"lat\": 35.70735461217549,\n" +
            "                \"lng\": 51.34507325914153\n" +
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
            "                            \"id\": \"516f1f6a19a94eb3b5dc5e2f\",\n" +
            "                            \"name\": \"Tarasht Dizi | دیزی سرای طرشت - ناصر قهرمانی (دیزی سرای طرشت - ناصر قهرمانی)\",\n" +
            "                            \"contact\": {},\n" +
            "                            \"location\": {\n" +
            "                                \"address\": \"شمال میدان آزادی، خیابان شهید صالحی، چهار راه طرشت شمالی\",\n" +
            "                                \"crossStreet\": \"انتهای کوچه اکبری٬ جنب حسینیه بزرگ\",\n" +
            "                                \"lat\": 35.707524334749344,\n" +
            "                                \"lng\": 51.34552553892047,\n" +
            "                                \"labeledLatLngs\": [\n" +
            "                                    {\n" +
            "                                        \"label\": \"display\",\n" +
            "                                        \"lat\": 35.707524334749344,\n" +
            "                                        \"lng\": 51.34552553892047\n" +
            "                                    }\n" +
            "                                ],\n" +
            "                                \"distance\": 1061,\n" +
            "                                \"cc\": \"IR\",\n" +
            "                                \"city\": \"تهران\",\n" +
            "                                \"state\": \"تهران\",\n" +
            "                                \"country\": \"ایران\",\n" +
            "                                \"formattedAddress\": [\n" +
            "                                    \"شمال میدان آزادی، خیابان شهید صالحی، چهار راه طرشت شمالی (انتهای کوچه اکبری٬ جنب حسینیه بزرگ)\",\n" +
            "                                    \"تهران, تهران\",\n" +
            "                                    \"ایران\"\n" +
            "                                ]\n" +
            "                            },\n" +
            "                            \"categories\": [\n" +
            "                                {\n" +
            "                                    \"id\": \"58daa1558bbb0b01f18ec1c0\",\n" +
            "                                    \"name\": \"Dizi Place\",\n" +
            "                                    \"pluralName\": \"Dizi Places\",\n" +
            "                                    \"shortName\": \"Dizi\",\n" +
            "                                    \"icon\": {\n" +
            "                                        \"prefix\": \"https://ss3.4sqi.net/img/categories_v2/food/middleeastern_\",\n" +
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
            "                        \"referralId\": \"e-1-516f1f6a19a94eb3b5dc5e2f-0\"\n" +
            "                    },\n" +
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
            "                            \"id\": \"54071b1f498e852b08679295\",\n" +
            "                            \"name\": \"Sarv Bakery & Pastry Shop | نان و شيرينی سرو\",\n" +
            "                            \"contact\": {},\n" +
            "                            \"location\": {\n" +
            "                                \"address\": \"South Homayounshahr St.\",\n" +
            "                                \"lat\": 35.71091878622641,\n" +
            "                                \"lng\": 51.35457113449924,\n" +
            "                                \"labeledLatLngs\": [\n" +
            "                                    {\n" +
            "                                        \"label\": \"display\",\n" +
            "                                        \"lat\": 35.71091878622641,\n" +
            "                                        \"lng\": 51.35457113449924\n" +
            "                                    }\n" +
            "                                ],\n" +
            "                                \"distance\": 1910,\n" +
            "                                \"cc\": \"IR\",\n" +
            "                                \"country\": \"ایران\",\n" +
            "                                \"formattedAddress\": [\n" +
            "                                    \"South Homayounshahr St.\",\n" +
            "                                    \"ایران\"\n" +
            "                                ]\n" +
            "                            },\n" +
            "                            \"categories\": [\n" +
            "                                {\n" +
            "                                    \"id\": \"5744ccdfe4b0c0459246b4e2\",\n" +
            "                                    \"name\": \"Pastry Shop\",\n" +
            "                                    \"pluralName\": \"Pastry Shops\",\n" +
            "                                    \"shortName\": \"Pastry\",\n" +
            "                                    \"icon\": {\n" +
            "                                        \"prefix\": \"https://ss3.4sqi.net/img/categories_v2/food/dessert_\",\n" +
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
            "                        \"referralId\": \"e-1-54071b1f498e852b08679295-1\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}"
}