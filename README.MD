
# GraphQL Automation using Rest-Assured

Idea behind this project is to showcase a "JAVA" way of automating GraphQL calls. Currently, only Query types are supported, but mutations will be added soon :).

The project uses sample graphql for "Rick and Morty" series (locations, characters, episodes, etc). We use this to write some basic tests (e.g., fetch name and air date of first 5 episodes, etc).

## How to Automate GraphQL Query

Lets cut to the chase directly :). We can discuss details of design and architecture later on. Lets just get our hands dirty on how to fire a query and verify response.

First thing we need to do is create an object of type "Query"

```
Query query = new Query(arg1, arg2);
```
What are these arg1 and arg2 ? Good Question. Let's explore method declaration.
```
    /**
     *
     * @param path relative file path to json file containing query for this object
     * @param queryId unique id of query in json file specified by above path
     * @throws IOException if no file found in above path
     * @throws NoSuchQueryException if no query found in json file specified by above path
     */
    public Query(QueryFilePath path, String queryId) throws IOException, NoSuchQueryException {
        super(path, queryId);
    }
```
Quite Self-Explainatory right :p? No? cool. Lets dive deep.
- arg1 is an enum of type QueryFilePath. It just stores the relative path of the file, which contains the actual query.
- arg2 is a unique id of the Query. Unique, only as long as query is in scope of the current file. Same id can be used without any issue in another Query file.
  
That's it! We are now good to go and hit our query and see the response. However, this is with an assumption that no variables are used in the query. Details of using variables are discussed ahead.
  For now, lets just see how the query id and query are actually stored in query file "QUERY_EPISODE_DATA.json".
```
{
  "1_name_date_first_5_episodes": "query($episodeIds:[ID!]!){\n  episodesByIds(ids:$episodeIds){\n    id name air_date\n  }\n}"
}
```
Ignore the variable ($episodeIds:[ID!]!) for now.
One more thing to note is, if there is an issue with the queryId, i.e., our code was unable to find it in the specified file, the "NoSuchQueryException" will be thrown.

Alright now, lets actually fire the query. Its actually Quite easy. We have 2 choices :

#### 1] Capture response as "ValidatableResponse" type:
```
ValidatableResponse response = query.hitAndGetValidatableResponse();
```
Then we can put assertions like :
```
 Assert.assertEquals(response.extract().path("data.episodesByIds[0].name"), expectedEpisodeName, "verify episode name");
```
However, as can be seen, issue with this approach is the hardcoded json path, which can be a issue prone and cause maintainance nighmares.

#### 2] Use Pojo approach (what I call the "Java-way")
```
GraphQLResponse response = query.hitAndGetPojoResponse();
Assert.assertEquals(response.getData().getEpisodesByIds().get(0).getName(), expectedEpisodeName, "verify episode name");
```
As can be seen, above syntaxt is a bit more readable, and a lot less error prone. Further, you can power your response object with Java stuff like impleneting Comparators (Sorting sorted!), Cloneable (useful for data reuse and prevents multiple api calls to fetch same data). Sky is the limit, literally! But that's discussion for another scope.

You might be wondering, how does GraphQLResponse object have knowledge of response attributes? Well it doesn't automatically have it, some configuration (one-time) needs to be done for same. It will be covered in a later topic.

Lets discuss the variables now. Our Query class supports it quite well.
```
query.addVariable("episodeIds", episodeIds);
```
Done ! Further, varibles can be chained together, and moreover, can be attached to the constructor call itself like below :
```
Query query = new Query(QueryFilePath.QUERY_EPISODE_DATA, "1_name_date_first_5_episodes")
                .addVariable("episodeIds", episodeIds)
                .addVariable("unusedKey", "unusedValue");
```

This much knowledge is enough to hit any query.
In future commits, below topics will be covered :
- How to configure  response pojo that we talked about earlier
- Design details of QueryBuilder class, which actually does all the hard work
- Design of rest tool, which the above mentioned QueryBuilder class extends
- and many more (mutations, subscriptions etc)

Cheers !