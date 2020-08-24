package decagon;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.AuthorDetails;
import model.CommentCount;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import model.SearchResult;

public class Author {

    public static void main(String[] args) {

        String authorDetailString = getAuthorDetails(2);

        List<String> nameLists = getUserNames(authorDetailString);
        System.out.println("The list of name using submission count  " + nameLists);
        String username = getUsernameWithHighestCommentCount(authorDetailString);
        System.out.println("The username with highest comment count  " + username);
        List<String> nameSorted = getUsernamesSortedByRecordDate(authorDetailString);
        System.out.println("The list of name sorted by record date  " + nameSorted);
    }

    public static String getAuthorDetails(int pageNumber) {
        String url = "https://jsonmock.hackerrank.com/api/article_users/search?page=%s";

        url = String.format(url, pageNumber);

        StringBuilder response = new StringBuilder();

        URL obj;
        try {
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }


    private static List<String> getUserNames(String response) {
        List<String> result = new ArrayList<>();
        try{

            SearchResult searchResult = new ObjectMapper().readValue(response, SearchResult.class);
            List<AuthorDetails> authorDetails = searchResult.getAuthorDetails();
            result = authorDetails.stream()
                    .sorted(Comparator.comparingLong(AuthorDetails::getSubmission_count).reversed())
                    .map(AuthorDetails::getUsername)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;


    }

    private static String getUsernameWithHighestCommentCount(String str){
        List<CommentCount> result = new ArrayList<>();
        String nameResult = null;
        try {
            // Parse JSON
            Object obj1 = new JSONParser().parse(str);
            JSONObject jo = (JSONObject) obj1;
            JSONArray data = (JSONArray) jo.get("data");

            Iterator<JSONObject> itr = data.iterator();

            while(itr.hasNext()) {
                Object item = new JSONParser().parse(itr.next().toString());
                JSONObject itemObj = (JSONObject) item;
                String username = (String) itemObj.get("username");
                Long commentCount = (Long) itemObj.get("comment_count");

                result.add(new CommentCount(username, commentCount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<CommentCount> name = result.stream().max(Comparator.comparing(CommentCount::getCount));

        if (name.isPresent()) {
            nameResult = name.get().getName();
        }

        return nameResult;
    }

    private static List<String> getUsernamesSortedByRecordDate(String response) {
        List<String> result = new ArrayList<>();
        try{

            SearchResult searchResult = new ObjectMapper().readValue(response, SearchResult.class);
            List<AuthorDetails> authorDetails = searchResult.getAuthorDetails();
                                result = authorDetails.stream()
                                        .sorted(Comparator.comparingLong(AuthorDetails::getCreated_at))
                                        .map(AuthorDetails::getUsername)
                                        .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
