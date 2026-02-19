import java.io.Console;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class taigaAPI {
    private static String refreshToken = "";
    private static String authToken = "";
    private static HttpClient httpClient = HttpClient.newHttpClient();

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();

        System.out.print("Enter username or email: ");
        String username = scanner.nextLine();
        System.out.println();

        System.out.print("Enter your password: ");
        String password;
        if (console != null) {
            char[] passwordChars = console.readPassword();
            password = new String(passwordChars);
        } else {
            password = scanner.nextLine();
        }
        System.out.println();

        JSONObject login = new JSONObject();
        login.put("password", password);
        login.put("type", "normal");
        login.put("username", username);

        HttpRequest loginRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.taiga.io/api/v1/auth"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(login.toString()))
                .build();

        HttpResponse<String> loginResponse = httpClient.send(loginRequest, HttpResponse.BodyHandlers.ofString());

        if (loginResponse.statusCode() != 200) {
            System.out.println("Error code " + loginResponse.statusCode() + " - Login failed. Terminating script!");
        } else {
            JSONObject auth = new JSONObject(loginResponse.body());
            refreshToken = auth.getString("refresh");
            authToken = auth.getString("auth_token");
            String authHeader = "Bearer " + authToken;

            System.out.println("Login successful!");
            System.out.println();
            System.out.println("Enter the project slug:");

            String slug = scanner.nextLine();

            HttpRequest projectRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.taiga.io/api/v1/projects/by_slug?slug=" + slug))
                    .header("Authorization", authHeader)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(projectRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Error code " + response.statusCode() + " - Project not found. Terminating script!");
            } else {
                JSONObject project = new JSONObject(response.body());
                System.out.println();
                System.out.println("This project has " + project.getJSONArray("members").length() + " members. The members are: ");
                System.out.println("----------------------------------------------------------");

                Map<String, Integer> members = new LinkedHashMap<>();
                for (int i = 0; i < project.getJSONArray("members").length(); i++) {
                    JSONObject member = project.getJSONArray("members").getJSONObject(i);
                    members.put(member.getString("full_name_display"), 0);
                    System.out.println(member.getString("full_name_display") + " : " + member.getString("role_name"));
                }

                System.out.println();

                HttpRequest sprintsRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.taiga.io/api/v1/milestones?project=" + project.getInt("id")))
                        .header("Authorization", authHeader)
                        .GET()
                        .build();

                HttpResponse<String> sprintsResponse = httpClient.send(sprintsRequest, HttpResponse.BodyHandlers.ofString());
                JSONArray sprints = new JSONArray(sprintsResponse.body());

                System.out.println("This project has " + sprints.length() + " sprints:");
                System.out.println("----------------------------------------------------------");

                for (int i = 0; i < sprints.length(); i++) {
                    JSONObject sprint = sprints.getJSONObject(i);
                    System.out.println(i + " : " + sprint.getString("name"));
                    System.out.println("Start date: " + sprint.getString("estimated_start"));
                    System.out.println("End date: " + sprint.getString("estimated_finish"));
                    System.out.println("Total points: " + sprint.get("total_points"));
                    System.out.println("Closed points: " + sprint.get("closed_points"));
                    System.out.println();
                }

                System.out.println("Choose a sprint (type in the number next to the sprint name): ");
                String sprintNum = scanner.nextLine();
                System.out.println();
                System.out.println("You chose sprint " + sprints.getJSONObject(Integer.parseInt(sprintNum)).getString("name"));

                HttpRequest milestoneRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.taiga.io/api/v1/milestones/" + sprints.getJSONObject(Integer.parseInt(sprintNum)).getInt("id")))
                        .header("Authorization", authHeader)
                        .GET()
                        .build();

                HttpResponse<String> milestoneResponse = httpClient.send(milestoneRequest, HttpResponse.BodyHandlers.ofString());
                JSONObject milestone = new JSONObject(milestoneResponse.body());

                List<Integer> userStoryIDs = new ArrayList<>();
                System.out.println("The user stories are: ");
                System.out.println("----------------------------------------------------------");

                for (int i = 0; i < milestone.getJSONArray("user_stories").length(); i++) {
                    JSONObject userStory = milestone.getJSONArray("user_stories").getJSONObject(i);
                    System.out.println("Name: " + userStory.getString("subject"));
                    System.out.println("Finished: " + !userStory.isNull("finish_date"));

                    String createdDate = userStory.getString("created_date");
                    System.out.println("Created: " + createdDate.substring(0, 10));

                    String modifiedDate = userStory.getString("modified_date");
                    System.out.println("Moved to sprint: " + modifiedDate.substring(0, 10));

                    userStoryIDs.add(userStory.getInt("id"));
                    System.out.println();
                }

                System.out.println("Tasks: ");
                System.out.println("----------------------------------------------------------");

                HttpRequest tasksRequest = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.taiga.io/api/v1/tasks?project=" + project.getInt("id") + "&milestone=" + sprints.getJSONObject(Integer.parseInt(sprintNum)).getInt("id")))
                        .header("Authorization", authHeader)
                        .GET()
                        .build();

                HttpResponse<String> tasksResponse = httpClient.send(tasksRequest, HttpResponse.BodyHandlers.ofString());
                JSONArray tasks = new JSONArray(tasksResponse.body());

                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject task = tasks.getJSONObject(i);
                    System.out.println("Name: " + task.getString("subject"));
                    if (task.isNull("assigned_to_extra_info")) {
                        System.out.println("Assigned to: None");
                    } else {
                        String name = task.getJSONObject("assigned_to_extra_info").getString("full_name_display");
                        System.out.println("Assigned to: " + name);
                        members.put(name, members.get(name) + 1);
                    }
                    System.out.println();
                }

                System.out.println("Total: ");
                System.out.println("----------------------------------------------------------");
                for (String key : members.keySet()) {
                    System.out.println("Name: " + key);
                    System.out.println("Assigned tasks: " + members.get(key));
                }

                System.out.println("----------------------------------------------------------");

                for (int userStoryID : userStoryIDs) {
                    HttpRequest userstoryRequest = HttpRequest.newBuilder()
                            .uri(URI.create("https://api.taiga.io/api/v1/userstories/" + userStoryID))
                            .header("Authorization", authHeader)
                            .GET()
                            .build();

                    HttpResponse<String> userstoryResponse = httpClient.send(userstoryRequest, HttpResponse.BodyHandlers.ofString());
                    JSONObject userstory = new JSONObject(userstoryResponse.body());

                    System.out.println("User Story Title - " + (!userstory.getString("subject").isEmpty() ? userstory.getString("subject") : "No Title Found"));
                    System.out.println("User Story description - " + (!userstory.getString("description").isEmpty() ? userstory.getString("description") : "No Description Found"));
                    System.out.println("User Story tags - " + (userstory.getJSONArray("tags").length() != 0 ? userstory.getJSONArray("tags") : "No Tag(s) Found"));

                    HttpRequest historyRequest = HttpRequest.newBuilder()
                            .uri(URI.create("https://api.taiga.io/api/v1/history/userstory/" + userStoryID))
                            .header("Authorization", authHeader)
                            .GET()
                            .build();

                    HttpResponse<String> historyResponse = httpClient.send(historyRequest, HttpResponse.BodyHandlers.ofString());
                    JSONArray userstoryhistory = new JSONArray(historyResponse.body());

                    List<String> all_w = new ArrayList<>();
                    for (int i = 0; i < userstoryhistory.length(); i++) {
                        JSONObject item = userstoryhistory.getJSONObject(i);
                        if (item.has("comment")) {
                            if (!item.getString("comment").isEmpty()) {
                                all_w.add(item.getString("comment"));
                            }
                        }
                    }
                    System.out.println("Here are the comments - " + (all_w.size() != 0 ? all_w : "No Comment(s) Found"));

                    System.out.println();
                }
            }
        }

        scanner.close();
    }
}
