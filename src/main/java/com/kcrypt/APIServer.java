package com.kcrypt;

import com.kcrypt.models.UserAuthModel;
import com.kcrypt.service.AuthService;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import static spark.Spark.*;




public class APIServer {
    int port;
    String address;
    final AuthService authService = new AuthService();
    final Gson  gson = new Gson();

    public APIServer(String address, int port) {
        this.address = address;
        this.port = port;

    }

    public APIServer() {
        this(":", 3000);
    }




    public void run() {
//        get("", this::hello);
        get("/signin", this::signIn);
        get("/signup", this::signUp);
    }
//
//    String hello(Request req, Response resp) {
//        return "Hello World";
//    }

//
    private String signIn(Request req, Response resp) {
        return "";
    }
//
    private String signUp(Request req, Response resp) {
        UserAuthModel userAuthModel = gson.fromJson(req.body(), UserAuthModel.class);
        authService.createUser(userAuthModel.getEmail(), userAuthModel.getPassword());
        return "";
    }

//    private String savePassword() {
//        return "";
//    }
//
//    private String getPassword() {
//        return "";
//    }
}
