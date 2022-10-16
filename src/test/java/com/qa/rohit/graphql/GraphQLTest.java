package com.qa.rohit.graphql;

import com.qa.rohit.enums.QueryFilePath;
import com.qa.rohit.exceptions.NoSuchQueryException;
import com.qa.rohit.grapql.Query;
import com.qa.rohit.grapql.pojomodels.GraphQLResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GraphQLTest {

    //this data could be fetched from excel too for truly data driven test
    @DataProvider
    private Object[][] episodeNameAndAirDateDataOfFirst_5_Episodes(){
        return new Object[][]{
                {List.of(1), "Pilot", "December 2, 2013"},
                {List.of(2), "Lawnmower Dog", "December 9, 2013"},
                {List.of(3), "Anatomy Park", "December 16, 2013"},
                {List.of(4), "M. Night Shaym-Aliens!", "January 13, 2014"},
                {List.of(5), "Meeseeks and Destroy", "January 20, 2014"},
        };
    }

    @Test(dataProvider = "episodeNameAndAirDateDataOfFirst_5_Episodes")
    public void testEpisodeNameAndAirDatesOf(List<Integer> episodeIds, String expectedEpisodeName, String expectedEpisodeAirDate) throws IOException, NoSuchQueryException {
        Query query = new Query(QueryFilePath.QUERY_EPISODE_DATA, "1_name_date_first_5_episodes")
                .addVariable("episodeIds", episodeIds)
                .addVariable("unusedKey", "unusedValue");   //unused variable for demo purpose
        GraphQLResponse response = query.hitAndGetPojoResponse();

        //not null checks
        Assert.assertNotNull(response.getData().getEpisodesByIds(), "verify episode data is not null");
        Assert.assertEquals(response.getData().getEpisodesByIds().size(), 1, "verify episode data size");
        Assert.assertNotNull(response.getData().getEpisodesByIds().get(0).getId(), "verify episode id is not null");
        Assert.assertNotNull(response.getData().getEpisodesByIds().get(0).getName(), "verify episode name is not null");
        Assert.assertNotNull(response.getData().getEpisodesByIds().get(0).getAirDate(), "verify episode air date is not null");

        //value assetions
        Assert.assertEquals(response.getData().getEpisodesByIds().get(0).getId(), episodeIds.get(0), "verify episode id");
        Assert.assertEquals(response.getData().getEpisodesByIds().get(0).getName(), expectedEpisodeName, "verify episode name");
        Assert.assertEquals(response.getData().getEpisodesByIds().get(0).getAirDate(), expectedEpisodeAirDate, "verify episode air date");
    }

}
